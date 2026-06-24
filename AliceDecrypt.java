
import java.math.BigInteger;

public class AliceDecrypt {
    public static void main(String[] args) {
        BigInteger d = new BigInteger(args[0]);
        BigInteger c = new BigInteger(args[1]);
        BigInteger n = new BigInteger(args[2]);

        System.out.println(decrypt(d, c, n));
    }

    public static String decrypt(BigInteger d, BigInteger c, BigInteger n) {
        String message = "";
        BigInteger IntMessage = c.modPow(d, n);
        System.out.println(IntMessage + " int message");
        // This turns the BigInteger into a string and takes every 2 numbers and matches
        // them to their corresponding letter in the alphabet (ASCII)
        String strMes = IntMessage.toString();
        for (int i = 0; i < strMes.length(); i += 2) {
            char add = strMes.charAt(i);
            char add2 = strMes.charAt(i + 1);
            String twoDigi = "" + add + add2;
            int twoDigiInt = Integer.parseInt(twoDigi);
            char corrLetter = (char) twoDigiInt;
            message += corrLetter;
        }
        return message;
    }
}
