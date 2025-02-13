public class HorseBarn {

  private Horse[] spaces;

  // part A

  public int findHorseSpace(String name) {
    for (int curSpace = 0; curSpace < spaces.length; curSpace++) {
      if (spaces[curSpace] != null &&
        name.equals(spaces[curSpace].getName())) {
        return curSpace;
      }
    }
    return -1;
  }

  // part B

  public void consolidate() {
    for (int eSpace = 0; eSpace < spaces.length - 1; eSpace++) {
      if (this.spaces[eSpace] == null) {
        for (int nextOccSpace = eSpace + 1; nextOccSpace < spaces.length; nextOccSpace++) {
          if (spaces[nextOccSpace] != null) {
            spaces[eSpace] = spaces[nextOccSpace];
            spaces[nextOccSpace] = null;
            nextOccSpace = spaces.length;
          }
        }
      }
    }
  }
}