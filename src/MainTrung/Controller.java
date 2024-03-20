package MainTrung;

import Interface.Menu;
import LogIn.DangNhap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.ResourceBundle;



public class Controller implements Initializable {

    @FXML
    private TextField TextNhapTenSach;

    @FXML
    private TextField TextDocGiaMuonSach;

    @FXML
    private TextField textMaMuonSach;

    @FXML
    private DatePicker TextNgayHenTraSach;
    @FXML
    private TextField TextNgayHenTra;
    @FXML
    private DatePicker TextNgayMuonSach;

    @FXML
    private TextField TextTimKiemMaSach_tra;

    @FXML
    private TextField TextNhapSoLuongSachMuon;
    @FXML
    private TextField TextMaNhanVien;
    @FXML
    private ComboBox<String> textMaSach;
    @FXML
    private ComboBox<String> textSoThe;
    @FXML
    private ComboBox<String> TextDocGia;
    @FXML
    private TableColumn<TraSach, Date> cNgayHenTra_tra;

    @FXML
    private TableColumn<MuonSach, String> cMaNXB;

    @FXML
    private TableColumn<MuonSach, String> cMaSach;

    @FXML
    private TableColumn<MuonSach, String> cMaTacGia;

    @FXML
    private TableColumn<MuonSach, String> cMaTheLoai;
    @FXML
    private TextField TextTimKiemMaDocGia_tra;
    @FXML
    private TableColumn<MuonSach, Integer> cNamXB;

    @FXML
    private TableColumn<MuonSach, String> cTenSach;

    @FXML
    private TableView<MuonSach> tableMuonSach;

    @FXML
    private TextField textTimKiemSach;

    @FXML
    private TableColumn<TraSach, String> cMaMuonSach_tra;

    @FXML
    private TableColumn<TraSach, String> cMaSach_tra;
    @FXML
    private TableColumn<TraSach, Date> cNgayMuon_tra;
    @FXML
    private TableColumn<TraSach, Integer> cSoLuong_tra;
    @FXML
    private TableColumn<TraSach, String> cTenSach_tra;
    @FXML
    private TableColumn<TraSach, String> cMaDocGia_tra;
    @FXML
    private TextField textTimKiemSachTheoTen;

    @FXML
    private TableView<TraSach> tableTraSach;
    @FXML
    private TextField TextNgayMuon1;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;


    public ObservableList<MuonSach> DuLieuMuonSach() { //ObservableList: là cho phép người dùng theo dõi khi mà chúng có sự thay đổi
        ObservableList<MuonSach> CacQuyenSach = FXCollections.observableArrayList(); // Tạo ra 1 ObservableList rỗng mang tên là 'CacQuyenSach' để lưu trữ sách
        connect = ConnectSQLServer.connectDB();//kết nối sql
        try {
            MuonSach muonsachs;
            prepare = connect.prepareStatement("SELECT * FROM ViewSach "); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
            result = prepare.executeQuery();
            while (result.next()) { //Nó lặp qua tập kết quả và tạo một đối tượng Sach mới cho mỗi hàng, sử dụng các giá trị từ các cột làm tham số cho hàm tạo.
                muonsachs = new MuonSach(result.getString("MaSach"),
                        result.getString("TenSach"),
                        result.getString("TenTheLoai"),
                        result.getString("TenTacGia"),
                        result.getString("TenNXB"),
                        result.getInt("NamXB")
                );
                CacQuyenSach.add(muonsachs);//Nó thêm từng đối tượng Sách vào danh sách CacQuyenSach.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CacQuyenSach;//Nó trả về danh sách CacQuyenSach là đầu ra của hàm.
    }
    //-----------


    private ObservableList<MuonSach> DanhSachMuonSach;

    public void HienThiMuonSach() {
        DanhSachMuonSach = DuLieuMuonSach();
        cMaSach.setCellValueFactory(new PropertyValueFactory<MuonSach, String>("MaSach"));
        cTenSach.setCellValueFactory(new PropertyValueFactory<MuonSach, String>("TenSach"));
        cMaTacGia.setCellValueFactory(new PropertyValueFactory<MuonSach, String>("TenTacGia"));
        cMaTheLoai.setCellValueFactory(new PropertyValueFactory<MuonSach, String>("TenTheLoai"));
        cMaNXB.setCellValueFactory(new PropertyValueFactory<MuonSach, String>("TenNXB"));
        cNamXB.setCellValueFactory(new PropertyValueFactory<MuonSach, Integer>("NamXB"));
        tableMuonSach.setItems(DanhSachMuonSach);
    }

    public void SearchSach() {
        FilteredList<MuonSach> filteredData = new FilteredList<>(DanhSachMuonSach, b -> true);
        textTimKiemSach.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(muonsach -> {
                // If filter text is empty, display all books.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // Compare properties of the book with filter text.
                if (muonsach.getMaSach().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches book title.
                }
                return false; // Filter does not match any property.
            });
        });

        // Wrap the FilteredList in a SortedList.
        SortedList<MuonSach> sortedData = new SortedList<>(filteredData);

        // Bind the SortedList comparator to the TableView comparator.
        // Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableMuonSach.comparatorProperty());

        // Add sorted (and filtered) data to the table.
        tableMuonSach.setItems(sortedData);
    }

