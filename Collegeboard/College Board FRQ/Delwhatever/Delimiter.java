public class Delimiters {
  private String openDel;
  private String closeDel;

  public Delimiters(String open, String close) {
    openDel = open;
    closeDel = close;
  }

  public ArrayList < String > getDelimitersList(String[] tokens) {
    ArrayList < String > delimiters = new ArrayList < String > ();
    for (String indexVal: tokens) {
      if (indexVal.equals(openDel) || indexVal.equals(closeDel)) {
        delimiters.add(indexVal);
      }
    }
    return delimiters;
  }

  public boolean isBalanced(ArrayList < String > delimiters) {
    int open = 0;
    int closed = 0;
    for (String del: delimiters) {
      if (del.equals(openDel)) {
        open++;
      }
      if (del.equals(closeDel)) {
        closed++;
      }
      if (closed > open) return false;
    }
    return (open == closed);
  }
