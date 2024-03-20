package QuanLyThuVien.QuanLyThuVien;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
public class TacGiaController implements Initializable {
    @FXML
    private Button ButtonIn_ThemTacGia;

    @FXML
    private TextField NhapTacGia_MaTacGia;
    @FXML
    private TableColumn<TacGia, String> c_MaTacGia;
    @FXML
    private TableView<TacGia> BangTacGia;
    @FXML
    private TableColumn<TacGia, String> c_TenTacGia;
    @FXML
    private TextField NhapTacGia_TenTacGia;
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

    public void ThemTacGia() {
        try {
            connect = ConnectSQL.connectDB();
            String sql_Nhap = "INSERT INTO TacGia (MaTacGia, TenTacGia) VALUES ( ?, ?)\n"; /*imgSach,*/
            String sql_check = "SELECT * FROM TacGia Where MaTacGia = ?";
            //---------------------------------------------------
            prepare = connect.prepareStatement(sql_check);
            prepare.setString(1, NhapTacGia_MaTacGia.getText());
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
                alert.setContentText("Tác Giả đã tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            }else {
                //---------------------------------------------------
                String NoiDungConThieu = "";
                if (NhapTacGia_MaTacGia.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Mã Độc Giả.\n";
                }
                if (NhapTacGia_TenTacGia.getText().isEmpty()) {
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
                    prepare.setString(1, NhapTacGia_MaTacGia.getText());
                    prepare.setString(2, NhapTacGia_TenTacGia.getText());
//                        prepare.setString(9, NhapSach_ChonTacGia.getValue());
                    System.out.println("Đã Thêm Thành Công Tác Giả Có Mã: " + NhapTacGia_MaTacGia.getText());
                    prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Đã Thêm Thành Công Tác Giả Có Mã: " + NhapTacGia_MaTacGia.getText());
                    alert.showAndWait();
                }
            }
//            try {
//                // Load the fxml file and create a new stage for the demo scene
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("Sach.fxml"));
//                Parent root = loader.load();
//                Stage stage = new Stage();
//                stage.setTitle("Sách");
//                stage.setScene(new Scene(root));
//                // Show the new stage
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            HienThiTacGiaR();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public void SuaTacGia(){
        try {
            String sql = "UPDATE TacGia SET TenTacGia = ? WHERE MaTacGia = ?";
            connect = ConnectSQL.connectDB();
            prepare = connect.prepareStatement(sql);

            String sql_check = "SELECT * FROM TacGia WHERE MaTacGia = ?";
            PreparedStatement prepareCheck = connect.prepareStatement(sql_check);
            prepareCheck.setString(1, NhapTacGia_MaTacGia.getText());
            ResultSet resultCheck = prepareCheck.executeQuery();
            if (!resultCheck.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo Cho Bạn");
                alert.setContentText("Tác Giả Không tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            } else {
                String NoiDungConThieu = "";
                if (NhapTacGia_TenTacGia.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Tên Tác Giả.\n";
                }
                if (!NoiDungConThieu.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText(NoiDungConThieu);
                    alert.showAndWait();
                }else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(2, NhapTacGia_MaTacGia.getText());
                    prepare.setString(1, NhapTacGia_TenTacGia.getText());
                }
                //---------------------------
                int rowsAffected = prepare.executeUpdate();
                if (rowsAffected > 0) {
                    // Xóa thành công
                    System.out.println("Đã Sửa Độc Giả Có Mã: " + NhapTacGia_MaTacGia.getText());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Đã Sửa Độc Giả Có Mã: " + NhapTacGia_MaTacGia.getText());
                    alert.showAndWait();
                } else {
                    // Không tìm thấy Độc Giả để xóa
                    System.out.println("Không tìm thấy Độc Giả có mã : " + NhapTacGia_MaTacGia.getText());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Không tìm thấy Độc Giả có mã: " + NhapTacGia_MaTacGia.getText());
                    alert.showAndWait();
                }
                //------------------------------
                HienThiTacGiaR();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void ChonTacGia_TTCT() {
        TacGia dulieutacgia = BangTacGia.getSelectionModel().getSelectedItem();
        int num = BangTacGia.getSelectionModel().getFocusedIndex();
        if ((num - 1) < -1) {
            return;
        }
        NhapTacGia_MaTacGia.setText(dulieutacgia.getMaTacGia());
        NhapTacGia_TenTacGia.setText(dulieutacgia.getTenTacGia());
    }
    public ObservableList<TacGia> DuLieuTacGia() { //ObservableList: là cho phép người dùng theo dõi khi mà chúng có sự thay đổi
        ObservableList<TacGia> CacTacGia = FXCollections.observableArrayList(); // Tạo ra 1 ObservableList rỗng mang tên là 'CacQuyenSach' để lưu trữ sách
        String sql = "SELECT * FROM TacGia";
        connect = ConnectSQL.connectDB();//kết nối sql
        try {
            TacGia TacGias;
            prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
            result = prepare.executeQuery();
            while (result.next()) { //Nó lặp qua tập kết quả và tạo một đối tượng Sach mới cho mỗi hàng, sử dụng các giá trị từ các cột làm tham số cho hàm tạo.
                TacGias = new TacGia(
                        result.getString("MaTacGia"),
                        result.getString("TenTacGia")
                );
                CacTacGia.add(TacGias);//Nó thêm từng đối tượng Sách vào danh sách CacQuyenSach.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CacTacGia;//Nó trả về danh sách CacQuyenSach là đầu ra của hàm.
    }
    private ObservableList<TacGia> DanhSachTacGia;
    public void HienThiTacGiaR() {
        DanhSachTacGia = DuLieuTacGia();
        c_MaTacGia.setCellValueFactory(new PropertyValueFactory<TacGia, String>("MaTacGia"));
        c_TenTacGia.setCellValueFactory(new PropertyValueFactory<TacGia, String>("TenTacGia"));
        BangTacGia.setItems(DanhSachTacGia);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HienThiTacGiaR();
    }
}
