// JCardBet.java
// David Barkman
// david.barkman@cox.net
// Feb 17, 2009
// Class - CIS163AA
// Section - 27602

// import packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JCardBet extends JApplet implements ActionListener{

    // initialize the japplet objects
    Container con = getContentPane();
    JLabel title = new JLabel("***Bet the Card***");
    JLabel compCard = new JLabel("");
    JLabel playCard = new JLabel("");
    JLabel score = new JLabel("Your Cash: $10");
    JLabel gameOver = new JLabel("");
    Font arialB30 = new Font("Arial", Font.BOLD, 30);
    Font arialB20 = new Font("Arial", Font.BOLD, 20);
    JButton fiveHigher = new JButton("$5 Higher");
    JButton tenHigher = new JButton("$10 Higher");
    JButton fiveLower = new JButton("$5 Lower");
    JButton tenLower = new JButton("$10 Lower");
    JButton ok = new JButton("OK");
    FlowLayout flow = new FlowLayout();

    // initialize the deck of cards
    DeckOfCards deck = new DeckOfCards();

    // grab the first card for the computer and all it's fields
    int initialIndex = deck.getNextCardIndex();
    String initialRank = deck.getNextCardRank(initialIndex);
    String initialSuit = deck.getNextCardSuit(initialIndex);
    int initialOrder = deck.getNextCardOrder(initialIndex);
    String nextCompCard = initialRank + " of " + initialSuit;

    // create an array to hold the two currently played cards
    Card[] cardArray = new Card[2];

    // set the players cash amount to $10
    int cashAmt = 10;

    public void init() {

        // add the computers current card to the current card array
        cardArray[0] = new Card(initialRank, initialSuit, initialOrder);

        // build the applet
        compCard.setText("Computer: " + nextCompCard);

        title.setFont(arialB30);
        compCard.setFont(arialB20);
        playCard.setFont(arialB20);
        score.setFont(arialB20);
        gameOver.setFont(arialB20);
        con.setLayout(flow);
        con.add(title);
        con.add(compCard);
        con.add(fiveHigher);
        con.add(tenHigher);
        con.add(fiveLower);
        con.add(tenLower);
        con.add(playCard);
        con.add(score);
        con.add(ok);
        ok.setEnabled(false);
        con.add(gameOver);

        fiveHigher.addActionListener(this);
        tenHigher.addActionListener(this);
        fiveLower.addActionListener(this);
        tenLower.addActionListener(this);
        ok.addActionListener(this);
    } 

    public void actionPerformed(ActionEvent thisEvent) {

        // find out if it's the computer or player's turn
        // 0 = computer's turn; 1 = player's turn
        // when the number of cards remaining is odd, it's the player's turn to draw a card
        // when even, the computer draws
        int arrayIndex = 0;
        int howManyCards = deck.cardsLeft();
        if (howManyCards % 2 == 1) arrayIndex = 1;

        // grab the next card
        int index = deck.getNextCardIndex();
        String rank = deck.getNextCardRank(index);
        String suit = deck.getNextCardSuit(index);
        int order = deck.getNextCardOrder(index);

        // set the card in the current card array
        cardArray[arrayIndex] = new Card(rank, suit, order);
        String card = rank + " of " + suit;

        // find out if player clicked ok or a bet button
        Object source = thisEvent.getSource();
        if (source == ok) {
            // ok was pressed, new round
            ok.setEnabled(false);
            fiveHigher.setEnabled(true);
            tenHigher.setEnabled(true);
            fiveLower.setEnabled(true);
            tenLower.setEnabled(true);

            // new round, blank out the player's card and display the new computer's card
            playCard.setText("");
            compCard.setText("Computer: " + card);

        } else {
            // a bet was made
            ok.setEnabled(true);
            fiveHigher.setEnabled(false);
            tenHigher.setEnabled(false);
            fiveLower.setEnabled(false);
            tenLower.setEnabled(false);

            // get the order for the computer and player card
            int compOrder = cardArray[0].getOrder();
            int playOrder = cardArray[1].getOrder();

            // find out who won, default is computer
            // 0 = computer win - 1 = player win
            int winner = 0;
            if ((source == fiveHigher || source == tenHigher) && playOrder > compOrder) {
                winner = 1;
            } else if ((source == fiveLower || source == tenLower) && playOrder < compOrder) {
                winner = 1;
            }

            // find out what bet the player made (what button was clicked)
            // if the computer won, subtract money and if player won, add money
            if (source == fiveHigher) {
                if (winner == 0) {
                    cashAmt = cashAmt - 5;
                } else {
                    cashAmt = cashAmt + 5;
                }
            } else if (source == tenHigher) {
                if (winner == 0) {
                    cashAmt = cashAmt - 10;
                } else {
                    cashAmt = cashAmt + 10;
                }
            } else if (source == fiveLower) {
                if (winner == 0) {
                    cashAmt = cashAmt - 5;
                } else {
                    cashAmt = cashAmt + 5;
                }
            } else if (source == tenLower) {
                if (winner == 0) {
                    cashAmt = cashAmt - 10;
                } else {
                    cashAmt = cashAmt + 10;
                }
            }

            // display the players card and current cash amount
            playCard.setText("Your card: " + card);
            score.setText("Your cash: $" + cashAmt);

            // find out if the game is over
            int howManyLeft = deck.cardsLeft();
            // no more cards left to play
            if (howManyLeft == 0) {
                ok.setEnabled(false);
                gameOver.setText("Game Over! - All Cards Played");
            // player has gone broke
            } else if (cashAmt <= 0) {
                ok.setEnabled(false);
                gameOver.setText("Game Over! - You've Gone Broke!");
            // player has made $100 or more
            } else if (cashAmt >= 100) {
                ok.setEnabled(false);
                gameOver.setText("Game Over! - You Broke the House!");
            }
        }
    }
}
