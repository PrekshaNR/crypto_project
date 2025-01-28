import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

public class Main {

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        // Select large prime p and primitive root g
        BigInteger p = new BigInteger("23"); // Example prime number
        BigInteger g = new BigInteger("5");  // Example primitive root

        // Select private key d
        BigInteger d = new BigInteger(p.bitLength() - 1, random).mod(p.subtract(BigInteger.ONE)).add(BigInteger.ONE);

        // Compute public key e2 = g^d mod p
        BigInteger e2 = g.modPow(d, p);

        // Select random integer r
        BigInteger r = new BigInteger(p.bitLength() - 1, random).mod(p.subtract(BigInteger.ONE)).add(BigInteger.ONE);

        // Compute c1 = g^r mod p
        BigInteger c1 = g.modPow(r, p);

        // Select message m
        
        System.out.println("Enter the plain text");
        String message=sc.nextLine();
        
        BigInteger m = new BigInteger(message.getBytes()); // Example message

        // Compute c2 = m * e2^r mod p
        BigInteger c2 = m.multiply(e2.modPow(r, p)).mod(p);

        // Display the ciphertext (c1, c2)
        System.out.println("Ciphertext: (c1, c2) = (" + c1 + ", " + c2 + ")");

        // Decrypt the message
        BigInteger decryptedMessage = c2.multiply(c1.modPow(d.negate(), p)).mod(p);

        // Display the decrypted message
        System.out.println("Decrypted message: " + decryptedMessage);
       
    }
}
