package application;

import java.net.URL;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.PasswordField;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.StageStyle;

public class dashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane pieChartCard;

    @FXML
    private AnchorPane topNLikes;

    @FXML
    private AnchorPane topNShare;

    @FXML
    private Button postsData_importBtn;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button home_btn;

    @FXML
    private Button addPosts_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane home_form;

    @FXML
    private PieChart PieChart_ShareDistribution;

    @FXML
    private TableView<postsData> home_totalFemaleChart;

    @FXML
    private TableView<postsData> home_totalMaleChart;

    @FXML
    private AnchorPane addPosts_form;

    @FXML
    private TextField addPosts_search;

    @FXML
    private TableView<postsData> addPosts_tableView;

    @FXML
    private TableColumn<postsData, String> addPosts_col_postId;

    @FXML
    private TableColumn<postsData, String> addPosts_col_likes;

    @FXML
    private TableColumn<postsData, String> addPosts_col_author;

    @FXML
    private TableColumn<postsData, String> addPosts_col_content;

    @FXML
    private TableColumn<postsData, String> addPosts_col_pub_date;

    @FXML
    private TableColumn<postsData, String> addPosts_col_share;

    @FXML
    private TextField addPosts_studentNum;

    @FXML
    private TextField addPosts_likes;

    @FXML
    private TextField addPosts_author;

    @FXML
    private DatePicker addPosts_pub_date;

    @FXML
    private TextField addPosts_share;

    @FXML
    private TextField addPosts_content;

    @FXML
    private ImageView addPosts_imageView;

    @FXML
    private Button addPosts_insertBtn;

    @FXML
    private Button addPosts_addBtn;

    @FXML
    private Button addPosts_updateBtn;

    @FXML
    private Button addPosts_deleteBtn;

    @FXML
    private Button addPosts_clearBtn;

    @FXML
    private Button userProfile_btn;

    @FXML
    private Button postData_exportBtn;

    @FXML
    private AnchorPane userProfile_form;

    @FXML
    private TextField userProfile_first_name;

    @FXML
    private TextField userProfile_last_name;

    @FXML
    private TextField userProfile_username;

    @FXML
    private PasswordField userProfile_password;

    @FXML
    private PasswordField userProfile_cPassword;

    @FXML
    private Button userProfile_updateBtn;

    @FXML
    private TableView<postsData> topNLikes_tableView;

    @FXML
    private TableColumn<postsData, String> topNLikes_col_postId;

    @FXML
    private TableColumn<postsData, String> topNLikes_col_content;

    @FXML
    private TableColumn<postsData, String> topNLikes_col_likes;

    @FXML
    private TableView<postsData> topNShare_tableView;

    @FXML
    private TableColumn<postsData, String> topNShare_col_postId;

    @FXML
    private TableColumn<postsData, String> topNShare_col_content;

    @FXML
    private TableColumn<postsData, String> topNShare_col_share;

    @FXML
    private Button subscribeButton;

    @FXML
    private TableView<postsData> tableView;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    public void VIPSubscription() {

        String username = getData.username;
        String email = getData.email;
        boolean isVIP = checkVIPStatus(username, email, true);

        if (!isVIP) {
            boolean userWantsToUpgrade = askUserToUpgrade();
            if (userWantsToUpgrade) {
                upgradeToVIP(username, email);
                askUserToLogoutAndLogin();
            }
        }
    }

    public boolean checkVIPStatus(String username, String email, boolean type) {
        connect = database.connectDb();
        try {
            String query = "SELECT is_VIP FROM users WHERE username = ? and email_id = ?";
            try (PreparedStatement statement = connect.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, email);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        Integer vipStatus = result.getInt("is_VIP");
                        System.out.println("vipStatus " + vipStatus);
                        if (vipStatus == 0) {
                            System.out.println("vipStatus return false");

                            return false;
                        } else {
                            if (type) {
                                Alert alert;
                                alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Information Message");
                                alert.setHeaderText(null);
                                alert.setContentText("You have already subscribed...!");
                                alert.showAndWait();
                            }

                            return true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void upgradeToVIP(String username, String email) {
        connect = database.connectDb();
        try {
            String query = "UPDATE users SET is_VIP = 1 WHERE username = ? and email_id =  ? ";
            try (PreparedStatement statement = connect.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, email);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean askUserToUpgrade() {
        Alert alert;
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Would you like to subscribe to the application for a monthly fee of $0?");

        // Show the dialog and wait for a user response
        Optional<ButtonType> result = alert.showAndWait();

        // Check if the user clicked OK (or Yes, depending on the locale)
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed, you can proceed with the action
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Successfully Subscribed!\nPlease log out and log in again to access VIP functionalities");
            alert.showAndWait();
            return true;
        } else {
            return false;
        }

        // // Implement UI or user interaction to ask if they want to upgrade
        // // Return true if they agree to upgrade, false otherwise
        // return false;
    }

    public void askUserToLogoutAndLogin() {
        // Display a message asking the user to log out and log in again
    }

    public void PieChartShareDistribution() {

        String username = getData.username;
        String email = getData.email;
        boolean isVIP = checkVIPStatus(username, email, false);
        if (isVIP) {
            pieChartCard.setVisible(true);

            topNLikes.setPrefHeight(198);
            topNLikes.setPrefWidth(449);
            ;
            topNLikes.setLayoutY(52.0);
            topNLikes.setLayoutX(14.0);

            topNLikes_tableView.setPrefHeight(280);
            topNLikes_tableView.setPrefWidth(449);

            topNLikes_col_postId.setPrefWidth(75);
            topNLikes_col_content.setPrefWidth(230);
            topNLikes_col_likes.setPrefWidth(145);

            // Adjusting the share table
            topNShare.setPrefHeight(197);
            topNShare.setPrefWidth(449);
            topNShare.setLayoutY(315.0);
            topNShare.setLayoutX(14.0);

            topNShare_tableView.setPrefHeight(246.0);
            topNShare_tableView.setPrefWidth(449.0);

            topNShare_col_postId.setPrefWidth(75);
            topNShare_col_content.setPrefWidth(230);
            topNShare_col_share.setPrefWidth(145);

            PieChart_ShareDistribution.getData().clear();

            String sql = "SELECT CASE WHEN `share` BETWEEN 0 AND 99 THEN '0-99 Shares' "
                    + "WHEN `share` BETWEEN 100 AND 999 THEN '100-999 Shares' "
                    + "WHEN `share` >= 1000 THEN '1000+ Shares' END AS ShareCategory, "
                    + "COUNT(*) AS Count FROM posts GROUP BY ShareCategory";

            connect = database.connectDb();
            try {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                while (result.next()) {
                    System.out.println();
                    PieChart_ShareDistribution.getData().add(new PieChart.Data(result.getString(1), result.getInt(2)));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pieChartCard.setVisible(false);

            topNLikes.setPrefHeight(198);
            topNLikes.setPrefWidth(667);
            ;
            topNLikes.setLayoutY(52.0);
            topNLikes.setLayoutX(101.0);

            topNLikes_tableView.setPrefHeight(280);
            topNLikes_tableView.setPrefWidth(449);

            topNLikes_col_postId.setPrefWidth(150);
            topNLikes_col_content.setPrefWidth(300);
            topNLikes_col_likes.setPrefWidth(220);

            // Adjusting the share table
            topNShare.setPrefHeight(198);
            topNShare.setPrefWidth(667);
            topNShare.setLayoutY(315.0);
            topNShare.setLayoutX(98.0);

            topNShare_tableView.setPrefHeight(246.0);
            topNShare_tableView.setPrefWidth(449.0);

            topNShare_col_postId.setPrefWidth(150);
            topNShare_col_content.setPrefWidth(300);
            topNShare_col_share.setPrefWidth(220);

        }

    }

    // USING THIS METHOD WE ARE SHOWING THE TOP N SHARE DATA
    public void topNShare() {

        // Set up the columns
        topNShare_col_postId.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        topNShare_col_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        topNShare_col_share.setCellValueFactory(new PropertyValueFactory<>("share"));

        // Fetch and display data from MySQL
        ObservableList<postsData> data = fetchTopNShareData();
        topNShare_tableView.setItems(data);
    }

    private ObservableList<postsData> fetchTopNShareData() {
        ObservableList<postsData> data = FXCollections.observableArrayList();

        try {
            connect = database.connectDb();
            Statement statement = connect.createStatement();
            String query = "SELECT * FROM posts order by share desc";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int post_id = resultSet.getInt("post_id");
                String share = resultSet.getString("share");
                String content = resultSet.getString("content");

                postsData dataRow = new postsData(post_id, share, content);
                data.add(dataRow);
            }

            resultSet.close();
            statement.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    // USING THIS METHOD WE ARE SHOWING THE TOP N LIKES DATA
    public void topNLikes() {

        // Set up the columns
        topNLikes_col_postId.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        topNLikes_col_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        topNLikes_col_likes.setCellValueFactory(new PropertyValueFactory<>("likes"));

        // Fetch and display data from MySQL
        ObservableList<postsData> data = fetchTopNLikesData();
        topNLikes_tableView.setItems(data);
    }

    private ObservableList<postsData> fetchTopNLikesData() {
        ObservableList<postsData> data = FXCollections.observableArrayList();

        try {
            connect = database.connectDb();
            Statement statement = connect.createStatement();
            String query = "SELECT * FROM posts order by likes desc";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int post_id = resultSet.getInt("post_id");
                int likes = resultSet.getInt("likes");
                String content = resultSet.getString("content");

                postsData dataRow = new postsData(post_id, likes, content);
                data.add(dataRow);
            }

            resultSet.close();
            statement.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public void exportPostData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File file = fileChooser.showSaveDialog(null); // Opens the save dialog

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {

                ObservableList<TableColumn<postsData, ?>> columns = addPosts_tableView.getColumns();

                // Write the column headers to the CSV file
                for (TableColumn<postsData, ?> column : columns) {
                    writer.write(column.getText() + ",");
                }
                writer.write("\n");

                // Write the data from the table to the CSV file
                for (postsData item : addPosts_tableView.getItems()) {
                    for (TableColumn<postsData, ?> column : columns) {
                        Object cellData = column.getCellData(item);
                        writer.write(cellData + ",");
                    }
                    writer.write("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static final List<String> REQUIRED_COLUMNS = Arrays.asList("Post ID", "Author", "Content",
            "Publish Date", "Likes", "Share");

    public void importPostsData() throws IOException {
        Alert alert;
        System.out.println("I'm in the table");

        String username = getData.username;
        String email = getData.email;
        boolean isVIP = checkVIPStatus(username, email, false);

        if (isVIP) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File selectedFile = fileChooser.showOpenDialog(null);
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {

                String headerLine = br.readLine(); // Read the header line

                if (headerLine == null) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("CSV file is empty");
                    alert.showAndWait();
                }

                String[] headers = headerLine.split(",");

                // Check if all required columns are present
                if (!containsRequiredColumns(headers)) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(
                            "CSV file is missing one or more required columns\nRequired Column should be:-\nPost ID,Author,Content,Publish Date,Likes,Share");
                    alert.showAndWait();
                }

                String line;
                int lineCount = 1; // To keep track of the line number for error reporting

                System.out.println("headers " + headers);
                List<postsData> posts = new ArrayList<>();

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");

                    if (data.length != headers.length) {
                        // throw new InvalidCSVException("Invalid number of columns on line " +
                        // lineCount);
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid number of columns on line" + lineCount);
                        alert.showAndWait();
                    }

                    int post_id = parseAndValidateInt(data[0], "Post ID", lineCount);
                    String author = data[1];
                    String content = data[2];
                    String publishDate = data[3];
                    int likes = parseAndValidateInt(data[4], "Post ID", lineCount);
                    String share = data[5];
                    System.out.println("I'm in the table  -  " + data[0].toString());

                    // postsData post = new postsData(id, author, content,
                    // publishDate, likes, share);
                    // posts.add(post);
                }
                // return posts;

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Post Data Inserted...!");
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("This Feature is available only for subscribed users");
            alert.showAndWait();

        }
    }

    private int parseAndValidateInt(String value, String columnName, int lineCount) {
        Alert alert;
        try {
            int intValue = Integer.parseInt(value);
            if (intValue < 0) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(columnName + " value is negative on line " + lineCount);
                alert.showAndWait();
            }
            return intValue;

        } catch (NumberFormatException e) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(columnName + " is not a valid integer on line " + lineCount);
            alert.showAndWait();
            return 0;
        }
    }

    private boolean containsRequiredColumns(String[] headers) {
        for (String columnName : REQUIRED_COLUMNS) {
            boolean found = false;
            for (String header : headers) {
                if (columnName.equals(header.trim())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public void addPostsAdd() {

        String insertData = "INSERT INTO posts "
                + "(post_id,likes,author,content,birth,share,image,date) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;

            if (addPosts_studentNum.getText().isEmpty()
                    || addPosts_likes.getText().isEmpty()
                    || addPosts_author.getText().isEmpty()
                    || addPosts_content.getText().isEmpty()
                    || addPosts_pub_date.getValue() == null
                    || addPosts_share.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                // CHECK IF THE POSTS IS ALREADY EXIST
                String checkData = "SELECT post_id FROM posts WHERE post_id = '"
                        + addPosts_studentNum.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Post ID" + addPosts_studentNum.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, addPosts_studentNum.getText());
                    prepare.setString(2, (String) addPosts_likes.getText());
                    prepare.setString(3, addPosts_author.getText());
                    prepare.setString(4, (String) addPosts_content.getText());
                    prepare.setString(5, String.valueOf(addPosts_pub_date.getValue()));
                    prepare.setString(6, (String) addPosts_share.getText());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(7, uri);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addPostsShowListData();
                    // TO CLEAR THE FIELDS
                    addPostsClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPostsUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String updateData = "UPDATE posts SET "
                + "likes = '" + addPosts_likes.getText()
                + "', author = '" + addPosts_author.getText()
                + "', content = '" + addPosts_content.getText()
                + "', birth = '" + addPosts_pub_date.getValue()
                + "', share = '" + addPosts_share.getText()
                + "', image = '" + uri + "' WHERE post_id = '"
                + addPosts_studentNum.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (addPosts_studentNum.getText().isEmpty()
                    || addPosts_likes.getText().isEmpty()
                    || addPosts_author.getText().isEmpty()
                    || addPosts_content.getText().isEmpty()
                    || addPosts_pub_date.getValue() == null
                    || addPosts_share.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Are you sure you want to UPDATE Post ID" + addPosts_studentNum.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addPostsShowListData();
                    // TO CLEAR THE FIELDS
                    addPostsClear();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPostsDelete() {

        String deleteData = "DELETE FROM posts WHERE post_id = '"
                + addPosts_studentNum.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (addPosts_studentNum.getText().isEmpty()
                    || addPosts_likes.getText().isEmpty()
                    || addPosts_author.getText().isEmpty()
                    || addPosts_content.getText().isEmpty()
                    || addPosts_pub_date.getValue() == null
                    || addPosts_share.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Are you sure you want to DELETE Post ID " + addPosts_studentNum.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addPostsShowListData();
                    // TO CLEAR THE FIELDS
                    addPostsClear();

                } else {
                    return;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addPostsClear() {
        addPosts_studentNum.setText("");
        addPosts_likes.setText("");
        addPosts_author.setText("");
        addPosts_content.setText("");
        addPosts_pub_date.setValue(null);
        addPosts_share.setText("");
        addPosts_imageView.setImage(null);

        getData.path = "";
    }

    public void addPostsInsertImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            image = new Image(file.toURI().toString(), 120, 149, false, true);
            addPosts_imageView.setImage(image);

            getData.path = file.getAbsolutePath();

        }
    }

    public void addPostsSearch() {
        String searchValue = addPosts_search.textProperty().getValue();
        try {
            connect = database.connectDb();
            // Write your SQL query with a WHERE clause to filter data based on searchValue
            String sql = "SELECT * FROM posts WHERE post_id LIKE ? OR author LIKE ?";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchValue + "%");
            preparedStatement.setString(2, "%" + searchValue + "%");

            // Execute the query and retrieve the results
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create a list to store the search results
            List<postsData> searchResults = new ArrayList<>();

            while (resultSet.next()) {
                // Create postsData objects and add them to the searchResults list
                postsData data = new postsData(
                        resultSet.getInt("post_id"),
                        resultSet.getInt("likes"),
                        resultSet.getString("author"),
                        resultSet.getString("content"),
                        resultSet.getDate("birth"),
                        resultSet.getString("share"),
                        resultSet.getString("image"));
                searchResults.add(data);
            }

            // Close the database resources
            resultSet.close();
            preparedStatement.close();
            connect.close();

            // Update the TableView with the search results
            ObservableList<postsData> searchResultsList = FXCollections.observableArrayList(searchResults);
            addPosts_tableView.setItems(searchResultsList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // LETS WORK FIRST THE ADD POSTS FORM : )
    public ObservableList<postsData> addPostsListData() {

        ObservableList<postsData> listPosts = FXCollections.observableArrayList();

        String sql = "SELECT * FROM posts";

        connect = database.connectDb();

        try {
            postsData postD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                postD = new postsData(result.getInt("post_id"),
                        result.getInt("likes"),
                        result.getString("author"),
                        result.getString("content"),
                        result.getDate("birth"),
                        result.getString("share"),
                        result.getString("image"));

                listPosts.add(postD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPosts;
    }

    private ObservableList<postsData> addPostsListD;

    public void addPostsShowListData() {
        addPostsListD = addPostsListData();

        addPosts_col_postId.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        addPosts_col_likes.setCellValueFactory(new PropertyValueFactory<>("likes"));
        addPosts_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        addPosts_col_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        addPosts_col_pub_date.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addPosts_col_share.setCellValueFactory(new PropertyValueFactory<>("share"));

        addPosts_tableView.setItems(addPostsListD);

    }

    public void addPostsSelect() {

        postsData postD = addPosts_tableView.getSelectionModel().getSelectedItem();
        int num = addPosts_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }

        addPosts_studentNum.setText(String.valueOf(postD.getStudentNum()));
        addPosts_likes.setText(String.valueOf(postD.getLikes()));
        addPosts_author.setText(postD.getAuthor());
        addPosts_content.setText(postD.getContent());
        addPosts_pub_date.setValue(LocalDate.parse(String.valueOf(postD.getBirth())));
        addPosts_share.setText(postD.getShare());
        String uri = "file:" + postD.getImage();

        image = new Image(uri, 120, 149, false, true);
        addPosts_imageView.setImage(image);

        getData.path = postD.getImage();

    }

    public void userProfilesUpdate() {
        Alert alert;

        String checkData = "SELECT * FROM users WHERE username = '"
                + userProfile_username.getText() + "'";

        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(checkData);
            result = prepare.executeQuery();

            if (result.next()) {
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                String updateData = "UPDATE users SET "
                        + " first_name = '" + userProfile_first_name.getText()
                        + "', last_name = '" + userProfile_last_name.getText()
                        + "', username = '" + userProfile_username.getText()
                        + "', password = '" + userProfile_password.getText()
                        + "', update_date = '" + sqlDate + "' WHERE email_id = '"
                        + getData.email + "'";

                String password = userProfile_password.getText();
                String confirmPassword = userProfile_cPassword.getText();

                if (userProfile_first_name.getText().isEmpty()
                        || userProfile_last_name.getText().isEmpty()
                        || userProfile_username.getText().isEmpty()
                        || userProfile_password.getText().isEmpty()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields");
                    alert.showAndWait();

                } else if (!password.equals(confirmPassword)) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Password does not match");
                    alert.showAndWait();
                } else if (password.length() < 8) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Password, at least 8 characters needed");
                    alert.showAndWait();
                }

                else {

                    alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText(
                            "Are you sure you want to UPDATE user  " + userProfile_username.getText() + "?");
                    Optional<ButtonType> option = alert.showAndWait();
                    System.out.println("updateData   :" + updateData);

                    if (option.get().equals(ButtonType.OK)) {
                        statement = connect.createStatement();
                        statement.executeUpdate(updateData);

                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Updated!");
                        alert.showAndWait();

                    } else {
                        return;
                    }

                }

            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double x = 0;
    private double y = 0;

    public void logout() {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                // HIDE YOUR DASHBOARD FORM
                logout.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void displayUsername() {

        username.setText(getData.first_name + " " + getData.last_name);
        userProfile_first_name.setText(getData.first_name);
        userProfile_last_name.setText(getData.last_name);

        userProfile_username.setText(getData.username);
        userProfile_password.setText(getData.password);
        userProfile_cPassword.setText(getData.password);
    }

    public void defaultNav() {
        home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addPosts_form.setVisible(false);

            userProfile_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addPosts_btn.setStyle("-fx-background-color:transparent");

            userProfile_btn.setStyle("-fx-background-color:transparent");
            topNLikes();
            topNShare();
            PieChartShareDistribution();

        } else if (event.getSource() == addPosts_btn) {
            home_form.setVisible(false);
            addPosts_form.setVisible(true);
            userProfile_form.setVisible(false);

            addPosts_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            userProfile_btn.setStyle("-fx-background-color:transparent");

            // TO BECOME UPDATED ONCE YOU CLICK THE ADD POSTS BUTTON ON NAV
            addPostsShowListData();
            addPostsSearch();

        } else if (event.getSource() == userProfile_btn) {
            home_form.setVisible(false);
            addPosts_form.setVisible(false);
            userProfile_form.setVisible(true);

            userProfile_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addPosts_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

        }
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();
        defaultNav();

        topNLikes();
        topNShare();
        PieChartShareDistribution();

        // TO SHOW IMMIDIATELY WHEN WE PROCEED TO DASHBOARD APPLICATION FORM
        addPostsShowListData();

    }

}
