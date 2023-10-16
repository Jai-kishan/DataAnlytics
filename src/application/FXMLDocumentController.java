package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField login_showPassword;

    @FXML
    private CheckBox login_selectShowPassword;

    @FXML
    private Button login_createAccount;

    @FXML
    private Hyperlink login_forgotPassword;

    @FXML
    private Button close;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private TextField signup_first_name;

    @FXML
    private TextField signup_last_name;

    @FXML
    private TextField signup_email;

    @FXML
    private TextField signup_username;

    @FXML
    private PasswordField signup_password;

    @FXML
    private PasswordField signup_cPassword;

    @FXML
    private Button signup_btn;

    @FXML
    private Button signup_loginAccount;
    @FXML
    private AnchorPane forgot_form;

    @FXML
    private TextField forgot_answer;

    @FXML
    private Button forgot_proceedBtn;

    @FXML
    private Button forgot_backBtn;

    @FXML
    private TextField forgot_username;

    @FXML
    private AnchorPane changePass_form;

    @FXML
    private Button changePass_proceedBtn;

    @FXML
    private Button changePass_backBtn;

    @FXML
    private PasswordField changePass_password;

    @FXML
    private PasswordField changePass_cPassword;

    // DATABASE TOOls
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    // NOW LETS CREATE OUR DATABASE : )

    private double x = 0;
    private double y = 0;

    public void loginAdmin() {
        alertMessage alert = new alertMessage();
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";

        connect = database.connectDb();

        try { // IT WORKS GOOD : ) NOW LETS DESIGN THE DASHBOARD FORM : )
              // Alert alert;

            if (login_selectShowPassword.isSelected()) {
                password.setText(login_showPassword.getText());
            }
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            System.out.println(sql);
            System.out.println("username  :" + username.getText());
            System.out.println("password   :" + password.getText());
            result = prepare.executeQuery();
            // CHECK IF FIELDS ARE EMPTTY
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert.errorMessage("Please fill all blank fields");
           
            } else {
                if (result.next()) {
                    // THEN PROCEED TO DASHBOARD FORM
                    getData.username = result.getString("username");
                    getData.first_name = result.getString("first_name");
                    getData.last_name = result.getString("last_name");
                    getData.password = result.getString("password");
                    getData.email = result.getString("email_id");

                    alert.successMessage("Successfully Login!");


                    // TO HIDE THE LOGIN FORM
                    loginBtn.getScene().getWindow().hide();
                    // LINK YOUR DASHBOARD
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);

                    stage.setScene(scene);
                    stage.show();

                } else {
                    // THEN ERROR MESSAGE WILL APPEAR
                    alert.errorMessage("Wrong Username/Password");
       
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            alert.errorMessage(e.getMessage());
        }

    }

    public void showPassword() {

        if (login_selectShowPassword.isSelected()) {
            login_showPassword.setText(password.getText());
            login_showPassword.setVisible(true);
            password.setVisible(false);
        } else {
            password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            password.setVisible(true);
        }

    }

    public void forgotPassword() {

        alertMessage alert = new alertMessage();

        if (forgot_username.getText().isEmpty() || forgot_answer.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {

            String checkData = "SELECT username, email_id FROM users " + "WHERE username = ? AND email_id = ?";

            connect = database.connectDb();

            try {

                prepare = connect.prepareStatement(checkData);
                prepare.setString(1, forgot_username.getText());
                prepare.setString(2, forgot_answer.getText());

                result = prepare.executeQuery();
                // IF CORRECT
                if (result.next()) {
                    // PROCEED TO CHANGE PASSWORD
                    signup_form.setVisible(false);
                    login_form.setVisible(false);
                    forgot_form.setVisible(false);
                    changePass_form.setVisible(true);
                } else {
                    alert.errorMessage("Incorrect information");
                }

            } catch (Exception e) {
                e.printStackTrace();
                alert.errorMessage(e.getMessage());
            }

        }

    }

    public void register() {

        alertMessage alert = new alertMessage();
        System.out.println(signup_email.getText());
        System.out.println(signup_cPassword.getText());

        String email = signup_email.getText();
        String username = signup_username.getText();
        String firstName = signup_first_name.getText();
        String lastName = signup_last_name.getText();
        String password = signup_password.getText();
        String confirmPassword = signup_cPassword.getText();

        // CHECK IF WE HAVE EMPTY FIELDS
        if (email.isEmpty() || username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()
                || confirmPassword.isEmpty()) {
            alert.errorMessage("All fields are necessary to be filled");

        } else if (!password.equals(confirmPassword)) {
            alert.errorMessage("Password does not match");
        } else if (password.length() < 8) {
            alert.errorMessage("Invalid Password, at least 8 characters needed");
        } else {
            // Check if the username is already taken
            String checkUsernameQuery = "SELECT * FROM users WHERE username = ?";
            try (Connection connection = database.connectDb();
                    PreparedStatement usernameCheckStmt = connection.prepareStatement(checkUsernameQuery)) {

                usernameCheckStmt.setString(1, username);
                ResultSet usernameResult = usernameCheckStmt.executeQuery();

                if (usernameResult.next()) {
                    alert.errorMessage(username + " is already taken");
                } else {
                    String insertData = "INSERT INTO users (first_name, last_name, username, email_id, password, created_date, is_VIP) VALUES (?,?,?,?,?,?,?)";

                    try (PreparedStatement insertStmt = connection.prepareStatement(insertData)) {
                        insertStmt.setString(1, firstName);
                        insertStmt.setString(2, lastName);
                        insertStmt.setString(3, username);
                        insertStmt.setString(4, email);
                        insertStmt.setString(5, password);
                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        insertStmt.setDate(6, sqlDate);
                        insertStmt.setInt(7, 0);

                        int rowsAffected = insertStmt.executeUpdate();

                        if (rowsAffected > 0) {
                            alert.successMessage("Registered Successfully!");
                            registerClearFields();
                            signup_form.setVisible(false);
                            login_form.setVisible(true);
                        } else {
                            alert.errorMessage("Registration failed. Please try again.");
                        }
                    } catch (SQLException e) {
                        alert.errorMessage("Error while inserting data: " + e.getMessage());
                    }
                }
            } catch (SQLException e) {
                alert.errorMessage("Error while checking username availability: " + e.getMessage());
            }
        }
    }

    // TO CLEAR ALL FIELDS OF REGISTRATION FORM
    public void registerClearFields() {
        signup_first_name.setText("");
        signup_last_name.setText("");
        signup_email.setText("");
        signup_username.setText("");
        signup_password.setText("");
        signup_cPassword.setText("");

    }

    public void changePassword() {

        alertMessage alert = new alertMessage();
        // CHECK ALL FIELDS IF EMPTY OR NOT
        if (changePass_password.getText().isEmpty() || changePass_cPassword.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else if (!changePass_password.getText().equals(changePass_cPassword.getText())) {
            // CHECK IF THE PASSWORD AND CONFIRMATION ARE NOT MATCH
            alert.errorMessage("Password does not match");
        } else if (changePass_password.getText().length() < 8) {
            // CHECK IF THE LENGTH OF PASSWORD IS LESS THAN TO 8
            alert.errorMessage("Invalid Password, at least 8 characters needed");
        } else {
            // USERNAME IS OUR REFERENCE TO UPDATE THE DATA OF THE USER
            String updateData = "UPDATE users SET password = ?, update_date = ? " + "WHERE username = '"
                    + forgot_username.getText() + "'";

            connect = database.connectDb();

            try {

                prepare = connect.prepareStatement(updateData);
                prepare.setString(1, changePass_password.getText());

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(2, String.valueOf(sqlDate));

                prepare.executeUpdate();
                alert.successMessage("Succesfully changed Password");

                // LOGIN FORM WILL APPEAR
                signup_form.setVisible(false);
                login_form.setVisible(true);
                forgot_form.setVisible(false);
                changePass_form.setVisible(false);

                username.setText("");
                password.setVisible(true);
                password.setText("");
                login_showPassword.setVisible(false);
                login_selectShowPassword.setSelected(false);

                changePass_password.setText("");
                changePass_cPassword.setText("");

            } catch (Exception e) {
                e.printStackTrace();
                alert.errorMessage(e.getMessage());
            }

        }

    }

    public void switchForm(ActionEvent event) {

        // THE REGISTRATION FORM WILL BE VISIBLE
        if (event.getSource() == signup_loginAccount || event.getSource() == forgot_backBtn) {
            signup_form.setVisible(false);
            login_form.setVisible(true);
            forgot_form.setVisible(false);
            changePass_form.setVisible(false);
        } else if (event.getSource() == login_createAccount) { // THE LOGIN FORM WILL BE VISIBLE
            signup_form.setVisible(true);
            login_form.setVisible(false);
            forgot_form.setVisible(false);
            changePass_form.setVisible(false);
        } else if (event.getSource() == login_forgotPassword) {
            signup_form.setVisible(false);
            login_form.setVisible(false);
            forgot_form.setVisible(true);
            changePass_form.setVisible(false);
        } else if (event.getSource() == changePass_backBtn) {
            signup_form.setVisible(false);
            login_form.setVisible(false);
            forgot_form.setVisible(true);
            changePass_form.setVisible(false);
        }

    }

    public void close() {
        System.exit(0);
    }

    // LETS NAME THE COMPONENTS ON LOGIN FORM : )

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