    public void SearchSachTheoTen() {
        FilteredList<MuonSach> filteredData = new FilteredList<>(DanhSachMuonSach, b -> true);
        textTimKiemSachTheoTen.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(muonsach -> {
                // If filter text is empty, display all books.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // Compare properties of the book with filter text.
                if (muonsach.getTenSach().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches book title.
                }
                return false; // Filter does not match any property.
            });
        });

        // Wrap the FilteredList in a SortedList.
        SortedList<MuonSach> sortedData = new SortedList<>(filteredData);

        // Bind the SortedList comparator to the TableView comparator.
        // Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableMuonSach.comparatorProperty());

        // Add sorted (and filtered) data to the table.
        tableMuonSach.setItems(sortedData);
    }

    public ObservableList<TraSach> DuLieuTraSach() { //ObservableList là cho phép người dùng theo dõi
        ObservableList<TraSach> CacQuyenSachMuon = FXCollections.observableArrayList(); // Tạo 1 ObservableList
        connect = ConnectSQLServer.connectDB(); // ket noi sql
        try {
            TraSach traSachs;
            prepare = connect.prepareStatement("SELECT * FROM MuonSach");
            result = prepare.executeQuery();
            while (result.next()) { // Lặp qua tập kết quả và tạo 1 đối tượng mới cho mỗi hàng
                traSachs = new TraSach(
                        result.getString("MaMuonSach"),
                        result.getString("MaSach"),
                        result.getDate("NgayMuon"),
                        result.getString("MaDocGia"),
                        result.getInt("SoLuong"),
                        result.getDate("NgayTra")
                );
                CacQuyenSachMuon.add(traSachs); // thêm từng đối tượng vào danh sách
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return CacQuyenSachMuon; // trả về danh sách là đầu ra

    }


    //                     IN    RA    DANH    SÁCH
    private ObservableList<TraSach> DanhSachTraSach;

    public void HienThiTraSach() {
        DanhSachTraSach = DuLieuTraSach();
        cMaMuonSach_tra.setCellValueFactory(new PropertyValueFactory<TraSach, String>("MaMuonSach"));
        cMaSach_tra.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
        cNgayMuon_tra.setCellValueFactory(new PropertyValueFactory<>("NgayMuon"));
        cMaDocGia_tra.setCellValueFactory(new PropertyValueFactory<>("MaDocGia"));
        cSoLuong_tra.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
        cNgayHenTra_tra.setCellValueFactory(new PropertyValueFactory<>("NgayTra"));
        tableTraSach.setItems(DanhSachTraSach);
    }


    public void MuonSachtoTraSach() {
        try {
            connect = ConnectSQLServer.connectDB();

            // Kiểm tra Mã mượn sách đã tồn tại hay chưa
            String checkQuery = "SELECT COUNT(*) FROM MuonSach WHERE MaMuonSach = ?";
            prepare = connect.prepareStatement(checkQuery);
            prepare.setString(1, textMaMuonSach.getText());
            ResultSet resultSet = prepare.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                // Mã mượn sách đã tồn tại
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Mã mượn sách đã tồn tại!");
                alert.showAndWait();
                return;
            }

            // Kiểm tra định dạng ngày mượn
            String ngayMuonText = TextNgayMuon1.getText();
            try {
                LocalDate ngayMuon = LocalDate.parse(ngayMuonText);

                // Kiểm tra nếu ngày mượn không phải là ngày hiện tại
                LocalDate ngayHienTai = LocalDate.now();
                if (!ngayMuon.isEqual(ngayHienTai)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Ngày mượn không phải là ngày hiện tại!");
                    alert.showAndWait();
                    return;
                }
            } catch (DateTimeParseException e) {
                // Ngày mượn có định dạng không hợp lệ
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Định dạng ngày mượn không hợp lệ! Vui lòng nhập theo định dạng yyyy-MM-dd.");
                alert.showAndWait();
                return;
            }


            // Kiểm tra định dạng ngày hẹn trả
            String ngayHenTraText = TextNgayHenTra.getText();
            try {
                LocalDate ngayHenTra = LocalDate.parse(ngayHenTraText);
            } catch (DateTimeParseException e) {
                // Ngày hẹn trả có định dạng không hợp lệ
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Định dạng ngày hẹn trả không hợp lệ! Vui lòng nhập theo định dạng yyyy-MM-dd.");
                alert.showAndWait();
                return;
            }

            // Kiểm tra ngày hẹn trả
            LocalDate ngayMuon = LocalDate.parse(ngayMuonText);
            LocalDate ngayHenTra = LocalDate.parse(ngayHenTraText);

            if (ngayHenTra.isBefore(ngayMuon)) {
                // Ngày hẹn trả không phải là tương lai
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Ngày hẹn trả phải là tương lai!");
                alert.showAndWait();
                return;
            }

            LocalDate ngayHenTraMax = ngayMuon.plusYears(2);
            if (ngayHenTra.isAfter(ngayHenTraMax)) {
                // Năm hẹn trả vượt quá 2 năm kể từ ngày mượn
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Năm hẹn trả vượt quá 2 năm kể từ ngày mượn!");
                alert.showAndWait();
                return;
            }



            // Kiểm tra số lượng sách trong database trước khi mượn
            String maSach = textMaSach.getValue();
            int soLuongMuon = Integer.parseInt(TextNhapSoLuongSachMuon.getText());
            int soLuongConLai = kiemTraSoLuongSachTrongKho(maSach);

            try {
                soLuongMuon = Integer.parseInt(TextNhapSoLuongSachMuon.getText());
                if (soLuongMuon <= 0) {
                    // Số lượng sách mượn không hợp lệ
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Số lượng sách mượn không hợp lệ! Vui lòng nhập số nguyên lớn hơn 0.");
                    alert.showAndWait();
                    return;
                }
            } catch (NumberFormatException e) {
                // Số lượng sách mượn không hợp lệ
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Số lượng sách mượn không hợp lệ! Vui lòng nhập số nguyên lớn hơn 0.");
                alert.showAndWait();
                return;
            }

            if (soLuongMuon > soLuongConLai) {
                // Số lượng sách mượn vượt quá số lượng sách sẵn có
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Số lượng sách mượn vượt quá số lượng sách sẵn có!");
                alert.showAndWait();
                return;
            }

            // Tiếp tục thêm vào cơ sở dữ liệu và xử lý logic phù hợp
            String sql = "INSERT INTO MuonSach(MaMuonSach, MaSach, NgayMuon, MaDocGia, SoLuong, NgayTra) VALUES (?, ?, ?, ?, ?, ?)";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, textMaMuonSach.getText());
            prepare.setString(2, textMaSach.getValue());
            prepare.setString(3, TextNgayMuon1.getText());
            prepare.setString(4, TextDocGia.getValue());
            prepare.setString(5, TextNhapSoLuongSachMuon.getText());
            prepare.setString(6, TextNgayHenTra.getText());

            prepare.executeUpdate();

            // Giảm số lượng sách trong kho
            truSoLuongSachTrongKho(maSach, soLuongMuon);

            // Xóa dữ liệu sau khi insert thành công
            textMaMuonSach.clear();
            textMaSach.setValue(null);
            TextNgayMuon1.clear();
            TextDocGia.setValue(null);
            TextNhapSoLuongSachMuon.clear();
            TextNgayHenTra.clear();

            // Hiển thị thông báo thành công
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Mượn sách thành công!");
            alert.showAndWait();

            HienThiTraSach();
            HienThiMuonSach();
            SearchMaDocGia();
            SearchSachTheoTen();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm kiểm tra số lượng sách trong kho
    public int kiemTraSoLuongSachTrongKho(String maSach) {
        try {
            connect = ConnectSQLServer.connectDB();
            String query = "SELECT SoLuong FROM Sach WHERE MaSach = ?";
            prepare = connect.prepareStatement(query);
            prepare.setString(1, maSach);
            ResultSet resultSet = prepare.executeQuery();
            resultSet.next();
            int soLuongConLai = resultSet.getInt("SoLuong");
            return soLuongConLai;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void truSoLuongSachTrongKho(String maSach, int soLuongMuon) {
        try {
            connect = ConnectSQLServer.connectDB();
            String query = "UPDATE Sach SET SoLuong = SoLuong - ? WHERE MaSach = ?";
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, soLuongMuon);
            prepare.setString(2, maSach);
            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Chon Mã Sách
    public void ChonMaSach() {
        try {
            connect = ConnectSQLServer.connectDB();
            String sql = "SELECT * FROM Sach";
            prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
            result = prepare.executeQuery();
            ObservableList<String> list = FXCollections.observableArrayList();
            while (result.next()) {
                list.add(result.getString("MaSach"));
            }
            textMaSach.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Chọn mã độc giả
    public void ChonMaDocGia() {
        try {
            connect = ConnectSQLServer.connectDB();
            String sql = "SELECT * FROM DocGia";
            prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
            result = prepare.executeQuery();
            ObservableList<String> list = FXCollections.observableArrayList();
            while (result.next()) {
                list.add(result.getString("MaDocGia"));
            }
            TextDocGia.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void SearchMaDocGia() {
        FilteredList<TraSach> filteredData = new FilteredList<>(DanhSachTraSach, b -> true);
        TextTimKiemMaDocGia_tra.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(traSach -> {
                // If filter text is empty, display all books.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // Compare properties of the book with filter text.
                if (traSach.getMaDocGia().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches book title.
                }
                return false; // Filter does not match any property.
            });
        });
        // Wrap the FilteredList in a SortedList.
        SortedList<TraSach> sortedData = new SortedList<>(filteredData);

        // Bind the SortedList comparator to the TableView comparator.
        // Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableTraSach.comparatorProperty());

        // Add sorted (and filtered) data to the table.
        tableTraSach.setItems(sortedData);
    }

    public void TraSachDangMuon() {
        try {
            connect = ConnectSQLServer.connectDB();

            // Lấy thông tin sách cần trả
            String maMuonSach = TextTimKiemMaSach_tra.getText();
            String maSach = "";
            int soLuongMuon = 0;

            // Kiểm tra số lượng sách mượn trước khi xóa
            String checkSql = "SELECT MaSach, SoLuong FROM MuonSach WHERE MaMuonSach = ?";
            prepare = connect.prepareStatement(checkSql);
            prepare.setString(1, maMuonSach);
            ResultSet rs = prepare.executeQuery();
            if (rs.next()) {
                maSach = rs.getString("MaSach");
                soLuongMuon = rs.getInt("SoLuong");
            }
            rs.close();
            prepare.close();

            // Xóa thông tin sách đã trả
            String deleteSql = "DELETE FROM MuonSach WHERE MaMuonSach = ?";
            prepare = connect.prepareStatement(deleteSql);
            prepare.setString(1, maMuonSach);
            int rowsAffected = prepare.executeUpdate();
            prepare.close();

            if (rowsAffected > 0) {
                // Trả sách thành công, cập nhật số lượng sách trong kho
                congSoLuongSachVaoKho(maSach, soLuongMuon);

                // Hiển thị thông báo thành công
                System.out.println("Đã trả sách có mã mượn sách: " + maMuonSach);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo");
                alert.setHeaderText(null);
                alert.setContentText("Đã trả sách có mã mượn sách: " + maMuonSach);
                alert.showAndWait();

                TextTimKiemMaDocGia_tra.clear();
                TextTimKiemMaSach_tra.clear();

                HienThiTraSach();
                HienThiMuonSach();
                SearchMaDocGia();
            } else {
                // Không tìm thấy sách để xóa
                System.out.println("Không tìm thấy Độc Giả có mã mượn sách: " + maMuonSach);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo");
                alert.setHeaderText(null);
                alert.setContentText("Không tìm thấy Độc Giả có mã mượn sách: " + maMuonSach);
                alert.showAndWait();
            }

            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Phương thức cộng lại số lượng sách vào kho
    private void congSoLuongSachVaoKho(String maSach, int soLuongMuon) {
        try {
            connect = ConnectSQLServer.connectDB();
            String sql = "UPDATE Sach SET SoLuong = SoLuong + ? WHERE MaSach = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, soLuongMuon);
            prepare.setString(2, maSach);
            prepare.executeUpdate();
            prepare.close();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void AnHienThiThongTin() {
        MuonSach dulieudocgia = tableMuonSach.getSelectionModel().getSelectedItem();
        int num = tableMuonSach.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        textMaSach.setValue(dulieudocgia.getMaSach());
    }

    public void AnHienThiThongTin_traSach() {
        TraSach dulieutrasach = tableTraSach.getSelectionModel().getSelectedItem();
        int num = tableTraSach.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        TextTimKiemMaSach_tra.setText(dulieutrasach.getMaMuonSach());
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){

            HienThiMuonSach();
            HienThiTraSach();
            SearchSachTheoTen();
            SearchMaDocGia();
            ChonMaSach();
            ChonMaDocGia();
    }

    public void TenDangNhapTaiKhoan(){
        String userSuccessfulLogin = DangNhap.getUserNameCorrect();
    }
    @FXML
    private void LogOut(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Interface.Menu.openFXML("/Login/DangNhap.fxml");
    }

    @FXML
    private void HomeUser(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Interface.Menu.openFXML("/Interface/HomeUser.fxml");
    }

    @FXML
    private void Account(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Interface.Menu.openFXML("/Account/TaiKhoan.fxml");
    }

    @FXML
    private void Statistical(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/MainTrung/ThongKeSach.fxml");
    }
}















