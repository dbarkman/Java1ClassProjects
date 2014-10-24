// Card.java
// David Barkman
// david.barkman@cox.net
// Feb 17, 2009
// Class - CIS163AA
// Section - 27602

// Card class for creating card objects
// used for initial 52 card deck and for the current two displayed cards
public class Card {

    // set some variables
    private String rank, suit, owner;
    private int order;

    // one constructor, takes rank, suit and order (1-12)
    // used for 52 card deck
    public Card(String rank, String suit, int order) {
        this.rank = rank;
        this.suit = suit;
        this.order = order;
    }

    // another constructor, takes rank, suit, order and owner (who played the card)
    // used for currently displayed cards
    public Card(String rank, String suit, int order, String owner){
        this.rank = rank;
        this.suit = suit;
        this.order = order;
        this.owner = owner;
    }

    // rank of the card object (2 - Ace)
    public String getRank() {
        return rank;
    }

    // suit of the card: hearts, clubs, diamonds and spades
    public String getSuit() {
        return suit;
    }

    // order of the card (1-12)
    public int getOrder() {
        return order;
    }

    // who played the card
    public String getOwner() {
        return owner;
    }
}
