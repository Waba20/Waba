
public class Sender{
    private final int e, n;

    public Sender(int e, int n) {
        this.e = e;
        this.n = n;
    }

    public int getE() { return e; }
    public int getN() { return n; }

    // Text -> numbers
    public long[] convert(String message) {
        return MessageConverter.textToNumbers(message, n);
    }

    // C = M^e mod n
    public long[] encrypt(long[] numbers) {
        long[] c = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            c[i] = RSAMath.modPow(numbers[i], e, n);
        }
        return c;
    }
}