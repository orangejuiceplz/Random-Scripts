public class Elephant extends Herbivore {
	private double tuskLength;
    
    public Elephant(String name, double tuskLength) {
    	super("elephant", name);
        this.tuskLength = tustLength;
    }
    
    public String toString() {
        return this.super.toString() + " with tusks " + this.tuskLength + " meters long";
    }
}
