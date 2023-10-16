module DataAnalyticsHub {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	exports controllers;
	
	opens controllers to javafx.graphics, javafx.fxml, javafx.base;
	opens application to javafx.graphics, javafx.fxml, javafx.base;

}
