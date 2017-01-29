package ru.r5am.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML private Button clearButton;
    @FXML private TextField firstTextField;
    @FXML private TextField secondTextField;
    @FXML private Label resultLabel;


    /**
     *  Обработка нажатий мышкой на Buttons (клавиатура отдельно обрабатывается!)
     */
    public void mouseButtonProcessing(ActionEvent mouseActionEvent) {
        Object source = mouseActionEvent.getSource();    // Определить источник события (нажатия)
        if (!(source instanceof Button)) return;         // Если источник события не кнопка, то ничего не делать и выйти
        Button clickedButton = (Button) source;          // Нисходящее приведение (Object приводим к Button)

        switch (clickedButton.getId()) {            // По ID определяем конкретную кнопку
            case "compareButton":
                // Сравниваем строки
                System.out.println(String.format("Нажата кнопка '%s'.", compareButton.getText()));
                actionCompare();
                break;

            case "clearButton":
                // Очистить текстовые поля ввода
                cleanTextFields();
                break;
        }
    }

    private void cleanTextFields() {
        firstTextField.clear();
        secondTextField.clear();
    }

    private void actionCompare() {
        String firstString = firstTextField.getText();
        String secondString = secondTextField.getText();
        System.out.println(String.format("1: %s\n2: %s\n", firstString, secondString));
        if(firstString.equals(secondString)) {
            System.out.println("Строки одинаковы.");
            resultLabel.setText("Строки одинаковы");
        }
        else {
            System.out.print("Строки различаются: ");
            // Поиск несовпадающих символов
            int len1 = firstString.length();
            int len2 = secondString.length();
            if (len1 != len2) {
                System.out.println("cтроки разной длины.");
            } else {
                String result = "";                 // Позиции различающихся символов
                int differencesCounter = 0;         // Счётчик количества различий в строках
                for (int i = 0; i < len1; i++) {
                    if (firstString.charAt(i) != secondString.charAt(i)) {
                        differencesCounter++;
                        if (differencesCounter == 1) {  // Первое различие
                            result += String.format("%d", i + 1);       // Для пользователя нумерация символов с '1'
                        } else {
                            result += String.format(", %d", i + 1);
                        }
                    }
                }
                System.out.println(String.format("различаются символы %s.", result));
            }
        }
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
