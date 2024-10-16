import java.util.Random;
import java.util.Scanner;

public class DiceRoll {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Type 'roll' to roll the dice or 'end' to stop:");
            String input = scanner.nextLine();
            
            if ("roll".equalsIgnoreCase(input)) {
                int roll = rand.nextInt(6) + 1;
                System.out.println("You rolled: " + roll);
            } else if ("end".equalsIgnoreCase(input)) {
                System.out.println("Ending the program.");
                break;
            } else {
                System.out.println("Invalid input. Please type 'roll' or 'end'.");
            }
        }
        
        canner.close();
    }
}
