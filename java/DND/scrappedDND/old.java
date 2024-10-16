// Alexander Kociuba, Paul Melo, Shiven Kishore
import java.util.ArrayList;
import java.util.Scanner;
public class DNDtester 
{
    public static void main(String[] args) 
    {
        DND player = new DND(10, 5.0, 100.0, "Sakuya Izayoi"); // creates a new player
       
     
        DND monster = new DND(5, 2.5, 50.0, "monster"); // creates a new monster
        
        // Display character stats
       
        System.out.println("Player Stats:");
       
        player.displayStats(); 
         System.out.println("\nMonster Stats:");
       
        monster.displayStats();

       
        // Player attacks monster
      
        int roll = player.rollD20();
       
        double mult = player.attackMult();
       
        double damage = player.calculateDamage() * mult;
       
        System.out.println("\nPlayer rolled a " + roll + " and deals " + damage + " damage!");

        // Update monster's health after an attack and display updated stats

        monster.updateHealth(damage);

        System.out.println("\nMonster Stats after attack:");

        monster.displayStats();

        // Check if the monster is defeated

        if (monster.getHealth() <= 0) {

            System.out.println("\nMonster Defeated!");

        } else {

            System.out.println("\nThe fight continues...");

        }
        
        
        


       
       
        
    }
}
