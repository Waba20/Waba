import java.util.Scanner;


public class Main {

    // Given RSA parameters (Part A)
    static final int P = 19;
    static final int Q = 23;
    static final int E = 13;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("===== RSA ENCRYPTION / DECRYPTION SYSTEM =====");
        System.out.println("p = " + P + ", q = " + Q + ", e = " + E + "\n");

        // Receiver generates keys
        Receiver receiver = new Receiver(P, Q, E);
        receiver.displayKeys();

        // Sender gets ONLY the public key
        Sender sender = new Sender(receiver.getE(), receiver.getN());

        String again = "Y";
        do {
            System.out.print("\n[Sender] Enter a message to send: ");
            String message = in.nextLine();

            if (message.isEmpty()) {
                System.out.println("Message cannot be empty.");
                continue;
            }

            try {
                // ---- Sender side ----
                long[] numbers = sender.convert(message);
                long[] cipher = sender.encrypt(numbers);

                System.out.println("\n--- SENDER ---");
                System.out.println("Original message    : " + message);
                System.out.println("Numeric (ASCII)     : " + MessageConverter.format(numbers));
                System.out.println("Public key used     : (e=" + sender.getE() + ", n=" + sender.getN() + ")");
                System.out.println("Ciphertext          : " + MessageConverter.format(cipher));

                // ---- Simulated transmission ----
                System.out.println("\n--- TRANSMISSION ---");
                System.out.println("Sending ciphertext over insecure network (plaintext is NOT sent)...");
                long[] received = cipher.clone();
                System.out.println("Receiver got        : " + MessageConverter.format(received));

                // ---- Receiver side ----
                String decrypted = receiver.decrypt(received);
                System.out.println("\n--- RECEIVER ---");
                System.out.println("Decrypted message   : " + decrypted);

                // ---- Summary ----
                System.out.println("\n-SUMMARY ---");
                System.out.println("Original  : " + message);
                System.out.println("Encrypted : " + MessageConverter.format(cipher));
                System.out.println("Decrypted : " + decrypted);
                System.out.println("Match     : " + (message.equals(decrypted)
                        ? "YES - decrypted message is identical to the original"
                        : "NO - mismatch!"));
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            System.out.print("\nSend another message? (Y/N): ");
            again = in.nextLine().trim();
        } while (again.equalsIgnoreCase("Y"));

        System.out.println("Goodbye.");
        in.close();
    }
}