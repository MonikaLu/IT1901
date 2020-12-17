package fxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  public static void main(final String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader fxmlLoader =
        new FXMLLoader(getClass().getResource("FxApp.fxml"));
    final Parent root = fxmlLoader.load();
    final Scene scene = new Scene(root);
    stage.setTitle("Moodtracker");
    stage.setScene(scene);
    stage.show();
  }
}