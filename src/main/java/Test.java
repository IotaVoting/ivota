import jota.IotaAPI;
import jota.dto.response.GetTransactionsToApproveResponse;
import jota.dto.response.SendTransferResponse;
import jota.error.*;
import jota.model.Transfer;

import java.util.Collections;
import java.util.List;

public class Test {
    static IotaAPI iotaAPI;

    public static void main(String[] args) throws InvalidAddressException, InvalidSecurityLevelException, InvalidTransferException, InvalidTrytesException, NotEnoughBalanceException {
        System.out.println(SeedGenerator.generateSeed());

        iotaAPI = new IotaAPI.Builder()
                .protocol("http")
                .host("hvo-app.de")
                .port("14265")
                .build();

        String testSeed = "OWGZAUFGYFMSGZWEMBHS9JODSDQZYHKTVHZYFTASOSZVWVYJVSQJWMFHTQJIOB9RZ9CRRTL9BBVYSOCCS";

        for (String s : getAddresses(testSeed, 2)) {
            System.out.println(s);
        }

        System.out.println(iotaAPI.findTransactionsByAddresses("VUBPZZOBWKX9NPRES9VGAAXPREJEXANMDDNSEOCFMJCTUYLFQMLNCRIVAAZVRAGYPUZERRNNWCIYFTILWYLWQRGHR9"));
        System.out.println(iotaAPI.findTransactionsByAddresses("WOUWTHCYXOPFOTFSZKKIYFYTHYIETVYZ9RVGSA9QHLVQGIROZXXFSXYRBYFQNSHZCFNHJUWXJFBVCOGWWPBGIZRYI9"));

    }

    private static List<String> getAddresses(String seed, int n) throws InvalidAddressException, InvalidSecurityLevelException {
        return iotaAPI.getNewAddress(seed, 2, 0, true, n, false).getAddresses();
    }
}
