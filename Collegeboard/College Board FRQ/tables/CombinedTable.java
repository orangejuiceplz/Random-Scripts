public class CombinedTable {
  private SingleTable tb1;
  private SingleTable tb2;
  public CombinedTable(SingleTable tb1, SingleTable tb2) {
    this.tb1 = tb1;
    this.tb2 = tb2;
  }
  // ?
  public boolean canSeat(int ppl) {
    int totalAval = this.tb1.getNumSeats() + this.tb2.getNumSeats() - 2;
    boolean possible = (totalAval >= ppl);
    return possible;
  }
  // no idea
  public double getDesirability() {
    double avg = (this.tb1.getViewQuality() + this.tb2.getViewQuality()) / 2.0;
    if (this.tb1.getHeight() == this.tb2.getHeight()) {
      return avg;
    } else {
      return avg - 10;
    }
  }
}
