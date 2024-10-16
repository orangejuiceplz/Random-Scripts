public class jujutsuSorcerer {

    private String technique;

    private boolean hasCursedEnergy;

    private int damage;

    private String name;

    public jujutsuSorcerer() {
        this.name = "null";
        this.technique = "no technique";
        this.hasCursedEnergy = false;
        this.damage = 10;
    }
    public jujutsuSorcerer (String name, boolean hasCursedEnergy, String technique, int damage) {
        this.name = name;
        this.hasCursedEnergy = hasCursedEnergy;
        this.technique = technique;
        this.damage = damage;
    }

    public void setTechnique(String technique) {
        if (hasCursedEnergy) {
            this.technique = technique;
        } else {
            this.technique = "no technique";
        }
    }

    public String getTechnique() {
        return technique;
    }

    public boolean hasCursedEnergy() {
        return hasCursedEnergy;
    }

    public void introduceYourself() {
        System.out.print("i'm " + name);
        
        if (!hasCursedEnergy) {
            System.out.println(". i'm a jujutsu sorcerer but i have no cursed energy, however i have the " + technique + " technique");
        } else {
            System.out.println(". i'm a jujutsu sorcerer with the " + technique + " technique");
        }
    }
    
    @Override
    public String toString() {
        String energyStatus = hasCursedEnergy ? "has cursed energy" : "no cursed energy";
        return String.format("sorcerer: %s | sechnique: %s | %s | damage: %d",
                             name, technique, energyStatus, damage);
    }


    }
