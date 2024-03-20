package Account;

import ConnectionDataBase.DBConnection;
import LogIn.DangNhap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.time.LocalDate;

import java.sql.*;


public class TaoTheThuVien {
    @FXML
    private Label NotificationCard;

    @FXML
    private TextField textAddressCard;

    @FXML
    private TextField textMSSVCard;

    @FXML
    private TextField textNameCard;
    private String MessageNoti = "";


//    private boolean checkCard(String nameCheck){
//        Connection connection = DBConnection.ConnectDB();
//        if (connection != null){
//            DBConnection connectionDB = new DBConnection();
//            String query = "SELECT * FROM TheThuVien WHERE TenDangNhap = ?";
//            PreparedStatement statement = connectionDB.prepareStatement(query);
//            try {
//                statement.setString(1, nameCheck);
//                ResultSet resultSet = statement.executeQuery();
//                return resultSet.next();
//            }catch (SQLException e){
//                throw new RuntimeException(e);
//            }
//        }
//        return false;
//    }

    @FXML
    public void CreateCard(ActionEvent event) {
        String NameCard = textNameCard.getText();
        String MSSVCard = textMSSVCard.getText();
        String AddressCard = textAddressCard.getText();
        String userSuccessfulLogin = DangNhap.getUserNameCorrect();

        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.toString();

        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        int endYear = currentYear;
        int endMonth = currentMonth + 3;
        if (endMonth > 12) {
            endYear++;
            endMonth -= 12;
        }

        LocalDate endDate = LocalDate.of(endYear, endMonth, currentDate.getDayOfMonth());
        String formattedEndDate = endDate.toString();

        Connection connection = DBConnection.ConnectDB();
        if (connection != null) {
            DBConnection connectionDB = new DBConnection();
            String query = "INSERT INTO TheThuVien (SoThe, NgayBatDau, NgayKetThuc, TenDangNhap) VALUES (?, ?, ?, ?)";
            PreparedStatement statement1 = connectionDB.prepareStatement(query);

            try {String getCurrentSoTheQuery = "SELECT MAX(SoThe) AS CurrentSoThe FROM TheThuVien";
                Statement getCurrentSoTheStatement = connection.createStatement();
                ResultSet resultSet = getCurrentSoTheStatement.executeQuery(getCurrentSoTheQuery);
                String currentSoThe = "ST001";

                if (resultSet.next()) {
                    String lastSoThe = resultSet.getString("CurrentSoThe");
                    if (lastSoThe != null) {
                        int sequenceNumber = Integer.parseInt(lastSoThe.substring(2));
                        sequenceNumber++;
                        currentSoThe = "ST" + String.format("%03d", sequenceNumber);
                    }
                }

                statement1.setString(1, currentSoThe);
                statement1.setString(2, formattedDate);
                statement1.setString(3, formattedEndDate);
                statement1.setString(4, userSuccessfulLogin);

                ResultSet resultSet1 = statement1.executeQuery();

                String sql = "INSERT INTO DocGia (TenDocGia, DiaChi, SoThe, SDT, TenDangNhap) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement2 = connectionDB.prepareStatement(sql);

                statement2.setString(1, NameCard);
                statement2.setString(2, AddressCard);
                statement2.setString(3, currentSoThe);
                statement2.setString(4, MSSVCard);
                statement2.setString(5, userSuccessfulLogin);

                ResultSet resultSet2 = statement2.executeQuery();
                if(resultSet1.next() && resultSet2.next()){
                    MessageNoti = "Tạo thẻ thư viện thành công!";
                }else {
                    MessageNoti = "Tạo thẻ thư viện không thành công!";
                }
                NotificationCard.setText(MessageNoti);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @FXML
    private void CancelCard(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
