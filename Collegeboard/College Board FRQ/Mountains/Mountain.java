public class Mountain {

    public static boolean isIncreasing(int[] array, int stop) {
  
      // not shown but i'm just going to return true for vscode
      return true;
    }
  
    public static boolean isDecreasing(int[] array, int start) {
      // not shown but i'm just going to return true for vscode
      return true;
    }
  
    // part a
  
    public static int getPeakIndex(int[] array) {
      for (int curPos = 1; curPos < array.length - 1; curPos++) {
        if (array[curPos] > array[curPos - 1] &&
          array[curPos] < array[curPos + 1]) {
          return curPos;
        }
      }
      return -1;
    }
  
    // part b
  
    public static boolean isMountain(int[] array) {
      int peakIndex = getPeakIndex(array);
      return isIncreasing(array, peakIndex) && isDecreasing(array, peakIndex);
    }
  }