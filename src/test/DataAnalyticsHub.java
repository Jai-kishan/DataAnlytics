//package test;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import application.alertMessage;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.ButtonType;
//
////@ExtendWith(MockitoExtension.class)
//public class DataAnalyticsHub {
//
//    @Mock
//    private alertMessage alert;
//
//    @Test
//    public void testAskUserToUpgradeWithOK() {
//        // Mock the alert to return OK when opconfirmationMessage is called
//        Alert mockAlert = new Alert(AlertType.CONFIRMATION);
//        ButtonType okButton = new ButtonType("OK");
//        Mockito.when(alert.opconfirmationMessage(Mockito.anyString())).thenReturn(Optional.of(okButton));
//
//        dashboardController yourClass = new dashboardController(alert);
//
//        boolean result = yourClass.askUserToUpgrade();
//
//        assertTrue(result);
//
//        // Verify that the success message is displayed
//        Mockito.verify(alert).successMessage(
//            "Successfully Subscribed!\nPlease log out and log in again to access VIP functionalities"
//        );
//    }
//
//    @Test
//    public void testAskUserToUpgradeWithCancel() {
//        // Mock the alert to return Cancel when opconfirmationMessage is called
//        Alert mockAlert = new Alert(AlertType.CONFIRMATION);
//        ButtonType cancelButton = new ButtonType("Cancel");
//        Mockito.when(alert.opconfirmationMessage(Mockito.anyString())).thenReturn(Optional.of(cancelButton));
//
//        dashboardController yourClass = new dashboardController(alert);
//
//        boolean result = yourClass.askUserToUpgrade();
//
//        assertFalse(result);
//
//        // No success message should be displayed
//        Mockito.verify(alert, Mockito.never()).successMessage(Mockito.anyString());
//    }
//}
//
//
//
