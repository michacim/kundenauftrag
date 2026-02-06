package com.example.user_fx.controller;

import com.example.user_fx.dao.DBUserDAO;
import com.example.user_fx.dao.UserDAO;
import com.example.user_fx.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private UserDAO dao = new DBUserDAO();

    //static int count =0;
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
        User u = new User( usernameField.getText() );
        dao.save(u);
        tableView.getItems().setAll(dao.findAll());// refresh TableView
    }

    @FXML
    void initialize(){
        tableView.getItems().setAll(dao.findAll());// refresh
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        createContextMenu();
    }

    private void createContextMenu() {
        /////////// Contextmenu /////////////////////////
        ContextMenu cm = new ContextMenu();
        MenuItem deleteItem =new MenuItem("Delete");

        deleteItem.setOnAction(event -> {
            System.out.println("delete");
            User deletedUser = tableView.getSelectionModel().getSelectedItem();
            System.out.println(deletedUser);
            boolean deleted= dao.delete(deletedUser.getId());
            if(deleted) {
                tableView.getItems().setAll(dao.findAll());// refresh TableView
            }
//            tableView.getItems().remove(deletedUser);
//            System.out.println(tableView.getItems());
        });
        cm.getItems().add(deleteItem);
        tableView.setContextMenu(cm);
    }

}
