import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SeedGenerator {
    private static final String SEED_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ9";
    private static SecureRandom secureRandom;

    static {
        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static String generateSeed() {
        StringBuilder sb = new StringBuilder(81);
        for (int i = 0; i < 81; i++) {
            sb.append(SEED_ALPHABET.charAt(secureRandom.nextInt(27)));
        }
        return sb.toString();
    }
}