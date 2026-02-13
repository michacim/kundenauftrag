package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.model.User;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class LoginDialog  extends Dialog<User> {

    public LoginDialog(){
        setTitle("Login");

        setResizable(true);

        Label label1 = new Label("Username: ");
        Label label2 = new Label("Password: ");
        TextField text1 = new TextField();
        PasswordField text2 = new PasswordField();

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(text2, 2, 2);
        getDialogPane().setContent(grid);

        ButtonType buttonTypeOk = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(buttonTypeOk);

        setResultConverter(b -> {

            if (b == buttonTypeOk) {

                return new User(text1.getText(), text2.getText());
            }

            return null;
        });
    }
}
