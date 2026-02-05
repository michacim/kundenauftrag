package com.example.hello_fx;

import com.example.hello_fx.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {

    static int count =0;
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
        User u = new User(++count, usernameField.getText() );

        tableView.getItems().add(u);

    }

    @FXML
    void initialize(){
        tableView.getItems().add(new User(++count,"max"));
        tableView.getItems().add(new User(++count,"ina"));

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        /////////// Contextmenu /////////////////////////
        ContextMenu cm = new ContextMenu();
        MenuItem deleteItem =new MenuItem("Delete");

        deleteItem.setOnAction(event -> {
            System.out.println("delete");
            User deletedUser = tableView.getSelectionModel().getSelectedItem();
            tableView.getItems().remove(deletedUser);

            System.out.println(tableView.getItems());
        });
        cm.getItems().add(deleteItem);
        tableView.setContextMenu(cm);
    }

}
