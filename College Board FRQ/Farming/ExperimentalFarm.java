public class ExperimentalFarm {
    private Plot[][] farmPlots;
  
    public ExperimentalFarm(Plot[][] p) {
      // impl not shown
    }
  
    // part a
    public Plot getHighestYield(String c) {
      Plot plot = null;
      int highest = farmPlots[0][0].getCropYield();
  
      for (Plot[] row: farmPlots) {
        for (Plot curPlot: row) {
          if (curPlot.getCropType().equals(c) && curPlot.getCropYield() > highest) {
            highest = curPlot.getCropYield();
            plot = curPlot;
          }
        }
      }
  
      return plot;
    }
  
    // part b
  
    public boolean sameCrop(int col) {
      String crop = farmPlots[0][col].getCropType();
  
      for (Plot[] row: farmPlots) {
        if (!row[col].getCropType().equals(crop)) {
          return false;
        }
      }
  
      return true;
    }
  }