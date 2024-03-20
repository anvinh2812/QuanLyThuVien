package Account;

import ConnectionDataBase.DBConnection;
import LogIn.DangNhap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoiMatKhau {
    @FXML
    private Label NotificationPass;

    @FXML
    private TextField textNewPassword1;

    @FXML
    private TextField textNewPassword2;

    @FXML
    private TextField textPassword;
    private String errorMessagePass = "Mật khẩu cũ không chính xác!";
    private String successMessage = "Đổi mật khẩu thành công!";
    private String errorMessageCheck = "Nhập lại mật khẩu không chính xác!";
    @FXML
    private void cancelPass(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ClickPass(ActionEvent event) {
        String passwordOld = textPassword.getText();
        String userSuccessfulLogin = DangNhap.getUserNameCorrect();
        DBConnection connectionDB = new DBConnection();
        String query = "SELECT MatKhau FROM TaiKhoan WHERE TenDangNhap = ?";
        try (PreparedStatement statement = connectionDB.prepareStatement(query)) {
            statement.setString(1, userSuccessfulLogin);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String passwordOldDB = rs.getString("MatKhau");
                    if (passwordOld.equals(passwordOldDB)) {
                        UpdatePassword();
                    } else {
//                        NotificationPass.setText(errorMessagePass);
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Thông báo cho bạn");
                        alert.setHeaderText(null);
                        alert.setContentText("Mật khẩu cũ không chính xác!");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo cho bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Không tìm thấy tài khoản");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void UpdatePassword(){
        String NewPasswordUpdate = textNewPassword1.getText();
        String CheckPasswordNew = textNewPassword2.getText();
        String userSuccessfulLogin = DangNhap.getUserNameCorrect();
        DBConnection connectionDB = new DBConnection();
        String sql = "UPDATE TaiKhoan SET MatKhau = ? WHERE TenDangNhap = ?";
        try (PreparedStatement statement = connectionDB.prepareStatement(sql)) {
            statement.setString(1, NewPasswordUpdate);
            statement.setString(2, userSuccessfulLogin);
            if (CheckPasswordNew.equals(NewPasswordUpdate)){
                    statement.executeUpdate();
//                    NotificationPass.setText(successMessage);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo cho bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Đổi mật khẩu thành công!");
                    alert.showAndWait();
            }else {
//                NotificationPass.setText(errorMessageCheck);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo cho bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Nhập lại mật khẩu cũ không chính xác!");
                    alert.showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
