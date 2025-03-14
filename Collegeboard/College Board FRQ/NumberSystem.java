public class NumberSystem {
    
    public static int gcf(int a, int b) {
        if (a % b == 0) {
            return b;
        } else {
            return gcf(b, (a % b));
        }
    }
    
    public static void reduceFraction(int numerator, int denominator) {
        int gcf = gcf(numerator, denominator);
        if (numerator % denominator == 0) {
            System.out.println(numerator + "/" + denominator + " reduces to " + numerator / denominator);
        } else {
            System.out.println(numerator + "/" + denominator + " reduces to " + numerator / gcf + "/" + denominator / gcf);
        }
    }
    
    
    public static void main(String[] args) {
        NumberSystem.reduceFraction(8, 20);
    }
}
