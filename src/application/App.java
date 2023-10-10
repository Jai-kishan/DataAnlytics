package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

 
public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
  Parent root;
        try {
           root = FXMLLoader.load(getClass().getResource("Signup.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setTitle("Sign Up Page");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
 public static void main(String[] args) {
        launch(args);
    }
}