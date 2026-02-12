module com.example.mycomponents {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mycomponents to javafx.fxml;
    exports com.example.mycomponents;
}