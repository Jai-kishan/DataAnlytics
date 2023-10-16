// package application;

// import java.net.URL;

// import java.io.File;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.BufferedReader;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.Statement;
// import java.sql.SQLException;

// import java.time.LocalDate;
// import java.time.LocalDateTime;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Date;
// import java.util.List;
// import java.util.Optional;
// import java.util.ResourceBundle;

// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.collections.transformation.FilteredList;
// import javafx.collections.transformation.SortedList;

// import javafx.event.ActionEvent;

// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.fxml.Initializable;

// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.TableView;
// import javafx.scene.chart.AreaChart;
// import javafx.scene.chart.BarChart;
// import javafx.scene.control.PasswordField;
// import javafx.scene.chart.PieChart;
// import javafx.scene.chart.LineChart;
// import javafx.scene.chart.XYChart;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Alert.AlertType;
// import javafx.scene.control.Button;
// import javafx.scene.control.ButtonType;
// import javafx.scene.control.ComboBox;
// import javafx.scene.control.DatePicker;
// import javafx.scene.control.Label;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TextField;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.input.MouseEvent;
// import javafx.scene.layout.AnchorPane;
// import javafx.scene.layout.VBox;

// import javafx.stage.Stage;
// import javafx.stage.FileChooser;
// import javafx.stage.FileChooser.ExtensionFilter;
// import javafx.stage.StageStyle;

// public class dashboardController implements Initializable {

//     @FXML
//     private AnchorPane main_form;

//     @FXML
//     private Button postsData_importBtn;

//     @FXML
//     private Button close;

//     @FXML
//     private Button minimize;

//     @FXML
//     private Label username;

//     @FXML
//     private Button home_btn;

//     @FXML
//     private Button addStudents_btn;

//     @FXML
//     private Button logout;

//     @FXML
//     private AnchorPane home_form;

//     @FXML
//     private Label home_totalEnrolled;

//     @FXML
//     private Label home_totalFemale;

//     @FXML
//     private Label home_totalMale;

//     @FXML
//     private PieChart home_totalEnrolledChart;

//     @FXML
//     private TableView<studentData> home_totalFemaleChart;

//     @FXML
//     private TableView<studentData> home_totalMaleChart;

//     @FXML
//     private AnchorPane addStudents_form;

//     @FXML
//     private TextField addStudents_search;

//     @FXML
//     private TableView<studentData> addStudents_tableView;

//     @FXML
//     private TableColumn<studentData, String> addStudents_col_postId;

//     @FXML
//     private TableColumn<studentData, String> addStudents_col_likes;

//     @FXML
//     private TableColumn<studentData, String> addStudents_col_firstName;

//     @FXML
//     private TableColumn<studentData, String> addStudents_col_author;

//     @FXML
//     private TableColumn<studentData, String> addStudents_col_content;

//     @FXML
//     private TableColumn<studentData, String> addStudents_col_pub_date;

//     @FXML
//     private TableColumn<studentData, String> addStudents_col_share;

//     @FXML
//     private TextField addStudents_studentNum;

//     @FXML
//     private TextField addStudents_likes;

//     @FXML
//     private TextField addStudents_firstName;

//     @FXML
//     private TextField addStudents_author;

//     @FXML
//     private DatePicker addStudents_pub_date;

//     @FXML
//     private TextField addStudents_share;

//     @FXML
//     private TextField addStudents_content;

//     @FXML
//     private ImageView addStudents_imageView;

//     @FXML
//     private Button addStudents_insertBtn;

//     @FXML
//     private Button addStudents_addBtn;

//     @FXML
//     private Button addStudents_updateBtn;

//     @FXML
//     private Button addStudents_deleteBtn;

//     @FXML
//     private Button addStudents_clearBtn;

//     @FXML
//     private Button userProfile_btn;

//     @FXML
//     private Button postData_exportBtn;

//     @FXML
//     private AnchorPane userProfile_form;

//     @FXML
//     private TextField userProfile_first_name;

//     @FXML
//     private TextField userProfile_last_name;

//     @FXML
//     private TextField userProfile_username;

//     @FXML
//     private PasswordField userProfile_password;

//     @FXML
//     private PasswordField userProfile_cPassword;

//     @FXML
//     private Button userProfile_updateBtn;

//     @FXML
//     private TableView<studentData> topNLikes_tableView;

//     @FXML
//     private TableColumn<studentData, String> topNLikes_col_postId;

//     @FXML
//     private TableColumn<studentData, String> topNLikes_col_content;

//     @FXML
//     private TableColumn<studentData, String> topNLikes_col_likes;

//     @FXML
//     private TableView<studentData> topNShare_tableView;

//     @FXML
//     private TableColumn<studentData, String> topNShare_col_postId;

