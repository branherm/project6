package edu.guilford;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScrabbleFXApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main window FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Scrabble Score Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Load the analysis window
        Stage analysisStage = new Stage();
        FXMLLoader analysisLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent analysisRoot = analysisLoader.load();
        Scene analysisScene = new Scene(analysisRoot, 600, 400);
        analysisStage.setTitle("Text Analysis");
        analysisStage.setScene(analysisScene);
        analysisStage.show();

        // Link the controllers
        MainWindowController mainController = loader.getController();
        AnalysisWindowController analysisController = analysisLoader.getController();
        mainController.setAnalysisController(analysisController);
    }

    public static void main(String[] args) {
        launch(args);
    }
}