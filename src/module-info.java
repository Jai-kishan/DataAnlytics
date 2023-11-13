module DataAnalyticsHub {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires org.junit.jupiter.api;
	requires junit;
//	requires jdk.incubator.vector;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;

}
