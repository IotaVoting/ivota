import jota.IotaAPI;
import jota.error.*;
import jota.model.Transfer;

import java.util.Arrays;
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
        Transfer transfer = new Transfer(testAddress, 0, "kden", "kdennor");
        iotaAPI.sendTransfer(testSeed, 2, 15, 15, Collections.singletonList(transfer), null, testAddress);
        System.out.println(transfer);
    }

    private static List<String> getAddresses(String seed, int n) throws InvalidAddressException, InvalidSecurityLevelException {
        return iotaAPI.getNewAddress(seed, 2, 0, true, n, false).getAddresses();
    }
}
