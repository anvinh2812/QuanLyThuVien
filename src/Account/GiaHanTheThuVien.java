package Account;

import ConnectionDataBase.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GiaHanTheThuVien {
    @FXML
    private TextField HoTenGH;

    @FXML
    private TextField MSSVGH;

    @FXML
    private TextField SoTheGH;

    @FXML
    private TextField TenDangNhapGH;

    public boolean kiemTraDuLieu() {
        String hoTen = HoTenGH.getText();
        String tenDangNhap = TenDangNhapGH.getText();

        DBConnection connectionDB = new DBConnection();
        ResultSet resultSet = null;
        boolean dataExists = false;

        try {
            String sql = "SELECT * FROM DocGia WHERE TenDocGia = ? AND TenDangNhap = ?";
            PreparedStatement statement = connectionDB.prepareStatement(sql);
            statement.setString(1, hoTen);
            statement.setString(2, tenDangNhap);

            // Thực hiện truy vấn
            resultSet = statement.executeQuery();

            // Kiểm tra xem dữ liệu có tồn tại trong bảng DocGia và trên cùng một dòng hay không
            if (resultSet.next()) {
                String tenDocGia = resultSet.getString("TenDocGia");
                String tenDangNhapDB = resultSet.getString("TenDangNhap");

                // Kiểm tra xem dữ liệu lấy ra từ cơ sở dữ liệu có trùng khớp với dữ liệu từ TextField không
                if (tenDocGia.equals(hoTen) && tenDangNhapDB.equals(tenDangNhap)) {
                    dataExists = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Trả về kết quả kiểm tra
        return dataExists;
    }

    public boolean kiemTraDuLieu2() {
        // Lấy dữ liệu từ TextField
        String mssv = MSSVGH.getText();
        String soThe = SoTheGH.getText();
        String tenDangNhap = TenDangNhapGH.getText();

        // Kết nối đến cơ sở dữ liệu
        DBConnection connectionDB = new DBConnection();
        ResultSet resultSet = null;
        boolean dataExists = false;

        try {
            // Chuẩn bị truy vấn SQL để kiểm tra dữ liệu
            String sql = "SELECT * FROM TheThuVien WHERE MSSV = ? AND SoThe = ? AND TenDangNhap = ?";
            PreparedStatement statement = connectionDB.prepareStatement(sql);
            statement.setString(1, mssv);
            statement.setString(2, soThe);
            statement.setString(3, tenDangNhap);

            // Thực hiện truy vấn
            resultSet = statement.executeQuery();

            // Kiểm tra xem dữ liệu có tồn tại trong bảng TheThuVien và trên cùng một dòng hay không
            if (resultSet.next()) {
                String mssvDB = resultSet.getString("MSSV");
                String soTheDB = resultSet.getString("SoThe");
                String tenDangNhapDB = resultSet.getString("TenDangNhap");

                // Kiểm tra xem dữ liệu lấy ra từ cơ sở dữ liệu có trùng khớp với dữ liệu từ TextField không
                if (mssvDB.equals(mssv) && soTheDB.equals(soThe) && tenDangNhapDB.equals(tenDangNhap)) {
                    dataExists = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Trả về kết quả kiểm tra
        return dataExists;
    }

    public boolean kiemTraNgayKetThuc() {
        // Lấy dữ liệu từ TextField
        String soThe = SoTheGH.getText();

        // Kết nối đến cơ sở dữ liệu
        DBConnection connectionDB = new DBConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isExpired = false;

        try {
            // Chuẩn bị truy vấn SQL để lấy ngày kết thúc
            String sql = "SELECT NgayKetThuc FROM TheThuVien WHERE SoThe = ?";
            statement = connectionDB.prepareStatement(sql);
            statement.setString(1, soThe);

            // Thực hiện truy vấn
            resultSet = statement.executeQuery();

            // Kiểm tra xem ngày kết thúc đã quá ngày hôm nay chưa
            if (resultSet.next()) {
                LocalDate ngayKetThuc = resultSet.getDate("NgayKetThuc").toLocalDate();
                LocalDate ngayHienTai = LocalDate.now();

                if (ngayKetThuc.isBefore(ngayHienTai)) {
                    isExpired = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        // Trả về kết quả kiểm tra
        return isExpired;
    }


    public void GiaHan() {
        boolean ketQua1 = kiemTraDuLieu();
        System.out.println("Kết quả kiểm tra: " + ketQua1);
        boolean ketQua2 = kiemTraDuLieu2();
        System.out.println("Kết quả kiểm tra: " + ketQua2);
        boolean kiemtra = kiemTraNgayKetThuc();
        System.out.println("Kết quả kiểm tra: " + kiemtra);
        if(ketQua1 == true && ketQua2 == true && kiemtra == true){
            String soThe = SoTheGH.getText();

            // Kết nối đến cơ sở dữ liệu
            DBConnection connectionDB = new DBConnection();
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                // Chuẩn bị truy vấn SQL để lấy ngày kết thúc
                String sql = "SELECT NgayKetThuc FROM TheThuVien WHERE SoThe = ?";
                statement = connectionDB.prepareStatement(sql);
                statement.setString(1, soThe);

                // Thực hiện truy vấn
                resultSet = statement.executeQuery();

                // Kiểm tra xem ngày kết thúc đã tồn tại hay không
                if (resultSet.next()) {
                    LocalDate ngayKetThuc = resultSet.getDate("NgayKetThuc").toLocalDate();
                    LocalDate ngayKetThucMoi = ngayKetThuc.plusYears(1);

                    // Format ngày kết thúc mới
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String ngayKetThucMoiFormatted = ngayKetThucMoi.format(formatter);

                    // Cập nhật ngày kết thúc mới vào cơ sở dữ liệu
                    String updateSql = "UPDATE TheThuVien SET NgayKetThuc = ? WHERE SoThe = ?";
                    PreparedStatement updateStatement = connectionDB.prepareStatement(updateSql);
                    updateStatement.setString(1, ngayKetThucMoiFormatted);
                    updateStatement.setString(2, soThe);

                    // Thực hiện cập nhật
                    updateStatement.executeUpdate();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo Cho Bạn");
                alert.setHeaderText(null);
                alert.setContentText("Gia Hạn Thẻ Thành Công");
                alert.showAndWait();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông Báo Cho Bạn");
            alert.setHeaderText(null);
            alert.setContentText("Gia Hạn Thẻ Không Thành Công");
            alert.showAndWait();
        }
    }


    public void Huy(javafx.event.ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