//     @FXML
//     private TableColumn<studentData, String> topNShare_col_content;

//     @FXML
//     private TableColumn<studentData, String> topNShare_col_share;

//     @FXML
//     private Button subscribeButton;


//     @FXML
//     private TableView<studentData> tableView;


//     private Connection connect;
//     private PreparedStatement prepare;
//     private Statement statement;
//     private ResultSet result;

//     private Image image;

//     public void VIPSubscription() {

//         String username = getData.username;
//         String email = getData.email;
//         boolean isVIP = checkVIPStatus(username, email);

//         if (!isVIP) {
//             boolean userWantsToUpgrade = askUserToUpgrade();
//             if (userWantsToUpgrade) {
//                 upgradeToVIP(username, email);
//                 askUserToLogoutAndLogin();
//             }
//         }
//     }

//     public boolean checkVIPStatus(String username, String email) {
//         connect = database.connectDb();
//         try {
//             String query = "SELECT is_VIP FROM users WHERE username = ? and email_id = ?";
//             try (PreparedStatement statement = connect.prepareStatement(query)) {
//                 statement.setString(1, username);
//                 statement.setString(2, email);
//                 try (ResultSet result = statement.executeQuery()) {
//                     if (result.next()) {
//                         Integer vipStatus = result.getInt("is_VIP");
//                         System.out.println("vipStatus " + vipStatus);
//                         if (vipStatus == 0) {
//                             return false;
//                         } else {
//                             Alert alert;
//                             alert = new Alert(AlertType.INFORMATION);
//                             alert.setTitle("Information Message");
//                             alert.setHeaderText(null);
//                             alert.setContentText("You have already subscribed...!");
//                             alert.showAndWait();

//                             return true;
//                         }
//                     }
//                 }
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return false;
//     }

