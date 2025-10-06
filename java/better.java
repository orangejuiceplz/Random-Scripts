public class better {

    public static void assignment1(int n, int k) {
        if (n > 9) {
            return;
        }
        if (n == 0) {
            return;
        }
        if (k > 9) {
            return;
        }
        if (n == 1) {
            System.out.println(k);
            return;
        }
        System.out.println(k);
        for (int i = k % 10 + 1; i <= 9; i++) {
            assignment1(n - 1, k * 10 + i);
        }
    }

    public static int assignment2(int n, int m) {
        if (m == 0) {
            return n;
        }
        if (m == 1) {
            return n * (n + 1) / 2;
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += assignment2(i, m - 1);
        }
        return sum;
    }

    public static void assignment3(int k, String str) {
        if (k == 0) {
            System.out.println(str);
            return;
        }
        assignment3(k - 1, str + "0");
        if (str.isEmpty() || str.charAt(str.length() - 1) == '0') {
            assignment3(k - 1, str + "1");
        }
    }

    public static void assignment4(int n, int left, int right, int index, String str) {
        if (index == n) {
            if (left == right) {
                System.out.println(str);
            }
            return;
        }
        if (index < n / 2) {
            assignment4(n, left, right, index + 1, str + "0");
            assignment4(n, left + 1, right, index + 1, str + "1");
        } else if (n % 2 == 1 && index == n / 2) {
            assignment4(n, left, right, index + 1, str + "0");
            assignment4(n, left, right, index + 1, str + "1");
        } else {
            assignment4(n, left, right + 1, index + 1, str + "1");
            assignment4(n, left, right, index + 1, str + "0");
        }
    }

    public static int assignment5_initial(int money, int price) {
        return money / price;
    }

    public static int assignment5_wrappers(int wrap, int wrappers) {
        if (wrappers < wrap) {
            return 0;
        }
        int newChocolates = wrappers / wrap;
        int remainingWrappers = wrappers % wrap;
        return newChocolates + assignment5_wrappers(wrap, newChocolates + remainingWrappers);
    }

    public static void main(String[] args) {
        System.out.println("=== Assignment 1: Strictly Increasing Digits ===");
        System.out.println("n=2:");
        for (int i = 1; i <= 9; i++) {
            assignment1(2, i);
        }
        System.out.println("\nn=3:");
        for (int i = 1; i <= 9; i++) {
            assignment1(3, i);
        }

        System.out.println("\n=== Assignment 2: mth Summation ===");
        System.out.println("n=3, m=2: " + assignment2(3, 2));
        System.out.println("n=4, m=1: " + assignment2(4, 1));
        System.out.println("n=5, m=0: " + assignment2(5, 0));

        System.out.println("\n=== Assignment 3: Binary Strings Without Consecutive 1's ===");
        System.out.println("k=3:");
        assignment3(3, "");
        System.out.println("\nk=4:");
        assignment3(4, "");

        System.out.println("\n=== Assignment 4: Binary Numbers with Equal Left/Right Sums ===");
        System.out.println("n=4:");
        assignment4(4, 0, 0, 0, "");
        System.out.println("\nn=5:");
        assignment4(5, 0, 0, 0, "");

        System.out.println("\n=== Assignment 5: Maximum Chocolates ===");
        int money = 20;
        int price = 3;
        int wrap = 5;
        int initial = assignment5_initial(money, price);
        int total = initial + assignment5_wrappers(wrap, initial);
        System.out.println("Money: " + money + ", Price: " + price + ", Wrap: " + wrap);
        System.out.println("Initial chocolates: " + initial);
        System.out.println("Total chocolates: " + total);
    }
}
