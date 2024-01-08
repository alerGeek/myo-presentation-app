package pl.pollub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyoPresentationApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MyoApp Prezentacja");
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml-view/main-view.fxml"));
        primaryStage.setScene(new Scene(root));
    }
}