import java.util.ArrayList;

public class Player {
    private ArrayList<UnoCard> hand;

    public Player() {
        this.hand = new ArrayList<>();
    }

    public void draw(Deck deck) {
        this.hand.add(deck.drawCard());
    }

    public UnoCard playCard(int index) {
        return this.hand.remove(index);
    }

    public int getHandSize() {
        return this.hand.size();
    }
}
