package com.example.kursverwaltung;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setAlwaysOnTop(true);
        FXMLLoader fxmlLoader = new FXMLLoader(CourseApplication.class.getResource("course-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
