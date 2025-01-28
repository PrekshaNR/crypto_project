import java.util.*;
public class Main {

    // Method to implement the Extended Euclidean Algorithm
    public static int[] extendedEuclidean(int a, int b) {
        if (b == 0) {
            return new int[] { a, 1, 0 };
        }

        int[] result = extendedEuclidean(b, a % b);
        int gcd = result[0];
        int x1 = result[1];
        int y1 = result[2];
        int x = y1;
        int y = x1 - (a / b) * y1;

        return new int[] { gcd, x, y };
    }

    // Method to find the modular inverse of b mod n
    public static int modInverse(int b, int n) {
        int[] result = extendedEuclidean(b, n);
        int gcd = result[0];
        int x = result[1];

        if (gcd != 1) {
            throw new ArithmeticException("Inverse does not exist");
        } else {
            // Make sure the result is positive
            return (x % n + n) % n;
        }
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("enter the value b whose inverse is to be found out:");
        int b = sc.nextInt();
          System.out.println("enter the value n wrt which inverse is to be found out:");
        int n = sc.nextInt();
        try {
            int inverse = modInverse(b, n);
            System.out.println("The modular inverse of " + b + " mod " + n + " is: " + inverse);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
