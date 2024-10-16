import java.util.ArrayList;
import java.util.Scanner;

public class DNDtester 
{
    public static void main(String[] args) 
    {
        
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Double> difficultyMultipliers = new ArrayList<>();
        
        difficultyMultipliers.add(1.0); // Easy
        
        difficultyMultipliers.add(2.0); // Medium
        
        difficultyMultipliers.add(5.0); // Hard

        
        System.out.println("Select difficulty level:");
        System.out.println("0 for Easy");
        System.out.println("1 for Medium");
        System.out.println("2 for Hard");
        
        int difficultyLevel = scanner.nextInt();

        
        DND you = new DND(10, 1.0, 100.0, "Sakuya Izayoi"); // creates a new player

        // Randomly generate monster's base stats
        
        double monsterAtk = Math.round((Math.random() * 10 + 1) * 10) / 10.0;
        
        double monsterHeal = Math.round((Math.random() * 50 + 1) * 10) / 10.0;

        
        DND monster = new DND(monsterAtk, monsterHeal * difficultyMultipliers.get(difficultyLevel), "monster"); // creates a new monster

        
        // Display character stats
        
        System.out.println("Your Stats:");
        
        you.displayStats(); 
        
        System.out.println("\nMonster Stats:");
        
        monster.displayStats();

        
        // Player attacks monster until it is defeated
        
        while (monster.getHealth() > 0) {
         
            int roll = you.rollD20();
        
            double mult = you.attackMult();
        
            double damage = Math.round(you.calculateDamage() * mult * 10) / 10.0;
        
            System.out.println("\nYou rolled a " + roll + " and deals " + damage + " damage!");

        
            // Update monster's health after an attack and display updated stats
        
            monster.updateHealth(damage);
        
            System.out.println("\nMonster Stats after attack:");
        
            monster.displayStats();

        
            if (monster.getHealth() > 0) {
        
                System.out.println("\nThe fight continues, so you rolled again...");
            }
        }

        
        // Monster is defeated
        
        System.out.println("\nMonster Defeated!");
    }
}
