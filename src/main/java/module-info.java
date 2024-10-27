module es.aketzagonzalez {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens es.aketzagonzalez.aeropuertosQ to javafx.fxml;
    exports es.aketzagonzalez.aeropuertosQ;
}