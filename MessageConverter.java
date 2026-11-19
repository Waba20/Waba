
public class MessageConverter {


    public static long[] textToNumbers(String msg, int n) {
        long[] nums = new long[msg.length()];
        for (int i = 0; i < msg.length(); i++) {
            int code = msg.charAt(i);
            if (code >= n) {
                throw new IllegalArgumentException(
                        "Character '" + msg.charAt(i) + "' (code " + code + ") is too large for n=" + n);
            }
            nums[i] = code;
        }
        return nums;
    }


    public static String numbersToText(long[] nums) {
        StringBuilder sb = new StringBuilder();
        for (long x : nums) sb.append((char) x);
        return sb.toString();
    }


    public static String format(long[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        return sb.toString();
    }
}