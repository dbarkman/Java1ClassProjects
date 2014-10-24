// JTicTacToe.java
// David Barkman
// david.barkman@cox.net
// Feb 19, 2009
// Class - CIS163AA
// Section - 27602

// import packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JTicTacToe extends JApplet implements ActionListener{

    // initialize the japplet objects
    Container con = getContentPane();
    JLabel title = new JLabel("Tic Tac Toe");
    JLabel instr = new JLabel("Choose one button");
    JLabel result = new JLabel("");
    JLabel error = new JLabel("");
    Font arialB30 = new Font("Arial", Font.BOLD, 30);
    Font arialB20 = new Font("Arial", Font.BOLD, 20);
    JButton[] buttons = new JButton[9];
    FlowLayout flow = new FlowLayout(FlowLayout.CENTER);

    // initialize a new computer player
    Computer comp = new Computer();

    // nine turns in tic, tac, toe, but only 5 clicks for the human
    // after 5 clicks, if no winner, game over
    int clicks = 5;
    
    public void init() {

        // build the applet
        title.setFont(arialB30);
        instr.setFont(arialB20);
        con.setLayout(flow);
        result.setFont(arialB20);
        con.add(title);
        con.add(instr);
        // create an array of 9 buttons
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setPreferredSize(new Dimension(50, 50));
            con.add(buttons[i]);
            buttons[i].addActionListener(this);
        }
        con.add(error);
        con.add(result);
    }

    public void actionPerformed(ActionEvent thisEvent) {

        error.setText("");

        // find out what button was clicked
        Object source = thisEvent.getSource();
        for (int i = 0; i < buttons.length; i++) {
            if (source.equals(buttons[i])) {
                String currText = buttons[i].getText();
                if (currText.length() == 0) {
                    // if the position has not been played, play it
                    buttons[i].setText("X");
                    // check for a winner every time a marker is set on a position
                    int winnerX = comp.checkWinner("X");
                    // if a winner was found, disable all buttons and display a message
                    if (winnerX == 11) {
                        for (int j = 0; j < 9; j++) {
                            buttons[j].setEnabled(false);
                            result.setText("You Win!!");
                        }
                    } else {
                        // once clicks reaches 0, disable all buttons and display a message
                        clicks--;
                        if (clicks == 0) {
                            for (int j = 0; j < 9; j++) {
                                buttons[j].setEnabled(false);
                                result.setText("Tie Game");
                            }
                        } else {
                            // if human didn't win and there are still positions to play
                            // allow the computer to make another move
                            int compMove = comp.nextMove(i);
                            buttons[compMove].setText("O");
                            // check for a winner every time a marker is set on a position
                            int winnerO = comp.checkWinner("O");
                            // if a winner was found, disable all buttons and display a message
                            if (winnerO == 10) {
                                for (int j = 0; j < 9; j++) {
                                    buttons[j].setEnabled(false);
                                    result.setText("Computer Wins!!");
                                }
                            }
                        }
                    }
                } else {
                    // if the position has already been played, display an error
                    error.setText("Please Click Another Button");
                }
            }
        }
    }
}
