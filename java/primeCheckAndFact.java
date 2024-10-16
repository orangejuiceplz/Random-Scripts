import java.util.Scanner;

public class primeCheckAndFact {

    public static void main(String[] args) {
        Scanner kirisame = new Scanner(System.in);

        System.out.println("enter a number bro:");
        int num = kirisame.nextInt();

        if (isPrime(num)) {
            System.out.println(num + " is a prime number, you're good.");
            System.out.println("the prime factors of " + num + " are:");
            primeFactorization(num); 
        } else {
             System.out.println(num + " is not a prime number, shame.");
        }
    }
    
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void primeFactorization(int num) {
        for (int i = 2; i <= num; i++) {
            while (num % i == 0) {
                System.out.println(i);
                num /= i;
            }
        }
    }
}
