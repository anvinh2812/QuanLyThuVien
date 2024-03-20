package QuanLyThuVien.QuanLyThuVien;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
public class TheLoaiController implements Initializable {
    LocalDate NgayThanhNamHienTai = LocalDate.now();
    int NamHienTai = NgayThanhNamHienTai.getYear();
    private SimpleDateFormat DateFormat;
    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    public Alert alert;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button BTN_ThemTheLoai;

    @FXML
    private TableView<TheLoai> BangTheLoai;
    @FXML
    private TableColumn<TheLoai, String> cr_MaTheLoai;
    @FXML
    private TableColumn<TheLoai, String> cr_TenTheLoai;
    @FXML
    private TextField NhapTheLoai_MaTheLoai;

    @FXML
    private TextField NhapTheLoai_TenTheLoai;
    public void ThemTheLoai() {
        try {
            connect = ConnectSQL.connectDB();
            String sql_Nhap = "INSERT INTO TheLoai (MaTheLoai, TenTheLoai) VALUES ( ?, ?)\n";
            String sql_check = "SELECT * FROM TheLoai Where MaTheLoai = ?";
            //---------------------------------------------------
            prepare = connect.prepareStatement(sql_check);
            prepare.setString(1, NhapTheLoai_MaTheLoai.getText());
            result = prepare.executeQuery();
            //---------------------------------------------------
            LocalDate NgayThanhNamHienTai = LocalDate.now();
            int NamHienTai = NgayThanhNamHienTai.getYear();
            System.out.println("Ngày tháng năm hiện tại:" + NgayThanhNamHienTai);
            System.out.println("Năm hiện tại: " + NamHienTai);
            //---------------------------------------------------
            if (result.next()) {
                // Nếu không rỗng, tức là đã có dữ liệu trùng trong cơ sở dữ liệu
                // Hiển thị thông báo trùng bằng Alert hoặc Label
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo Cho Bạn");
//                alert.setHeaderText ("Dữ liệu trùng");
                alert.setContentText("Thể Loại đã tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            }else {
                //---------------------------------------------------
                String NoiDungConThieu = "";
                if (NhapTheLoai_MaTheLoai.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Mã Độc Giả.\n";
                }
                if (NhapTheLoai_TenTheLoai.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Tên Độc Giả.\n";
                }
                if (!NoiDungConThieu.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText(NoiDungConThieu);
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql_Nhap);
                    prepare.setString(1, NhapTheLoai_MaTheLoai.getText());
                    prepare.setString(2, NhapTheLoai_TenTheLoai.getText());
//                        prepare.setString(9, NhapSach_ChonTheLoai.getValue());
                    System.out.println("Đã Thêm Thành Công Tác Giả Có Mã: " + NhapTheLoai_MaTheLoai.getText());
                    prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Đã Thêm Thành Công Tác Giả Có Mã: " + NhapTheLoai_MaTheLoai.getText());
                    alert.showAndWait();
                }
            }
            HienThiTheLoaiR();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SuaTheLoai(){
        try {
            String sql = "UPDATE TheLoai SET TenTheLoai = ? WHERE MaTheLoai = ?";
            connect = ConnectSQL.connectDB();
            prepare = connect.prepareStatement(sql);

            String sql_check = "SELECT * FROM TheLoai WHERE MaTheLoai = ?";
            PreparedStatement prepareCheck = connect.prepareStatement(sql_check);
            prepareCheck.setString(1, NhapTheLoai_MaTheLoai.getText());
            ResultSet resultCheck = prepareCheck.executeQuery();
            if (!resultCheck.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo Cho Bạn");
                alert.setContentText("Thể Loại Không tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            } else {
                String NoiDungConThieu = "";
                if (NhapTheLoai_TenTheLoai.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Mã NXB.\n";
                }
                if (!NoiDungConThieu.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText(NoiDungConThieu);
                    alert.showAndWait();
                }else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(2, NhapTheLoai_MaTheLoai.getText());
                    prepare.setString(1, NhapTheLoai_TenTheLoai.getText());
                }
                //---------------------------
                int rowsAffected = prepare.executeUpdate();
                if (rowsAffected > 0) {
                    // Xóa thành công
                    System.out.println("Đã Sửa Độc Giả Có Mã: " + NhapTheLoai_MaTheLoai.getText());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Đã Sửa Độc Giả Có Mã: " + NhapTheLoai_MaTheLoai.getText());
                    alert.showAndWait();
                } else {
                    // Không tìm thấy Độc Giả để xóa
                    System.out.println("Không tìm thấy Độc Giả có mã : " + NhapTheLoai_MaTheLoai.getText());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Không tìm thấy Độc Giả có mã: " + NhapTheLoai_MaTheLoai.getText());
                    alert.showAndWait();
                }
                //------------------------------
                HienThiTheLoaiR();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void ChonNXB_TTCT() {
        TheLoai dulieutheloai = BangTheLoai.getSelectionModel().getSelectedItem();
        int num = BangTheLoai.getSelectionModel().getFocusedIndex();
        if ((num - 1) < -1) {
            return;
        }
        NhapTheLoai_MaTheLoai.setText(dulieutheloai.getMaTheLoai());
        NhapTheLoai_TenTheLoai.setText(dulieutheloai.getTenTheLoai());
    }
    public ObservableList<TheLoai> DuLieuTheLoai() { //ObservableList: là cho phép người dùng theo dõi khi mà chúng có sự thay đổi
        ObservableList<TheLoai> CacTheLoai = FXCollections.observableArrayList(); // Tạo ra 1 ObservableList rỗng mang tên là 'CacQuyenSach' để lưu trữ sách
        String sql = "SELECT * FROM TheLoai";
        connect = ConnectSQL.connectDB();//kết nối sql
        try {
            TheLoai theLoais;
            prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
            result = prepare.executeQuery();
            while (result.next()) { //Nó lặp qua tập kết quả và tạo một đối tượng Sach mới cho mỗi hàng, sử dụng các giá trị từ các cột làm tham số cho hàm tạo.
                theLoais = new TheLoai(
                        result.getString("MaTheLoai"),
                        result.getString("TenTheLoai")
                );
                CacTheLoai.add(theLoais);//Nó thêm từng đối tượng Sách vào danh sách CacQuyenSach.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CacTheLoai;//Nó trả về danh sách CacQuyenSach là đầu ra của hàm.
    }
    private ObservableList<TheLoai> DanhSachTheLoai;
    public void HienThiTheLoaiR() {
        DanhSachTheLoai = DuLieuTheLoai();
        cr_MaTheLoai.setCellValueFactory(new PropertyValueFactory<TheLoai, String>("MaTheLoai"));
        cr_TenTheLoai.setCellValueFactory(new PropertyValueFactory<TheLoai, String>("TenTheLoai"));
        BangTheLoai.setItems(DanhSachTheLoai);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HienThiTheLoaiR();
    }
}
