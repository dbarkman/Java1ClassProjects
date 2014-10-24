// Computer.java
// David Barkman
// david.barkman@cox.net
// Feb 20, 2009
// Class - CIS163AA
// Section - 27602

import java.util.ArrayList;
import java.util.Random;


// Computer class used for the computer player
public class Computer {
    
    // when the computer player is initialized, it will initialize the 9 positions
    // this is the virtual board the computer uses to keep track of the game
    Positions[] position = new Positions[9];

    // constructor for the computer object, immediatly initializes the 9 positions
    // sets the posistion number (0-8) and taken to 0
    public Computer() {
        for (int i = 0; i < 9; i++) {
            position[i] = new Positions(i, 0);
        }
    }

    // the main method used for computer moves
    // several strategies are used to make a decision
    // including checking for defensive and offensive plays
    public int nextMove(int num) {
        // "play" referes to the position to be played
        // 9 is the default play
        // playing 9 will cause an error, so several strategies
        // are checked in order to find a real play (0-8)
        int play = 9;
        // human goes first, so his played position is marked on the virtual board
        position[num].setMarker("X");
        // if this is the first play for the computer
        // it will either play center (4) if available
        // or play a corner
        // playing corners on your first round as O makes it harder for X to win
        // once an O postion is played, mark it on the virtual board
        if (positionsOpen() == 8) {
            if (position[4].isTaken() == 0) {
                play = 4;
                position[play].setMarker("O");
            } else {
                play = corners();
                position[play].setMarker("O");
            }
        } else {
            // if this wasn't the first play for O, first check for an
            // offensive move (two O's already played in sequence)
            // then check for defensive plays (two X's already played in sequence)
            play = checkOffense();
            if (play == 9) play = checkDefense();
            if (play == 9) play = sides();
            if (play == 9) play = corners();
            // mark the play chosen on the virtual board
            position[play].setMarker("O");
        }
        // send back the computers next move
        return play;
    }

    // grab a count of open postions
    private int positionsOpen() {
        int count = 9;
        for (int i = 0; i < 9; i++) {
            int taken = position[i].isTaken();
            if (taken == 1) count--;
        }
        return count;
    }

    // randomly pick a corner to play
    private int corners() {
        int play = 9;
        ArrayList<Integer> open = new ArrayList<Integer>();
        if (position[0].isTaken() == 0) open.add(0);
        if (position[2].isTaken() == 0) open.add(2);
        if (position[6].isTaken() == 0) open.add(6);
        if (position[8].isTaken() == 0) open.add(8);
        if (open.size() >= 1) {
            Random r = new Random();
            int num = r.nextInt(open.size());
            play = open.get(num);
        }
        return play;
    }

    // randomly pick a side to play
    private int sides() {
        int play = 9;
        ArrayList<Integer> open = new ArrayList<Integer>();
        if (position[1].isTaken() == 0) open.add(1);
        if (position[3].isTaken() == 0) open.add(3);
        if (position[5].isTaken() == 0) open.add(5);
        if (position[7].isTaken() == 0) open.add(7);
        if (open.size() >= 1) {
            Random r = new Random();
            int num = r.nextInt(open.size());
            play = open.get(num);
        }
        return play;
    }

