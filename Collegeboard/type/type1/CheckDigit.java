public class CheckDigit

{

/** Returns the check digit for num, as described in part (a).

* Precondition: The number of digits in num is between one and six, inclusive.

* num >= 0

*/

public static int getCheck(int num)

{

  int sum = 0;
  for (int i = 1; i <= getNumberOfDigits(num); i++) {
      sum += (8 - i) * getDigit(num, i);
  }
  return sum % 10;
}

 

/** Returns true if numWithCheckDigit is valid, or false otherwise, as described in part (b).

 * Precondition: The number of digits in numWithCheckDigit is between two and seven, inclusive.

* numWithCheckDigit >= 0

 */

public static boolean isValid(int numWithCheckDigit)

{
  int check = numWithCheckDigit % 10;
  int n = numWithCheckDigit / 10;
  int check2 = getCheck(n);
  if (check == check2) {
      return true;
  } else {
    return false;
}

 

/** Returns the number of digits in num. */

public static int getNumberOfDigits(int num)

{

/* implementation not shown */

}

 

/** Returns the nth digit of num.

* Precondition: n >= 1 and n <= the number of digits in num

*/

public static int getDigit(int num, int n)

{

/* implementation not shown */

}

 

// There may be instance variables, constructors, and methods not shown.

}
