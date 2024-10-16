import java.util.ArrayList;

    public class DND
{
    private int roll;
   
    private double attack;
   
    private double health;
    
    private String name;
    
    private int mult;
    
    private double damage;
   
    private double monsterAtk;
   
    private double monsterHealth;
   
    /* instance variable roll used to decide attacks, mult 
    goes along with it (i think? DND stuff i'm not that familar with)
    and damage is the final. 
    
    health and name are js complete separate
    
    */
    

    
    
    //this is the player
      public DND (int roll, double attack, double health, String name) 
    {
       this.roll = roll;
        this.attack = attack;
        this.health = health;
        this.name = name;
    }
    
    //this is the monster
   public DND (double atk, double heal, String name)
{
    roll = 0;
    attack = atk;
    health = heal;
    this.name = name;
    mult = 0;
    damage = 0.0;
}
    
   // rolling mechanic
    public int rollD20()
    {
      
        int randInteger1 = (int)(Math.random()*(21)+ 1);
      
        while (randInteger1 > 20 || randInteger1 < 1)
        {
            randInteger1 = (int)(Math.random()*(21)+ 1);
        }
      
        roll = randInteger1;
      
        return roll;
    }
    
 // displaying player stats
  public void displayStats() {
       
        System.out.println("Name: " + name);
       
        System.out.println("Roll: " + roll);
       
        System.out.println("Attack: " + attack);
       
        System.out.println("Health: " + health);
  }
  //if your roll is too low you get a bonus of times 3 if not its 2 times
    public double attackMult()
    {
        if (roll < 10)
        {
            return mult = 3;
        }
     
        else
     
        {
            return mult = 2;
        }
    }
    
      public double calculateDamage() {
     
        return roll * attack;
    }
    
    //this shows if you can defeat a monster or not
    
    public String attackMonster()
    {
        if (damage > health)
        {
            return "Monster Defeated!";
        }
    
        else 
    
        {
            health =- damage;
            return "monster has " + health + " HP";
        }
    }
    
    // updating health after taking damage
    
     public void updateHealth(double damage) {
    
        if (damage < health) {
            health -= damage;
        } else {
            health = 0;
        }
    }
    
    public double getHealth() {
        return health;
    }
  // i'm aware this is a getter method. i don't care, as we
  // already met the requirements . its for the fight
    public double monsterAtk(){
        double randIteger1 = (int)(Math.random()*(11.0)+ 1.0);
      
        while (randIteger1 > 10.0 || randIteger1 < 1.0)
        {
            randIteger1 = (int)(Math.random()*(11.0)+ 1.0);
        }
      
        monsterAtk = randIteger1;
      
        return monsterAtk;
    }

public double monsterHealth(){
        double randIeger1 = (int)(Math.random()*(11.0)+ 1.0);
      
        while (randIeger1 > 10.0 || randIeger1 < 1.0)
        {
            randIeger1 = (int)(Math.random()*(11.0)+ 1.0);
        }
      
        monsterHealth = randIeger1;
      
        return monsterHealth;
    }


    // unneeded toString() Just for show ig, never used.
    public String toString()
    
    {
        return "A monster with " + attack + " attack and " + health + "health attacks";
    }
    
    // ngl, we did a great job on the class
    
}
