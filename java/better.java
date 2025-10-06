public class better {

   static void strictlyIncreasingDigitsGen(int n, int k) {
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
           strictlyIncreasingDigitsGen(n, k + 1);
           return;
       }
       System.out.println(k);
       strictlyIncreasingDigitsGen(n - 1, k * 10 + (k % 10 + 1));
       strictlyIncreasingDigitsGen(n, k + 1);
   }

   static int mthSummation(int n, int m) {
       if (m == 0) {
           return n;
       }
       if (m == 1) {
           return n * (n + 1) / 2;
       }
       if (n == 0) {
           return 0;
       }
       return mthSummation(n - 1, m) + mthSummation(n, m - 1);
   }

   static void genBinStringNoConsecutiveOnes(int k, String str) {
       if (k == 0) {
           System.out.println(str);
           return;
       }
       genBinStringNoConsecutiveOnes(k - 1, str + "0");
       if (str.isEmpty() || str.charAt(str.length() - 1) == '0') {
           genBinStringNoConsecutiveOnes(k - 1, str + "1");
       }
   }

   static void printBinEqualSumHalves(int n, int left, int right, int index, String str) {
       if (index == n) {
           if (left == right) {
               System.out.println(str);
           }
           return;
       }
       if (index < n / 2) {
           printBinEqualSumHalves(n, left, right, index + 1, str + "0");
           printBinEqualSumHalves(n, left + 1, right, index + 1, str + "1");
       } else if (n % 2 == 1 && index == n / 2) {
           printBinEqualSumHalves(n, left, right, index + 1, str + "0");
           printBinEqualSumHalves(n, left, right, index + 1, str + "1");
       } else {
           printBinEqualSumHalves(n, left, right + 1, index + 1, str + "1");
           printBinEqualSumHalves(n, left, right, index + 1, str + "0");
       }
   }
   static int calcMaxChocInit(int money, int price) {
       if (money < price) {
           return 0;
       }
       return 1 + calcMaxChocInit(money - price, price);
   }

   static int calcMaxChocWrap(int wrap, int wrappers) { // where wrap is num wrappers needed for 1 chhoc exchange
       if (wrappers < wrap) {
           return 0;
       }
       int newChocolates = wrappers / wrap;
       int remainingWrappers = wrappers % wrap;
       return newChocolates + calcMaxChocWrap(wrap, newChocolates + remainingWrappers);
   }


   public static void main(String[] args) {

       int money = 100;
       int price = 2;
       int wrap = 5;
       int initial = calcMaxChocInit(money, price);
       int total = initial + calcMaxChocWrap(wrap, initial);
       System.out.println("Money: " + money + ", Price: " + price + ", Wrap: " + wrap);
       System.out.println("Initial chocolates: " + initial);
       System.out.println("Total chocolates: " + total);

   }














}

