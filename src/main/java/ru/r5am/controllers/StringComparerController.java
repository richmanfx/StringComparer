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
//                System.out.println(String.format("Нажата кнопка '%s'.", compareButton.getText()));
                actionCompare();
                break;

            case "clearButton":
                // Очистить текстовые поля ввода
//                System.out.println(String.format("Нажата кнопка '%s'.", clearButton.getText()));
                cleanTextFields();
                break;
        }
    }

    /**
     * Очищает текстовые поля и строку вывода результата
     */
    private void cleanTextFields() {
        firstTextField.clear();
        secondTextField.clear();
        resultLabel.setText("");
    }

    /**
     * Сравнивает строки, результат выводит на форму.
     */
    private void actionCompare() {
        String firstString = firstTextField.getText();
        String secondString = secondTextField.getText();
        if(firstString.equals(secondString)) {
            resultLabel.setText("Строки одинаковы");
        }
        else {
            // Сравнение размера строк
            if (firstString.length() != secondString.length()) {
                resultLabel.setText("У строк разная длина");
            } else {    // Поиск несовпадающих символов
                String result = getDifferencePositions(firstString, secondString);
                resultLabel.setText(String.format("Отличаются символы: %s", result));
            }
        }
    }

    /**
     * Выдаёт строку с позициями различающихся символов.
     * @param firstString Первыя строка.
     * @param secondString Вторая строка.
     * @return Строка с позициями различающихся символов.
     */
    private String getDifferencePositions(String firstString, String secondString) {
        int len1 = firstString.length();
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
        return result;
    }


    /**
     * Обработка кнопок клавиатуры (нажатия мышкой обрабатываются отдельно!!!)
     */
    @FXML
    private void KeyButtonProcessing(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {

            case ESCAPE:
                actionWindowClose(keyEvent);            // По ESCAPE закрыть окно
                break;

            case ENTER:
                actionCompare();                        // Сравнить строки
                break;

            case DELETE:
                cleanTextFields();                      // Очистить текстовые поля
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
