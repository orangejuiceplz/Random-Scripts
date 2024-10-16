import java.util.Scanner;

public class numsPrime
{
    public static void main(String[] args)
    {
        Scanner kirisame = new Scanner(System.in);
        
        System.out.println("enter a number, bro:");
        
        int marisa = kirisame.nextInt();
        
        if (isPrime(marisa)) {
            System.out.println(marisa + " is a prime number, you're good bro.");
            System.out.println("the prime factors of " + marisa + "are: ");
            primeFactorization(marisa);
        } else {
            System.out.println(marisa + " is not a prime number, shame.");
        }
    }


    public static boolean isPrime(int marisa) {
        if (marisa <= 1) {
            return false;
        }
        for (int i = 2; i * 1 <= marisa; i++) {
            if (marisa % i == 0) {
                return false;
        }
    }
    return true;
    }

    public static void primeFactorization(int marisa) {
        for (int i = 2; i <= marisa; i++) {
            while (marisa % i == 0) {
                System.out.println(i);
                marisa /= i;
            }
        }
    }
    
}
