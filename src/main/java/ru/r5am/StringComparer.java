package ru.r5am;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;

public class StringComparer extends Application {

    // Иконка
    private String programIcon = ".." + File.separator + ".." + File.separator +
                                 "images" + File.separator + "iconyinyang.png";

    private String fxmlMainForm = ".." + File.separator + ".." + File.separator +
                                  "fxml" + File.separator + "StringComparer.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root;
        InputStream mainFxmlStream = getClass().getResourceAsStream(fxmlMainForm);

        if (mainFxmlStream != null) {
            FXMLLoader loader = new FXMLLoader();
            root = loader.load(mainFxmlStream);
        } else {
            System.err.println("Couldn't find file: " + fxmlMainForm);
            System.exit(1);
            return;
        }

        // Максимальные и минимальные размеры главного окна
        int maximumWindowHeight = 370;       // Pixels
        int maximumWindowWidth = 1800;
        int minimumWindowHeight = 370;
        int minimumWindowWidth = 800;

        // Установка заголовка окна
        String programTitle = "String Comparer";
        primaryStage.setTitle(programTitle);

        // Установка размеров главной формы
        primaryStage.setMinWidth(minimumWindowWidth);
        primaryStage.setMinHeight(minimumWindowHeight);
        primaryStage.setMaxWidth(maximumWindowWidth);
        primaryStage.setMaxHeight(maximumWindowHeight);

        // Установка иконки приложения
        try {
            Image icon = new Image(getClass().getResource(programIcon).toExternalForm());
            primaryStage.getIcons().add(icon);
        } catch (NullPointerException ex) {
            System.out.println(String.format("Couldn't find icons file: %s", programIcon));
        }


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
