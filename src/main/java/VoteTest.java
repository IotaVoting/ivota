import jota.IotaAPI;
import jota.dto.response.SendTransferResponse;
import jota.model.Transfer;
import jota.utils.TrytesConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoteTest {
    private static final String VOTE_ADDRESS = "VUBPZZOBWKX9NPRES9VGAAXPREJEXANMDDNSEOCFMJCTUYLFQMLNCRIVAAZVRAGYPUZERRNNWCIYFTILWYLWQRGHR9"; //transaction receive address

    private static IotaAPI iotaAPI;

    public static void main(String[] args) throws Exception {
        iotaAPI = new IotaAPI.Builder()
                .protocol("https")
                .host("node.tangle.works")
                .port("443")
                .build();

        //origin wallet doesn't matter for voting.. just create a new one
        String seed = SeedGenerator.generateSeed();
        String originAddress = iotaAPI.getNewAddress(seed, 2, 0, true, 1, false).getAddresses().get(0);
        System.out.println("generated new wallet, seed: " + seed);
        System.out.println("generated new address: " + originAddress);

        System.out.println("voting...");
        List<Transfer> transfers = new ArrayList<>();
        transfers.add(new Transfer(VOTE_ADDRESS, 0, TrytesConverter.toTrytes("Vote#1"), "IVOTA9999999999999999999999"));
        SendTransferResponse resp = iotaAPI.sendTransfer(seed, 2, 9, 15, transfers, null, VOTE_ADDRESS);
        System.out.println("success: " + Arrays.toString(resp.getSuccessfully()));
    }
}