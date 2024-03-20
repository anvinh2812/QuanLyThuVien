package Account;

import ConnectionDataBase.DBConnection;
import Interface.Menu;
import LogIn.DangNhap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TaiKhoan implements Initializable {

    @FXML
    private Label LabelCard;

    @FXML
    private Label LabelDate;

    @FXML
    private Label LabelEmail;

    @FXML
    private Label LabelUser;

    @FXML
    private Label LabelCardLibrary;

    @FXML
    private Label LabelDateLibrary;

    @FXML
    private Label LabelDateEndLibrary;

    @FXML
    private Label LabelCardMSSV;

    @FXML
    private Label Notification;
    private String MessNotication = "";

    @FXML
    private Button Button_LamMoi1;

    @FXML
    private Button Button_LamMoi2;



    private boolean checkCard(String nameCheck) {
        Connection connection = DBConnection.ConnectDB();
        if (connection != null) {
            DBConnection connectionDB = new DBConnection();
            String query = "SELECT * FROM TheThuVien WHERE TenDangNhap = ?";
            PreparedStatement statement = connectionDB.prepareStatement(query);
            try {
                statement.setString(1, nameCheck);
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public void HienThiThongTinTaiKhoan(){
        String userSuccessfulLogin = DangNhap.getUserNameCorrect();
        DBConnection connectionDB = new DBConnection();
        String query1 = "SELECT MaDocGia, SDT, Email, TenDocGia FROM DocGia WHERE TenDangNhap = ?";
        PreparedStatement statement1 = connectionDB.prepareStatement(query1);
        try {
            statement1.setString(1, userSuccessfulLogin);
            try (ResultSet rs = statement1.executeQuery()) {
                if (rs.next()) {
                    String cardValue = rs.getString("MaDocGia");
                    String dateValue = rs.getString("SDT");
                    String emailValue = rs.getString("Email");
                    String userValue = rs.getString("TenDocGia");

                    LabelCard.setText(cardValue);
                    LabelDate.setText(dateValue);
                    LabelEmail.setText(emailValue);
                    LabelUser.setText(userValue);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query2 = "SELECT SoThe, MSSV, NgayBatDau, NgayKetThuc FROM TheThuVien WHERE TenDangNhap = ?";
        PreparedStatement statement2 = connectionDB.prepareStatement(query2);
        try {
            statement2.setString(1, userSuccessfulLogin);
            try (ResultSet rs = statement2.executeQuery()) {
                if (rs.next()) {
                    String cardValueLibrary = rs.getString("SoThe");
                    String cardMSSV = rs.getString("MSSV");
                    String dateValueLibrary = rs.getString("NgayBatDau");
                    String dateEndlValueLibrary = rs.getString("NgayKetThuc");

                    LabelCardLibrary.setText(cardValueLibrary);
                    LabelCardMSSV.setText(cardMSSV);
                    LabelDateLibrary.setText(dateValueLibrary);
                    LabelDateEndLibrary.setText(dateEndlValueLibrary);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HienThiThongTinTaiKhoan();
    }

    private void StartSettingAccount() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ChinhSuaTaiKhoan.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void StartChange() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DoiMatKhau.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void SettingProfile(ActionEvent event) {
        StartSettingAccount();
    }

    @FXML
    private void CreateCart(ActionEvent event) {
        String userSuccessfulLogin = DangNhap.getUserNameCorrect();
        boolean isCheckNameCard = checkCard(userSuccessfulLogin);
        if (!isCheckNameCard) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("TaoTheThuVien.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông Báo Cho Bạn");
            alert.setHeaderText(null);
            alert.setContentText("Đã tồn tại thẻ thư viện!");
            alert.showAndWait();
        }
    }

    public void LamMoi1() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TaiKhoan.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) Button_LamMoi1.getScene().getWindow();
        currentStage.close();
    }

    public void LamMoi2() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TaiKhoan.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) Button_LamMoi2.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void ChangePassword(ActionEvent event) {
        StartChange();
    }

    @FXML
    private void ExtendCard(ActionEvent event) {
        Menu.openFXML("/Account/GiaHanTheThuVien.fxml");
    }

    @FXML
    private void LogOut(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/Login/DangNhap.fxml");
    }

    @FXML
    private void HomeUser(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/Interface/HomeUser.fxml");
    }

    @FXML
    private void Books(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/MainTrung/MuonSach.fxml");
    }

    @FXML
    private void Statistical(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/MainTrung/ThongKeSach.fxml");
    }
}
