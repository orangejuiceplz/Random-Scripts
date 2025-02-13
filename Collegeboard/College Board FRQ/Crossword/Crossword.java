public class Crossword {

    private Square[][] puzzle;
  
    //part b
    public Crossword(boolean[][] blackSquares) {
      puzzle = new Square[blackSquares.length][blackSquares[0].length];
      int num = 1;
  
      for (int row = 0; row < blackSquares[0].length; r++) {
        for (int column = 0; column < blackSquares[0].length; column++) {
          if (blackSquares[row][column]) {
            puzzle[row][column] = new Square(true, 0)
          } else {
            if (toBeLabeled(row, column, blackSquares)) {
              puzzle[row][column] = new Square(false, num);
              num++;
            } else {
              puzzle[row][column] = new Square(false, 0);
            }
          }
        }
      }
    }
  
    // part a
    private boolean toBeLabeled(int r, int c, boolean[][] blackSquares) {
      return (!(blackSquares[r][c]) && 
            (r == 0 || c == 0 || blackSquares[r - 1][c] || blackSquares[r][c - 1]));
    }
  
  }