//     public void upgradeToVIP(String username, String email) {
//         connect = database.connectDb();
//         try {
//             String query = "UPDATE users SET is_VIP = 1 WHERE username = ? and email_id =  ? ";
//             try (PreparedStatement statement = connect.prepareStatement(query)) {
//                 statement.setString(1, username);
//                 statement.setString(2, email);
//                 statement.executeUpdate();
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public boolean askUserToUpgrade() {
//         Alert alert;
//         alert = new Alert(AlertType.CONFIRMATION);
//         alert.setTitle("Confirmation Dialog");
//         alert.setHeaderText(null);
//         alert.setContentText("Would you like to subscribe to the application for a monthly fee of $0?");

//         // Show the dialog and wait for a user response
//         Optional<ButtonType> result = alert.showAndWait();

//         // Check if the user clicked OK (or Yes, depending on the locale)
//         if (result.isPresent() && result.get() == ButtonType.OK) {
//             // User confirmed, you can proceed with the action
//             alert = new Alert(AlertType.INFORMATION);
//             alert.setTitle("Information Message");
//             alert.setHeaderText(null);
//             alert.setContentText(
//                     "Successfully Subscribed!\nPlease log out and log in again to access VIP functionalities");
//             alert.showAndWait();
//             return true;
//         } else {
//             return false;
//         }

//         // // Implement UI or user interaction to ask if they want to upgrade
//         // // Return true if they agree to upgrade, false otherwise
//         // return false;
//     }

//     public void askUserToLogoutAndLogin() {
//         // Display a message asking the user to log out and log in again
//     }




//     public void homeDisplayTotalEnrolledStudents() {

//         String sql = "SELECT COUNT(id) FROM student";

//         connect = database.connectDb();

//         int countEnrolled = 0;

//         try {
//             prepare = connect.prepareStatement(sql);
//             result = prepare.executeQuery();

//             if (result.next()) {
//                 countEnrolled = result.getInt("COUNT(id)");
//             }

//             home_totalEnrolled.setText(String.valueOf(countEnrolled));

//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//     }

//     public void homeDisplayFemaleEnrolled() {

//         String sql = "SELECT COUNT(id) FROM student WHERE course = 'pass'";

//         connect = database.connectDb();

//         try {
//             int countFemale = 0;

//             prepare = connect.prepareStatement(sql);
//             result = prepare.executeQuery();

//             if (result.next()) {
//                 countFemale = result.getInt("COUNT(id)");
//             }

//             home_totalFemale.setText(String.valueOf(countFemale));

//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//     }

//     public void homeDisplayMaleEnrolled() {

//         String sql = "SELECT COUNT(id) FROM student WHERE course = 'pass'";

//         connect = database.connectDb();

//         try {
//             int countMale = 0;

//             prepare = connect.prepareStatement(sql);
//             result = prepare.executeQuery();

//             if (result.next()) {
//                 countMale = result.getInt("COUNT(id)");
//             }
//             home_totalMale.setText(String.valueOf(countMale));

//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//     }

//     public void homeDisplayTotalEnrolledChart() {

//         // Assuming you have a PieChart object defined as home_totalEnrolledChart
//         home_totalEnrolledChart.getData().clear();

//         // String sql = "SELECT CASE WHEN `share` BETWEEN 0 AND 99 THEN '0-99 Shares'
//         // WHEN `share` BETWEEN 100 AND 999 THEN '100-999 Shares' WHEN `share` >= 1000
//         // THEN '1000+ Shares' END AS ShareCategory, COUNT(*) AS Count FROM student
//         // GROUP BY ShareCategory;";
//         String sql = "SELECT CASE WHEN `share` BETWEEN 0 AND 99 THEN '0-99 Shares' "
//                 + "WHEN `share` BETWEEN 100 AND 999 THEN '100-999 Shares' "
//                 + "WHEN `share` >= 1000 THEN '1000+ Shares' END AS ShareCategory, "
//                 + "COUNT(*) AS Count FROM student GROUP BY ShareCategory";
//         connect = database.connectDb();
//         try {

//             home_totalEnrolledChart.setTitle("Share Distribution");

//             prepare = connect.prepareStatement(sql);
//             result = prepare.executeQuery();

//             while (result.next()) {
//                 System.out.println();
//                 home_totalEnrolledChart.getData().add(new PieChart.Data(result.getString(1), result.getInt(2)));
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//     }



//     // USING THIS METHOD WE ARE SHOWING THE TOP N SHARE DATA
//     public void topNShare() {

//         // Set up the columns
//         topNShare_col_postId.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
//         topNShare_col_content.setCellValueFactory(new PropertyValueFactory<>("content"));
//         topNShare_col_share.setCellValueFactory(new PropertyValueFactory<>("share"));

//         // Fetch and display data from MySQL
//         ObservableList<studentData> data = fetchTopNShareData();
//         topNShare_tableView.setItems(data);
//     }

//     private ObservableList<studentData> fetchTopNShareData() {
//         ObservableList<studentData> data = FXCollections.observableArrayList();

//         try {
//             connect = database.connectDb();
//             Statement statement = connect.createStatement();
//             String query = "SELECT * FROM student order by share desc";
//             ResultSet resultSet = statement.executeQuery(query);

//             while (resultSet.next()) {
//                 int post_id = resultSet.getInt("post_id");
//                 String share = resultSet.getString("share");
//                 String content = resultSet.getString("content");

//                 studentData dataRow = new studentData(post_id, share, content);
//                 data.add(dataRow);
//             }

//             resultSet.close();
//             statement.close();
//             connect.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }

//         return data;
//     }



//     // USING THIS METHOD WE ARE SHOWING THE TOP N LIKES DATA
//     public void topNLikes() {

//         // Set up the columns
//         topNLikes_col_postId.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
//         topNLikes_col_content.setCellValueFactory(new PropertyValueFactory<>("content"));
//         topNLikes_col_likes.setCellValueFactory(new PropertyValueFactory<>("likes"));

//         // Fetch and display data from MySQL
//         ObservableList<studentData> data = fetchTopNLikesData();
//         topNLikes_tableView.setItems(data);
//     }

//     private ObservableList<studentData> fetchTopNLikesData() {
//         ObservableList<studentData> data = FXCollections.observableArrayList();

//         try {
//             connect = database.connectDb();
//             Statement statement = connect.createStatement();
//             String query = "SELECT * FROM student order by likes desc";
//             ResultSet resultSet = statement.executeQuery(query);

//             while (resultSet.next()) {
//                 int post_id = resultSet.getInt("post_id");
//                 int likes = resultSet.getInt("likes");
//                 String content = resultSet.getString("content");

//                 studentData dataRow = new studentData(post_id, likes, content);
//                 data.add(dataRow);
//             }

//             resultSet.close();
//             statement.close();
//             connect.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }

//         return data;
//     }




//     public void exportPostData() {
//         FileChooser fileChooser = new FileChooser();
//         fileChooser.setTitle("Save CSV File");
//         fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

//         File file = fileChooser.showSaveDialog(null); // Opens the save dialog

//         if (file != null) {
//             try (FileWriter writer = new FileWriter(file)) {

//                 ObservableList<TableColumn<studentData, ?>> columns = addStudents_tableView.getColumns();

//                 // Write the column headers to the CSV file
//                 for (TableColumn<studentData, ?> column : columns) {
//                     writer.write(column.getText() + ",");
//                 }
//                 writer.write("\n");

//                 // Write the data from the table to the CSV file
//                 for (studentData item : addStudents_tableView.getItems()) {
//                     for (TableColumn<studentData, ?> column : columns) {
//                         Object cellData = column.getCellData(item);
//                         writer.write(cellData + ",");
//                     }
//                     writer.write("\n");
//                 }
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//     }


//     private static final List<String> REQUIRED_COLUMNS = Arrays.asList("Post ID", "First Name", "Author", "Content", "Publish Date", "Likes", "Share");

//     public List<studentData> importPostsData() throws IOException {
//         Alert alert;
//         System.out.println("I'm in the table");

//         FileChooser fileChooser = new FileChooser();
//         fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
//         File selectedFile = fileChooser.showOpenDialog(null);
//         try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
            
//             String headerLine = br.readLine(); // Read the header line

//             if (headerLine == null) {
//                 alert = new Alert(AlertType.ERROR);
//                 alert.setTitle("Error Message");
//                 alert.setHeaderText(null);
//                 alert.setContentText("CSV file is empty");
//                 alert.showAndWait();
//             }

//             String[] headers = headerLine.split(",");

//             // Check if all required columns are present
//             if (!containsRequiredColumns(headers)) {
//                 // throw new InvalidCSVException("CSV file is missing one or more required columns");
//                 alert = new Alert(AlertType.ERROR);
//                 alert.setTitle("Error Message");
//                 alert.setHeaderText(null);
//                 alert.setContentText("CSV file is missing one or more required columns\nRequired Column should be:-\nPost ID,First Name,Author,Content,Publish Date,Likes,Share");
//                 alert.showAndWait();                
//             }            

//             String line;
//             int lineCount = 1; // To keep track of the line number for error reporting

//             System.out.println("headers " + headers);
//             List<studentData> posts = new ArrayList<>();

//             while ((line = br.readLine()) != null) {
//                 String[] data = line.split(",");

//                 if (data.length != headers.length) {
//                     // throw new InvalidCSVException("Invalid number of columns on line " + lineCount);
//                     alert = new Alert(AlertType.ERROR);
//                     alert.setTitle("Error Message");
//                     alert.setHeaderText(null);
//                     alert.setContentText("Invalid number of columns on line" + lineCount);
//                     alert.showAndWait();                     
//                 }

//                 int id = parseAndValidateInt(data[0], "Post ID", lineCount);
//                 String firstName = data[1];
//                 String author = data[2];
//                 String content = data[3];
//                 String publishDate = data[4];
//                 int likes = parseAndValidateInt(data[5], "Post ID", lineCount);
//                 String share = data[6];
//                 System.out.println("I'm in the table  -  "+ data[0].toString());

//                 studentData post = new studentData(id, firstName, author, content, publishDate, likes, share);
//                 posts.add(post);
//             }
//             return posts;

//             alert = new Alert(AlertType.INFORMATION);
//             alert.setTitle("Information Message");
//             alert.setHeaderText(null);
//             alert.setContentText("Successfully Post Data Inserted...!");
//             alert.showAndWait();  
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }



