package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.dao.DuplicateKeyException;
import com.example.kursverwaltung.dao.KursDAO;
import com.example.kursverwaltung.dao.KursDAOImpl;
import com.example.kursverwaltung.db.DBConnectionException;
import com.example.kursverwaltung.model.Kurs;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;

public class HelloController {

    // ---------- Member ---------------------
    private KursDAO dao;// new KursDAOImpl();


    // --------- FX ----------------------------------

    @FXML
    private TableView<Kurs> kursTable;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<Kurs, LocalDate> beginnCol;
    @FXML
    private TableColumn<Kurs, String> dozentCol;
    @FXML
    private TableColumn<?, ?> wochenCol;


///// ----------  Save -----------------------------

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker startdateField;

    @FXML
    private TextField wochenField;
    @FXML
    private TextField dozentField;

    @FXML
    void onSave(ActionEvent event) {
        System.out.println("save");
        try {
            Kurs k = new Kurs(nameField.getText(), startdateField.getValue(),dozentField.getText(),Integer.parseInt( wochenField.getText()));
            //TODO Pflichtfelder validieren

            boolean saved= dao.save(k);
            if(saved){
                //TODO refresh TableView
                System.out.println("saved: "+k);
                kursTable.getItems().setAll(dao.findAll());//refresh
            }
        }catch (DuplicateKeyException e){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Fehler");
            al.setContentText("Kursname schon vergeben!");
            al.show();
        }
        catch (Exception e) {
           if(e.getMessage().contains("\"date\" is null")){
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Fehler");
                al.setContentText("Kein Datum gewählt!");
                al.show();
            }else{
              e.printStackTrace();
            }

        }


    }

    @FXML
    void initialize(){
        try {
            dao = new KursDAOImpl();
            //------------TableView config
            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            beginnCol.setCellValueFactory(new PropertyValueFactory<>("courseStart"));
            dozentCol.setCellValueFactory(new PropertyValueFactory<>("teacher"));
            wochenCol.setCellValueFactory(new PropertyValueFactory<>("weeks"));

            kursTable.getItems().setAll(dao.findAll());//refresh
        } catch (DBConnectionException e) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Fehler!");
            al.setContentText(e.getMessage() +"\nBitte Datenbankverbindung herstellen und Neustarten!");
            al.showAndWait();
            Platform.exit();
        }

    }

    private boolean validate(){
        return false;
    }

}
