import java.util.*;
public class Main {

    // Method to encrypt the plaintext using the key
    public static String encrypt(String plaintext, String key) {
        StringBuilder encryptedText = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (int i = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);
            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                int encryptedChar = (currentChar - base + key.charAt(keyIndex) - 'A') % 26 + base;
                encryptedText.append((char) encryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                encryptedText.append(currentChar);
            }
        }
        return encryptedText.toString();
    }

    // Method to decrypt the ciphertext using the key
    public static String decrypt(String ciphertext, String key) {
        StringBuilder decryptedText = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (int i = 0; i < ciphertext.length(); i++) {
            char currentChar = ciphertext.charAt(i);
            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                int decryptedChar = (currentChar - base - (key.charAt(keyIndex) - 'A') + 26) % 26 + base;
                decryptedText.append((char) decryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                decryptedText.append(currentChar);
            }
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the plaintext");
        String plaintext = sc.nextLine();
        System.out.println("enter the key");
        String key = sc.nextLine();
        String encryptedText = encrypt(plaintext, key);
        String decryptedText = decrypt(encryptedText, key);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Key: " + key);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
