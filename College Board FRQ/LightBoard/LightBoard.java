public class LightBoard {
    private boolean[][] lights;
  
    // part a
    
public LightBoard(int numRows, int numCols) {
    lights = new boolean[numRows][numCols];

    for(int r = 0; r < lights.length; r++) {
        for(int c = 0; c < lights[0].length; c++) {
            if(Math.random() <= 0.4) {
                lights[r][c] = true;
            }
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
