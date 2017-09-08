import jota.IotaAPI;
import jota.dto.response.FindTransactionResponse;
import jota.error.InvalidAddressException;
import jota.error.InvalidSecurityLevelException;
import jota.model.Bundle;
import jota.utils.TrytesConverter;

import java.util.Arrays;
import java.util.List;

public class CheckVotesTest {
    private static IotaAPI iotaAPI;

    public static void main(String[] args) throws Exception {
        iotaAPI = new IotaAPI.Builder()
                .protocol("http")
                .host("hvo-app.de")
                .port("14265")
                .build();

        String testSeed = "OWGZAUFGYFMSGZWEMBHS9JODSDQZYHKTVHZYFTASOSZVWVYJVSQJWMFHTQJIOB9RZ9CRRTL9BBVYSOCCS";
        List<String> addresses = getAddresses(testSeed, 20);
        System.out.println(addresses);

        addresses.forEach(address -> {
            FindTransactionResponse findTransactionResponse = iotaAPI.findTransactionsByAddresses(address);
            if (findTransactionResponse.getHashes().length > 0) {
                System.out.println("Found " + findTransactionResponse.getHashes().length + " transactions for address: " + address);
                try {
                    // TODO: 08/09/2017 just take first bundle ("transaction")
                    // TODO: 08/09/2017 read: https://domschiener.gitbooks.io/iota-guide/content/chapter1/bundles.html
                    // TODO: 08/09/2017 read: https://domschiener.gitbooks.io/iota-guide/content/chapter1/transactions-and-bundles.html
                    //seems like 1 bundle = 1 transfer
                    Bundle[] bundles = iotaAPI.bundlesFromAddresses(new String[]{address}, true);
                    Arrays.stream(bundles).forEach(bundle -> {
                        bundle.getTransactions().size(); //todo theory says 4 transactions / bundle .. seems like only 1
                        bundle.getTransactions().forEach(transaction -> {
                            String trytes = transaction.toTrytes();
                            String trytesMsg = trytes.substring(0, 2186); //should be 2187 in theory but resulting in out of bounds
                            System.out.println("   " + TrytesConverter.toString(trytesMsg));
                        });
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        System.exit(0);
    }

    private static List<String> getAddresses(String seed, int n) throws InvalidAddressException, InvalidSecurityLevelException {
        return iotaAPI.getNewAddress(seed, 2, 0, true, n, false).getAddresses();
    }
}
