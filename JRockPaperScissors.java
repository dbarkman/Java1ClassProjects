// JRockPaperScissors.java
// David Barkman
// david.barkman@cox.net
// Feb 13, 2009
// Class - CIS163AA
// Section - 27602

// import packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class JRockPaperScissors extends JApplet implements ActionListener {

    // initialize the JApplet objects
    Container con = getContentPane();
    JLabel title = new JLabel("Rock, Paper Scissors");
    JLabel label1 = new JLabel("Choose one button");
    JLabel label2 = new JLabel("---------------Results---------------");
    JLabel result1 = new JLabel("");
    JLabel result2 = new JLabel("");
    JLabel result3 = new JLabel("");
    Font arialB30 = new Font("Arial", Font.BOLD, 30);
    Font arialB22 = new Font("Arial", Font.BOLD, 22);
    Font arialB12 = new Font("Arial", Font.BOLD, 12);
    JButton rock = new JButton("Rock");
    JButton paper = new JButton("Paper");
    JButton scissors = new JButton("Scissors");
    FlowLayout flow = new FlowLayout(FlowLayout.LEFT);

    // initialize some variables
    String player, computer;
    int result;
    int tieCount = 0;
    int playerCount = 0;
    int compCount = 0;
    int total = 0;

    // display some applet objects
    public void init() {

        title.setFont(arialB30);
        label1.setFont(arialB12);
        con.setLayout(flow);
        con.add(title);
        con.add(label1);
        con.add(rock);
        con.add(paper);
        con.add(scissors);
        rock.addActionListener(this);
        paper.addActionListener(this);
        scissors.addActionListener(this);
    }

    public void actionPerformed(ActionEvent thisEvent) {

        // find out what button the player pressed
        Object source = thisEvent.getSource();
        if (source == rock) {
            player = "rock";
        } else if (source == paper) {
            player = "paper";
        } else if (source == scissors) {
            player = "scissors";
        }

        // have the computer make a choice
        Random r = new Random();
        int num = r.nextInt(3);
        if (num == 0) {
            computer = "rock";
        } else if (num == 1) {
            computer = "paper";
        } else if (num == 2) {
            computer = "scissors";
        }

        // decide who won the round
        // 0 = tie, 1 = player, 2 = computer
        if (player.equals(computer)) {
            result = 0;
        } else if (player.equals("rock") && computer.equals("scissors")) {
            result = 1;
        } else if (player.equals("rock") && computer.equals("paper")) {
            result = 2;
        } else if (player.equals("paper") && computer.equals("rock")) {
            result = 1;
        } else if (player.equals("paper") && computer.equals("scissors")) {
            result = 2;
        } else if (player.equals("scissors") && computer.equals("paper")) {
            result = 1;
        } else if (player.equals("scissors") && computer.equals("rock")) {
            result = 2;
        }

        // increment the count on the result of the hand
        if (result == 0) {
            result2.setText("Winner: Tie                                                            ");
            tieCount++;
        } else if (result == 1) {
            result2.setText("Winner: you                                                            ");
            playerCount++;
        } else if (result == 2) {
            result2.setText("Winner: computer                                                            ");
            compCount++;
        }

        // display the results for the hand and the current standings
        label2.setFont(arialB22);
        con.add(label2);

        result1.setFont(arialB12);
        con.add(result1);
        result1.setText("You picked " + player + " --- " + "Computer picked " + computer + "                    ");
        con.add(result2);
        result3.setText("You: " + playerCount + " Computer: " + compCount + " Tie: " + tieCount);
        con.add(result3);
        validate();
    }
}
