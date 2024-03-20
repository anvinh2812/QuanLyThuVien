package CreateAcc;

import ConnectionDataBase.DBConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.fxml.FXML;


public class TaoTK implements Initializable {
    @FXML
    private Label MessageSignUp;

    @FXML
    private TextField TextPasswordSignUp;

    @FXML
    private PasswordField PasswordSignUp;

    @FXML
    private Button SignUp;

    @FXML
    private TextField UserNameSignUp;

    @FXML
    private CheckBox showPasswordCheckboxSU;

    private String MessageSU = "";

    @FXML
    public boolean isAccountCheck(String usernamecheck){
        Connection connection = DBConnection.ConnectDB();
        if (connection != null){
            DBConnection connectionDB = new DBConnection();
            String query = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ?";
            PreparedStatement statement = connectionDB.prepareStatement(query);
            try {
                statement.setString(1, usernamecheck);
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @FXML
    public boolean isAccountAdded(String username, String password) {
        Connection connection = DBConnection.ConnectDB();
        if (connection != null) {
            DBConnection connectionDB = new DBConnection();
            String query = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ?";
            PreparedStatement statement = connectionDB.prepareStatement(query);
            try {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public boolean validateAccount(String username, String password){
        if(username.length()<3 || username.length() > 20){
            return false;
        }
        if (password.length() <3 || password.length() > 20) {
            return false;
        }
        if (!username.matches("[a-zA-Z0-9]+")) {
            return false;
        }
        if (!password.matches("^(?=.*[a-z])(?=.*\\d).+$")) {
            return false;
        }else {
            return true;
        }
    }

    private void Notification(int TimeToStop, String MessageNoti){
        MessageSignUp.setText(MessageNoti);

        Thread thread = new Thread(()->{
            try {
                Thread.sleep(TimeToStop);
                Platform.runLater(() -> {
                    MessageSignUp.setText("");
                });
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void gobackLogin(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Login/DangNhap.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void ClickSignIn(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        gobackLogin();
    }

    @FXML
    public void ClickSignUp(ActionEvent event) {
        String usernamesignup = UserNameSignUp.getText();
        String passwordsignup = PasswordSignUp.getText();
        String accessRights = "Độc Giả";

        boolean isValidAccount = validateAccount(usernamesignup, passwordsignup);
        if (isValidAccount==true) {
            Connection connection = DBConnection.ConnectDB();
            if (connection != null) {
                DBConnection connectionDB = new DBConnection();
                String query = "INSERT INTO TaiKhoan (TenDangNhap, MatKhau, Quyen) VALUES (?, ?, ?)";
                PreparedStatement statement = connectionDB.prepareStatement(query);
                try {
                    statement.setString(1, usernamesignup);
                    statement.setString(2, passwordsignup);
                    statement.setString(3, accessRights);
                    boolean isCheck = isAccountCheck(usernamesignup);
                    if (!isCheck) {
                        boolean isAdded = isAccountAdded(usernamesignup, passwordsignup);
                        statement.executeUpdate();
                        if (!isAdded) {
                            Notification(1000, "Tạo tài khoản thành công!");
                        } else {
                            Notification(1000, "Tạo tài khoản không thành công!");
                        }
                    } else {
                        Notification(1000, "Tên đăng nhập đã có người đặt!");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            Notification(1000, "Tài khoản không hợp lệ!");
        }
    }


    @FXML
    private void ShowPasswordSU(){
        boolean selectedSU = showPasswordCheckboxSU.isSelected();

        TextPasswordSignUp.setVisible(selectedSU);
        PasswordSignUp.setVisible(!selectedSU);

        if(selectedSU){
            TextPasswordSignUp.setText(PasswordSignUp.getText());
        }else {
            PasswordSignUp.setText(TextPasswordSignUp.getText());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextPasswordSignUp.setVisible(false);
    }
}