package QuanLyThuVien.QuanLyThuVien;

import java.util.concurrent.atomic.AtomicBoolean;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import Interface.Menu;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.control.TableView;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.scene.control.Tooltip;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

public class SachController implements Initializable {
    LocalDate NgayThanhNamHienTai = LocalDate.now();
    int NamHienTai = NgayThanhNamHienTai.getYear();
    private SimpleDateFormat DateFormat;
    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    public Alert alert;
    private  Stage stage;
    private Scene scene;
    private Parent root;
//--------------------------------------------
@FXML
private Button XemTheLoai;
    @FXML
    private Button btnHuy;
//--------------------------------------------------------

    //---------------------------------------------------------------------
// Sach ---------------------------------------------------------------
    @FXML
    private AnchorPane nav_form;
    @FXML
    private TableView<Sach> BangSach;
    @FXML
    private TableColumn<Sach, String> cMaNXB;

    @FXML
    private TableColumn<Sach, Date> cNgayNhap;
    @FXML
    private TableColumn<Sach, String> cMaSach;

    @FXML
    private TableColumn<Sach, String> cMaTacGia;
    @FXML
    private TableColumn<Sach, String> cTenTheLoai;

    @FXML
    private TableColumn<Sach, String> cMaTheLoai;

    @FXML
    private TableColumn<Sach, Integer> cNamXB;

    @FXML
    private TableColumn<Sach, Integer> cSoLuong;

    @FXML
    private TableColumn<Sach, String> cTenSach;
    @FXML
    private TableColumn<Sach, String> cTenTacGia;

    @FXML
    private Label ttct_MaNXB;

    @FXML
    private Label ttct_MaSach;

    @FXML
    private Label ttct_MaTacGia;

    @FXML
    private Label ttct_TenTacGia;

    @FXML
    private Label ttct_TenNXB;
    @FXML
    private Label ttct_TenTheLoai;

    @FXML
    private Label ttct_MaTheLoai;

    @FXML
    private Label ttct_NamXB;

    @FXML
    private Label ttct_SoLuong;
    @FXML
    private Label ttct_TenSach;
    @FXML
    private ImageView ttct_AnhSach;
    @FXML
    private TextField TimKiem_Sach;
    @FXML
    private Label ttct_NgayNhap;
    @FXML
    private ComboBox<String> NhapSach_ChonTheLoai;
    @FXML
    private ComboBox<String> NhapSach_ChonMaTheLoai;
    @FXML
    private ComboBox<String> NhapSach_ChonMaTacGia;
    @FXML
    private ComboBox<String> NhapSach_ChonMaNXB;
    @FXML
    private ImageView NhapSach_AnhSach;

//    @FXML
//    private TextField NhapSach_MaNXB;

    @FXML
    private TextField NhapSach_MaSach;

//    @FXML
//    private TextField NhapSach_MaTacGia;

    @FXML
    private TextField NhapSach_NamXB;

    @FXML
    private DatePicker NhapSach_NgayNhap;

    @FXML
    private TextField NhapSach_SoLuong;

    @FXML
    private TextField NhapSach_TenSach;
    @FXML
    private TextField NhapSach_TenTacGia;

    @FXML
    private Button BTN_IMGSach;

    @FXML
    private Button BTN_HuySach;

    @FXML
    private Button BTN_SuaSach;

    @FXML
    private Button BTN_ThemSach;

    @FXML
    private Button BTN_XoaSach;
    @FXML
    private Button Button_ThemTheLoai;
    @FXML
    private VBox Card_Layout;
    @FXML
    private AnchorPane Card_Box;
    @FXML
    private Button Button_ThemNXB;

    @FXML
    private Button Button_LamMoi;

    @FXML
    private void MoThemNXB(ActionEvent event) throws IOException {
        // Tạo một đối tượng FXMLLoader để load file FXML của cửa sổ mới
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NXB.fxml"));
        // Tạo một đối tượng Parent để chứa nội dung của cửa sổ mới
        Parent root = loader.load();
        // Tạo một đối tượng Scene để chứa Parent
        Scene scene = new Scene(root);
        // Tạo một đối tượng Stage để hiển thị Scene
        Stage stage = new Stage();
        // Thiết lập tiêu đề, kích thước, chế độ modal cho Stage
        stage.setTitle("Cửa sổ mới");
        stage.setScene(scene);
        // Hiển thị Stage
        stage.show();
        // Lấy Stage hiện tại từ ActionEvent
        /*Stage currentStage = (Stage) Button_ThemNXB.getScene().getWindow();
        // Đóng Stage hiện tại
        currentStage.close();*/
    }
        public void LamMoi() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sach.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Sách");
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage) Button_LamMoi.getScene().getWindow();
            currentStage.close();
        }
