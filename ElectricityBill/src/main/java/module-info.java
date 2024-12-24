module com.example.electricitybill {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.electricitybill to javafx.fxml;
    exports com.example.electricitybill;
}