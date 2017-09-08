import jota.IotaAPI;
import jota.dto.response.GetInclusionStateResponse;
import jota.dto.response.SendTransferResponse;
import jota.error.*;
import jota.model.Transfer;
import jota.utils.TrytesConverter;

import java.util.ArrayList;
import java.util.List;

public class IotaTest {
    private static final String SEED_SERVER = "OWGZAUFGYFMSGZWEMBHS9JODSDQZYHKTVHZYFTASOSZVWVYJVSQJWMFHTQJIOB9RZ9CRRTL9BBVYSOCCS"; //origin seed
    private static final String ADDRESS_SERVER = "WOUWTHCYXOPFOTFSZKKIYFYTHYIETVYZ9RVGSA9QHLVQGIROZXXFSXYRBYFQNSHZCFNHJUWXJFBVCOGWWPBGIZRYI9"; //origin address
    private static final String ADDRESS_PRIVATE = "HEY9DPVFKAVCYXEKJLMWONVCRZDQBETJGIGXAABFMYSATFJYLYBLEPTYWFWTPRYRIEZQCVFJXDSOBTBJBRSFWIBYED"; //target address

    private static final String PROTOCOL = "http";
    private static final String HOST = "hvo-app.de";
    private static final int PORT = 14265;

    private IotaAPI api;

    public static void main(String[] args) {
        IotaTest test = new IotaTest();
        test.start();
    }


    private void start() {
        api = new IotaAPI.Builder().protocol(PROTOCOL).host(HOST).port(((Integer) PORT).toString()).build();

// sending trans
        try {
            List<Transfer> transfers = new ArrayList<>();
            Transfer t = new Transfer(ADDRESS_SERVER, 0, TrytesConverter.toTrytes("some msg"), "999999999999999999999999999");
            transfers.add(t);
            SendTransferResponse resp = api.sendTransfer(SEED_SERVER, 2, 9, 15, transfers, null, ADDRESS_PRIVATE);
        } catch (InvalidTrytesException | InvalidSecurityLevelException | InvalidAddressException | InvalidTransferException | NotEnoughBalanceException e) {
            e.printStackTrace();
        }


// checking if trans is approved
        try {
            String transHash = "VSYXNDEEEIWCCUZJWIQRDCYMMITSBYIICZZQUXZNSGEVATULVGYYOOCYDDQBGBFVAEHBXFEBDRXF99999";
            GetInclusionStateResponse resp2 = api.getLatestInclusion(new String[] {transHash});

        } catch (NoNodeInfoException e) {
            e.printStackTrace();
        }
    }
}