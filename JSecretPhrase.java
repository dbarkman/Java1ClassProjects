// JSecretPhrase.java
// David Barkman
// david.barkman@cox.net
// Feb 16, 2009
// Class - CIS163AA
// Section - 27602

// import packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class JSecretPhrase extends JApplet implements ActionListener{

    // phrases to use for the game
    String[] phrases = {"The Departed", "The Pursuit of Happyness", "The Bucket List", "Casino Royale", "Walk the Line", "Inside Man", "Knocked Up", "Michael Clayton", "Failure to Launch", "The Bourne Supremacy"};

    // randomly pick one of the phrases to use
    // sets the phrase to "phrasePicked"
    Random r = new Random();
    int num = r.nextInt(10);
    String phrasePicked = phrases[num];

    // initialize the JApplet objects
    Container con = getContentPane();
    JLabel title = new JLabel("Secrte Phrase Game");
    JLabel label1 = new JLabel("Play our game - guess the movie title - Click one letter");
    JLabel label2 = new JLabel("");
    JLabel secret = new JLabel("");
    Font arialB30 = new Font("Arial", Font.BOLD, 30);
    Font arialB20 = new Font("Arial", Font.BOLD, 20);
    FlowLayout flow = new FlowLayout(FlowLayout.LEFT);

    // put the phrase in a string buffer called phrase
    StringBuffer phrase = new StringBuffer(phrasePicked);

    // put all the letters of the alphabet into a string
    String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // initialize the array of letter buttons
    JButton[] buttons = new JButton[alpha.length()];

    public void init() {

        // display the applet objects
        title.setFont(arialB30);
        label1.setFont(arialB20);
        con.setLayout(flow);
        con.add(title);
        con.add(label1);

        // turn phrase into all "*", only letters, not spaces
        int phraseLength = phrase.length();
        for (int i = 0; i < phraseLength; i++) {
            char letter = phrase.charAt(i);
            if (Character.isLetter(letter)) {
                phrase.setCharAt(i,'*');
            }
        }

        // phrase the string buffer is copied into phraseSt the String
        // can only set an applet object with a String
        String phraseSt = phrase.toString();
        secret.setText(phraseSt);
        secret.setFont(arialB30);
        con.add(secret);

        con.add(label2);

        // add button objects to the "buttons" array and setup the action listeners
        int alphaLength = alpha.length();
        for (int i = 0; i < alphaLength; i++) {
            char letter = alpha.charAt(i);
            String letterStr = Character.toString(letter);
            buttons[i] = new JButton(letterStr);
            con.add(buttons[i]);
            buttons[i].addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent thisEvent) {

        // find out what button was clicked
        Object source = thisEvent.getSource();
        for (int i = 0; i < buttons.length; i++) {
            if (source.equals(buttons[i])) {
                buttons[i].setEnabled(false);
                String buttonText = buttons[i].getText();

                // find out if the button clicked matches a letter in the phrase
                // use "count" to keep track of how letters matched the letter clicked
                int count = 0;
                int phraseStrLength = phrasePicked.length();
                for (int j = 0; j < phraseStrLength; j++) {
                    char letter = phrasePicked.charAt(j);
                    String letterStr = Character.toString(letter);
                    // if it matches swap out the *(s) for the letter(s)
                    if (letterStr.equalsIgnoreCase(buttonText)) {
                        phrase.setCharAt(j, letter);
                        // turn phrase back into a String and display the updated phrase
                        String phraseSt = phrase.toString();
                        secret.setText(phraseSt);
                        count++;

                        // count the *s in the phrase to see if any are left
                        int letterCount = 0;
                        int secretLength = phraseSt.length();
                        for (int k = 0; k < secretLength; k++) {
                            char letter1 = phraseSt.charAt(k);
                            if (letter1 == '*') letterCount++;
                        }
                        // if all the letters have been converted, disable all the buttons
                        // and display the Congratulations message
                        if (letterCount == 0) {
                            for (int m = 0; m < buttons.length; m++) {
                                buttons[m].setEnabled(false);
                            }
                            label2.setText("Congratulations!");
                            break;
                        }
                    }
                    // display a message based on if the letter was matched
                    if (count == 0) {
                        label2.setText("Sorry, " + buttonText + " is not in the phrase.");
                    } else if (count >= 1) {
                        label2.setText("Correct!");
                    }
                }
            }
        }
    }
}
