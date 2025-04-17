package edu.guilford;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainWindowController {

    @FXML
    private TextField wordField;
    @FXML
    private Label standardScoreLabel;
    @FXML
    private Label randomScoreLabel;

    private ScrabbleSet standardSet = new ScrabbleSet("English");
    private ScrabbleSet randomSet = new ScrabbleSet();
    private AnalysisWindowController analysisController;

    public void setAnalysisController(AnalysisWindowController analysisController) {
        this.analysisController = analysisController;
    }

    @FXML
    private void handleCalculateScore() {
        String word = wordField.getText();
        int standardScore = standardSet.getWordScore(word);
        int randomScore = randomSet.getWordScore(word);

        if (standardScore == -1) {
            standardScoreLabel.setText("Invalid word");
        } else {
            standardScoreLabel.setText(String.valueOf(standardScore));
        }

        if (randomScore == -1) {
            randomScoreLabel.setText("Invalid word");
        } else {
            randomScoreLabel.setText(String.valueOf(randomScore));
        }
    }

    @FXML
    private void handleViewSets() {
        analysisController.displayScrabbleSets(standardSet, randomSet);
    }
}