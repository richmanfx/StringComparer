package ru.r5am.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

/**
 * Created by Zoer on 22.01.2017.
 *
 */

public class StringComparerController {

    // Привязка переменных к компонентам в StringComparer.fxml
    @FXML private Button compareButton;
    @FXML private TextField firstTextField;
    @FXML private TextField secondTextField;


    /**
     *  Обработка нажатий мышкой на Buttons (клавиатура отдельно обрабатывается!)
     */
    public void mouseButtonProcessing(ActionEvent mouseActionEvent) {

        Object source = mouseActionEvent.getSource();        // Определить источник события (нажатия)

        if (!(source instanceof Button))        // Если источник события не кнопка, то ничего не делать и выйти
            return;

        Button clickedButton = (Button) source;     // Нисходящее приведение (Object приводим к Button)

        switch (clickedButton.getId()) {            // По ID определяем конкретную кнопку
            case "compareButton":
                // Сравниваем строки
                System.out.println("Нажата кнопка Сравнить!");
                actionCompare();
                break;

//            case "buttonCancel":
//                 Выход без сохранения макросов
//                MainController.actionClose(actionEvent);
//                break;
        }
    }

    private void actionCompare() {
        String firstString = firstTextField.getText();
        String secondString = secondTextField.getText();
        System.out.println(String.format("1: %s\n2: %s\n", firstString, secondString));
    }


    /**
     * Обработка кнопок клавиатуры (нажатия мышкой обрабатываются отдельно!!!)
     */
    @FXML
    private void KeyButtonProcessing(KeyEvent keyEvent) {
        System.out.println("Pressed key: " + keyEvent.getCode());
        switch (keyEvent.getCode()) {

            case ESCAPE:
                actionWindowClose(keyEvent);             // По ESCAPE закрыть окно
                break;
        }
    }

    /**
     * Закрывает окно.
     */
    private static void actionWindowClose(InputEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