    // logic sequence to see if X has a sequence of two on the board, if so block the sequence
    private int checkDefense() {
        int play = 9;
        // create an array list of X's plays
        ArrayList<Integer> marked = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            if (position[i].isTaken() == 1) {
                if (position[i].getMarker().equals("X")) marked.add(i);
            }
        }
        int h = marked.size();
        int i = 0;
        while (play == 9 && h > 0) {
            int mark1 = marked.get(i);
            // process through the array to look for sequenses of two X's, if found, block
            switch(mark1) {
                case 0:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 1 && position[2].isTaken() == 0) {play = 2; break;}
                        if (mark2 == 2 && position[1].isTaken() == 0) {play = 1; break;}
                        if (mark2 == 3 && position[6].isTaken() == 0) {play = 6; break;}
                        if (mark2 == 4 && position[8].isTaken() == 0) {play = 8; break;}
                        if (mark2 == 5 && position[2].isTaken() == 0) {play = 2; break;}
                        if (mark2 == 6 && position[3].isTaken() == 0) {play = 3; break;}
                        if (mark2 == 7 && position[6].isTaken() == 0) {play = 6; break;}
                        if (mark2 == 8 && position[4].isTaken() == 0) {play = 4; break;}
                    }
                    break;
                case 1:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 2 && position[0].isTaken() == 0) {play = 0; break;}
                        if (mark2 == 3 && position[0].isTaken() == 0) {play = 0; break;}
                        if (mark2 == 4 && position[7].isTaken() == 0) {play = 7; break;}
                        if (mark2 == 5 && position[2].isTaken() == 0) {play = 2; break;}
                        if (mark2 == 6 && position[0].isTaken() == 0) {play = 0; break;}
                        if (mark2 == 7 && position[4].isTaken() == 0) {play = 4; break;}
                        if (mark2 == 8 && position[2].isTaken() == 0) {play = 2; break;}
                    }
                    break;
                case 2:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 3 && position[0].isTaken() == 0) {play = 0; break;}
                        if (mark2 == 4 && position[6].isTaken() == 0) {play = 6; break;}
                        if (mark2 == 5 && position[8].isTaken() == 0) {play = 8; break;}
                        if (mark2 == 6 && position[4].isTaken() == 0) {play = 4; break;}
                        if (mark2 == 7 && position[8].isTaken() == 0) {play = 8; break;}
                        if (mark2 == 8 && position[5].isTaken() == 0) {play = 5; break;}
                    }
                    break;
                case 3:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 4 && position[5].isTaken() == 0) {play = 5; break;}
                        if (mark2 == 5 && position[4].isTaken() == 0) {play = 4; break;}
                        if (mark2 == 6 && position[0].isTaken() == 0) {play = 0; break;}
                        if (mark2 == 7 && position[6].isTaken() == 0) {play = 6; break;}
                        if (mark2 == 8 && position[6].isTaken() == 0) {play = 6; break;}
                    }
                    break;
                case 4:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 5 && position[3].isTaken() == 0) {play = 3; break;}
                        if (mark2 == 6 && position[2].isTaken() == 0) {play = 2; break;}
                        if (mark2 == 7 && position[1].isTaken() == 0) {play = 1; break;}
                        if (mark2 == 8 && position[0].isTaken() == 0) {play = 0; break;}
                    }
                    break;
                case 5:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 6 && position[8].isTaken() == 0) {play = 8; break;}
                        if (mark2 == 7 && position[8].isTaken() == 0) {play = 8; break;}
                        if (mark2 == 8 && position[2].isTaken() == 0) {play = 2; break;}
                    }
                    break;
                case 6:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 7 && position[8].isTaken() == 0) {play = 8; break;}
                        if (mark2 == 8 && position[7].isTaken() == 0) {play = 7; break;}
                    }
                    break;
                case 7:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 8 && position[6].isTaken() == 0) {play = 6; break;}
                    }
                    break;
            }
        h--;
        i++;
        }
        return play;
    }

    // logic sequence to see if O has a sequence of two on the board, if so block the sequence
    private int checkOffense() {
        int play = 9;
        // create an array list of O's plays
        ArrayList<Integer> marked = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            if (position[i].isTaken() == 1) {
                if (position[i].getMarker().equals("O")) marked.add(i);
            }
        }
        //for (int i = 0; i < marked.size(); i++) {
        int h = marked.size();
        int i = 0;
        while (play == 9 && h > 0) {
            int mark1 = marked.get(i);
            // process through the array to look for sequenses of two O's, if found, play for the win
            switch(mark1) {
                case 0:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 1 && position[2].isTaken() == 0) {play = 2; break;}
                        if (mark2 == 2 && position[1].isTaken() == 0) {play = 1; break;}
                        if (mark2 == 3 && position[6].isTaken() == 0) {play = 6; break;}
                        if (mark2 == 4 && position[8].isTaken() == 0) {play = 8; break;}
                        if (mark2 == 6 && position[3].isTaken() == 0) {play = 3; break;}
                        if (mark2 == 8 && position[4].isTaken() == 0) {play = 4; break;}
                    }
                    break;
                case 1:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 2 && position[0].isTaken() == 0) {play = 0; break;}
                        if (mark2 == 4 && position[7].isTaken() == 0) {play = 7; break;}
                        if (mark2 == 7 && position[4].isTaken() == 0) {play = 4; break;}
                    }
                    break;
                case 2:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 4 && position[6].isTaken() == 0) {play = 6; break;}
                        if (mark2 == 5 && position[8].isTaken() == 0) {play = 8; break;}
                        if (mark2 == 6 && position[4].isTaken() == 0) {play = 4; break;}
                        if (mark2 == 8 && position[5].isTaken() == 0) {play = 5; break;}
                    }
                    break;
                case 3:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 4 && position[5].isTaken() == 0) {play = 5; break;}
                        if (mark2 == 5 && position[4].isTaken() == 0) {play = 4; break;}
                        if (mark2 == 6 && position[0].isTaken() == 0) {play = 0; break;}
                    }
                    break;
                case 4:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 5 && position[3].isTaken() == 0) {play = 3; break;}
                        if (mark2 == 6 && position[2].isTaken() == 0) {play = 2; break;}
                        if (mark2 == 7 && position[1].isTaken() == 0) {play = 1; break;}
                        if (mark2 == 8 && position[0].isTaken() == 0) {play = 0; break;}
                    }
                    break;
                case 5:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 8 && position[2].isTaken() == 0) {play = 2; break;}
                    }
                    break;
                case 6:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 7 && position[8].isTaken() == 0) {play = 8; break;}
                        if (mark2 == 8 && position[7].isTaken() == 0) {play = 7; break;}
                    }
                    break;
                case 7:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 8 && position[6].isTaken() == 0) {play = 6; break;}
                    }
                    break;
            }
        h--;
        i++;
        }
        return play;
    }

    // logic sequence to check for a winner
    // when called, a marker is sent in, either X or O
    // returns 9 no winner; 10 computer winnner; 11 player winner
    public int checkWinner(String marker) {
        int player = 0;
        if (marker.equals("O")) {
            player = 10;
        } else if (marker.equals("X")) {
            player = 11;
        }
        int play = 9;
        // create an array list of plays based on the marker sent in
        ArrayList<Integer> marked = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            if (position[i].isTaken() == 1) {
                if (position[i].getMarker().equals(marker)) marked.add(i);
            }
        }
        int h = marked.size();
        int i = 0;
        while (play == 9 && h > 0) {
            int mark1 = marked.get(i);
            // search the array for any sequences of three markers
            switch(mark1) {
                case 0:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 1 && position[2].isTaken() == 1 && position[2].getMarker().equals(marker)) {play = player; break;}
                        if (mark2 == 3 && position[6].isTaken() == 1 && position[6].getMarker().equals(marker)) {play = player; break;}
                        if (mark2 == 4 && position[8].isTaken() == 1 && position[8].getMarker().equals(marker)) {play = player; break;}
                    }
                    break;
                case 1:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 4 && position[7].isTaken() == 1 && position[7].getMarker().equals(marker)) {play = player; break;}
                    }
                    break;
                case 2:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 4 && position[6].isTaken() == 1 && position[6].getMarker().equals(marker)) {play = player; break;}
                        if (mark2 == 5 && position[8].isTaken() == 1 && position[8].getMarker().equals(marker)) {play = player; break;}
                    }
                    break;
                case 3:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 4 && position[5].isTaken() == 1 && position[5].getMarker().equals(marker)) {play = player; break;}
                    }
                    break;
                case 6:
                    for (int j = 0; j < marked.size(); j++) {
                        int mark2 = marked.get(j);
                        if (mark2 == 7 && position[8].isTaken() == 1 && position[8].getMarker().equals(marker)) {play = player; break;}
                        if (mark2 == 8 && position[7].isTaken() == 1 && position[7].getMarker().equals(marker)) {play = player; break;}
                    }
                    break;
            }
        h--;
        i++;
        }
        return play;
    }
}
