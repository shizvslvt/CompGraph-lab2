package com.example.compgraphlab2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Screen secondScreen = Screen.getScreens().get(1);

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root, 1600, 900);
        primaryStage.setTitle("бим бум бим бам");
        Rectangle2D visualBounds = secondScreen.getVisualBounds();
        double centerX = visualBounds.getMinX() + (visualBounds.getWidth() - scene.getWidth()) / 2;
        double centerY = visualBounds.getMinY() + (visualBounds.getHeight() - scene.getHeight()) / 2;
        primaryStage.setX(centerX);
        primaryStage.setY(centerY);
        primaryStage.setWidth(scene.getWidth());
        primaryStage.setHeight(scene.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
