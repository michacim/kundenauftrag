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
            alert(e,"Kursname schon vergeben!").show();
        }
        catch (Exception e) {
           if(e.getMessage().contains("\"date\" is null")){
                alert(e,"Kein Datum gewählt!").show();
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
            setupTableView();



        } catch (DBConnectionException e) {
            alert(e,"\nBitte Datenbankverbindung herstellen und Neustarten!").showAndWait();
            Platform.exit();
        }
    }

    private static Alert alert(Exception e, String msg) {
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Fehler!");
        al.setContentText(e.getMessage() +msg);
        return al;
    }

    private void setupTableView() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        beginnCol.setCellValueFactory(new PropertyValueFactory<>("courseStart"));
        dozentCol.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        wochenCol.setCellValueFactory(new PropertyValueFactory<>("weeks"));

        kursTable.getItems().setAll(dao.findAll());//refresh

        setupContextMenu();

    }

    private void setupContextMenu() {
        var cm = new ContextMenu();
        var deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(e -> {
           var k = kursTable.getSelectionModel().getSelectedItem();
           var deleted =  dao.deleteById(k.getId());
           if(deleted){
               kursTable.getItems().setAll(dao.findAll());//refresh
           }
        } );

        cm.getItems().add(deleteItem);
        kursTable.setContextMenu(cm);

    }

    private boolean validate(){
        return false;
    }

}