//     private int parseAndValidateInt(String value, String columnName, int lineCount) {
//         Alert alert;
//         try {
//             int intValue = Integer.parseInt(value);
//             if (intValue < 0) {
//                 alert = new Alert(AlertType.ERROR);
//                 alert.setTitle("Error Message");
//                 alert.setHeaderText(null);
//                 alert.setContentText(columnName + " value is negative on line " + lineCount);
//                 alert.showAndWait();                     
//             }
//             return intValue;

//         } catch (NumberFormatException e) {
//                 alert = new Alert(AlertType.ERROR);
//                 alert.setTitle("Error Message");
//                 alert.setHeaderText(null);
//                 alert.setContentText(columnName + " is not a valid integer on line " + lineCount);
//                 alert.showAndWait();  
//                 return 0;              
//         }
//     }    

//     private boolean containsRequiredColumns(String[] headers) {
//         for (String columnName : REQUIRED_COLUMNS) {
//             boolean found = false;
//             for (String header : headers) {
//                 if (columnName.equals(header.trim())) {
//                     found = true;
//                     break;
//                 }
//             }
//             if (!found) {
//                 return false;
//             }
//         }
//         return true;
//     }    




//     public void addStudentsAdd() {

//         String insertData = "INSERT INTO student "
//                 + "(post_id,likes,course,firstName,author,content,birth,share,image,date) "
//                 + "VALUES(?,?,?,?,?,?,?,?,?,?)";

//         connect = database.connectDb();

//         try {
//             Alert alert;

