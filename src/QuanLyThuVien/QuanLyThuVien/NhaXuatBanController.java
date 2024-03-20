package QuanLyThuVien.QuanLyThuVien;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;

public class NhaXuatBanController implements Initializable {
    @FXML
    private Button ButtonIn_ThemNXB;

    @FXML
    private TextField NhapNXB_EmailNXB;

    @FXML
    private TextField NhapNXB_MaNXB;

    @FXML
    private TextField NhapNXB_SDT;

    @FXML
    private TextField NhapNXB_TenNXB;
    @FXML
    private TableColumn<NhaXuatBan, String> c_Email;

    @FXML
    private TableColumn<NhaXuatBan, String> c_MaNXB;

    @FXML
    private TableColumn<NhaXuatBan, String> c_SDT;

    @FXML
    private TableColumn<NhaXuatBan, String> c_TenNXB;
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

    public void ThemNXB() {
        try {
            connect = ConnectSQL.connectDB();
            String sql_Nhap = "INSERT INTO NXB (MaNXB, TenNXB,Email, SDT) VALUES ( ?, ?, ?, ?)\n"; /*imgSach,*/
            String sql_check = "SELECT * FROM NXB Where MaNXB = ?";
            //---------------------------------------------------
            prepare = connect.prepareStatement(sql_check);
            prepare.setString(1, NhapNXB_MaNXB.getText());
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
                alert.setContentText("NXB đã tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            }
            else if (!NhapNXB_SDT.getText().matches("\\d+") || Integer.parseInt(NhapNXB_SDT.getText()) < 0) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setContentText("Số Điện Thoại Không Hợp Lệ");
                    alert.initOwner(NhapNXB_SDT.getScene().getWindow()); // Set the parent window
                    alert.showAndWait();
                });
            }
            else {
                //---------------------------------------------------
                String NoiDungConThieu = "";
                if (NhapNXB_TenNXB.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Mã NXB.\n";
                }
                if (NhapNXB_MaNXB.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Tên NXB.\n";
                }
                if (NhapNXB_SDT.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Số Điện Thoại NXB.\n";
                }
                if (NhapNXB_EmailNXB.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Email.\n";
                }
                if (!NoiDungConThieu.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText(NoiDungConThieu);
                    alert.showAndWait();
                }else if (!NhapNXB_SDT.getText().matches("\\d+") || Integer.parseInt(NhapNXB_SDT.getText()) < 0) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Thông Báo Cho Bạn");
                        alert.setContentText("Số Điện Thoại Không Hợp Lệ");
                        alert.initOwner(NhapNXB_SDT.getScene().getWindow()); // Set the parent window
                        alert.showAndWait();
                    });
                    return;
                }
                else {
                    prepare = connect.prepareStatement(sql_Nhap);
                    prepare.setString(1, NhapNXB_MaNXB.getText());
                    prepare.setString(2, NhapNXB_TenNXB.getText());
                    prepare.setString(3, NhapNXB_EmailNXB.getText());
                    prepare.setString(4, NhapNXB_SDT.getText());
//                        prepare.setString(9, NhapSach_ChonTheLoai.getValue());
                    System.out.println("Đã Thêm Thành Công NXB Có Mã: " + NhapNXB_MaNXB.getText());
                    prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Đã Thêm Thành Công NXB Có Mã: " + NhapNXB_MaNXB.getText());
                    alert.showAndWait();
                }
            }
            HienThiNXB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void SuaNXB(){
        try {
            String sql = "UPDATE NXB SET TenNXB = ?, Email = ?, SDT = ? WHERE MaNXB = ?";
            connect = ConnectSQL.connectDB();
            prepare = connect.prepareStatement(sql);

            String sql_check = "SELECT * FROM NXB WHERE MaNXB = ?";
            PreparedStatement prepareCheck = connect.prepareStatement(sql_check);
            prepareCheck.setString(1, NhapNXB_MaNXB.getText());
            ResultSet resultCheck = prepareCheck.executeQuery();
            if (!resultCheck.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo Cho Bạn");
                alert.setContentText("Độc Giả Không tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            } else {
                String NoiDungConThieu = "";
                if (NhapNXB_TenNXB.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Mã NXB.\n";
                }
                if (NhapNXB_SDT.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Số Điện Thoại NXB.\n";
                }
                if (NhapNXB_EmailNXB.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Email.\n";
                }
                if (!NoiDungConThieu.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText(NoiDungConThieu);
                    alert.showAndWait();
                }else if (!NhapNXB_SDT.getText().matches("\\d+") || Integer.parseInt(NhapNXB_SDT.getText()) < 0) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Thông Báo Cho Bạn");
                        alert.setContentText("Số Điện Thoại Không Hợp Lệ");
                        alert.initOwner(NhapNXB_SDT.getScene().getWindow()); // Set the parent window
                        alert.showAndWait();
                    });
                    return;
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(4, NhapNXB_MaNXB.getText());
                    prepare.setString(1, NhapNXB_TenNXB.getText());
                    prepare.setString(2, NhapNXB_EmailNXB.getText());
                    prepare.setString(3, NhapNXB_SDT.getText());

                }
                //---------------------------
                int rowsAffected = prepare.executeUpdate();
                if (rowsAffected > 0) {
                    // Xóa thành công
                    System.out.println("Đã Sửa Độc Giả Có Mã: " + NhapNXB_MaNXB.getText());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Đã Sửa Độc Giả Có Mã: " + NhapNXB_MaNXB.getText());
                    alert.showAndWait();
                } else {
                    // Không tìm thấy Độc Giả để xóa
                    System.out.println("Không tìm thấy Độc Giả có mã : " + NhapNXB_MaNXB.getText());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Không tìm thấy Độc Giả có mã: " + NhapNXB_MaNXB.getText());
                    alert.showAndWait();
                }
                //------------------------------
                HienThiNXB();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void ChonNXB_TTCT() {
        NhaXuatBan dulieunxb = BangNXB.getSelectionModel().getSelectedItem();
        int num = BangNXB.getSelectionModel().getFocusedIndex();
        if ((num - 1) < -1) {
            return;
        }
        NhapNXB_MaNXB.setText(dulieunxb.getMaNXB());
        NhapNXB_TenNXB.setText(dulieunxb.getTenNXB());
        NhapNXB_EmailNXB.setText(dulieunxb.getEmail());
        NhapNXB_SDT.setText(dulieunxb.getSDT());
    }
    @FXML
    private void Dong(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sach.fxml"));
        // Tạo một đối tượng Parent để chứa nội dung của cửa sổ mới
        Parent root = loader.load();
        // Tạo một đối tượng Scene để chứa Parent
        Scene scene = new Scene(root);
        // Tạo một đối tượng Stage để hiển thị Scene
        Stage stage = new Stage();
        // Thiết lập tiêu đề, kích thước, chế độ modal cho Stage
        stage.setTitle("Cửa sổ mới");
//        stage.setWidth(300);
//        stage.setHeight(200);
//        stage.initModality(Modality.WINDOW_MODAL);
        // Gán Scene cho Stage
        stage.setScene(scene);
        // Hiển thị Stage
        stage.show();
        // Lấy Stage hiện tại từ ActionEvent
        Stage currentStage = (Stage) ButtonIn_ThemNXB.getScene().getWindow();
        // Đóng Stage hiện tại
        currentStage.close();
    }
    public ObservableList<NhaXuatBan> DuLieuNXB() { //ObservableList: là cho phép người dùng theo dõi khi mà chúng có sự thay đổi
        ObservableList<NhaXuatBan> CacNXB = FXCollections.observableArrayList(); // Tạo ra 1 ObservableList rỗng mang tên là 'CacQuyenSach' để lưu trữ sách
        String sql = "SELECT * FROM NXB";
        connect = ConnectSQL.connectDB();//kết nối sql
        try {
            NhaXuatBan nxbs;
            prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
            result = prepare.executeQuery();
            while (result.next()) { //Nó lặp qua tập kết quả và tạo một đối tượng Sach mới cho mỗi hàng, sử dụng các giá trị từ các cột làm tham số cho hàm tạo.
                nxbs = new NhaXuatBan(
                        result.getString("MaNXB"),
                        result.getString("TenNXB"),
                        result.getString("Email"),
                        result.getString("SDT")
                );
                CacNXB.add(nxbs);//Nó thêm từng đối tượng Sách vào danh sách CacQuyenSach.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CacNXB;//Nó trả về danh sách CacQuyenSach là đầu ra của hàm.
    }
    private ObservableList<NhaXuatBan> DanhSachNXB;
    @FXML
    private TableView<NhaXuatBan> BangNXB;
    public void HienThiNXB() {
        DanhSachNXB = DuLieuNXB();
        c_MaNXB.setCellValueFactory(new PropertyValueFactory<NhaXuatBan, String>("MaNXB"));
        c_TenNXB.setCellValueFactory(new PropertyValueFactory<NhaXuatBan, String>("TenNXB"));
        c_Email.setCellValueFactory(new PropertyValueFactory<NhaXuatBan, String>("Email"));
        c_SDT.setCellValueFactory(new PropertyValueFactory<NhaXuatBan,String>("SDT"));
        BangNXB.setItems(DanhSachNXB);
    }


    //    @FXML
//    private void MoCuaSoMoi(ActionEvent event,Stage primarystage) throws IOException {
//
//
//        // Tạo một đối tượng FXMLLoader để load file FXML của cửa sổ mới
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sach.fxml"));
//        // Tạo một đối tượng Parent để chứa nội dung của cửa sổ mới
//        Parent root = loader.load();
//        // Tạo một đối tượng Scene để chứa Parent
//        Scene scene = new Scene(root);
//        // Tạo một đối tượng Stage để hiển thị Scene
//        Stage stage = new Stage();
//        // Thiết lập tiêu đề, kích thước, chế độ modal cho Stage
////        stage.setTitle("Cửa sổ mới");
////        stage.setWidth(300);
////        stage.setHeight(200);
////        stage.initModality(Modality.WINDOW_MODAL);
//        // Gán Scene cho Stage
//        stage.setScene(scene);
//        // Hiển thị Stage
//        stage.show();
//
//        // Lấy Stage hiện tại từ ActionEvent
//        Stage currentStage = (Stage) ButtonIn_ThemNXB.getScene().getWindow();
//        // Đóng Stage hiện tại
//        currentStage.close();
//    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HienThiNXB();
    }
}