package Interface;

import ConnectionDataBase.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class Menu implements Initializable {
    @FXML
    private Label TheLoai1;

    @FXML
    private Label TheLoai2;

    @FXML
    private Label TheLoai3;

    @FXML
    private Label TheLoai4;

    @FXML
    private Label TheLoai5;

    @FXML
    private Label TheLoaiSach;

    @FXML
    private Label TongSach;

    public void TinhTongSoLuong() {
        String sqlQuery = "SELECT SUM(SoLuong) AS TongSoLuong FROM Sach";
        Connection connection = DBConnection.ConnectDB();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int tongSoLuong = rs.getInt("TongSoLuong");
                TongSach.setText(Integer.toString(tongSoLuong));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void TinhTongTheLoai() {
        String sqlQuery = "SELECT COUNT(*) AS TongTheLoai FROM TheLoai";
        Connection connection = DBConnection.ConnectDB();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int tongTheLoai = rs.getInt("TongTheLoai");
                TheLoaiSach.setText(Integer.toString(tongTheLoai));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void GanGiaTriTheLoai() {
        String sqlQuery = "SELECT TOP 5 TenTheLoai FROM TheLoai";
        Connection connection = DBConnection.ConnectDB();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            ResultSet rs = pstmt.executeQuery();
            int count = 1;
            while (rs.next()) {
                String tenTheLoai = rs.getString("TenTheLoai");
                switch (count) {
                    case 1:
                        TheLoai1.setText(tenTheLoai);
                        break;
                    case 2:
                        TheLoai2.setText(tenTheLoai);
                        break;
                    case 3:
                        TheLoai3.setText(tenTheLoai);
                        break;
                    case 4:
                        TheLoai4.setText(tenTheLoai);
                        break;
                    case 5:
                        TheLoai5.setText(tenTheLoai);
                        break;
                }
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void startProfile(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Account/TaiKhoan.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void ViewProfie(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        startProfile();
    }

    @FXML
    private void LogOut(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        openFXML("/Login/DangNhap.fxml");
    }

    @FXML
    private void Books(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        openFXML("/MainTrung/MuonSach.fxml");
    }

    @FXML
    private void Statistical(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        openFXML("/MainTrung/ThongKeSach.fxml");
    }

    public static void openFXML(String fxmlPath) {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(Menu.class.getResource(fxmlPath));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TinhTongSoLuong();
        TinhTongTheLoai();
        GanGiaTriTheLoai();
    }
}

