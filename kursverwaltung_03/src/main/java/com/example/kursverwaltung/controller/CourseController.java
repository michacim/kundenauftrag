package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.dao.DuplicateKeyException;
import com.example.kursverwaltung.dao.CourseDAO;
import com.example.kursverwaltung.dao.CourseDAOImpl;
import com.example.kursverwaltung.db.DBConnectionException;
import com.example.kursverwaltung.model.Course;
import com.example.kursverwaltung.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.time.LocalDate;
import java.util.Optional;

public class CourseController {

    private CourseDAO dao;// new CourseDAOImpl();


    // --------- FX ----------------------------------

    @FXML
    private Label modeLabel;
    @FXML
    public TabPane tabPane;
    @FXML
    private TextField searchField;
    // ---------- Member ---------------------
    @FXML
    private TableView<Course> kursTable;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<Course, LocalDate> beginnCol;
    @FXML
    private TableColumn<Course, String> dozentCol;
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

    private boolean updateMode;

    @FXML
    void onSave(ActionEvent event) {
        System.out.println("save");
        boolean saved = false;
        try {
            Course course = new Course(nameField.getText(), startdateField.getValue(),dozentField.getText(),Integer.parseInt( wochenField.getText()));
            //TODO Pflichtfelder validieren
            if(updateMode){
                course.setId(kursTable.getSelectionModel().getSelectedItem().getId());
                saved=  dao.update(course);
                updateMode=false;
                modeLabel.setText("Save");//FIXME
            }else{
                saved= dao.save(course);

            }

            if(saved){

                System.out.println("saved: "+course);
                kursTable.getItems().setAll(dao.findAll());//refresh
                resetSaveFields();
                tabPane.getSelectionModel().select(0);// go to TableView-Tab

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

    private void resetSaveFields() {
        dozentField.clear();
        nameField.clear();
        wochenField.setText("1");
        startdateField.setValue(null);
    }

    @FXML
    void initialize(){
        LoginDialog login = new LoginDialog();

       Optional<User> opt=  login.showAndWait();
       User loginUser = opt.get();
       //FIXME if username und password falsch dann return
        // return
        modeLabel.setText("Save");
        try {
            dao = new CourseDAOImpl();
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

        var updateItem = new MenuItem("Update");
        updateItem.setOnAction(event -> {
            System.out.println("update");
            updateMode =true;
            modeLabel.setText("Update"); // FIXME dynamisch
            //TODO make updateMode visible
            Course updateKurs = kursTable.getSelectionModel().getSelectedItem();
            nameField.setText(updateKurs.getName());
            dozentField.setText(updateKurs.getTeacher());
            wochenField.setText(updateKurs.getWeeks()+"");
            startdateField.setValue(updateKurs.getCourseStart());
            tabPane.getSelectionModel().select(1);
        });

        cm.getItems().addAll(deleteItem,updateItem);
        kursTable.setContextMenu(cm);

    }

    //TODO impl
    private boolean validate(){
        return false;
    }

    @FXML
    void onSearch(KeyEvent keyEvent) {
        kursTable.getItems().setAll(dao.findByKursname(searchField.getText()));//
    }


}
