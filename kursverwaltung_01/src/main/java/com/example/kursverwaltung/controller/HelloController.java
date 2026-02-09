package com.example.kursverwaltung.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TableColumn<?, ?> beginnCol;

    @FXML
    private TableColumn<?, ?> dozentCol;

    @FXML
    private TextField dozentField;

    @FXML
    private TableView<?> kursTable;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker startdateField;

    @FXML
    private TableColumn<?, ?> wochenCol;

    @FXML
    private TextField wochenField;

    @FXML
    void onSave(ActionEvent event) {

    }

}
