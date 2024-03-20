package LogIn;


import ConnectionDataBase.DBConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class DangNhap implements Initializable {

    @FXML
    private TextField Password;

    @FXML
    private TextField UserName;

    @FXML
    private Label errorMessageLabel;

    private String errorMessage = "Tài khoản hoặc mật khẩu không chính xác !";

    private static String userNameCorrect;

    @FXML
    private CheckBox showPasswordCheckbox;

    @FXML
    private TextField textFieldPass;

    private boolean isCheckPermission(String username, String password){
        Connection connection = DBConnection.ConnectDB();
        if (connection != null) {
            DBConnection connectionDB = new DBConnection();
            String query = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ? AND Quyen = ?";
            PreparedStatement statement = connectionDB.prepareStatement(query);
            try {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, "Độc Giả");
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }


    public void startHome(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Interface/HomeUser.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void startCreateAccount(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/CreateAcc/TaoTK.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void Notification(int TimeToStop){
        errorMessageLabel.setText(errorMessage);

        Thread thread = new Thread(()->{
           try {
               Thread.sleep(TimeToStop);
               Platform.runLater(() -> {
                   errorMessageLabel.setText("");
               });
           }catch (InterruptedException e){
               e.printStackTrace();
           }
        });
        thread.start();
    }

    private void StartHomeAdmin(){
        Interface.Menu.openFXML("/Interface/HomeAdmin.fxml");
    }

    @FXML
    private void ClickLogin(ActionEvent event) {
        String username = UserName.getText();
        String password = Password.getText();
        Connection connection = DBConnection.ConnectDB();
        boolean isCheck = isCheckPermission(username, password);
        if (connection != null) {
            DBConnection connectionDB = new DBConnection();
            String query = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ?";
            PreparedStatement statement = connectionDB.prepareStatement(query);
            try {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    if (isCheck){
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                        userNameCorrect = username;
                        startHome();
                    }else {
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                        userNameCorrect = username;
                        StartHomeAdmin();
                    }
                } else {
                    Notification(1000);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
        System.out.println("Không thể kết nối tới cơ sở dữ liệu");
        }
    }

    public static String getUserNameCorrect() {
        return userNameCorrect;
    }


    @FXML
    public void CreateAccount(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        startCreateAccount();
    }

    @FXML
    private void ShowPassword(){
        boolean selected = showPasswordCheckbox.isSelected();

        textFieldPass.setVisible(selected);
        Password.setVisible(!selected);

        if(selected){
            textFieldPass.setText(Password.getText());
        }else {
            Password.setText(textFieldPass.getText());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textFieldPass.setVisible(false);
    }

}
