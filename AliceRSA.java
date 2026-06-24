import java.math.BigInteger;
import java.util.*;

public class AliceRSA {

    public static void main(String[] args) {
        System.out.println(Alice());
    }

    // miller rabin test to find big prime
    public static BigInteger findBigInt() {
        int bitLength = 1024;
        BigInteger n = new BigInteger(bitLength, new Random()).setBit(bitLength - 1);
        List<Integer> firstPrimes = List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181,
                191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307,
                311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433,
                439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541);
        boolean primescheck = false;
        while (primescheck == false) {
            n = new BigInteger(bitLength, new Random()).setBit(bitLength - 1);
            int count = 0;
            for (int i = 0; i < firstPrimes.size(); i++) {
                if ((n.mod(BigInteger.valueOf(firstPrimes.get(i)))).equals(BigInteger.valueOf(0))) {
                    break;
                }
                count++;
            }
            if (count == 100) {
                primescheck = true;
            }
        }
        BigInteger min = BigInteger.valueOf(2);
        Random random = new Random();
        int randLen = bitLength;
        BigInteger diff = n.subtract(min);
        BigInteger a = new BigInteger(randLen, random);
        if (a.compareTo(min) < 0) {
            a = a.add(min);
        }
        if (a.compareTo(diff) >= 0) {
            a = a.mod(diff).add(min);
        }
        int s = 0;
        int base = 2;
        BigInteger d = BigInteger.valueOf(0);
        while (n.subtract(
                (n.subtract(BigInteger.valueOf(1)).divide(BigInteger.valueOf(base))).mod(BigInteger.valueOf(2)))
                .equals(BigInteger.valueOf(0))) {
            s += 1;
            base = (int) Math.pow(base, s);
        }
        d = n.divide(BigInteger.valueOf(base));
        for (int i = 0; i < s - 1; i++) {
            if (a.modPow(d, n).equals(BigInteger.valueOf(1))) {
                return n;
            } else if (a.modPow(d, n).equals(BigInteger.valueOf(-1))) {
                return n;
            } else {
                d.add(BigInteger.valueOf(2));
            }
        }

        if (a.modPow(d, n).equals(BigInteger.valueOf(1)) || a.modPow(d, n).equals(BigInteger.valueOf(-1))) {
            return n;
        } else {

            return findBigInt();
        }
    }

    // this method finds E
    public static BigInteger findE(BigInteger a) {
        int exp = 0;
        BigInteger max = a;
        BigInteger min = BigInteger.valueOf(1);
        Random random = new Random();
        int randLen = max.bitLength();
        BigInteger diff = a.subtract(min);
        BigInteger res = new BigInteger(randLen, random);
        if (res.compareTo(min) < 0) {
            res = res.add(min);
        }
        if (res.compareTo(diff) >= 0) {
            res = res.mod(diff).add(min);
        }
        if ((GCD(a, res)) == 1) {
            return res;
        } else {
            return findE(a);
        }

    }

    public static String Alice() {
        String out = "";
        BigInteger largePrime = findBigInt();
        BigInteger largePrime2 = findBigInt();
        BigInteger n = (largePrime).multiply(largePrime2);
        BigInteger phiN = (largePrime.subtract(BigInteger.valueOf(1)))
                .multiply(largePrime2.subtract(BigInteger.valueOf(1)));
        BigInteger publicExponent = findE(phiN);
        BigInteger privateExponent = publicExponent.modInverse(phiN);
        System.out.println(" Private key d " + privateExponent);
        System.out.println(" Public key n " + n);
        out += " Public exponent e " + publicExponent;
        return out;
    }

    // this is the manually implemented GCD method using Euclidean algorithm
    public static int GCD(BigInteger a, BigInteger b) {
        BigInteger quotient = BigInteger.valueOf(0);
        BigInteger divisor = b;
        // BigInteger multiple = a.divide(divisor);
        BigInteger remainder = a.remainder(divisor);
        List<BigInteger> list = new ArrayList<>();
        while (remainder.signum() != 0) {
            quotient = divisor;
            divisor = remainder;
            remainder = quotient.remainder(divisor);
            list.add(remainder);
        }
        BigInteger find = list.get(list.size() - 2);
        int gcd = find.intValue();
        return gcd;
    }

}
