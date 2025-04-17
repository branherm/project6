package edu.guilford;

public class Tile {
    // Attributes with appropriate visibility (private)
    private char letter;  // Single character for the tile
    private int pointValue;  // Point value for the tile

    // Constructor
    public Tile(char letter, int pointValue) {
        this.letter = letter;
        this.pointValue = pointValue;
    }

    // Getter for letter
    public char getLetter() {
        return letter;
    }

    // Setter for letter
    public void setLetter(char letter) {
        this.letter = letter;
    }

    // Getter for pointValue
    public int getPointValue() {
        return pointValue;
    }

    // Setter for pointValue
    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    // toString method for String representation of a Tile object
    @Override
    public String toString() {
        return "Tile{" +
                "letter=" + letter +
                ", pointValue=" + pointValue +
                '}';
    }
}