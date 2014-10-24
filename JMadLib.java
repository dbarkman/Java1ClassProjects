// JMadLib.java
// David Barkman
// david.barkman@cox.net
// Feb 13, 2009
// Class - CIS163AA
// Section - 27602

// import packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JMadLib extends JApplet implements ActionListener {

    // initialize the JApplet obects
    Container con1 = getContentPane();
    JLabel label = new JLabel("Welcome to Mad Libs");
    JLabel label1 = new JLabel("Enter a noun");
    JLabel label2 = new JLabel("Completed Rhyme");
    JLabel line1 = new JLabel("");
    JLabel line2 = new JLabel("");
    JLabel line3 = new JLabel("");
    JLabel line4 = new JLabel("");
    Font font1 = new Font("Arial", Font.BOLD, 30);
    Font font2 = new Font("Arial", Font.BOLD, 20);
    JTextField field1 = new JTextField("",5);
    JButton button1 = new JButton("Press Me");
    FlowLayout flow1 = new FlowLayout();

    // initialize some variables
    String inputStr, noun1, noun2, adj, verb;

    // count is used to process through each question
    int count = 0;

    public void init() {

        // display the applet objects and request the first noun
        label.setFont(font1);
        con1.setLayout(flow1);
        con1.add(label);
        con1.add(label1);
        con1.add(field1);
        con1.add(button1);
        button1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent thisEvent) {

        switch(count) {
            case 0:
                // change the screen to ask for another noun
                inputStr = field1.getText();
                field1.setText("");
                field1.requestFocus();
                noun1 = inputStr;
                label1.setText("Enter another noun");
                count++;
                break;
            case 1:
                // change the screen to ask for an adjective
                inputStr = field1.getText();
                field1.setText("");
                field1.requestFocus();
                noun2 = inputStr;
                label1.setText("Enter an adjective");
                count++;
                break;
            case 2:
                // change the screen to ask for a past tense verb
                inputStr = field1.getText();
                field1.setText("");
                field1.requestFocus();
                adj = inputStr;
                label1.setText("Enter a past tense verb");
                count++;
                break;
            default:
                // take all the input and change the screen to the "new" nursery ryme
                label2.setFont(font1);
                inputStr = field1.getText();
                verb = inputStr;
                remove(label);
                remove(label1);
                remove(field1);
                remove(button1);
                con1.add(label2);
                line1.setText("Mary had a little " + noun1);
                line2.setText("Its " + noun2 + " was " + adj + " as snow");
                line3.setText("And everywhere that Mary " + verb);
                line4.setText("The " + noun1 + " was sure to go");
                line1.setFont(font2);
                line2.setFont(font2);
                line3.setFont(font2);
                line4.setFont(font2);
                con1.add(line1);
                con1.add(line2);
                con1.add(line3);
                con1.add(line4);
                validate();
        }
    }
}
