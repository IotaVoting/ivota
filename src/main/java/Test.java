import jota.IotaAPI;
import jota.error.InvalidAddressException;
import jota.error.InvalidSecurityLevelException;

import java.util.List;

public class Test {
    static IotaAPI iotaAPI;

    public static void main(String[] args) throws InvalidAddressException, InvalidSecurityLevelException {
        System.out.println(SeedGenerator.generateSeed());

        iotaAPI = new IotaAPI.Builder()
                .protocol("http")
                .host("hvo-app.de")
                .port("14265")
                .build();

        System.out.println(getAdresses(SeedGenerator.generateSeed(), 5));
    }

    private static List<String> getAdresses(String seed, int n) throws InvalidAddressException, InvalidSecurityLevelException {
        return iotaAPI.getNewAddress(seed, 2, 0, true, n, false).getAddresses();
    }
}
