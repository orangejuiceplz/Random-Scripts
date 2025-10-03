public class better {

    // ==================== Programming Assignment #1 ====================
    // Recursive Strictly Increasing Digits Generator
    
    /**
     * Generates all strictly increasing numbers with exactly n digits
     * @param n Number of digits
     */
    public static void generateStrictlyIncreasing(int n) {
        if (n > 9 || n < 1) {
            return; // Edge case: cannot have more than 9 digits
        }
        generateStrictlyIncreasingHelper(n, 0, 1);
    }
    
    /**
     * Helper function to build strictly increasing numbers recursively
     * @param n Number of digits remaining
     * @param current Current number being built
     * @param lastDigit Last digit used (next digit must be > this)
     */
    private static void generateStrictlyIncreasingHelper(int n, int current, int lastDigit) {
        if (n == 0) {
            System.out.println(current);
            return;
        }
        
        // Try all digits from lastDigit to 9
        for (int digit = lastDigit; digit <= 9; digit++) {
            generateStrictlyIncreasingHelper(n - 1, current * 10 + digit, digit + 1);
        }
    }

    // ==================== Programming Assignment #2 ====================
    // mth Summation of the First n Natural Numbers
    
    /**
     * Computes the mth summation of the first n natural numbers
     * @param n First n natural numbers
     * @param m Number of summations to perform
     * @return The mth summation result
     */
    public static long mthSummation(int n, int m) {
        // Base case: 0th summation returns n
        if (m == 0) {
            return n;
        }
        
        // Base case: 1st summation is sum of first n natural numbers
        if (m == 1) {
            return (long) n * (n + 1) / 2;
        }
        
        // Recursive case: Sum of (m-1)th summation from 1 to n
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += mthSummation(i, m - 1);
        }
        return sum;
    }

    // ==================== Programming Assignment #3 ====================
    // Generate Binary Strings Without Consecutive 1's
    
    /**
     * Generates all binary strings of size k without consecutive 1's
     * @param k Size of the binary string
     */
    public static void generateBinaryStrings(int k) {
        if (k == 0) {
            return; // Edge case: no strings of size 0
        }
        generateBinaryStringsHelper(k, "");
    }
    
    /**
     * Helper function to generate binary strings recursively
     * @param k Remaining length
     * @param current Current string being built
     */
    private static void generateBinaryStringsHelper(int k, String current) {
        if (k == 0) {
            System.out.println(current);
            return;
        }
        
        // Always can add 0
        generateBinaryStringsHelper(k - 1, current + "0");
        
        // Can add 1 only if the last character is not 1
        if (current.isEmpty() || current.charAt(current.length() - 1) != '1') {
            generateBinaryStringsHelper(k - 1, current + "1");
        }
    }

    // ==================== Programming Assignment #4 ====================
    // Print n-Digit Binary Numbers with Equal Sum in Left and Right Halves
    
    /**
     * Prints all binary numbers of size n with equal sum in left and right halves
     * @param n Size of the binary number
     */
    public static void printEqualSumBinary(int n) {
        if (n == 0) {
            return;
        }
        printEqualSumBinaryHelper(n, "", 0, 0, 0);
    }
    
    /**
     * Helper function to generate binary numbers with equal left and right sums
     * @param n Total size of the binary number
     * @param current Current binary string being built
     * @param leftSum Sum of the left half
     * @param rightSum Sum of the right half
     * @param pos Current position (0-indexed)
     */
    private static void printEqualSumBinaryHelper(int n, String current, int leftSum, int rightSum, int pos) {
        if (pos == n) {
            if (leftSum == rightSum) {
                System.out.println(current);
            }
            return;
        }
        
        int leftHalf = n / 2;
        int rightStart = (n % 2 == 1) ? leftHalf + 1 : leftHalf;
        
        // Try adding 0
        if (pos < leftHalf) {
            // In left half
            printEqualSumBinaryHelper(n, current + "0", leftSum, rightSum, pos + 1);
        } else if (pos >= rightStart) {
            // In right half
            printEqualSumBinaryHelper(n, current + "0", leftSum, rightSum, pos + 1);
        } else {
            // Middle element (for odd n)
            printEqualSumBinaryHelper(n, current + "0", leftSum, rightSum, pos + 1);
        }
        
        // Try adding 1
        if (pos < leftHalf) {
            // In left half
            printEqualSumBinaryHelper(n, current + "1", leftSum + 1, rightSum, pos + 1);
        } else if (pos >= rightStart) {
            // In right half
            printEqualSumBinaryHelper(n, current + "1", leftSum, rightSum + 1, pos + 1);
        } else {
            // Middle element (for odd n)
            printEqualSumBinaryHelper(n, current + "1", leftSum, rightSum, pos + 1);
        }
    }

    // ==================== Programming Assignment #5 ====================
    // Calculate Maximum Number of Chocolates You Can Eat
    
    /**
     * Calculates the initial number of chocolates that can be bought with money
     * @param money Total money available
     * @param price Price of one chocolate
     * @return Number of chocolates that can be bought initially
     */
    public static int initialChocolates(int money, int price) {
        return money / price;
    }
    
    /**
     * Calculates additional chocolates from wrapper exchanges recursively
     * @param wrappers Current number of wrappers
     * @param wrap Number of wrappers needed for one chocolate
     * @return Total additional chocolates from wrapper exchanges
     */
    public static int chocolatesFromWrappers(int wrappers, int wrap) {
        if (wrappers < wrap) {
            return 0; // Not enough wrappers to exchange
        }
        
        int newChocolates = wrappers / wrap;
        int remainingWrappers = wrappers % wrap;
        
        // Recursively exchange new wrappers
        return newChocolates + chocolatesFromWrappers(newChocolates + remainingWrappers, wrap);
    }
    
    /**
     * Calculates maximum number of chocolates that can be eaten
     * @param money Total money available
     * @param price Price of one chocolate
     * @param wrap Number of wrappers needed for one chocolate
     * @return Maximum number of chocolates
     */
    public static int maxChocolates(int money, int price, int wrap) {
        int initial = initialChocolates(money, price);
        int fromWrappers = chocolatesFromWrappers(initial, wrap);
        return initial + fromWrappers;
    }

    // ==================== Main Method for Testing ====================
    
    public static void main(String[] args) {
        System.out.println("===== Assignment #1: Strictly Increasing Digits =====");
        System.out.println("n = 2:");
        generateStrictlyIncreasing(2);
        System.out.println("\nn = 1:");
        generateStrictlyIncreasing(1);
        
        System.out.println("\n===== Assignment #2: mth Summation =====");
        System.out.println("n = 3, m = 2: " + mthSummation(3, 2));
        System.out.println("n = 4, m = 1: " + mthSummation(4, 1));
        System.out.println("n = 5, m = 0: " + mthSummation(5, 0));
        
        System.out.println("\n===== Assignment #3: Binary Strings Without Consecutive 1's =====");
        System.out.println("k = 3:");
        generateBinaryStrings(3);
        System.out.println("\nk = 1:");
        generateBinaryStrings(1);
        
        System.out.println("\n===== Assignment #4: Equal Sum Binary Numbers =====");
        System.out.println("n = 4:");
        printEqualSumBinary(4);
        System.out.println("\nn = 5 (first few):");
        printEqualSumBinary(5);
        
        System.out.println("\n===== Assignment #5: Maximum Chocolates =====");
        System.out.println("Money = 20, Price = 3, Wrap = 5:");
        System.out.println("Initial chocolates: " + initialChocolates(20, 3));
        System.out.println("Total chocolates: " + maxChocolates(20, 3, 5));
        
        System.out.println("\nMoney = 15, Price = 1, Wrap = 3:");
        System.out.println("Initial chocolates: " + initialChocolates(15, 1));
        System.out.println("Total chocolates: " + maxChocolates(15, 1, 3));
    }
}