//             if (addStudents_studentNum.getText().isEmpty()
//                     || addStudents_likes.getText().isEmpty()
//                     || addStudents_firstName.getText().isEmpty()
//                     || addStudents_author.getText().isEmpty()
//                     || addStudents_content.getText().isEmpty()
//                     || addStudents_pub_date.getValue() == null
//                     || addStudents_share.getText().isEmpty()
//                     || getData.path == null || getData.path == "") {
//                 alert = new Alert(AlertType.ERROR);
//                 alert.setTitle("Error Message");
//                 alert.setHeaderText(null);
//                 alert.setContentText("Please fill all blank fields");
//                 alert.showAndWait();
//             } else {
//                 // CHECK IF THE STUDENTNUMBER IS ALREADY EXIST
//                 String checkData = "SELECT post_id FROM student WHERE post_id = '"
//                         + addStudents_studentNum.getText() + "'";

//                 statement = connect.createStatement();
//                 result = statement.executeQuery(checkData);

//                 if (result.next()) {
//                     alert = new Alert(AlertType.ERROR);
//                     alert.setTitle("Error Message");
//                     alert.setHeaderText(null);
//                     alert.setContentText("Post ID" + addStudents_studentNum.getText() + " was already exist!");
//                     alert.showAndWait();
//                 } else {
//                     prepare = connect.prepareStatement(insertData);
//                     prepare.setString(1, addStudents_studentNum.getText());
//                     prepare.setString(2, (String) addStudents_likes.getText());
//                     prepare.setString(3, (String) "pass");
//                     prepare.setString(4, addStudents_firstName.getText());
//                     prepare.setString(5, addStudents_author.getText());
//                     prepare.setString(6, (String) addStudents_content.getText());
//                     prepare.setString(7, String.valueOf(addStudents_pub_date.getValue()));
//                     prepare.setString(8, (String) addStudents_share.getText());

//                     String uri = getData.path;
//                     uri = uri.replace("\\", "\\\\");
//                     prepare.setString(9, uri);

//                     Date date = new Date();
//                     java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//                     prepare.setString(10, String.valueOf(sqlDate));

//                     prepare.executeUpdate();

//                     alert = new Alert(AlertType.INFORMATION);
//                     alert.setTitle("Information Message");
//                     alert.setHeaderText(null);
//                     alert.setContentText("Successfully Added!");
//                     alert.showAndWait();

