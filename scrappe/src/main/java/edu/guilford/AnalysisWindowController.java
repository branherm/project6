package edu.guilford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AnalysisWindowController {

    @FXML
    private TextArea standardSetText;
    @FXML
    private TextArea randomSetText;
    @FXML
    private TextArea analysisText;

    public void displayScrabbleSets(ScrabbleSet standardSet, ScrabbleSet randomSet) {
        standardSetText.setText(standardSet.toString());
        randomSetText.setText(randomSet.toString());
    }

    public void analyzeTextFile() {
        // Create standard and random Scrabble sets
        ScrabbleSet standardSet = new ScrabbleSet("English");
        ScrabbleSet randomSet = new ScrabbleSet();

        StringBuilder analysisResult = new StringBuilder();

        try {
            ArrayList<String> words = new ArrayList<>();
            // Load the file from resources
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getClass().getResourceAsStream("/frankenstein.txt")));
            String line;

            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Split each line into words and add to list
                String[] lineWords = line.split("\\s+");
                for (String word : lineWords) {
                    // Remove any non-letter characters from the word
                    String cleanedWord = word.replaceAll("[^a-zA-Z]", "").toUpperCase();
                    if (!cleanedWord.isEmpty()) {
                        words.add(cleanedWord);
                    }
                }
            }
            reader.close();

            // Initialize analysis variables
            String highestStandardWord = "";
            int highestStandardScore = 0;
            String highestRandomWord = "";
            int highestRandomScore = 0;
            int totalStandardScore = 0;
            int totalRandomScore = 0;
            int wordCount = 0;

            // Analyze each word
            for (String word : words) {
                int standardScore = standardSet.getWordScore(word);
                int randomScore = randomSet.getWordScore(word);

                // Track highest scoring words
                if (standardScore > highestStandardScore) {
                    highestStandardScore = standardScore;
                    highestStandardWord = word;
                }

                if (randomScore > highestRandomScore) {
                    highestRandomScore = randomScore;
                    highestRandomWord = word;
                }

                // Accumulate total scores
                if (standardScore > 0) {
                    totalStandardScore += standardScore;
                }
                if (randomScore > 0) {
                    totalRandomScore += randomScore;
                }

                wordCount++;
            }

            // Calculate average scores
            double avgStandardScore = (double) totalStandardScore / wordCount;
            double avgRandomScore = (double) totalRandomScore / wordCount;

            // Build the analysis result string
            analysisResult.append("Text Analysis Results (frankenstein.txt):\n\n");
            analysisResult.append("Total words analyzed: ").append(wordCount).append("\n\n");

            analysisResult.append("Standard Scrabble Set Analysis:\n");
            analysisResult.append("Highest scoring word: ").append(highestStandardWord)
                    .append(" (").append(highestStandardScore).append(" points)\n");
            analysisResult.append("Average word score: ").append(String.format("%.2f", avgStandardScore))
                    .append("\n\n");

            analysisResult.append("Random Scrabble Set Analysis:\n");
            analysisResult.append("Highest scoring word: ").append(highestRandomWord)
                    .append(" (").append(highestRandomScore).append(" points)\n");
            analysisResult.append("Average word score: ").append(String.format("%.2f", avgRandomScore)).append("\n");

        } catch (IOException e) {
            analysisResult.append("Error reading frankenstein.txt: ").append(e.getMessage());
        } catch (Exception e) {
            analysisResult.append("An error occurred during analysis: ").append(e.getMessage());
        }

        // Display the results in the analysisText TextArea
        analysisText.setText(analysisResult.toString());

        // Also display the scrabble sets for reference
        displayScrabbleSets(standardSet, randomSet);
    }
}


