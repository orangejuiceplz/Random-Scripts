import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<UnoCard> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        String[] values = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (String color : colors) {
            for (String value : values) {
                this.cards.add(new UnoCard(color, value));
            }
        }
    }

    public UnoCard drawCard() {
        return this.cards.remove(this.cards.size() - 1);
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }
}
