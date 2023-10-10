package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class SignupControler {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;
    
    @FXML
    private TextField userNameField;

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private PasswordField confirmPasswordField;
    
    @FXML
    private Button SignUpBtn;

    @FXML
    public void signUpBtnClicked(ActionEvent event) throws SQLException{

        
        Window owner = SignUpBtn.getScene().getWindow();

        System.out.println(firstNameField.getText());
        System.out.println(lastNameField.getText());
        System.out.println(emailIdField.getText());



        if (firstNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your first name");
            return;
        }     

        if (lastNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your last name");
            return;
        }   

        if (userNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your  username");
            return;
        }   

        if (emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email");
            return;
        }   

        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your password");
            return;
        }   

        if (confirmPasswordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your confirm password");
            return;
        }   

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = userNameField.getText();
        String email = emailIdField.getText();
        
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertRecord(firstName, lastName, username, email, password, confirmPassword);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
            "Welcome " + firstName + lastName);

    }

    private void showAlert(Alert.AlertType alertType, Window owner,  String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
