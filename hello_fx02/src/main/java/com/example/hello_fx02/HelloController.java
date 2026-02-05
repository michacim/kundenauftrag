package com.example.hello_fx02;

import com.example.hello_fx02.model.User;
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
        System.out.println("init...");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));//getId()
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));//getUsername()

        tableView.getItems().add(new User(1,"Max"));
        tableView.getItems().add(new User(2,"Otto"));
    }

}