//    public void MoThemNXB() {
//        try {
//            // Load the fxml file and create a new stage for the demo scene
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("NXB.fxml"));
//            Parent root = loader.load();
//            Stage stage = new Stage();
//            stage.setTitle("Nhà Xuất Bản");
//            stage.setScene(new Scene(root));
//            // Show the new stage
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public void MoThemTacGia() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TacGia.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Tác Giả");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void MoXemTheLoai() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TheLoai.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Thể Loại");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Sach> SachDaThemGanDay(){
        ObservableList<Sach> ls = FXCollections.observableArrayList();
        try {
            connect = ConnectSQL.connectDB();
            String sql = "SELECT Sach.*, TacGia.TenTacGia\n" +
                    "FROM Sach INNER JOIN TacGia\n" +
                    "ON Sach.MaTacGia = TacGia.MaTacGia\n" +
                    "ORDER BY Sach.NgayNhap DESC";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                Sach sach = new Sach();
                sach.setTenSach(result.getString("TenSach"));
                sach.setTenTacGia(result.getString("TenTacGia"));
                sach.setimgsach(result.getString("imgSach"));
                ls.add(sach);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    private List<Sach> SachDaThemGanDay;
    //--------------------------------
    //-----------------------------
    public void HienThiSachDaThemGanDay(){
        SachDaThemGanDay = new ArrayList<>(SachDaThemGanDay());
        try {
            for (int i = 0; i< SachDaThemGanDay.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
                Card_Box = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setDataSach(SachDaThemGanDay.get(i));
                Card_Layout.getChildren().add(Card_Box);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void ChonTheloaiSach() {
        try {
            connect = ConnectSQL.connectDB();
            String sql = "SELECT * FROM TheLoai";
            prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
            result = prepare.executeQuery();
            ObservableList<String> list = FXCollections.observableArrayList();
            while (result.next()){
                list.add(result.getNString( "TenTheLoai"));
            }
            NhapSach_ChonTheLoai.setItems(list);

//            NhapSach_ChonTheLoai.setEditable(true);
//            NhapSach_ChonTheLoai.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
//                String enteredText = NhapSach_ChonTheLoai.getEditor().getText();
//                if (!enteredText.isEmpty()) {
//                    ObservableList<String> filteredList = FXCollections.observableArrayList();
//                    for (String item : list) {
//                        if (item.toLowerCase().contains(enteredText.toLowerCase())) {
//                            filteredList.add(item);
//                        }
//                    }
//                    NhapSach_ChonTheLoai.setItems(filteredList);
//                    if (!NhapSach_ChonTheLoai.isShowing()) {
//                        NhapSach_ChonTheLoai.show();
//                    }
//                } else {
//                    NhapSach_ChonTheLoai.setItems(list);
//                    if (!NhapSach_ChonTheLoai.isShowing()) {
//                        NhapSach_ChonTheLoai.show();
//                    }
//                }
//            });

//                    NhapSach_ChonTheLoai.setConverter(new StringConverter<String>() {
//                        @Override
//                        public String toString(String object) {
//                            // Trả về cách bạn muốn mỗi mục được hiển thị trong combobox
//                            // Ví dụ, nếu đối tượng là "Phiêu Lưu", trả về "Adventure"
//                            switch (object) {
//                                case "Phiêu Lưu": return "Adventure";
//                                case "Tiểu Thuyết": return "Novel";
//                                case "Trinh Thám": return "Mystery";
//                                case "Báo": return "Newspaper";
//                                // Thêm các kiểu khác theo ý bạn
//                                default: return object;
//                            }
//                        }
//                        @Override
//                        public String fromString(String string) {
//                            // Trả về cách bạn muốn mỗi mục được chuyển đổi từ một chuỗi
//                            // Ví dụ, nếu chuỗi là "Adventure", trả về "Phiêu Lưu"
//                            switch (string) {
//                                case "Adventure": return "Phiêu Lưu";
//                                case "Novel": return "Tiểu Thuyết";
//                                case "Mystery": return "Trinh Thám";
//                                case "Newspaper": return "Báo";
//                                // Thêm các kiểu khác theo ý bạn
//                                default: return string;
//                            }
//                        }
//                    });
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    //----------------------------------------------------------------------------------------------
    public void ChonMaTacGia() {
        try {
            connect = ConnectSQL.connectDB();
            String sql = "SELECT * FROM TacGia";
            prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
            result = prepare.executeQuery();
            ObservableList<String> list = FXCollections.observableArrayList();
            while (result.next()){
                list.add(result.getString("MaTacGia"));
            }
            NhapSach_ChonMaTacGia.setItems(list);

            NhapSach_ChonMaTacGia.setEditable(true);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void ChonMaNXB() {
        try {
            connect = ConnectSQL.connectDB();
            String sql = "SELECT * FROM NXB";
            prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
            result = prepare.executeQuery();
            ObservableList<String> list = FXCollections.observableArrayList();
            while (result.next()){
                list.add(result.getString("MaNXB"));
            }
            NhapSach_ChonMaNXB.setItems(list);

            NhapSach_ChonMaNXB.setEditable(true);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void ChonMaTheLoai() {
        try {
            connect = ConnectSQL.connectDB();
            String sql = "SELECT * FROM TheLoai";
            prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
            result = prepare.executeQuery();
            ObservableList<String> list = FXCollections.observableArrayList();
            while (result.next()){
                list.add(result.getString("MaTheLoai"));
            }
            NhapSach_ChonMaTheLoai.setItems(list);
            NhapSach_ChonMaTheLoai.setEditable(true);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    //----
    public ObservableList<Sach> DuLieuSach() { //ObservableList: là cho phép người dùng theo dõi khi mà chúng có sự thay đổi
        ObservableList<Sach> CacQuyenSach = FXCollections.observableArrayList(); // Tạo ra 1 ObservableList rỗng mang tên là 'CacQuyenSach' để lưu trữ sách
        String sql = "SELECT Sach.*,  TheLoai.TenTheLoai, TacGia.TenTacGia, NXB.TenNXB\n" +
                "FROM Sach\n" +
                "INNER JOIN TheLoai\n" +
                "ON Sach.MaTheLoai = TheLoai.MaTheLoai\n" +
                "INNER JOIN TacGia\n" +
                "ON Sach.MaTacGia = TacGia.MaTacGia\n" +
                "INNER JOIN NXB\n" +
                "ON Sach.MaNXB = NXB.MaNXB";

        connect = ConnectSQL.connectDB();//kết nối sql
        try {
            Sach sachs;
            prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
            result = prepare.executeQuery();
            while (result.next()) { //Nó lặp qua tập kết quả và tạo một đối tượng Sach mới cho mỗi hàng, sử dụng các giá trị từ các cột làm tham số cho hàm tạo.
                sachs = new Sach(result.getString("MaSach"),
                        result.getString("TenSach"),
                        result.getString("MaTheLoai"),
                        result.getString("TenTheLoai"),
                        result.getString("MaTacGia"),
                        result.getString("TenTacGia"),
                        result.getString("MaNXB"),
                        result.getInt("NamXB"),
                        result.getInt("SoLuong"),
                        result.getDate("NgayNhap"),
                        result.getString("imgsach")
                );
                CacQuyenSach.add(sachs);//Nó thêm từng đối tượng Sách vào danh sách CacQuyenSach.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CacQuyenSach;//Nó trả về danh sách CacQuyenSach là đầu ra của hàm.
    }
    //-----------
    private ObservableList<Sach> DanhSachSach;
//    String EMAIL_PATTERN =
//            "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
    public void HienThiSach() {
        HienThiSachDaThemGanDay();
        ChonMaTacGia();
        ChonTheloaiSach();
        ChonMaNXB();
        ChonMaTheLoai();
        DanhSachSach = DuLieuSach();
        cMaSach.setCellValueFactory(new PropertyValueFactory<Sach, String>("MaSach"));
        cTenSach.setCellValueFactory(new PropertyValueFactory<Sach, String>("TenSach"));
        cMaTacGia.setCellValueFactory(new PropertyValueFactory<Sach, String>("MaTacGia"));
        cTenTacGia.setCellValueFactory(new PropertyValueFactory<Sach, String>("TenTacGia"));
        cMaTheLoai.setCellValueFactory(new PropertyValueFactory<Sach, String>("MaTheLoai"));
        cTenTheLoai.setCellValueFactory(new PropertyValueFactory<Sach, String>("TenTheLoai"));
        cMaNXB.setCellValueFactory(new PropertyValueFactory<Sach, String>("MaNXB"));
        cSoLuong.setCellValueFactory(new PropertyValueFactory<Sach, Integer>("SoLuong"));
        cNamXB.setCellValueFactory(new PropertyValueFactory<Sach, Integer>("NamXB"));
        cNgayNhap.setCellValueFactory(new PropertyValueFactory<Sach, Date>("NgayNhap"));
        BangSach.setItems(DanhSachSach);
        TimKiem_Sach();
    }
    private ObservableList<Sach> DanhSach_TTCT;
    public void ChonSach_TTCT() {
        Sach dulieusach = BangSach.getSelectionModel().getSelectedItem();
//        DanhSach_TTCT = DuLieuSach();
        int num = BangSach.getSelectionModel().getFocusedIndex();
        if ((num - 1) < -1) {
            return;
        }
//                ttct_MaSach.setText(dulieusach.getMaSach());
//                ttct_TenSach.setText(dulieusach.getTenSach());
//                ttct_MaTacGia.setText(dulieusach.getMaTacGia());
//                ttct_TenTacGia.setText(dulieusach.getTenTacGia());
//                ttct_MaTheLoai.setText(dulieusach.getMaTheLoai());
//                ttct_TenTheLoai.setText(dulieusach.getTenTheLoai());
//                ttct_MaNXB.setText(dulieusach.getMaNXB());
//
//                //---------------------------------
//
//                //------------------------------
//
//                ttct_NamXB.setText(String.valueOf(dulieusach.getNamXB())); // INT
//                ttct_SoLuong.setText(String.valueOf(dulieusach.getSoLuong())); //INT
//                ttct_NgayNhap.setText(String.valueOf(dulieusach.getNgayNhap()));
//                String uri = "file:" + dulieusach.getImgSach();
//                image = new Image(uri, 134, 171, false, true);
//                ttct_AnhSach.setImage(image);


        NhapSach_MaSach.setText(dulieusach.getMaSach());
        NhapSach_TenSach.setText(dulieusach.getTenSach());
        NhapSach_ChonMaTacGia.setValue(dulieusach.getMaTacGia());
        NhapSach_ChonMaTheLoai.setValue(dulieusach.getMaTheLoai());
        NhapSach_ChonTheLoai.setValue(dulieusach.getTenTheLoai());
        NhapSach_ChonMaNXB.setValue(dulieusach.getMaNXB());
        NhapSach_NamXB.setText(String.valueOf(dulieusach.getNamXB()));
        NhapSach_SoLuong.setText(String.valueOf(dulieusach.getSoLuong()));
        NhapSach_NgayNhap.setValue(dulieusach.getNgayNhap().toLocalDate());


    }
    public void TimKiem_Sach(){
            // tạo một đối tượng FilteredList từ một ObservableList ban đầu (DanhSachSach) và
            // đặt điều kiện ban đầu là hiển thị tất cả dữ liệu
        FilteredList<Sach> filteredData = new FilteredList<>(DanhSachSach, b -> true);
            //thiết lập một Predicate (một điều kiện để lọc dữ liệu)
            // mỗi khi giá trị của bộ lọc thay đổi. Dòng này sử dụng phương thức addListener()
            // để lắng nghe sự thay đổi của thuộc tính textProperty() của đối tượng TimKiem_Sach.
            // Khi thuộc tính này thay đổi, một đoạn mã được thực thi để cập nhật điều kiện lọc dữ liệu.
        TimKiem_Sach.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                    //đặt điều kiện lọc dữ liệu cho đối tượng filteredData.
                    // Nếu giá trị mới của bộ lọc là rỗng, tức là không có bộ lọc được áp dụng,
                    // thì tất cả các sách trong danh sách sẽ được hiển thị.
                    // Ngược lại, nếu giá trị bộ lọc không rỗng,
                    // điều kiện lọc sẽ so sánh mã sách (MaSach) và tên sách (TenSach) với giá trị của bộ lọc.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (employee.getMaSach().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (employee.getTenSach().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else if (employee.getMaTacGia().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else if (employee.getMaTheLoai().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (String.valueOf(employee.getNamXB()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false;// Does not match.
            });
        });
            //một đối tượng SortedList được tạo từ đối tượng
            // filteredData để sắp xếp dữ liệu theo một tiêu chí nhất định.
        SortedList<Sach> sortedData = new SortedList<>(filteredData);
            //comparator của đối tượng sortedData được liên kết với comparator của đối tượng TableView (BangSach).
            // Điều này đảm bảo rằng việc sắp xếp TableView sẽ có tác dụng lên sortedData.
        sortedData.comparatorProperty().bind(BangSach.comparatorProperty());
            //dữ liệu đã được sắp xếp và lọc được thêm vào TableView (BangSach) bằng phương thức setItems().
        BangSach.setItems(sortedData);

        //----------------------------------------------------------
    }
    public void HuySach() {
        if(NhapSach_MaSach.getText().isEmpty() && NhapSach_TenSach.getText().isEmpty() &&
        NhapSach_ChonMaTacGia.getValue() == null && NhapSach_ChonMaNXB.getValue() == null
        && NhapSach_NamXB.getText().isEmpty()  && NhapSach_SoLuong.getText().isEmpty()
        && NhapSach_ChonMaTheLoai.getValue() == null) {
            System.out.println("Không Có Gì Để Hủy Hết");
            alert = new Alert(AlertType.WARNING);
            alert.setTitle("Thông Báo Cho Bạn");
            alert.setHeaderText(null);
            alert.setContentText("Không Có Gì Để Hủy Hết");
            alert.showAndWait();
        }
        else {
            NhapSach_MaSach.setText("");
            NhapSach_TenSach.setText("");

            NhapSach_ChonMaTheLoai.valueProperty().unbind();
            NhapSach_ChonMaTheLoai.setValue(null);


            NhapSach_ChonTheLoai.valueProperty().unbind();
            NhapSach_ChonTheLoai.setValue(null);


            NhapSach_ChonMaTacGia.valueProperty().unbind();
            NhapSach_ChonMaTacGia.setValue(null);


            NhapSach_ChonMaNXB.valueProperty().unbind();
            NhapSach_ChonMaNXB.setValue(null);


            NhapSach_NamXB.setText("");
            NhapSach_NgayNhap.setValue(null);
            NhapSach_SoLuong.setText("");

            System.out.println("Đã Hủy Thành Công");
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông Báo Cho Bạn");
            alert.setHeaderText(null);
            alert.setContentText("Đã Hủy Thành Công");
            alert.showAndWait();
        }
    }
        class GoiY{
            public void GoiYNhapMaSach(){
                String[] words = {"vinh", "dasd", "tdf"};
                TextFields.bindAutoCompletion(NhapSach_MaSach, words);
            }
        }
    public void ThemSach(){

        try {
            connect = ConnectSQL.connectDB();
            String sql_Nhap = "INSERT INTO Sach (MaSach, TenSach, MaTheLoai, MaTacGia, MaNXB, SoLuong, NamXB,  NgayNhap) VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n"; /*imgSach,*/
            String sql_check = "SELECT * FROM Sach Where MaSach = ?";

            String sql_checkMaTacGia = "SELECT * FROM TacGia Where MaTacGia = ?";
            String sql_checkMaNXB = "SELECT * FROM NXB Where MaNXB = ?";
            String sql_checkMaTheLoai = "SELECT * FROM TheLoai Where MaTheLoai = ?";

            PreparedStatement prepare1 = connect.prepareStatement(sql_check);
            prepare1.setString(1, NhapSach_MaSach.getText());
            ResultSet result1 = prepare1.executeQuery();

            PreparedStatement prepare2 = connect.prepareStatement(sql_checkMaTacGia);
            prepare2.setString(1, NhapSach_ChonMaTacGia.getValue());
            ResultSet result2 = prepare2.executeQuery();

            PreparedStatement prepare3 = connect.prepareStatement(sql_checkMaNXB);
            prepare3.setString(1, NhapSach_ChonMaNXB.getValue());
            ResultSet result3 = prepare3.executeQuery();

            PreparedStatement prepare4 = connect.prepareStatement(sql_checkMaTheLoai);
            prepare4.setString(1, NhapSach_ChonMaTheLoai.getValue());
            ResultSet result4 = prepare4.executeQuery();

            prepare = connect.prepareStatement(sql_check);
            prepare.setString(1, NhapSach_MaSach.getText());
            result = prepare.executeQuery();
            if (result.next()) {
                // Nếu không rỗng, tức là đã có dữ liệu trùng trong cơ sở dữ liệu
                // Hiển thị thông báo trùng bằng Alert hoặc Label
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo Cho Bạn");
//                alert.setHeaderText ("Dữ liệu trùng");
                alert.setContentText("Sách đã tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            } else {
                //----------------
                String NoiDungConThieu = "";
                if (NhapSach_MaSach.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Mã Sách.\n";
                }
                if (NhapSach_TenSach.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Tên Sách.\n";
                }
                if (NhapSach_ChonMaNXB.getValue() == null) {
                    NoiDungConThieu += "Nhập Mã NXB.\n";
                }
                if (NhapSach_NamXB.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Năm XB.\n";
                }
                if (NhapSach_ChonMaTacGia.getValue() == null) {
                    NoiDungConThieu += "Nhập Mã Tác Giả.\n";
                }
                if (NhapSach_SoLuong.getText().isEmpty()) {
                    NoiDungConThieu += "Nhập Số Lượng.\n";
//                    }if (NhapSach_ChonTheLoai.getValue() == null){
//                        NoiDungConThieu += "Nhap The Loai.\n";
                }

                if (!NoiDungConThieu.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText(NoiDungConThieu);
                    alert.showAndWait();
                }
                if (!result2.next()) {
                    // Nếu không rỗng, tức là đã có dữ liệu trùng trong cơ sở dữ liệu
                    // Hiển thị thông báo trùng bằng Alert hoặc Label
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông Báo Cho Bạn");
//                alert.setHeaderText ("Dữ liệu trùng");
                    alert.setContentText("Mã Tác Giả không tồn tại trong cơ sở dữ liệu");
                    alert.showAndWait();
                }if (!result3.next()) {
                    // Nếu không rỗng, tức là đã có dữ liệu trùng trong cơ sở dữ liệu
                    // Hiển thị thông báo trùng bằng Alert hoặc Label
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông Báo Cho Bạn");
//                alert.setHeaderText ("Dữ liệu trùng");
                    alert.setContentText("Mã NXB không tồn tại trong cơ sở dữ liệu");
                    alert.showAndWait();
                }if (!result4.next()) {
                    // Nếu không rỗng, tức là đã có dữ liệu trùng trong cơ sở dữ liệu
                    // Hiển thị thông báo trùng bằng Alert hoặc Label
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông Báo Cho Bạn");
//                alert.setHeaderText ("Dữ liệu trùng");
                    alert.setContentText("Mã Thể Loại không tồn tại trong cơ sở dữ liệu");
                    alert.showAndWait();
                }
                //---------------------------
                else if (!NhapSach_SoLuong.getText().matches("\\d+") || Integer.parseInt(NhapSach_SoLuong.getText()) < 0) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Thông Báo Cho Bạn");
                        alert.setContentText("Số Lượng Không Hợp Lệ");
                        alert.initOwner(NhapSach_SoLuong.getScene().getWindow()); // Set the parent window
                        alert.showAndWait();
                    });
                    return;
                }
                else if (Integer.parseInt(NhapSach_NamXB.getText()) < 0) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Thông Báo Cho Bạn");
                        alert.setContentText("Năm Xuất Bản Không Hợp Lệ");
                        alert.initOwner(NhapSach_NamXB.getScene().getWindow()); // Set the parent window
                        alert.showAndWait();

                    });
                    return;
                }
                //-------------------------
//                else if (Integer.parseInt(NhapSach_SoLuong.getText()) < 0 || (Integer.parseInt(NhapSach_NamXB.getText()) < 0) || (Integer.parseInt(NhapSach_NamXB.getText()) > Integer.parseInt(String.valueOf(NamHienTai))) || NhapSach_NgayNhap.getValue().isAfter(NgayThanhNamHienTai)) {
//                    if (Integer.parseInt(NhapSach_SoLuong.getText()) < 0) {
//                        alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Thông Báo Cho Bạn");
//                        alert.setContentText("Số Lượng Không Được Phép Âm");
//                        alert.showAndWait();
//                        return;
//                    }
//                    if ((Integer.parseInt(NhapSach_NamXB.getText()) < 0) || (Integer.parseInt(NhapSach_NamXB.getText()) > Integer.parseInt(String.valueOf(NamHienTai)))) {
//                        alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Thông Báo Cho Bạn");
//                        alert.setContentText("Năm Không Hợp Lệ");
//                        alert.showAndWait();
//                        return;
//                    }
//                    else if (NhapSach_NgayNhap.getValue().isAfter(NgayThanhNamHienTai)) {
//                        alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Thông Báo Cho Bạn");
//                        alert.setContentText("Ngày Nhập Không Hợp Lệ");
//                        alert.showAndWait();
//                        return;
//                    }
                    else if ( NhapSach_NgayNhap.getValue().isAfter(NgayThanhNamHienTai)) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Thông Báo Cho Bạn");
                            alert.setContentText("Ngày Nhập Không Hợp Lệ");
                            alert.initOwner(NhapSach_NgayNhap.getScene().getWindow()); // Set the parent window
                            alert.showAndWait();
                        });
                        return;
                    }else {
                    prepare = connect.prepareStatement(sql_Nhap);
                    prepare.setString(1, NhapSach_MaSach.getText());
                    prepare.setString(2, NhapSach_TenSach.getText());
                    prepare.setString(3, NhapSach_ChonMaTheLoai.getValue());
                    prepare.setString(4, NhapSach_ChonMaTacGia.getValue());
                    prepare.setString(5, NhapSach_ChonMaNXB.getValue());
                    prepare.setString(6, NhapSach_SoLuong.getText());
                    prepare.setString(7, NhapSach_NamXB.getText());
                    prepare.setString(8, NhapSach_NgayNhap.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));

//                        prepare.setString(9, NhapSach_ChonTheLoai.getValue());
                    System.out.println("Đã Thêm Thành Công Sách Có Mã: " + NhapSach_MaSach.getText());
                    prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Đã Thêm Thành Công Sách Có Mã: " + NhapSach_MaSach.getText());
                    alert.showAndWait();
                    HienThiSach();
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void SuaSach(){
        try {
            String sql_sua = "UPDATE Sach SET TenSach = ?, MaTheLoai = ?, MaTacGia = ?, MaNXB = ?, SoLuong =?, NamXB = ?, NgayNhap = ?  WHERE MaSach = ?";
            connect = ConnectSQL.connectDB();
            prepare = connect.prepareStatement(sql_sua);

            String sql_check = "SELECT * FROM Sach WHERE MaSach = ?";

            String sql_checkMaTacGia = "SELECT * FROM TacGia Where MaTacGia = ?";
            String sql_checkMaNXB = "SELECT * FROM NXB Where MaNXB = ?";
            String sql_checkMaTheLoai = "SELECT * FROM TheLoai Where MaTheLoai = ?";

            PreparedStatement prepare1 = connect.prepareStatement(sql_check);
            prepare1.setString(1, NhapSach_MaSach.getText());
            ResultSet result1 = prepare1.executeQuery();

            PreparedStatement prepare2 = connect.prepareStatement(sql_checkMaTacGia);
            prepare2.setString(1, NhapSach_ChonMaTacGia.getValue());
            ResultSet result2 = prepare2.executeQuery();

            PreparedStatement prepare3 = connect.prepareStatement(sql_checkMaNXB);
            prepare3.setString(1, NhapSach_ChonMaNXB.getValue());
            ResultSet result3 = prepare3.executeQuery();

            PreparedStatement prepare4 = connect.prepareStatement(sql_checkMaTheLoai);
            prepare4.setString(1, NhapSach_ChonMaTheLoai.getValue());
            ResultSet result4 = prepare4.executeQuery();

            PreparedStatement prepareCheck = connect.prepareStatement(sql_check);
            prepareCheck.setString(1, NhapSach_MaSach.getText());
            ResultSet resultCheck = prepareCheck.executeQuery();
            if (!resultCheck.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo Cho Bạn");
                alert.setContentText("Sách Không tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            }if (!result2.next()) {
                // Nếu không rỗng, tức là đã có dữ liệu trùng trong cơ sở dữ liệu
                // Hiển thị thông báo trùng bằng Alert hoặc Label
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo Cho Bạn");
//                alert.setHeaderText ("Dữ liệu trùng");
                alert.setContentText("Mã Tác Giả không tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            }if (!result3.next()) {
                // Nếu không rỗng, tức là đã có dữ liệu trùng trong cơ sở dữ liệu
                // Hiển thị thông báo trùng bằng Alert hoặc Label
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo Cho Bạn");
//                alert.setHeaderText ("Dữ liệu trùng");
                alert.setContentText("Mã NXB không tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            }if (!result4.next()) {
                // Nếu không rỗng, tức là đã có dữ liệu trùng trong cơ sở dữ liệu
                // Hiển thị thông báo trùng bằng Alert hoặc Label
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo Cho Bạn");
//                alert.setHeaderText ("Dữ liệu trùng");
                alert.setContentText("Mã Thể Loại không tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            } else {
                String NoiDungConThieu = "";
                if (NhapSach_TenSach.getText().isEmpty()) {
                    NoiDungConThieu += "Thiếu Tên Sách Để Sửa.\n";
                }
                if (NhapSach_ChonMaNXB.getValue() == null) {
                    NoiDungConThieu += "Thiếu Mã NXB Để Sửa.\n";
                }
                if (NhapSach_NamXB.getText().isEmpty()) {
                    NoiDungConThieu += "Thiếu Năm XB Để Sửa.\n";
                }
                if (NhapSach_ChonMaTacGia.getValue() == null) {
                    NoiDungConThieu += "Thiếu Mã Tác Giả Để Sửa.\n";
                }
                if (NhapSach_SoLuong.getText().isEmpty()) {
                    NoiDungConThieu += "Thiếu Số Lượng Để Sửa.\n";
                }
                if (!NoiDungConThieu.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText(NoiDungConThieu);
                    alert.showAndWait();
                    return;
                } else if (!NhapSach_SoLuong.getText().matches("\\d+") || Integer.parseInt(NhapSach_SoLuong.getText()) < 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setContentText("Số Lượng Không Hợp Lệ");
                    alert.initOwner(NhapSach_SoLuong.getScene().getWindow()); // Set the parent window
                    alert.showAndWait();
                    return;
                } else if (!NhapSach_NamXB.getText().matches("\\d+") || Integer.parseInt(NhapSach_NamXB.getText()) < 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setContentText("Năm Xuất Bản Không Hợp Lệ");
                    alert.initOwner(NhapSach_NamXB.getScene().getWindow()); // Set the parent window
                    alert.showAndWait();
                    return;
                } else if ( NhapSach_NgayNhap.getValue().isAfter(NgayThanhNamHienTai)) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Thông Báo Cho Bạn");
                        alert.setContentText("Ngày Nhập Không Hợp Lệ");
                        alert.initOwner(NhapSach_NgayNhap.getScene().getWindow()); // Set the parent window
                        alert.showAndWait();
                    });
                    return;
                } else {
                    prepare = connect.prepareStatement(sql_sua);
                    prepare.setString(1, NhapSach_TenSach.getText());
                    prepare.setString(2, NhapSach_ChonMaTheLoai.getValue());
                    prepare.setString(3, NhapSach_ChonMaTacGia.getValue());
                    prepare.setString(4, NhapSach_ChonMaNXB.getValue());
                    prepare.setString(5, NhapSach_SoLuong.getText());
                    prepare.setString(6, NhapSach_NamXB.getText());
                    prepare.setString(7, NhapSach_NgayNhap.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
                    prepare.setString(8, NhapSach_MaSach.getText()); // Corrected index here
                }
                int rowsAffected = prepare.executeUpdate();
                if (rowsAffected > 0) {
                    // Xóa thành công
                    System.out.println("Đã Sửa Sách Có Mã: " + NhapSach_MaSach.getText());
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Đã Sửa Sách Có Mã: " + NhapSach_MaSach.getText());
                    alert.showAndWait();
                } else {
                    // Không tìm thấy sách để xóa
                    System.out.println("Không tìm thấy Sách có mã : " + NhapSach_MaSach.getText());
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Không tìm thấy Sách có mã: " + NhapSach_MaSach.getText());
                    alert.showAndWait();
                }
                HienThiSach();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void XoaSach(){
        try {
            String sql = "DELETE FROM Sach WHERE MaSach = ?";
            connect = ConnectSQL.connectDB();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, NhapSach_MaSach.getText());

            int rowsAffected = prepare.executeUpdate();
            if (rowsAffected > 0) {
                // Xóa thành công
                System.out.println("Đã xóa sách có mã sách: " + NhapSach_MaSach.getText());
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo Cho Bạn");
                alert.setHeaderText(null);
                alert.setContentText("Đã xóa sách có mã sách: "  + NhapSach_MaSach.getText());
                alert.showAndWait();
            } else {
                // Không tìm thấy sách để xóa
                System.out.println("Không tìm thấy sách có mã sách: " + NhapSach_MaSach.getText());
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông Báo Cho Bạn");
                alert.setHeaderText(null);
                alert.setContentText("Không tìm thấy sách có mã sách: " + NhapSach_MaSach.getText());
                alert.showAndWait();
            }
            HienThiSach();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void dong(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
//    @FXML
//    private void openNewWindow(ActionEvent event) throws IOException {
//        // Tạo một đối tượng FXMLLoader để load file FXML của cửa sổ mới
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("newWindow.fxml"));
//        // Tạo một đối tượng Parent để chứa nội dung của cửa sổ mới
//        Parent root = loader.load();
//        // Tạo một đối tượng Scene để chứa Parent
//        Scene scene = new Scene(root);
//        // Tạo một đối tượng Stage để hiển thị Scene
//        Stage stage = new Stage();
//        // Thiết lập tiêu đề, kích thước, chế độ modal cho Stage
//        stage.setTitle("Cửa sổ mới");
//        stage.setWidth(300);
//        stage.setHeight(200);
//        stage.initModality(Modality.WINDOW_MODAL);
//        // Gán Scene cho Stage
//        stage.setScene(scene);
//        // Hiển thị Stage
//        stage.show();
//        // Lấy Stage hiện tại từ ActionEvent
//        Stage currentStage = (Stage) openButton.getScene().getWindow();
//        // Đóng Stage hiện tại
//        currentStage.close();
//    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HienThiSach();

    }

    @FXML
    private void HomeAdmin(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/Interface/HomeAdmin.fxml");
    }

    @FXML
    private void Admin(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/QuanLyThuVien/QuanLyThuVien/NhanVien.fxml");
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
