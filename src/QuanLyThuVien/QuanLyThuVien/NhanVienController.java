package QuanLyThuVien.QuanLyThuVien;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

import Interface.Menu;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import java.util.List;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NhanVienController implements Initializable {
    LocalDate NgayThanhNamHienTai = LocalDate.now();
    int NamHienTai = NgayThanhNamHienTai.getYear();
    private SimpleDateFormat DateFormat;
    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    public Alert alert;
    @FXML
    private TableView<NhanVien> BangNhanVien;
    @FXML
    private TableColumn<NhanVien, String> cGioiTinhNhanVien;
    @FXML
    private TableColumn<NhanVien, String> cMaNhanVien;

    @FXML
    private TableColumn<NhanVien, Date> cNgaySinhNV;

    @FXML
    private TableColumn<NhanVien, Integer> cSoDienThoaiNV;

    @FXML
    private TableColumn<NhanVien, String> cTenDangNhap;

    @FXML
    private TableColumn<NhanVien, String> cTenNhanVien;
    @FXML
    private VBox Card_LayoutNhanVien;
    @FXML
    private Label ttct_MaNhanVien;

    @FXML
    private Label ttct_NgaySinhNV;

    @FXML
    private Label ttct_SDTNV;

    @FXML
    private Label ttct_TenDangNhap;

    @FXML
    private Label ttct_TenNhanVien;
    @FXML
    private TextField TimKiem_NhanVien;
//    @FXML
//    private ComboBox<String> NhapNhanVien_GioiTinh;
@FXML
private ChoiceBox<String> NhapNhanVien_GioiTinh;
    @FXML
    private ImageView NhapNhanVien_AnhNhanVien;

    @FXML
    private TextField NhapNhanVien_MaNhanVien;

    @FXML
    private DatePicker NhapNhanVien_NgaySinhNV;

    @FXML
    private TextField NhapNhanVien_SDTNV;

    @FXML
    private TextField NhapNhanVien_TenDangNhap;

    @FXML
    private TextField NhapNhanVien_TenNhanVien;

    @FXML
    private AnchorPane Card_BoxNhanVien;

    @FXML
    private ImageView Card_IMGNhanVien;

    @FXML
    private Label Card_NgaySinh;

    @FXML
    private Label Card_SDT;

    @FXML
    private Label Card_TenNhanVien;

    @FXML
    private Button BTN_HuyNhanVien;

    @FXML
    private Button BTN_IMGNhanVien;

    @FXML
    private Button BTN_SuaNhanVien;

    @FXML
    private Button BTN_ThemNhanVien;

    @FXML
    private Button BTN_XoaNhanVien;

    public ObservableList<NhanVien> NhanVienDaThemGanDay(){
        ObservableList<NhanVien> ls = FXCollections.observableArrayList();
        try {
            connect = ConnectSQL.connectDB();
            String sql = "SELECT * FROM NhanVien";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                NhanVien nhanVien = new NhanVien();
                nhanVien.setTenNhanVien(result.getString("TenNhanVien"));
                nhanVien.setSDT(Integer.valueOf(result.getString("SDT").toString()));
                nhanVien.setNgaySinh(java.sql.Date.valueOf(result.getString("NgaySinh")));
                ls.add(nhanVien);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    private List<NhanVien> NhanVienDaThemGanDay;
    public void HienThiNhanVienDaThemGanDay(){
        NhanVienDaThemGanDay = new ArrayList<>(NhanVienDaThemGanDay());

        try {
            for (int i = 0; i< NhanVienDaThemGanDay.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("CardNhanVien.fxml"));
                Card_BoxNhanVien = fxmlLoader.load();
                CardNhanVienController cardnhanviencontroller = fxmlLoader.getController();
                cardnhanviencontroller.setdatanhanvien(NhanVienDaThemGanDay.get(i));
                Card_LayoutNhanVien.getChildren().add(Card_BoxNhanVien);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public ObservableList<NhanVien> DuLieuNhanVien() {
        ObservableList<NhanVien> CacNhanVien = FXCollections.observableArrayList();
        String sql = "SELECT * FROM NhanVien";
        connect = ConnectSQL.connectDB();
        try {
            NhanVien nhanviens;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) { //Nó lặp qua tập kết quả và tạo một đối tượng NhanVien mới cho mỗi hàng, sử dụng các giá trị từ các cột làm tham số cho hàm tạo.
                nhanviens = new NhanVien(
                        result.getString("MaNhanVien"),
                        result.getString("TenNhanVien"),
                        result.getDate("NgaySinh"),
                        result.getInt("SDT"),
                        result.getString("TenDangNhap"),
                        result.getString("GioiTinh")
                );
                CacNhanVien.add(nhanviens);//Nó thêm từng đối tượng Nhân Viên vào danh Nhân Viên CacNhanVien
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return CacNhanVien;
    }

    public void ChonGioiTinhNhanVien() {
        ObservableList<String> list = FXCollections.observableArrayList("Nam", "Nu");
        NhapNhanVien_GioiTinh.setItems(list);
    }
    private ObservableList<NhanVien> DanhSachNhanVien;
    public void HienThiNhanVien(){
        HienThiNhanVienDaThemGanDay();
        DanhSachNhanVien = DuLieuNhanVien();
        cMaNhanVien.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("MaNhanVien"));
        cTenNhanVien.setCellValueFactory(new PropertyValueFactory<NhanVien, String >("TenNhanVien"));
        cNgaySinhNV.setCellValueFactory(new PropertyValueFactory<NhanVien, Date>("NgaySinh"));
        cSoDienThoaiNV.setCellValueFactory(new PropertyValueFactory<NhanVien, Integer>("SDT"));
        cTenDangNhap.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("TenDangNhap"));
        cGioiTinhNhanVien.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("GioiTinh"));
        BangNhanVien.setItems(DanhSachNhanVien);
        TimKiem_NhanVien();
    }
    private ObservableList<NhanVien> DanhSachNhanVien_TTCT;
    public void ChonNhanVien_TTCT(){
        NhanVien dulieunhanvien = BangNhanVien.getSelectionModel().getSelectedItem();
        DanhSachNhanVien_TTCT = DuLieuNhanVien();
        int num = BangNhanVien.getSelectionModel().getFocusedIndex();
        if ((num - 1) < -1) {
            return ;
        }
//        ttct_MaNhanVien.setText(dulieunhanvien.getMaNhanVien());
//        ttct_TenNhanVien.setText(dulieunhanvien.getTenNhanVien());
//        ttct_TenDangNhap.setText(dulieunhanvien.getTenDangNhap());
//        ttct_NgaySinhNV.setText(String.valueOf(dulieunhanvien.getNgaySinh()));
//        ttct_SDTNV.setText(String.valueOf(dulieunhanvien.getSDT()));
        NhapNhanVien_MaNhanVien.setText(dulieunhanvien.getMaNhanVien());
        NhapNhanVien_TenNhanVien.setText(dulieunhanvien.getTenNhanVien());
        NhapNhanVien_NgaySinhNV.setValue(dulieunhanvien.getNgaySinh().toLocalDate());
        NhapNhanVien_TenDangNhap.setText(dulieunhanvien.getTenDangNhap());
        NhapNhanVien_SDTNV.setText(String.valueOf(dulieunhanvien.getSDT()));
        NhapNhanVien_GioiTinh.setValue(String.valueOf(dulieunhanvien.getGioiTinh()));

    }
    public void TimKiem_NhanVien(){
        FilteredList<NhanVien> filteredData = new FilteredList<>(DanhSachNhanVien, b -> true);
        TimKiem_NhanVien.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (employee.getMaNhanVien().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (employee.getTenNhanVien().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (String.valueOf(employee.getTenDangNhap()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false; // Does not match.
            });
        });
        SortedList<NhanVien> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(BangNhanVien.comparatorProperty());
        BangNhanVien.setItems(sortedData);
    }
    public void HuyNhanVien(){

        if(NhapNhanVien_MaNhanVien.getText().isEmpty() && NhapNhanVien_TenNhanVien.getText().isEmpty() &&
                NhapNhanVien_TenDangNhap.getText().isEmpty() && NhapNhanVien_SDTNV.getText().isEmpty()) {
            System.out.println("Không Có Gì Để Hủy Hết");
            alert = new Alert(AlertType.WARNING);
            alert.setTitle("Thông Báo Cho Bạn");
            alert.setHeaderText(null);
            alert.setContentText("Không Có Gì Để Hủy Hết");
            alert.showAndWait();
        } else {

            NhapNhanVien_MaNhanVien.setText("");
            NhapNhanVien_TenNhanVien.setText("");
            NhapNhanVien_TenDangNhap.setText("");
            NhapNhanVien_SDTNV.setText("");
            NhapNhanVien_AnhNhanVien.setImage(null);
            NhapNhanVien_NgaySinhNV.setValue(null);

            System.out.println("Đã Hủy Thành Công");
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông Báo Cho Bạn");
            alert.setHeaderText(null);
            alert.setContentText("Đã Hủy Thành Công");
            alert.showAndWait();
        }
    }
    public void ThemNhanVien(){
        try {
            connect = ConnectSQL.connectDB();
            String sql_Nhap = "INSERT INTO NhanVien (MaNhanVien, TenNhanVien, NgaySinh, SDT, TenDangNhap) VALUES (?, ?, ?, ?, ?)\n"; /*imgSach,*/
//                    }
            String sql_check = "SELECT * FROM NhanVien Where MaNhanVien = ?";
            //---------------------------------------------------
            prepare = connect.prepareStatement(sql_check);
            prepare.setString(1, NhapNhanVien_MaNhanVien.getText());
            result = prepare.executeQuery();
            // Kiểm tra xem kết quả có rỗng hay không

                if (result.next()) {
                    // Nếu không rỗng, tức là đã có dữ liệu trùng trong cơ sở dữ liệu
                    // Hiển thị thông báo trùng bằng Alert hoặc Label
                    Alert alert = new Alert (Alert.AlertType.ERROR);
                    alert.setTitle ("Thông Báo Cho Bạn");
//                alert.setHeaderText ("Dữ liệu trùng");
                    alert.setContentText ("Nhân Viên đã tồn tại trong cơ sở dữ liệu");
                    alert.showAndWait();
                } else {
                    //---------------------------------------------------
                    String NoiDungConThieu = "";
                    if (NhapNhanVien_MaNhanVien.getText().isEmpty()) {
                        NoiDungConThieu += "Nhập Mã Nhân Viên.\n";
                    }
                    if (NhapNhanVien_TenNhanVien.getText().isEmpty()) {
                        NoiDungConThieu += "Nhập Tên Nhân Viên.\n";
//                } if (NhapSach_ChonMaNXB.getValue() == null) {
//                    NoiDungConThieu += "Nhap Ma NXB.\n";
                    }
                    if (NhapNhanVien_NgaySinhNV.getValue() == null) {
                        NoiDungConThieu += "Nhập Ngày Sinh Nhân Viên.\n";
                    }
                    if (NhapNhanVien_SDTNV.getText().isEmpty()) {
                        NoiDungConThieu += "Nhập Số Điện Thoại Nhân Viên.\n";
                    }
                    if (NhapNhanVien_TenDangNhap.getText().isEmpty()) {
                        NoiDungConThieu += "Nhập Tên Đăng Nhập Của Nhân Viên.\n";
//                }if (NhapNhanVien_NgaySinhNV.getValue() == null){
//                    NoiDungConThieu += "Nhập Ngày Sinh Nhân Viên.\n";
                    }
                    if (!NoiDungConThieu.isEmpty()) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Thông Báo Cho Bạn");
                        alert.setHeaderText(null);
                        alert.setContentText(NoiDungConThieu);
                        alert.showAndWait();
                    } else if(Integer.parseInt(NhapNhanVien_SDTNV.getText()) < 0 ){
                        Alert alert = new Alert (Alert.AlertType.ERROR);
                        alert.setTitle ("Thông Báo Cho Bạn");
                        alert.setContentText ("Số Điện Thoại Không Hợp Lệ");
                        alert.showAndWait();
                        //NhapSach_SoLuong.setText("");
                    }
                    else if (NhapNhanVien_NgaySinhNV.getValue().isAfter(NgayThanhNamHienTai)) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Thông Báo Cho Bạn");
                            alert.setContentText("Ngày Sinh Không Hợp Lệ");
                            alert.initOwner(NhapNhanVien_NgaySinhNV.getScene().getWindow()); // Set the parent window
                            alert.showAndWait();
                        });
                        return;
                    }else {
                        prepare = connect.prepareStatement(sql_Nhap);
                        prepare.setString(1, NhapNhanVien_MaNhanVien.getText());
                        prepare.setString(2, NhapNhanVien_TenNhanVien.getText());
                        prepare.setString(3, NhapNhanVien_NgaySinhNV.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
                        prepare.setString(4, NhapNhanVien_SDTNV.getText());
                        prepare.setString(5, NhapNhanVien_TenDangNhap.getText());

//                        prepare.setString(9, NhapSach_ChonTheLoai.getValue());
                        System.out.println("Đã Thêm Thành Công Nhân Viên Có Mã: " + NhapNhanVien_MaNhanVien.getText());
                        prepare.executeUpdate();
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Thông Báo Cho Bạn");
                        alert.setHeaderText(null);
                        alert.setContentText("Đã Thêm Thành Công Nhân Viên Có Mã: " + NhapNhanVien_MaNhanVien.getText());
                        alert.showAndWait();
                        HienThiNhanVien();
                    }
                }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void SuaNhanVien(){
        try {
            String sql = "UPDATE NhanVien SET TenNhanVien = ?, NgaySinh = ?, SDT = ?, TenDangNhap = ?, GioiTinh = ? WHERE MaNhanVien = ?";
            connect = ConnectSQL.connectDB();

                String NoiDungConThieu = "";
                if (NhapNhanVien_MaNhanVien.getText().isEmpty()) {
                    NoiDungConThieu += "Thiếu Mã Nhân Viên Để Sửa.\n";
                }
                if (NhapNhanVien_TenNhanVien.getText().isEmpty()) {
                    NoiDungConThieu += "Thiếu Tên Nhân Viên Để Sửa.\n";
                }
                if (NhapNhanVien_TenDangNhap.getText().isEmpty()) {
                    NoiDungConThieu += "Thiếu Tên Đăng Nhập Để Sửa.\n";
                }
                if (NhapNhanVien_SDTNV.getText().isEmpty()) {
                    NoiDungConThieu += "Thiếu Số Điện Thoại Để Sửa.\n";
                }
                if (NhapNhanVien_NgaySinhNV.getValue() == null) {
                    NoiDungConThieu += "Thiếu Ngày Sinh Nhân Viên Để Sửa.\n";
                }
                if (!NoiDungConThieu.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText(NoiDungConThieu);
                    alert.showAndWait();
                }else if(Integer.parseInt(NhapNhanVien_SDTNV.getText()) < 0 ){
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle ("Thông Báo Cho Bạn");
                alert.setContentText ("Số Điện Thoại Không Hợp Lệ");
                alert.showAndWait();
                //NhapSach_SoLuong.setText("");
                }
                else if (NhapNhanVien_NgaySinhNV.getValue().isAfter(NgayThanhNamHienTai)) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Thông Báo Cho Bạn");
                        alert.setContentText("Ngày Sinh Không Hợp Lệ");
                        alert.initOwner(NhapNhanVien_NgaySinhNV.getScene().getWindow()); // Set the parent window
                        alert.showAndWait();
                    });
                    return;
                }else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(6, NhapNhanVien_MaNhanVien.getText());
                    prepare.setString(1, NhapNhanVien_TenNhanVien.getText());
                    prepare.setString(2, NhapNhanVien_NgaySinhNV.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
                    prepare.setString(3, NhapNhanVien_SDTNV.getText());
                    prepare.setString(4, NhapNhanVien_TenDangNhap.getText());
                    prepare.setString(5, NhapNhanVien_GioiTinh.getValue());
                    int rowsAffected = prepare.executeUpdate();
                    if (rowsAffected > 0) {
                        // Xóa thành công
                        System.out.println("Đã Sửa Nhân Viên Có Mã: " + NhapNhanVien_MaNhanVien.getText());
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Thông Báo Cho Bạn");
                        alert.setHeaderText(null);
                        alert.setContentText("Đã Sửa Nhân Viên Có Mã: " + NhapNhanVien_MaNhanVien.getText());
                        alert.showAndWait();
                    } else {
                        // Không tìm thấy Nhân Viên để xóa
                        System.out.println("Không tìm thấy Nhân Viên có mã : " + NhapNhanVien_MaNhanVien.getText());
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Thông Báo Cho Bạn");
                        alert.setHeaderText(null);
                        alert.setContentText("Không tìm thấy Nhân Viên có mã: " + NhapNhanVien_MaNhanVien.getText());
                        alert.showAndWait();
                    }
                }
//            prepare.executeUpdate();
//            System.out.println("Đã Sửa Thành Công");
//            alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Thông Báo Cho Bạn");
//            alert.setHeaderText(null);
//            alert.setContentText("Đã Sửa Thành Công");
//            alert.showAndWait();
            HienThiNhanVien();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void XoaNhanVien(){
        try {
            String sql = "DELETE FROM NhanVien WHERE MaNhanVien = ?";
            connect = ConnectSQL.connectDB();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, NhapNhanVien_MaNhanVien.getText());

            int rowsAffected = prepare.executeUpdate();
            if (rowsAffected > 0) {
                // Xóa thành công
                System.out.println("Đã xóa Nhân Viên có mã: " + NhapNhanVien_MaNhanVien.getText());
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Thông Báo Cho Bạn");
                alert.setHeaderText(null);
                alert.setContentText("Đã xóa Nhân Viên có mã: "  + NhapNhanVien_MaNhanVien.getText());
                alert.showAndWait();
            } else {
                // Không tìm thấy Nhân Viên để xóa
                System.out.println("Không tìm thấy Nhân Viên có mã: " + NhapNhanVien_MaNhanVien.getText());
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Thông Báo Cho Bạn");
                alert.setHeaderText(null);
                alert.setContentText("Không tìm thấy Nhân Viên có mã: " + NhapNhanVien_MaNhanVien.getText());
                alert.showAndWait();
            }
            HienThiNhanVien();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HienThiNhanVien();
    }

    @FXML
    private void HomeAdmin(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/Interface/HomeAdmin.fxml");
    }

    @FXML
    private void Books(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/QuanLyThuVien/QuanLyThuVien/Sach.fxml");
    }

    @FXML
    private void ThongKe(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/MainPhuc/main/qltv/ThongKeTong.fxml");
    }

    @FXML
    private void DocGia(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/QuanLyThuVien/QuanLyThuVien/DocGia.fxml");
    }

    @FXML
    private void LogOut(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/Login/DangNhap.fxml");
    }
}
