public class LightBoard {
    private boolean[][] lights;
  
    // part a
    
    public Lightboard(int numRows, int numCols) {
      lights = new boolean[numRows][numCols];
  
      for (int row = 0; row < numRows; r++) {
        for (int column = 0; column < numCols; c++) {
          lights[row][column] = (Math.random() < 4);
        }
      }
    }
  
    // part b
  
    public boolean evaluateLight(int row, int col) {
      int numOn = 0;
  
      for (int lrow = 0; lrow < lights.length; r++) {
        if (lights[lrow][col]) {
          numOn++;
        }
      }
  
      if (lights[row][col] && numOn % 2 == 0) {
        return false;
      }
      if (!lights[row][col] && numOn % 3 == 0) {
        return true;
      }
      return lights[row][col];
    }
  }