package com.example.hello_fx;

import com.example.hello_fx.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    @FXML
    private TableView<User> tableView;


    @FXML
    private TableColumn<User, Integer> idCol;

    @FXML
    private TableColumn<User, String> userCol;

    @FXML
    private TextField usernameField;

    @FXML
    void onSave(ActionEvent event) {
        System.out.println("save");

    }

    @FXML
    void initialize(){
        tableView.getItems().add(new User(1,"max"));
        tableView.getItems().add(new User(2,"ina"));

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

}
