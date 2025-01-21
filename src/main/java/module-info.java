module com.cftp.calgarytrackerdemo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.cftp.calgaryflamestrackerproject to javafx.fxml;
    exports com.cftp.calgaryflamestrackerproject;
}