import java.math.BigInteger;

public class Bob {

    public static void main(String[] args) {
        BigInteger n = new BigInteger(args[0]);
        BigInteger e = new BigInteger(args[1]);
        System.out.println("Encoded message c: " + Bob(n, e));
    }

    public static BigInteger Bob(BigInteger n, BigInteger e) {
        String rawMessage = "HELLO THERE MR CAMPBELL!";
        System.out.println("Message being sent:" + rawMessage);
        String intStrMessage = "";
        for (int i = 0; i < rawMessage.length(); i++) {
            int add = rawMessage.charAt(i);
            intStrMessage += add;
        }
        BigInteger intMessage = new BigInteger(intStrMessage);
        BigInteger c = intMessage.modPow(e, n);
        return c;

    }

}
