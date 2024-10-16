#include <iostream>
#include <vector>
#include <random>
#include <algorithm>
#include <memory>

enum Suit { HEARTS, DIAMONDS, CLUBS, SPADES };
enum Rank { ACE = 1, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING };

struct Card {
    Suit suit;
    Rank rank;
};

class Deck {
private:
    std::vector<Card> cards;
public:
    Deck() {
        for (int suit = 0; suit < 4; ++suit) {
            for (int rank = 1; rank <= 13; ++rank) {
                cards.push_back({ static_cast<Suit>(suit), static_cast<Rank>(rank) });
            }
        }
        shuffle();
    }

    void shuffle() {
        std::random_device rd;
        std::mt19937 g(rd());
        std::shuffle(cards.begin(), cards.end(), g);
    }

    Card drawCard() {
        Card card = cards.back();
        cards.pop_back();
        return card;
    }
};

int calculateScore(const std::vector<Card>& hand) {
    int score = 0;
    int aces = 0;
    for (const auto& card : hand) {
        if (card.rank == ACE) {
            aces++;
            score += 11; // Temporarily count Ace as 11
        } else if (card.rank >= TEN) {
            score += 10;
        } else {
            score += card.rank;
        }
    }

    // Adjust for Aces
    while (score > 21 && aces > 0) {
        score -= 10; // Reduce score by 10 for each Ace counted as 11
        aces--;
    }

    return score;
}

class Player {
public:
    std::string name;
    int money;

    Player(const std::string& name, int money) : name(name), money(money) {}

    static Player createDealer() {
        return Player("Dealer", 10000); // Dealer with $10,000
    }

    void win(int amount) {
        money += amount;
    }

    void lose(int amount) {
        money -= amount;
    }
};

void playGame(Player& player1, Player& dealer) {
    int betAmount;
    std::cout << "Enter bet amount for " << player1.name << ": ";
    std::cin >> betAmount;
    if (betAmount > player1.money) {
        std::cout << "Insufficient funds!" << std::endl;
        return;
    }
    player1.lose(betAmount); // Deduct bet from player1's money

    Deck deck;
    std::vector<Card> playerHand;
    std::vector<Card> dealerHand;

    // Deal initial cards
    playerHand.push_back(deck.drawCard());
    dealerHand.push_back(deck.drawCard());
    playerHand.push_back(deck.drawCard());
    dealerHand.push_back(deck.drawCard());

    // Player's turn
    while (calculateScore(playerHand) < 21) {
        std::cout << player1.name << "'s hand: ";
        for (const auto& card : playerHand) {
            std::cout << card.rank << " ";
        }
        std::cout << "Score: " << calculateScore(playerHand) << std::endl;

        char choice;
        std::cout << "Hit (h) or Stand (s)? ";
        std::cin >> choice;
        if (choice == 'h') {
            playerHand.push_back(deck.drawCard());
        } else {
            break;
        }
    }

    // Dealer's turn
    while (calculateScore(dealerHand) < 17) {
        dealerHand.push_back(deck.drawCard());
    }

    // Determine winner
    int playerScore = calculateScore(playerHand);
    int dealerScore = calculateScore(dealerHand);

    std::cout << player1.name << "'s final hand: ";
    for (const auto& card : playerHand) {
        std::cout << card.rank << " ";
    }
    std::cout << "Score: " << playerScore << std::endl;

    std::cout << "Dealer's final hand: ";
    for (const auto& card : dealerHand) {
        std::cout << card.rank << " ";
    }
    std::cout << "Score: " << dealerScore << std::endl;

    if (playerScore > 21) {
        std::cout << "Dealer wins!" << std::endl;
        player1.lose(betAmount); // Player1 loses the bet
    } else if (dealerScore > 21) {
        std::cout << player1.name << " wins!" << std::endl;
        player1.win(betAmount); // Player1 wins the bet
    } else if (playerScore > dealerScore) {
        std::cout << player1.name << " wins!" << std::endl;
        player1.win(betAmount); // Player1 wins the bet
    } else if (playerScore < dealerScore) {
        std::cout << "Dealer wins!" << std::endl;
        player1.lose(betAmount); // Player1 loses the bet
    } else {
        std::cout << "It's a tie!" << std::endl;
    }
}

int main() {
    std::string player1Name;
    int player1Money;
    
    
    std::cout << "Hello, welcome to the simulation.\n";
    std::cout << "Please follow all instructions when prompted.\n";
    std::cout << "Enter Player 1's name: ";
    std::cin >> player1Name;
    std::cout << "Enter Player 1's starting money: ";
    std::cin >> player1Money;

    Player player1(player1Name, player1Money);
    Player dealer = Player::createDealer(); // Create the dealer with $10,000

    while (player1.money > 0) {
        playGame(player1, dealer);
        std::cout << player1.name << " has $" << player1.money << std::endl;
    }

    std::cout << "Game over! " << player1.name << " has no more money." << std::endl;

    return 0;
}
