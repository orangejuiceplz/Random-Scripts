public class Animal {
	private String type;
    private String species;
    private String name;
    
    public Animal(String type, String species, String name) {
    	  this.type = type;
        this.species = species;
        this.name = name;
    }
    
    public String toString() {
    	return name + " the " + species + " is a " + type;
    }
}
