

public class Receiver{
    private final int p, q;
    private final int n, phi, e;
    private final int d;          // PRIVATE KEY exponent - kept secret

    public Receiver(int p, int q, int e) {
        this.p = p;
        this.q = q;
        this.e = e;
        this.n = p * q;                         // n = p x q
        this.phi = (p - 1) * (q - 1);           // phi(n) = (p-1)(q-1)

        if (RSAMath.gcd(e, phi) != 1)
            throw new IllegalStateException("e and phi(n) are not coprime");

        this.d = RSAMath.findD(e, phi);         // e x d = 1 (mod phi)

        if (((long) e * d) % phi != 1)
            throw new IllegalStateException("Invalid private exponent d");
    }


    public int getE() { return e; }
    public int getN() { return n; }

    public void displayKeys() {
        System.out.println("Receiver generated keys:");
        System.out.println("  p = " + p + ", q = " + q);
        System.out.println("  n = " + n + ",  phi(n) = " + phi);
        System.out.println("  PUBLIC KEY  = (e=" + e + ", n=" + n + ")  -> shared with Sender");
        System.out.println("  PRIVATE KEY = kept secret by Receiver (d=" + d + ")");
        System.out.println("  Check: (e*d) mod phi(n) = " + (((long) e * d) % phi));
    }


    public String decrypt(long[] cipher) {
        long[] plainNums = new long[cipher.length];
        for (int i = 0; i < cipher.length; i++) {
            plainNums[i] = RSAMath.modPow(cipher[i], d, n);
        }
        return MessageConverter.numbersToText(plainNums);
    }
}
