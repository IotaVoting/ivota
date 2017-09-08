import jota.IotaAPI;

public class Test {
    public static void main(String[] args) {
        System.out.println(SeedGenerator.generateSeed());

        IotaAPI iotaAPI = new IotaAPI.Builder()
                .protocol("http")
                .host("hvo-app.de")
                .port("14265")
                .build();

        System.out.println(iotaAPI.getNodeInfo());
    }
}
