
public class RSAMath {

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    public static long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    public static int findD(int e, int phi) {
        for (int k = 1; ; k++) {
            long num = (long) phi * k + 1;
            if (num % e == 0) return (int) (num / e);
        }
    }
}