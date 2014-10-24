// Positions.java
// David Barkman
// david.barkman@cox.net
// Feb 20, 2009
// Class - CIS163AA
// Section - 27602

// positions class used for keeping track of the nine positions on the board
public class Positions {

    private int number;
    private String marker;
    private int taken = 0;

    // constructor used to create the nine positions on the board
    // fields are number (0-8) and taken (if the position has been played)
    // taken is set to 0 to show not taken
    public Positions(int number, int taken) {
        this.number = number;
        this.taken = taken;
    }

    // get the number on the board of the position
    public int getNumber() {
        return number;
    }

    // set X or O on the position based on the play
    public void setMarker(String marker) {
        this.marker = marker;
        taken = 1;
    }

    // get which marker was set on the position
    public String getMarker() {
        return marker;
    }

    // find out if the position has been played yet
    public int isTaken() {
        return taken;
    }
}
