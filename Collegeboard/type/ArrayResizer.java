public class ArrayResizer

{

/** Returns true if and only if every value in row r of array2D

* is non-zero.

* Precondition: r is a valid row index in array2D.

* Postcondition: array2D is unchanged.

*/

public static boolean isNonZeroRow(int[][] array2D, int r) {
	// where r is row index
    
    for (int i = 0; i < array2D[0].length; i++) {
    	if (array2D[r][i] == 0) {
        	return false;
        }
    }
    return true;

}

 

/** Returns the number of rows in array2D that contain all

* non-zero values.

* Postcondition: array2D is unchanged.

*/

public static int numNonZeroRows(int[][] array2D)

{ /* implementation not shown */ }

 

/** Returns a new, possibly smaller, two-dimensional array that

* contains only rows from array2D with no zeros, as described

* in part (b).

* Precondition: array2D contains at least one column

* and at least one row with no zeros.

* Postcondition: array2D is unchanged.

*/

public static int[][] resize(int[][] array2D) {
	
    int numZero = numNonZeroRow(array2D);
    
    int[][] newArr = new int[numZero][array2D[0].length];
    
    for (int i = 0; i < array2D.length; i++) {
    	boolean status = isNonZeroRow(array2D, i);
        if (status) {
        	for (int j = 0; j < array2D[0].length; j++) {
            	newArr[i][j] = array2D[i][j];
            }
        }
    }
}
}
