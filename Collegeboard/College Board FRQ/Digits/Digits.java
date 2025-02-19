

public class Digits {


    private ArrayList<Integer> digitlist;

    public Digits(int num) {

    digitList = new ArrayList<Integer>();
    digitList.add(0, new Integer(num % 10));

    int remaining = num / 10;
    
    while(remaining > 0) {
        digitList.add(0, new Integer(remaining % 10));
        remaining /= 10;
    }
}

    public boolean isStrictlyIncreasing() {


        for (int i =  1; i  < digits.size(); i++) {
            if (digitList.get(i - 1).compareTo(digitList.get(i)) >= 0) {
                return false;
            }
        }
        return true;
    }
}