public class DNDCharacter {

    private int roll;
    private double attack;
    private double health;
    private String name;

    public DNDCharacter() {
        this.roll = 0;
        this.attack = 0.0;
        this.health = 0.0;
        this.name = "";
    }

     public DNDCharacter(int roll, double attack, double health, String name) {
        this.roll = roll;
        this.attack = attack;
        this.health = health;
        this.name = name;
    }

     public void displayStats() {
        System.out.println("Name: " + name);
        System.out.println("Roll: " + roll);
        System.out.println("Attack: " + attack);
        System.out.println("Health: " + health);
    }

     public double calculateDamage() {
        return roll * attack;
    }

     public void updateHealth(double damage) {
        if (damage < health) {
            health -= damage;
        } else {
            health = 0;
        }
    }

    
}
