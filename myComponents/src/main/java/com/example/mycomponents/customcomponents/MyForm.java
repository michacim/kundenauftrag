package com.example.mycomponents.customcomponents;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MyForm extends AnchorPane {

    public MyForm(){
        getChildren().add(new TextField());
    }
}
