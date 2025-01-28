/*RSA algorithm*/
import java.io.*;
import java.math.*;
import java.nio.charset.*;
import java.util.*;

public class Main{
    private BigInteger p, q, N, phi, e, d;
    private int bitLength = 1024;
    private Random r;

    public Main() {
        r = new Random();
        p = BigInteger.probablePrime(bitLength, r);
        q = BigInteger.probablePrime(bitLength, r);
        System.out.println("Prime number p is " + p);
        System.out.println("Prime number q is " + q);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitLength / 2, r);

        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }

        System.out.println("Public key is " + e);
        d = e.modInverse(phi);
        System.out.println("Private key is " + d);
    }

    public static void main(String[] args) throws IOException {
        Main rsa = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the plain text: ");
        String testString = br.readLine();
        System.out.println("Encrypting string: " + testString);
        System.out.println("String in bytes: " + bytesToString(testString.getBytes()));
        byte[] encrypted = rsa.encrypt(testString.getBytes());
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
        System.out.println("Decrypted string: " + new String(decrypted, StandardCharsets.UTF_8));
    }

    private static String bytesToString(byte[] encrypted) {
        StringBuilder result = new StringBuilder();
        for (byte b : encrypted) {
            result.append(Byte.toString(b));
        }
        return result.toString();
    }

    public byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }

    public byte[] decrypt(byte[] message) {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }
}
