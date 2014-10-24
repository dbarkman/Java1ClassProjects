// DeckOfCards.java
// David Barkman
// david.barkman@cox.net
// Feb 17, 2009
// Class - CIS163AA
// Section - 27602

// import packages
import java.util.ArrayList;
import java.util.Random;

// DeckOfCards class used to create the game deck
public class DeckOfCards {
    
    // put the 52 cards into an array list, makes it easier to remove used cards from the deck/array
    ArrayList<Card> myCards = new ArrayList<Card>();

    // constructor for creating the deck, suit and rank is preset based on a standard deck of cards
    public DeckOfCards() {
        String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        // initialize the 52 cards
        int count = 0;
        for (int i = 0; i < 4; i++) {
            String suit = suits[i];
            for (int j = 0; j < 13; j++) {
                String rank = ranks[j];
                int order = j + 1;
                Card s = new Card(rank, suit, order);
                myCards.add(count, s);
                count++;
            }
        }
    }

    // mostly used during testing, but can be used to list all the cards currently in the array list
    public void listCards() {
        for (int i = 0; i < 52; i++) {
            Card s = myCards.get(i);
            System.out.println(s.getOrder() + " - " + s.getRank() + " of " + s.getSuit());
        }
    }

    // randomly picks the next card from the remaining cards
    // and sends back it's index in the array list
    public int getNextCardIndex() {
        int size = myCards.size();
        Random r = new Random();
        int num = r.nextInt(size);
        return num;
    }

    // get the rank of the currently picked card
    public String getNextCardRank(int num) {
        Card s = myCards.get(num);
        String rank = s.getRank();
        return rank;
    }

    // get the suit of the currently picked card
    public String getNextCardSuit(int num) {
        Card s = myCards.get(num);
        String suit = s.getSuit();
        return suit;
    }

    // get the order of the currently picked card
    public int getNextCardOrder(int num) {
        Card s = myCards.get(num);
        int order = s.getOrder();
        myCards.remove(num);
        return order;
    }

    // find out how many cards are left in the deck
    public int cardsLeft() {
        int left = myCards.size();
        return left;
    }
}
