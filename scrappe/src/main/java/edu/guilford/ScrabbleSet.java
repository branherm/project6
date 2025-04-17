package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

public class ScrabbleSet {
    // Attributes
    private ArrayList<Tile> tiles;  // List of Tile objects for letters and blank tile
    private ArrayList<Integer> letterCounts;  // List of counts for each letter

    // Constants for standard English Scrabble
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";  // Includes blank tile
    private static final int[] POINT_VALUES = {
            1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 
            1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10, 0 // Last value for blank tile
    };
    private static final int[] STANDARD_COUNTS = {
            9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 
            6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1, 2 // Last value for blank tile
    };

    // Constructor for a standard English Scrabble set
    public ScrabbleSet(String language) {
        if (!language.equalsIgnoreCase("English")) {
            throw new IllegalArgumentException("Only English Scrabble sets are supported.");
        }

        tiles = new ArrayList<>();
        letterCounts = new ArrayList<>();

        // Populate tiles and letter counts
        for (int i = 0; i < LETTERS.length(); i++) {
            char letter = LETTERS.charAt(i);
            int count = STANDARD_COUNTS[i];
            int pointValue = POINT_VALUES[i];

            letterCounts.add(count);
            for (int j = 0; j < count; j++) {
                tiles.add(new Tile(letter, pointValue));
            }
        }
    }

    // Constructor for a random Scrabble set
    public ScrabbleSet() {
        tiles = new ArrayList<>();
        letterCounts = new ArrayList<>();
        Random random = new Random();

        int remainingTiles = 100;

        // Initialize counts to at least 1 for each letter
        for (int i = 0; i < LETTERS.length(); i++) {
            letterCounts.add(1);
            remainingTiles--;
            tiles.add(new Tile(LETTERS.charAt(i), POINT_VALUES[i]));
        }

        // Randomly distribute the remaining tiles
        while (remainingTiles > 0) {
            int index = random.nextInt(LETTERS.length());
            letterCounts.set(index, letterCounts.get(index) + 1);
            tiles.add(new Tile(LETTERS.charAt(index), POINT_VALUES[index]));
            remainingTiles--;
        }
    }

    // Method to calculate the score of a word
    public int getWordScore(String word) {
        if (!word.matches("[a-zA-Z]+")) {
            return -1;  // Invalid word: contains non-letter characters
        }

        word = word.toUpperCase();  // Case-insensitive
        int[] tempCounts = new int[letterCounts.size()];
        for (int i = 0; i < letterCounts.size(); i++) {
            tempCounts[i] = letterCounts.get(i);
        }

        int score = 0;
        for (char c : word.toCharArray()) {
            int index = LETTERS.indexOf(c);
            if (index == -1 || tempCounts[index] <= 0) {
                return -1;  // Invalid word: uses unavailable letter
            }
            score += POINT_VALUES[index];
            tempCounts[index]--;  // Deduct letter count
        }

        return score;
    }

    // toString method for well-formatted representation of the Scrabble set
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Scrabble Set:\n");
        for (int i = 0; i < LETTERS.length(); i++) {
            sb.append(LETTERS.charAt(i))
              .append(": ")
              .append(letterCounts.get(i))
              .append(" tiles, ")
              .append(POINT_VALUES[i])
              .append(" points\n");
        }
        return sb.toString();
    }
}