//                     // TO UPDATE THE TABLEVIEW
//                     addStudentsShowListData();
//                     // TO CLEAR THE FIELDS
//                     addStudentsClear();
//                 }
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public void addStudentsUpdate() {

//         String uri = getData.path;
//         uri = uri.replace("\\", "\\\\");

//         String updateData = "UPDATE student SET "
//                 + "likes = '" + addStudents_likes.getText()
//                 + "', course = '" + "pass"
//                 + "', firstName = '" + addStudents_firstName.getText()
//                 + "', author = '" + addStudents_author.getText()
//                 + "', content = '" + addStudents_content.getText()
//                 + "', birth = '" + addStudents_pub_date.getValue()
//                 + "', share = '" + addStudents_share.getText()
//                 + "', image = '" + uri + "' WHERE post_id = '"
//                 + addStudents_studentNum.getText() + "'";

//         connect = database.connectDb();

//         try {
//             Alert alert;
//             if (addStudents_studentNum.getText().isEmpty()
//                     || addStudents_likes.getText().isEmpty()
//                     || addStudents_firstName.getText().isEmpty()
//                     || addStudents_author.getText().isEmpty()
//                     || addStudents_content.getText().isEmpty()
//                     || addStudents_pub_date.getValue() == null
//                     || addStudents_share.getText().isEmpty()
//                     || getData.path == null || getData.path == "") {
//                 alert = new Alert(AlertType.ERROR);
//                 alert.setTitle("Error Message");
//                 alert.setHeaderText(null);
//                 alert.setContentText("Please fill all blank fields");
//                 alert.showAndWait();
//             } else {

//                 alert = new Alert(AlertType.CONFIRMATION);
//                 alert.setTitle("Confirmation Message");
//                 alert.setHeaderText(null);
//                 alert.setContentText(
//                         "Are you sure you want to UPDATE Post ID" + addStudents_studentNum.getText() + "?");
//                 Optional<ButtonType> option = alert.showAndWait();

//                 if (option.get().equals(ButtonType.OK)) {
//                     statement = connect.createStatement();
//                     statement.executeUpdate(updateData);

//                     alert = new Alert(AlertType.INFORMATION);
//                     alert.setTitle("Information Message");
//                     alert.setHeaderText(null);
//                     alert.setContentText("Successfully Updated!");
//                     alert.showAndWait();

//                     // TO UPDATE THE TABLEVIEW
//                     addStudentsShowListData();
//                     // TO CLEAR THE FIELDS
//                     addStudentsClear();

//                 } else {
//                     return;
//                 }
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     public void addStudentsDelete() {

//         String deleteData = "DELETE FROM student WHERE post_id = '"
//                 + addStudents_studentNum.getText() + "'";

//         connect = database.connectDb();

//         try {
//             Alert alert;
//             if (addStudents_studentNum.getText().isEmpty()
//                     || addStudents_likes.getText().isEmpty()
//                     || addStudents_firstName.getText().isEmpty()
//                     || addStudents_author.getText().isEmpty()
//                     || addStudents_content.getText().isEmpty()
//                     || addStudents_pub_date.getValue() == null
//                     || addStudents_share.getText().isEmpty()
//                     || getData.path == null || getData.path == "") {
//                 alert = new Alert(AlertType.ERROR);
//                 alert.setTitle("Error Message");
//                 alert.setHeaderText(null);
//                 alert.setContentText("Please fill all blank fields");
//                 alert.showAndWait();
//             } else {
//                 alert = new Alert(AlertType.CONFIRMATION);
//                 alert.setTitle("Confirmation Message");
//                 alert.setHeaderText(null);
//                 alert.setContentText(
//                         "Are you sure you want to DELETE Post ID " + addStudents_studentNum.getText() + "?");

//                 Optional<ButtonType> option = alert.showAndWait();

//                 if (option.get().equals(ButtonType.OK)) {
//                     statement = connect.createStatement();
//                     statement.executeUpdate(deleteData);

//                     alert = new Alert(AlertType.INFORMATION);
//                     alert.setTitle("Information Message");
//                     alert.setHeaderText(null);
//                     alert.setContentText("Successfully Deleted!");
//                     alert.showAndWait();

//                     // TO UPDATE THE TABLEVIEW
//                     addStudentsShowListData();
//                     // TO CLEAR THE FIELDS
//                     addStudentsClear();

//                 } else {
//                     return;
//                 }

//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//     }

//     public void addStudentsClear() {
//         addStudents_studentNum.setText("");
//         addStudents_likes.setText("");
//         addStudents_firstName.setText("");
//         addStudents_author.setText("");
//         addStudents_content.setText("");
//         addStudents_pub_date.setValue(null);
//         addStudents_share.getText();
//         addStudents_imageView.setImage(null);

//         getData.path = "";
//     }

//     public void addStudentsInsertImage() {

//         FileChooser open = new FileChooser();
//         open.setTitle("Open Image File");
//         open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));

//         File file = open.showOpenDialog(main_form.getScene().getWindow());

//         if (file != null) {

//             image = new Image(file.toURI().toString(), 120, 149, false, true);
//             addStudents_imageView.setImage(image);

//             getData.path = file.getAbsolutePath();

//         }
//     } // WHILE WE INSERT THE DATA ON STUDENT, WE SHOULD INSERT ALSO THE DATA TO
//       // STUDENT_GRADE

//     public void addStudentsSearch() {
//         String searchValue = addStudents_search.textProperty().getValue();
//         try {
//             connect = database.connectDb();
//             // Write your SQL query with a WHERE clause to filter data based on searchValue
//             String sql = "SELECT * FROM student WHERE post_id LIKE ? OR firstName LIKE ? OR author LIKE ?";
//             PreparedStatement preparedStatement = connect.prepareStatement(sql);
//             preparedStatement.setString(1, "%" + searchValue + "%");
//             preparedStatement.setString(2, "%" + searchValue + "%");
//             preparedStatement.setString(3, "%" + searchValue + "%");

//             // Execute the query and retrieve the results
//             ResultSet resultSet = preparedStatement.executeQuery();

//             // Create a list to store the search results
//             List<studentData> searchResults = new ArrayList<>();

//             while (resultSet.next()) {
//                 // Create studentData objects and add them to the searchResults list
//                 studentData data = new studentData(
//                         resultSet.getInt("post_id"),
//                         resultSet.getInt("likes"),
//                         resultSet.getString("course"),
//                         resultSet.getString("firstName"),
//                         resultSet.getString("author"),
//                         resultSet.getString("content"),
//                         resultSet.getDate("birth"),
//                         resultSet.getString("share"),
//                         resultSet.getString("image"));
//                 searchResults.add(data);
//             }

//             // Close the database resources
//             resultSet.close();
//             preparedStatement.close();
//             connect.close();

//             // Update the TableView with the search results
//             ObservableList<studentData> searchResultsList = FXCollections.observableArrayList(searchResults);
//             addStudents_tableView.setItems(searchResultsList);

//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     // NOW WE NEED THE COURSE, SO LETS WORK NOW THE AVAILABLE COURSE FORM : )
//     // LETS WORK FIRST THE ADD STUDENTS FORM : )
//     public ObservableList<studentData> addStudentsListData() {

//         ObservableList<studentData> listStudents = FXCollections.observableArrayList();

//         String sql = "SELECT * FROM student";

//         connect = database.connectDb();

//         try {
//             studentData studentD;
//             prepare = connect.prepareStatement(sql);
//             result = prepare.executeQuery();

//             while (result.next()) {
//                 studentD = new studentData(result.getInt("post_id"),
//                         result.getInt("likes"),
//                         result.getString("course"),
//                         result.getString("firstName"),
//                         result.getString("author"),
//                         result.getString("content"),
//                         result.getDate("birth"),
//                         result.getString("share"),
//                         result.getString("image"));

//                 listStudents.add(studentD);
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         return listStudents;
//     }

//     private ObservableList<studentData> addStudentsListD;

//     public void addStudentsShowListData() {
//         addStudentsListD = addStudentsListData();

//         addStudents_col_postId.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
//         addStudents_col_likes.setCellValueFactory(new PropertyValueFactory<>("likes"));
//         addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//         addStudents_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
//         addStudents_col_content.setCellValueFactory(new PropertyValueFactory<>("content"));
//         addStudents_col_pub_date.setCellValueFactory(new PropertyValueFactory<>("birth"));
//         addStudents_col_share.setCellValueFactory(new PropertyValueFactory<>("share"));

//         addStudents_tableView.setItems(addStudentsListD);

//     }

//     public void addStudentsSelect() {

//         studentData studentD = addStudents_tableView.getSelectionModel().getSelectedItem();
//         int num = addStudents_tableView.getSelectionModel().getSelectedIndex();
//         if ((num - 1) < -1) {
//             return;
//         }

//         addStudents_studentNum.setText(String.valueOf(studentD.getStudentNum()));
//         addStudents_firstName.setText(studentD.getFirstName());
//         addStudents_likes.setText(String.valueOf(studentD.getLikes()));
//         addStudents_author.setText(studentD.getAuthor());
//         addStudents_content.setText(studentD.getContent());
//         addStudents_pub_date.setValue(LocalDate.parse(String.valueOf(studentD.getBirth())));
//         addStudents_share.setText(studentD.getShare());
//         String uri = "file:" + studentD.getImage();

//         image = new Image(uri, 120, 149, false, true);
//         addStudents_imageView.setImage(image);

//         getData.path = studentD.getImage();

//     }

//     public void userProfilesUpdate() {
//         String checkData = "SELECT * FROM users WHERE username = '"
//                 + userProfile_username.getText() + "'";

//         connect = database.connectDb();

//         try {

//             prepare = connect.prepareStatement(checkData);
//             result = prepare.executeQuery();

//             Date date = new Date();
//             java.sql.Date sqlDate = new java.sql.Date(date.getTime());

//             String updateData = "UPDATE users SET "
//                     + " first_name = '" + userProfile_first_name.getText()
//                     + "', last_name = '" + userProfile_last_name.getText()
//                     + "', username = '" + userProfile_username.getText()
//                     + "', password = '" + userProfile_password.getText()
//                     + "', update_date = '" + sqlDate + "' WHERE email_id = '"

//                     // + "', update_date = '" + String.valueOf(sqlDate) + "' WHERE email_id = '"
//                     + getData.email + "'";

//             Alert alert;

//             String password = userProfile_password.getText();
//             String confirmPassword = userProfile_cPassword.getText();

//             if (userProfile_first_name.getText().isEmpty()
//                     || userProfile_last_name.getText().isEmpty()
//                     || userProfile_username.getText().isEmpty()
//                     || userProfile_password.getText().isEmpty()) {
//                 alert = new Alert(AlertType.ERROR);
//                 alert.setTitle("Error Message");
//                 alert.setHeaderText(null);
//                 alert.setContentText("Please fill all blank fields");
//                 alert.showAndWait();

//             } else if (!password.equals(confirmPassword)) {
//                 alert = new Alert(AlertType.ERROR);
//                 alert.setTitle("Error Message");
//                 alert.setHeaderText(null);
//                 alert.setContentText("Password does not match");
//                 alert.showAndWait();
//             } else if (password.length() < 8) {
//                 alert = new Alert(AlertType.ERROR);
//                 alert.setTitle("Error Message");
//                 alert.setHeaderText(null);
//                 alert.setContentText("Invalid Password, at least 8 characters needed");
//                 alert.showAndWait();
//             }

//             else {

//                 alert = new Alert(AlertType.CONFIRMATION);
//                 alert.setTitle("Confirmation Message");
//                 alert.setHeaderText(null);
//                 alert.setContentText("Are you sure you want to UPDATE user  " + userProfile_username.getText() + "?");
//                 Optional<ButtonType> option = alert.showAndWait();
//                 System.out.println("updateData   :" + updateData);

//                 if (option.get().equals(ButtonType.OK)) {
//                     statement = connect.createStatement();
//                     statement.executeUpdate(updateData);

//                     alert = new Alert(AlertType.INFORMATION);
//                     alert.setTitle("Information Message");
//                     alert.setHeaderText(null);
//                     alert.setContentText("Successfully Updated!");
//                     alert.showAndWait();

//                     // TO UPDATE THE TABLEVIEW

//                 } else {
//                     return;
//                 }

//             } // NOT WE ARE CLOSER TO THE ENDING PART :) LETS PROCEED TO DASHBOARD FORM
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }




//     private double x = 0;
//     private double y = 0;

//     public void logout() {

//         try {

//             Alert alert = new Alert(AlertType.CONFIRMATION);
//             alert.setTitle("Confirmation Message");
//             alert.setHeaderText(null);
//             alert.setContentText("Are you sure you want to logout?");

//             Optional<ButtonType> option = alert.showAndWait();

//             if (option.get().equals(ButtonType.OK)) {

//                 // HIDE YOUR DASHBOARD FORM
//                 logout.getScene().getWindow().hide();

//                 // LINK YOUR LOGIN FORM
//                 Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//                 Stage stage = new Stage();
//                 Scene scene = new Scene(root);

//                 root.setOnMousePressed((MouseEvent event) -> {
//                     x = event.getSceneX();
//                     y = event.getSceneY();
//                 });

//                 root.setOnMouseDragged((MouseEvent event) -> {
//                     stage.setX(event.getScreenX() - x);
//                     stage.setY(event.getScreenY() - y);

//                     stage.setOpacity(.8);
//                 });

//                 root.setOnMouseReleased((MouseEvent event) -> {
//                     stage.setOpacity(1);
//                 });

//                 stage.initStyle(StageStyle.TRANSPARENT);

//                 stage.setScene(scene);
//                 stage.show();

//             } else {
//                 return;
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//     }


//     public void displayUsername() {

//         username.setText(getData.first_name + " " + getData.last_name);
//         userProfile_first_name.setText(getData.first_name);
//         userProfile_last_name.setText(getData.last_name);

//         userProfile_username.setText(getData.username);
//         userProfile_password.setText(getData.password);
//         userProfile_cPassword.setText(getData.password);
//     }


//     public void defaultNav() {
//         home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
//     }


//     public void switchForm(ActionEvent event) {
//         if (event.getSource() == home_btn) {
//             home_form.setVisible(true);
//             addStudents_form.setVisible(false);

//             userProfile_form.setVisible(false);

//             home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
//             addStudents_btn.setStyle("-fx-background-color:transparent");

//             userProfile_btn.setStyle("-fx-background-color:transparent");

//             homeDisplayTotalEnrolledStudents();
//             homeDisplayMaleEnrolled();
//             homeDisplayFemaleEnrolled();
//             topNLikes();
//             topNShare();
//             // homeDisplayFemaleEnrolledChart();
//             homeDisplayTotalEnrolledChart();

//         } else if (event.getSource() == addStudents_btn) {
//             home_form.setVisible(false);
//             addStudents_form.setVisible(true);
//             userProfile_form.setVisible(false);

//             addStudents_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
//             home_btn.setStyle("-fx-background-color:transparent");
//             userProfile_btn.setStyle("-fx-background-color:transparent");

//             // TO BECOME UPDATED ONCE YOU CLICK THE ADD STUDENTS BUTTON ON NAV
//             addStudentsShowListData();
//             addStudentsSearch();

//         } else if (event.getSource() == userProfile_btn) {
//             home_form.setVisible(false);
//             addStudents_form.setVisible(false);
//             userProfile_form.setVisible(true);

//             userProfile_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
//             addStudents_btn.setStyle("-fx-background-color:transparent");
//             home_btn.setStyle("-fx-background-color:transparent");

//         }
//     }

    
//     public void close() {
//         System.exit(0);
//     }

//     public void minimize() {
//         Stage stage = (Stage) main_form.getScene().getWindow();
//         stage.setIconified(true);
//     }

//     @Override
//     public void initialize(URL location, ResourceBundle resources) {
//         displayUsername();
//         defaultNav();

//         homeDisplayTotalEnrolledStudents();
//         homeDisplayMaleEnrolled();
//         homeDisplayFemaleEnrolled();
//         topNLikes();
//         topNShare();
//         homeDisplayTotalEnrolledChart();

//         // TO SHOW IMMIDIATELY WHEN WE PROCEED TO DASHBOARD APPLICATION FORM
//         addStudentsShowListData();

//     }

// }
