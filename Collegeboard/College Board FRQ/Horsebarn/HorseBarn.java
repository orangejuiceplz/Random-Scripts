public class HorseBarn {

  private Horse[] spaces;

  // part A

public int findHorseSpace(String name) {
    for(int i = 0; i < spaces.length; i++) {
        Horse h = spaces[i];
        if(h != null && name.equals(h.getName()))
            return i;
    }

    return -1;
}

  // part B

public void consolidate() {
    int nextSpace = 0;

    for(int i = 0; i < spaces.length; i++)
    {
        if(spaces[i] != null)
        {
            spaces[nextSpace] = spaces[i];
            nextSpace++;
        }
    }

    for(int i = nextSpace; i < spaces.length; i++)
        spaces[i] = null;
  }
}
