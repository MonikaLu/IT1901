module fxui {
  requires javafx.fxml;
  requires transitive javafx.graphics;
  requires java.net.http;
  requires javafx.controls;
  requires transitive moodtracker.core;

  exports fxui;

  opens fxui to javafx.fxml;
}
