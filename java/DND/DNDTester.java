public class DNDTester
{
    public static void main(String[] args) {

        DNDCharacter character = new DNDCharacter(10, 5.0, 100.0, "Hero");

        character.displayStats();

        double damage = character.calculateDamage();
        System.out.println("Damage: " + damage);

        character.updateHealth(damage);
        character.displayStats();
    }
}
