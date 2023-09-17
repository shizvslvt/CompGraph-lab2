module com.example.compgraphlab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.compgraphlab2 to javafx.fxml;
    exports com.example.compgraphlab2;
}