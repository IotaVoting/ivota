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
        String testAddress = getAddresses(testSeed, 1).get(0);
        System.out.println(testAddress);
        Transfer transfer = new Transfer(testAddress, 0, "KDEN", "IOTALKMESSAGE99999999999999");
        List<String> preparedTransfers = iotaAPI.prepareTransfers(testSeed, 2, Collections.singletonList(transfer), "", null);
        System.out.println(preparedTransfers);
        //GetTransactionsToApproveResponse getTransactionsToApproveResponse = iotaAPI.getTransactionsToApprove(15);
        //System.out.println(getTransactionsToApproveResponse);
        SendTransferResponse sendTransferResponse = iotaAPI.sendTransfer(testSeed, 2, 15, 15, Collections.singletonList(transfer), null, testAddress);
        System.out.println(sendTransferResponse);
        //iotaAPI.broadcastTransactions(preparedTransfers.get(0));
    }

    private static List<String> getAddresses(String seed, int n) throws InvalidAddressException, InvalidSecurityLevelException {
        return iotaAPI.getNewAddress(seed, 2, 0, true, n, false).getAddresses();
    }
}
