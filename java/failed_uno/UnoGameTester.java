public class UnoGameTester {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        Player player1 = new Player();
        Player player2 = new Player();

        for (int i = 0; i < 5; i++) {
            player1.draw(deck);
            player2.draw(deck);
        }

        System.out.println("Player 1 hand size: " + player1.getHandSize());
        System.out.println("Player 2 hand size: " + player2.getHandSize());
    }
}
