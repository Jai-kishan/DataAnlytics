module DataAnalyticsHub {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires fontawesomefx;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;

}
