import java.util.*;

public class Main {
    private static final int ALPHABET_SIZE = 26;
    private int a;
    private int b;

    public Main(int a, int b) {
        this.a = a;
        this.b = b;
    }

    private int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1; // Inverse doesn't exist if this is returned
    }

    public String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (char character : plaintext.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int encryptedChar = (a * (character - base) + b) % ALPHABET_SIZE + base;
                ciphertext.append((char) encryptedChar);
            } else {
                ciphertext.append(character);
            }
        }
        return ciphertext.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        int aInverse = modInverse(a, ALPHABET_SIZE);
        for (char character : ciphertext.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int decryptedChar = (aInverse * ((character - base) - b + ALPHABET_SIZE)) % ALPHABET_SIZE + base;
                plaintext.append((char) decryptedChar);
            } else {
                plaintext.append(character);
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("the chosen keys are k1=7 and k2=2");
        int k1=7;
        int k2=2;
        Main affineCipher = new Main(k1, k2);
        System.out.println("Enter the plaintext: ");
        String plaintext = sc.nextLine();
        String encrypted = affineCipher.encrypt(plaintext);
        String decrypted = affineCipher.decrypt(encrypted);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
