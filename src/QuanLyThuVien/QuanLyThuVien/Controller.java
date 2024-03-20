//package QuanLyThuVien;
//
//
//import java.awt.*;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.*;
//import java.text.SimpleDateFormat;
//import java.time.DateTimeException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeFormatterBuilder;
//import java.util.*;
//import java.util.Date;
//import java.util.List;
//
//import javafx.animation.TranslateTransition;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.collections.transformation.FilteredList;
//import javafx.collections.transformation.SortedList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.layout.HBox;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.paint.ImagePattern;
//import javafx.scene.control.DatePicker;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.paint.ImagePattern;
//import javafx.scene.shape.Circle;
//import javafx.stage.FileChooser;
//import javafx.stage.FileChooser.ExtensionFilter;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import javafx.util.Duration;
//import javafx.util.StringConverter;
//
//import javafx.scene.paint.Color;
//import javafx.scene.layout.VBox;
//
//public class Controller implements Initializable {
//    // Chung ---------------------------------
//    private SimpleDateFormat DateFormat;
//    private Image image;
//    private Connection connect;
//    private PreparedStatement prepare;
//    private Statement statement;
//    private ResultSet result;
//    public Alert alert;
////--------------------------------------------
//
//    @FXML
//    private Button btnHuy;
//
//
//    //--------------------------------------------------------
//
//    //---------------------------------------------------------------------
//    // Sach ---------------------------------------------------------------
//    @FXML
//    private TableView<Sach> BangSach;
//    @FXML
//    private TableColumn<Sach, String> cMaNXB;
//
//    @FXML
//    private TableColumn<Sach, Date> cNgayNhap;
//    @FXML
//    private TableColumn<Sach, String> cMaSach;
//
//    @FXML
//    private TableColumn<Sach, String> cMaTacGia;
//    @FXML
//    private TableColumn<Sach, String> cTenTheLoai;
//
//    @FXML
//    private TableColumn<Sach, String> cMaTheLoai;
//
//    @FXML
//    private TableColumn<Sach, Integer> cNamXB;
//
//    @FXML
//    private TableColumn<Sach, Integer> cSoLuong;
//
//    @FXML
//    private TableColumn<Sach, String> cTenSach;
//    @FXML
//    private TableColumn<Sach, String> cTenTacGia;
//
//    @FXML
//    private Label ttct_MaNXB;
//
//    @FXML
//    private Label ttct_MaSach;
//
//    @FXML
//    private Label ttct_MaTacGia;
//
//    @FXML
//    private Label ttct_TenTacGia;
//
//    @FXML
//    private Label ttct_TenNXB;
//    @FXML
//    private Label ttct_TenTheLoai;
//
//    @FXML
//    private Label ttct_MaTheLoai;
//
//    @FXML
//    private Label ttct_NamXB;
//
//    @FXML
//    private Label ttct_SoLuong;
//    @FXML
//    private Label ttct_TenSach;
//    @FXML
//    private ImageView ttct_AnhSach;
//    @FXML
//    private TextField TimKiem_Sach;
//    @FXML
//    private Label ttct_NgayNhap;
//    @FXML
//    private ComboBox<String> NhapSach_ChonTheLoai;
//    @FXML
//    private ComboBox<String> NhapSach_ChonMaTheLoai;
//    @FXML
//    private ComboBox<String> NhapSach_ChonMaTacGia;
//    @FXML
//    private ComboBox<String> NhapSach_ChonMaNXB;
//    @FXML
//    private ImageView NhapSach_AnhSach;
//
////    @FXML
////    private TextField NhapSach_MaNXB;
//
//    @FXML
//    private TextField NhapSach_MaSach;
//
////    @FXML
////    private TextField NhapSach_MaTacGia;
//
//    @FXML
//    private TextField NhapSach_NamXB;
//
//    @FXML
//    private DatePicker NhapSach_NgayNhap;
//
//    @FXML
//    private TextField NhapSach_SoLuong;
//
//    @FXML
//    private TextField NhapSach_TenSach;
//    @FXML
//    private TextField NhapSach_TenTacGia;
//
//    @FXML
//    private Button BTN_IMGSach;
//
//    @FXML
//    private Button BTN_HuySach;
//
//    @FXML
//    private Button BTN_SuaSach;
//
//    @FXML
//    private Button BTN_ThemSach;
//
//    @FXML
//    private Button BTN_XoaSach;
//    //--------------------------------------------------------------
//    // Nhan Vien -------------------------------------------------
//    @FXML
//    private TableView<NhanVien> BangNhanVien;
//    @FXML
//    private TableColumn<NhanVien, Boolean> cGioiTinhNhanVien;
//    @FXML
//    private TableColumn<NhanVien, String> cMaNhanVien;
//
//    @FXML
//    private TableColumn<NhanVien, Date> cNgaySinhNV;
//
//    @FXML
//    private TableColumn<NhanVien, Integer> cSoDienThoaiNV;
//
//    @FXML
//    private TableColumn<NhanVien, String> cTenDangNhap;
//
//    @FXML
//    private TableColumn<NhanVien, String> cTenNhanVien;
//    @FXML
//    private Label ttct_MaNhanVien;
//
//    @FXML
//    private Label ttct_NgaySinhNV;
//
//    @FXML
//    private Label ttct_SDTNV;
//
//    @FXML
//    private Label ttct_TenDangNhap;
//
//    @FXML
//    private Label ttct_TenNhanVien;
//    @FXML
//    private TextField TimKiem_NhanVien;
//    @FXML
//    private ImageView NhapNhanVien_AnhNhanVien;
//
//    @FXML
//    private TextField NhapNhanVien_MaNhanVien;
//
//    @FXML
//    private DatePicker NhapNhanVien_NgaySinhNV;
//
//    @FXML
//    private TextField NhapNhanVien_SDTNV;
//
//    @FXML
//    private TextField NhapNhanVien_TenDangNhap;
//
//    @FXML
//    private TextField NhapNhanVien_TenNhanVien;
//
//
//    @FXML
//    private Button BTN_HuyNhanVien;
//
//    @FXML
//    private Button BTN_IMGNhanVien;
//
//    @FXML
//    private Button BTN_SuaNhanVien;
//
//    @FXML
//    private Button BTN_ThemNhanVien;
//
//    @FXML
//    private Button BTN_XoaNhanVien;
////-------------------------------------------------------
////Doc Gia ----------------------------------------------------------------------
//    @FXML
//    private TableView<DocGia> BangDocGia;
//
//    @FXML
//    private TableColumn<DocGia, Boolean> cGioiTinhDocGia;
//    @FXML
//    private TableColumn<DocGia, String> cEmailDG;
//
//    @FXML
//    private TableColumn<DocGia, String> cMaDocGia;
//
//    @FXML
//    private TableColumn<DocGia, String> cNgayHetHan;
//
//    @FXML
//    private TableColumn<DocGia, String> cNgaySinhDG;
//
//    @FXML
//    private TableColumn<DocGia, String> cSDTDG;
//
//    @FXML
//    private TableColumn<DocGia, String> cSoThe;
//
//    @FXML
//    private TableColumn<DocGia, String> cTenDocGia;
//    @FXML
//    private Label ttct_EmailDG;
//
//    @FXML
//    private Label ttct_MaDocGia;
//
//    @FXML
//    private Label ttct_NgayHetHan;
//    @FXML
//    private Label ttct_NgaySinhDG;
//
//    @FXML
//    private Label ttct_SDTDG;
//
//    @FXML
//    private Label ttct_SoSachDaMuon;
//
//    @FXML
//    private Label ttct_TenDocGia;
//    @FXML
//    private Label ttct_SoThe;
//    @FXML
//    private TextField TimKiem_DocGia;
//    @FXML
//    private ImageView NhapDocGia_AnhDocGia;
//
//    @FXML
//    private TextField NhapDocGia_EmailDG;
//
//    @FXML
//    private TextField NhapDocGia_MaDocGia;
//
//    @FXML
//    private DatePicker NhapDocGia_NgaySinhDG;
//
//    @FXML
//    private TextField NhapDocGia_SDTDG;
//
//    @FXML
//    private TextField NhapDocGia_SoThe;
//
//    @FXML
//    private TextField NhapDocGia_TenDocGia;
//
//    @FXML
//    private Button BTN_HuyDocGia;
//
//    @FXML
//    private Button BTN_SuaDocGia;
//
//    @FXML
//    private Button BTN_ThemDocGia;
//
//    @FXML
//    private Button BTN_XoaDocGia;
//
//    //-------------------------------------------------------
////Code
//    //-------------------------------------------------------------------------------
//    //chung----------------------------------------------------------------
//
//    //--------------------------------------------------------------------
//    //// Sach-----------------------------------------------------------------------------------------------
////    @FXML
////    private VBox Card_Layout;
////    @FXML
////    private AnchorPane Card_Box;
////    private List<Sach> SachDaThemGanDay;
////    public ObservableList<Sach> SachDaThemGanDay(){
////        ObservableList<Sach> ls = FXCollections.observableArrayList();
////        try {
////            connect = ConnectSQL.connectDB();
////            String sql = "SELECT Sach.*, TacGia.TenTacGia\n" +
////                    "FROM Sach INNER JOIN TacGia\n" +
////                    "ON Sach.MaTacGia = TacGia.MaTacGia\n" +
////                    "ORDER BY Sach.NgayNhap DESC";
////            prepare = connect.prepareStatement(sql);
////            result = prepare.executeQuery();
////            while (result.next()){
////                Sach sach = new Sach();
////                sach.setTenSach(result.getString("TenSach"));
////                sach.setTenTacGia(result.getString("TenTacGia"));
////                sach.setImgSach(result.getString("imgSach"));
////                ls.add(sach);
////            }
////
////        }catch (SQLException e){
////            e.printStackTrace();
////        }
////        return ls;
////    }
////    public void HienThiSachDaThemGanDay(){
////        SachDaThemGanDay = new ArrayList<>(SachDaThemGanDay());
////        try {
////            for (int i = 0; i< SachDaThemGanDay.size(); i++){
////                FXMLLoader fxmlLoader = new FXMLLoader();
////                fxmlLoader.setLocation(getClass().getResource("Card.fxml"));
////                Card_Box = fxmlLoader.load();
////                CardController cardController = fxmlLoader.getController();
////                cardController.setData(SachDaThemGanDay.get(i));
////                Card_Layout.getChildren().add(Card_Box);
////            }
////        }catch (IOException e){
////            e.printStackTrace();
////        }
////    }
////            public void ChonTheloaiSach() {
////                try {
////                    connect = ConnectSQL.connectDB();
////                    String sql = "SELECT * FROM TheLoai";
////                    prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
////                    result = prepare.executeQuery();
////                    ObservableList<String> list = FXCollections.observableArrayList();
////                    while (result.next()){
////                        list.add(result.getNString( "TenTheLoai"));
////                    }
////                    NhapSach_ChonTheLoai.setItems(list);
//////                    NhapSach_ChonTheLoai.setConverter(new StringConverter<String>() {
//////                        @Override
//////                        public String toString(String object) {
//////                            // Trả về cách bạn muốn mỗi mục được hiển thị trong combobox
//////                            // Ví dụ, nếu đối tượng là "Phiêu Lưu", trả về "Adventure"
//////                            switch (object) {
//////                                case "Phiêu Lưu": return "Adventure";
//////                                case "Tiểu Thuyết": return "Novel";
//////                                case "Trinh Thám": return "Mystery";
//////                                case "Báo": return "Newspaper";
//////                                // Thêm các kiểu khác theo ý bạn
//////                                default: return object;
//////                            }
//////                        }
//////                        @Override
//////                        public String fromString(String string) {
//////                            // Trả về cách bạn muốn mỗi mục được chuyển đổi từ một chuỗi
//////                            // Ví dụ, nếu chuỗi là "Adventure", trả về "Phiêu Lưu"
//////                            switch (string) {
//////                                case "Adventure": return "Phiêu Lưu";
//////                                case "Novel": return "Tiểu Thuyết";
//////                                case "Mystery": return "Trinh Thám";
//////                                case "Newspaper": return "Báo";
//////                                // Thêm các kiểu khác theo ý bạn
//////                                default: return string;
//////                            }
//////                        }
//////                    });
////                } catch (SQLException e){
////                    e.printStackTrace();
////                }
////            }
////            //----------------------------------------------------------------------------------------------
////            public void ChonMaTacGia() {
////                try {
////                    connect = ConnectSQL.connectDB();
////                    String sql = "SELECT * FROM TacGia";
////                    prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
////                    result = prepare.executeQuery();
////                    ObservableList<String> list = FXCollections.observableArrayList();
////                    while (result.next()){
////                        list.add(result.getString("MaTacGia"));
////                    }
////                    NhapSach_ChonMaTacGia.setItems(list);
////                } catch (SQLException e){
////                    e.printStackTrace();
////                }
////            }
////            public void ChonMaNXB() {
////                try {
////                    connect = ConnectSQL.connectDB();
////                    String sql = "SELECT * FROM NXB";
////                    prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
////                    result = prepare.executeQuery();
////                    ObservableList<String> list = FXCollections.observableArrayList();
////                    while (result.next()){
////                        list.add(result.getString("MaNXB"));
////                    }
////                    NhapSach_ChonMaNXB.setItems(list);
////                } catch (SQLException e){
////                    e.printStackTrace();
////                }
////            }
////            public void ChonMaTheLoai() {
////                try {
////                    connect = ConnectSQL.connectDB();
////                    String sql = "SELECT * FROM TheLoai";
////                    prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
////                    result = prepare.executeQuery();
////                    ObservableList<String> list = FXCollections.observableArrayList();
////                    while (result.next()){
////                        list.add(result.getString("MaTheLoai"));
////                    }
////                    NhapSach_ChonMaTheLoai.setItems(list);
////                } catch (SQLException e){
////                    e.printStackTrace();
////                }
////            }
////            //----
////            public ObservableList<Sach> DuLieuSach() { //ObservableList: là cho phép người dùng theo dõi khi mà chúng có sự thay đổi
////                ObservableList<Sach> CacQuyenSach = FXCollections.observableArrayList(); // Tạo ra 1 ObservableList rỗng mang tên là 'CacQuyenSach' để lưu trữ sách
////                String sql = "SELECT Sach.*,  TheLoai.TenTheLoai, TacGia.TenTacGia, NXB.TenNXB\n" +
////                        "FROM Sach\n" +
////                        "INNER JOIN TheLoai\n" +
////                        "ON Sach.MaTheLoai = TheLoai.MaTheLoai\n" +
////                        "INNER JOIN TacGia\n" +
////                        "ON Sach.MaTacGia = TacGia.MaTacGia\n" +
////                        "INNER JOIN NXB\n" +
////                        "ON Sach.MaNXB = NXB.MaNXB";
////
////                connect = ConnectSQL.connectDB();//kết nối sql
////                try {
////                    Sach sachs;
////                    prepare = connect.prepareStatement(sql); // PrepareStatement là một đối tượng Java dùng để biểu diễn một câu lệnh SQL 'đã được biên dịch trước'. Một câu lệnh SQL được biên dịch trước và lưu trữ trong một đối tượng PrepareStatement. Đối tượng này có thể được sử dụng để thực thi câu lệnh nhiều lần một cách hiệu quả.
////                    result = prepare.executeQuery();
////                    while (result.next()) { //Nó lặp qua tập kết quả và tạo một đối tượng Sach mới cho mỗi hàng, sử dụng các giá trị từ các cột làm tham số cho hàm tạo.
////                        sachs = new Sach(result.getString("MaSach"),
////                            result.getString("TenSach"),
////                            result.getString("MaTheLoai"),
////                            result.getString("TenTheLoai"),
////                            result.getString("MaTacGia"),
////                            result.getString("TenTacGia"),
////                            result.getString("MaNXB"),
////                            result.getInt("NamXB"),
////                            result.getInt("SoLuong"),
////                            result.getDate("NgayNhap"),
////                            result.getString("ImgSach")
////                        );
////                        CacQuyenSach.add(sachs);//Nó thêm từng đối tượng Sách vào danh sách CacQuyenSach.
////                    }
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////                return CacQuyenSach;//Nó trả về danh sách CacQuyenSach là đầu ra của hàm.
////            }
////            //-----------
////            private ObservableList<Sach> DanhSachSach;
////            public void HienThiSach() {
////                ChonMaTacGia();
////                ChonTheloaiSach();
////                ChonMaNXB();
////                ChonMaTheLoai();
////                HienThiSachDaThemGanDay();
////                DanhSachSach = DuLieuSach();
////                cMaSach.setCellValueFactory(new PropertyValueFactory<Sach, String>("MaSach"));
////                cTenSach.setCellValueFactory(new PropertyValueFactory<Sach, String>("TenSach"));
////                cMaTacGia.setCellValueFactory(new PropertyValueFactory<Sach, String>("MaTacGia"));
////                cTenTacGia.setCellValueFactory(new PropertyValueFactory<Sach, String>("TenTacGia"));
////                cMaTheLoai.setCellValueFactory(new PropertyValueFactory<Sach, String>("MaTheLoai"));
////                cTenTheLoai.setCellValueFactory(new PropertyValueFactory<Sach, String>("TenTheLoai"));
////                cMaNXB.setCellValueFactory(new PropertyValueFactory<Sach, String>("MaNXB"));
////                cSoLuong.setCellValueFactory(new PropertyValueFactory<Sach, Integer>("SoLuong"));
////                cNamXB.setCellValueFactory(new PropertyValueFactory<Sach, Integer>("NamXB"));
////                cNgayNhap.setCellValueFactory(new PropertyValueFactory<Sach, Date>("NgayNhap"));
////                BangSach.setItems(DanhSachSach);
////                TimKiem_Sach();
////            }
////            private ObservableList<Sach> DanhSach_TTCT;
////            public void ChonSach_TTCT() {
////                Sach dulieusach = BangSach.getSelectionModel().getSelectedItem();
////                DanhSach_TTCT = DuLieuSach();
////                int num = BangSach.getSelectionModel().getFocusedIndex();
////                if ((num - 1) < -1) {
////                    return;
////                }
//////                ttct_MaSach.setText(dulieusach.getMaSach());
//////                ttct_TenSach.setText(dulieusach.getTenSach());
//////                ttct_MaTacGia.setText(dulieusach.getMaTacGia());
//////                ttct_TenTacGia.setText(dulieusach.getTenTacGia());
//////                ttct_MaTheLoai.setText(dulieusach.getMaTheLoai());
//////                ttct_TenTheLoai.setText(dulieusach.getTenTheLoai());
//////                ttct_MaNXB.setText(dulieusach.getMaNXB());
//////
//////                //---------------------------------
//////
//////                //------------------------------
//////
//////                ttct_NamXB.setText(String.valueOf(dulieusach.getNamXB())); // INT
//////                ttct_SoLuong.setText(String.valueOf(dulieusach.getSoLuong())); //INT
//////                ttct_NgayNhap.setText(String.valueOf(dulieusach.getNgayNhap()));
//////                String uri = "file:" + dulieusach.getImgSach();
//////                image = new Image(uri, 134, 171, false, true);
//////                ttct_AnhSach.setImage(image);
////
////
////                NhapSach_MaSach.setText(dulieusach.getMaSach());
////                NhapSach_TenSach.setText(dulieusach.getTenSach());
////                NhapSach_ChonMaTacGia.setValue(dulieusach.getMaTacGia());
////                NhapSach_ChonMaTheLoai.setValue(dulieusach.getMaTheLoai());
////                NhapSach_ChonTheLoai.setValue(dulieusach.getTenTheLoai());
////                NhapSach_ChonMaNXB.setValue(dulieusach.getMaNXB());
////                NhapSach_NamXB.setText(String.valueOf(dulieusach.getNamXB()));
////                NhapSach_SoLuong.setText(String.valueOf(dulieusach.getSoLuong()));
////                NhapSach_NgayNhap.setValue(dulieusach.getNgayNhap().toLocalDate());
////
////            }
////            public void TimKiem_Sach(){
////                // Wrap the ObservableList in a FilteredList (initially display all data).
////                FilteredList<Sach> filteredData = new FilteredList<>(DanhSachSach, b -> true);
////                // 2. Set the filter Predicate whenever the filter changes.
////                TimKiem_Sach.textProperty().addListener((observable, oldValue, newValue) -> {
////                    filteredData.setPredicate(employee -> {
////                        // If filter text is empty, display all persons.
////                        if (newValue == null || newValue.isEmpty()) {
////                            return true;
////                        }
////                        // Compare first name and last name of every person with filter text.
////                        String lowerCaseFilter = newValue.toLowerCase();
////                        if (employee.getMaSach().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
////                            return true; // Filter matches first name.
////                        } else if (employee.getTenSach().toLowerCase().indexOf(lowerCaseFilter) != -1) {
////                            return true; // Filter matches last name.
////                        }
////                        else if (String.valueOf(employee.getMaNXB()).indexOf(lowerCaseFilter)!=-1)
////                            return true;
////                        else
////                            return false;// Does not match.
////                    });
////                });
////                // 3. Wrap the FilteredList in a SortedList.
////                SortedList<Sach> sortedData = new SortedList<>(filteredData);
////                // 4. Bind the SortedList comparator to the TableView comparator.
////                // 	  Otherwise, sorting the TableView would have no effect.
////                sortedData.comparatorProperty().bind(BangSach.comparatorProperty());
////                // 5. Add sorted (and filtered) data to the table.
////                BangSach.setItems(sortedData);
////
////                //----------------------------------------------------------
////            }
////                public void HuySach() {
////                    NhapSach_MaSach.setText("");
////                    NhapSach_TenSach.setText("");
////
////                    NhapSach_ChonMaTheLoai.valueProperty().unbind();
////                    NhapSach_ChonMaTheLoai.setValue(null);
////
////
////                    NhapSach_ChonTheLoai.valueProperty().unbind();
////                    NhapSach_ChonTheLoai.setValue(null);
////
////
////                    NhapSach_ChonMaTacGia.valueProperty().unbind();
////                    NhapSach_ChonMaTacGia.setValue(null);
////
////
////                    NhapSach_ChonMaNXB.valueProperty().unbind();
////                    NhapSach_ChonMaNXB.setValue(null);
////
////
////                    NhapSach_NamXB.setText("");
////                    NhapSach_NgayNhap.setValue(null);
////                    NhapSach_SoLuong.setText("");
////                    System.out.println("Đã Hủy Thành Công");
////                }
////                public void ThemSach(){
////                try {
////                    connect = ConnectSQL.connectDB();
////                    String sql_Nhap = "INSERT INTO Sach (MaSach, TenSach, MaTheLoai, MaTacGia, MaNXB, SoLuong, NamXB,  NgayNhap) VALUES (?, ?, ?, ?, ?, ?, ?, ?)\n"; /*imgSach,*/
//////                    }
////                    //---------------------------------------------------
////                    String NoiDungConThieu = "";
////                    if (NhapSach_MaSach.getText().isEmpty()) {
////                        NoiDungConThieu += "Nhập Mã Sách.\n";
////                    } if (NhapSach_TenSach.getText().isEmpty()) {
////                        NoiDungConThieu += "Nhập Tên Sách.\n";
////                    } if (NhapSach_ChonMaNXB.getValue() == null) {
////                       NoiDungConThieu += "Nhập Mã NXB.\n";
////                    } if (NhapSach_NamXB.getText().isEmpty()) {
////                        NoiDungConThieu += "Nhập Năm XB.\n";
////                    }if (NhapSach_ChonMaTacGia.getValue() == null) {
////                        NoiDungConThieu += "Nhập Mã Tác Giả.\n";
////                    }if (NhapSach_SoLuong.getText().isEmpty()) {
////                        NoiDungConThieu += "Nhập Số Lượng.\n";
//////                    }if (NhapSach_ChonTheLoai.getValue() == null){
//////                        NoiDungConThieu += "Nhap The Loai.\n";
////                    }if (!NoiDungConThieu.isEmpty()) {
////                        Alert alert = new Alert(AlertType.WARNING);
////                        alert.setTitle("Thông Báo Cho Bạn");
////                        alert.setHeaderText(null);
////                        alert.setContentText(NoiDungConThieu);
////                        alert.showAndWait();
////                    }else {
////                        prepare = connect.prepareStatement(sql_Nhap);
////                        prepare.setString(1, NhapSach_MaSach.getText());
////                        prepare.setString(2, NhapSach_TenSach.getText());
////                        prepare.setString(3, NhapSach_ChonMaTheLoai.getValue());
////                        prepare.setString(4, NhapSach_ChonMaTacGia.getValue());
////                        prepare.setString(5, NhapSach_ChonMaNXB.getValue());
////                        prepare.setString(6, NhapSach_SoLuong.getText());
////                        prepare.setString(7, NhapSach_NamXB.getText());
////                        prepare.setString(8, NhapSach_NgayNhap.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
////
//////                        prepare.setString(9, NhapSach_ChonTheLoai.getValue());
////                        System.out.println("Đã Thêm Thành Công Sách Có Mã: " + NhapSach_MaSach.getText());
////                        prepare.executeUpdate();
////                        alert = new Alert(AlertType.INFORMATION);
////                        alert.setTitle("Thông Báo Cho Bạn");
////                        alert.setHeaderText(null);
////                        alert.setContentText("Đã Thêm Thành Công Sách Có Mã: " + NhapSach_MaSach.getText());
////                        alert.showAndWait();
////                        HienThiSach();
////                    }
////                } catch (SQLException e){
////                    e.printStackTrace();
////                }
////                }
////
////    public void SuaSach(){
////        try {
////            String sql = "UPDATE Sach SET TenSach = ?, MaTheLoai = ?, MaTacGia = ?, MaNXB = ?, SoLuong =?, NamXB = ?, NgayNhap = ?   WHERE MaSach = ?";
////            connect = ConnectSQL.connectDB();
////            prepare = connect.prepareStatement(sql);
////
////            prepare.setString(8, NhapSach_MaSach.getText());
////            prepare.setString(1, NhapSach_TenSach.getText());
////            prepare.setString(2, NhapSach_ChonMaTheLoai.getValue());
////            prepare.setString(3, NhapSach_ChonMaTacGia.getValue());
////            prepare.setString(4, NhapSach_ChonMaNXB.getValue());
////            prepare.setString(5, NhapSach_SoLuong.getText());
////            prepare.setString(6, NhapSach_NamXB.getText());
////            prepare.setString(7, NhapSach_NgayNhap.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
////
////            prepare.executeUpdate();
////            System.out.println("Đã Sửa Thành Công");
////            alert = new Alert(AlertType.INFORMATION);
////            alert.setTitle("Thông Báo Cho Bạn");
////            alert.setHeaderText(null);
////            alert.setContentText("Đã Sửa Thành Công");
////            alert.showAndWait();
////            HienThiSach();
////
////        }catch (SQLException e){
////            e.printStackTrace();
////        }
////
////    }
////    public void XoaSach(){
////        try {
////            String sql = "DELETE FROM Sach WHERE MaSach = ?";
////            connect = ConnectSQL.connectDB();
////            prepare = connect.prepareStatement(sql);
////            prepare.setString(1, NhapSach_MaSach.getText());
////
////            int rowsAffected = prepare.executeUpdate();
////            if (rowsAffected > 0) {
////                // Xóa thành công
////                System.out.println("Đã xóa sách có mã sách: " + NhapSach_MaSach.getText());
////                alert = new Alert(AlertType.INFORMATION);
////                alert.setTitle("Thông Báo Cho Bạn");
////                alert.setHeaderText(null);
////                alert.setContentText("Đã xóa sách có mã sách: "  + NhapSach_MaSach.getText());
////                alert.showAndWait();
////            } else {
////                // Không tìm thấy sách để xóa
////                System.out.println("Không tìm thấy sách có mã sách: " + NhapSach_MaSach.getText());
////                alert = new Alert(AlertType.INFORMATION);
////                alert.setTitle("Thông Báo Cho Bạn");
////                alert.setHeaderText(null);
////                alert.setContentText("Không tìm thấy sách có mã sách: " + NhapSach_MaSach.getText());
////                alert.showAndWait();
////            }
////            HienThiSach();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////    }
//
////----------------------------------------------------------------------------------------------------------------------------------
//
//    //NhanVien-------------------------------------------------------------------------------------------------------------------------------------
//        public ObservableList<NhanVien> DuLieuNhanVien() {
//            ObservableList<NhanVien> CacNhanVien = FXCollections.observableArrayList();
//            String sql = "SELECT * FROM NhanVien";
//            connect = ConnectSQL.connectDB();
//            try {
//                NhanVien nhanviens;
//                prepare = connect.prepareStatement(sql);
//                result = prepare.executeQuery();
//                while (result.next()) { //Nó lặp qua tập kết quả và tạo một đối tượng NhanVien mới cho mỗi hàng, sử dụng các giá trị từ các cột làm tham số cho hàm tạo.
//                    nhanviens = new NhanVien(result.getString("MaNhanVien"),
//                            result.getString("TenNhanVien"),
//                            result.getDate("NgSinh"),
//                            result.getInt("SDT"),
//                            result.getString("TenDangNhap"),
//                            result.getBoolean("GioiTinh"),
//                            result.getString("ImgNhanVien")
//                    );
//                    CacNhanVien.add(nhanviens);//Nó thêm từng đối tượng Sách vào danh sách CacNhanVien
//                }
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//            return CacNhanVien;
//        }
//        private ObservableList<NhanVien> DanhSachNhanVien;
//        public void HienThiNhanVien(){
//            DanhSachNhanVien = DuLieuNhanVien();
//            cMaNhanVien.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("MaNhanVien"));
//            cTenNhanVien.setCellValueFactory(new PropertyValueFactory<NhanVien, String >("TenNhanVien"));
//            cNgaySinhNV.setCellValueFactory(new PropertyValueFactory<NhanVien, Date>("NgaySinh"));
//            cSoDienThoaiNV.setCellValueFactory(new PropertyValueFactory<NhanVien, Integer>("SDT"));
//            cTenDangNhap.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("TenDangNhap"));
//            cGioiTinhNhanVien.setCellValueFactory(new PropertyValueFactory<NhanVien, Boolean>("GioiTinh"));
//            BangNhanVien.setItems(DanhSachNhanVien);
//            TimKiem_NhanVien();
//        }
//
//        public void ChonNhanVien_TTCT(){
//            NhanVien dulieunhanvien = BangNhanVien.getSelectionModel().getSelectedItem();
//            int num = BangNhanVien.getSelectionModel().getFocusedIndex();
//            if ((num - 1) < -1) {
//                return ;
//            }
//            ttct_MaNhanVien.setText(dulieunhanvien.getMaNhanVien());
//            ttct_TenNhanVien.setText(dulieunhanvien.getTenNhanVien());
//            ttct_TenDangNhap.setText(dulieunhanvien.getTenDangNhap());
//            ttct_NgaySinhNV.setText(String.valueOf(dulieunhanvien.getNgaySinh()));
//            ttct_SDTNV.setText(String.valueOf(dulieunhanvien.getSDT()));
//        }
//    public void TimKiem_NhanVien(){
//        // Wrap the ObservableList in a FilteredList (initially display all data).
//        FilteredList<NhanVien> filteredData = new FilteredList<>(DanhSachNhanVien, b -> true);
//        // 2. Set the filter Predicate whenever the filter changes.
//        TimKiem_NhanVien.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(employee -> {
//                // If filter text is empty, display all persons.
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                // Compare first name and last name of every person with filter text.
//                String lowerCaseFilter = newValue.toLowerCase();
//                if (employee.getMaNhanVien().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//                    return true; // Filter matches first name.
//                } else if (employee.getTenNhanVien().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches last name.
//                }
//                else if (String.valueOf(employee.getTenDangNhap()).indexOf(lowerCaseFilter)!=-1)
//                    return true;
//                else
//                    return false; // Does not match.
//            });
//        });
//        // 3. Wrap the FilteredList in a SortedList.
//        SortedList<NhanVien> sortedData = new SortedList<>(filteredData);
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        // 	  Otherwise, sorting the TableView would have no effect.
//        sortedData.comparatorProperty().bind(BangNhanVien.comparatorProperty());
//        // 5. Add sorted (and filtered) data to the table.
//        BangNhanVien.setItems(sortedData);
//    }
//        public void HuyNhanVien(){
//            NhapNhanVien_MaNhanVien.setText("");
//            NhapNhanVien_TenNhanVien.setText("");
//            NhapNhanVien_TenDangNhap.setText("");
//            NhapNhanVien_SDTNV.setText("");
//            NhapNhanVien_AnhNhanVien.setImage(null);
//            NhapNhanVien_NgaySinhNV.setValue(null);
//        }
//        public void ThemNhanVien(){
//            try {
//                connect = ConnectSQL.connectDB();
//                String sql_Nhap = "INSERT INTO NhanVien (MaNhanVien, TenNhanVien, NgSinh, SDT, TenDangNhap) VALUES (?, ?, ?, ?, ?)\n"; /*imgSach,*/
////                    }
//                //---------------------------------------------------
//                String NoiDungConThieu = "";
//                if (NhapNhanVien_MaNhanVien.getText().isEmpty()) {
//                    NoiDungConThieu += "Nhập Mã Nhân Viên.\n";
//                } if (NhapNhanVien_TenNhanVien.getText().isEmpty()) {
//                    NoiDungConThieu += "Nhập Tên Nhân Viên.\n";
////                } if (NhapSach_ChonMaNXB.getValue() == null) {
////                    NoiDungConThieu += "Nhap Ma NXB.\n";
//                } if (NhapNhanVien_NgaySinhNV.getValue() == null) {
//                    NoiDungConThieu += "Nhập Ngày Sinh Nhân Viên.\n";
//                }if (NhapNhanVien_SDTNV.getText().isEmpty()) {
//                    NoiDungConThieu += "Nhập Số Điện Thoại Nhân Viên.\n";
//                }if (NhapNhanVien_TenDangNhap.getText().isEmpty()) {
//                    NoiDungConThieu += "Nhập Tên Đăng Nhập Của Nhân Viên.\n";
////                }if (NhapSach_ChonTheLoai.getValue() == null){
////                    NoiDungConThieu += "Nhap The Loai.\n";
//                }if (!NoiDungConThieu.isEmpty()) {
//                    Alert alert = new Alert(AlertType.WARNING);
//                    alert.setTitle("Thông Báo Cho Bạn");
//                    alert.setHeaderText(null);
//                    alert.setContentText(NoiDungConThieu);
//                    alert.showAndWait();
//                }else {
//                    prepare = connect.prepareStatement(sql_Nhap);
//                    prepare.setString(1, NhapNhanVien_MaNhanVien.getText());
//                    prepare.setString(2, NhapNhanVien_TenNhanVien.getText());
//                    prepare.setString(3, NhapNhanVien_NgaySinhNV.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
//                    prepare.setString(4, NhapNhanVien_SDTNV.getText());
//                    prepare.setString(5, NhapNhanVien_TenDangNhap.getText());
//
////                        prepare.setString(9, NhapSach_ChonTheLoai.getValue());
//                    System.out.println("Đã Thêm Thành Công Nhân Viên Có Mã: " + NhapNhanVien_MaNhanVien.getText());
//                    prepare.executeUpdate();
//                    alert = new Alert(AlertType.INFORMATION);
//                    alert.setTitle("Thông Báo Cho Bạn");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Đã Thêm Thành Công Nhân Viên Có Mã: " + NhapNhanVien_MaNhanVien.getText());
//                    alert.showAndWait();
//                    HienThiNhanVien();
//                }
//            } catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//
//    public void SuaNhanVien(){
//        try {
//            String sql = "UPDATE NhanVien SET TenNhanVien = ?, NgSinh = ?, SDT = ?, TenDangNhap = ? WHERE MaNhanVien = ?";
//            connect = ConnectSQL.connectDB();
//            prepare = connect.prepareStatement(sql);
//
//            prepare.setString(5, NhapNhanVien_MaNhanVien.getText());
//            prepare.setString(1, NhapNhanVien_TenNhanVien.getText());
//            prepare.setString(2, NhapNhanVien_NgaySinhNV.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
//            prepare.setString(3,NhapNhanVien_SDTNV.getText());
//            prepare.setString(4,NhapNhanVien_TenDangNhap.getText());
//
//
//            prepare.executeUpdate();
//            System.out.println("Đã Sửa Thành Công");
//            alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Thông Báo Cho Bạn");
//            alert.setHeaderText(null);
//            alert.setContentText("Đã Sửa Thành Công");
//            alert.showAndWait();
//            HienThiNhanVien();
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//    public void XoaNhanVien(){
//        try {
//            String sql = "DELETE FROM NhanVien WHERE MaNhanVien = ?";
//            connect = ConnectSQL.connectDB();
//            prepare = connect.prepareStatement(sql);
//            prepare.setString(1, NhapNhanVien_MaNhanVien.getText());
//
//            int rowsAffected = prepare.executeUpdate();
//            if (rowsAffected > 0) {
//                // Xóa thành công
//                System.out.println("Đã xóa sách có mã sách: " + NhapNhanVien_MaNhanVien.getText());
//                alert = new Alert(AlertType.INFORMATION);
//                alert.setTitle("Thông Báo Cho Bạn");
//                alert.setHeaderText(null);
//                alert.setContentText("Đã xóa sách có mã sách: "  + NhapNhanVien_MaNhanVien.getText());
//                alert.showAndWait();
//            } else {
//                // Không tìm thấy sách để xóa
//                System.out.println("Không tìm thấy sách có mã sách: " + NhapNhanVien_MaNhanVien.getText());
//                alert = new Alert(AlertType.INFORMATION);
//                alert.setTitle("Thông Báo Cho Bạn");
//                alert.setHeaderText(null);
//                alert.setContentText("Không tìm thấy sách có mã sách: " + NhapNhanVien_MaNhanVien.getText());
//                alert.showAndWait();
//            }
//            HienThiNhanVien();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //------------------------------------------------------------------------------------------------
//    // Doc Gia ----------------------------------------------------------------------------------
//        public ObservableList<DocGia> DuLieuDocGia(){
//            ObservableList<DocGia> CacDocGia = FXCollections.observableArrayList();
//            String sql = "SELECT * FROM DocGia";
//            connect = ConnectSQL.connectDB();
//            try {
//                DocGia docgias;
//                prepare = connect.prepareStatement(sql);
//                result = prepare.executeQuery();
//                while (result.next()){
//                    docgias = new DocGia(result.getString("MaDocGia"),
//                            result.getString("TenDocGia"),
//                            result.getString("SDT"),
//                            result.getString("SoThe"),
//                            result.getString("Email"),
//                            result.getBoolean("GioiTinh")
////                            , result.getDate("NgaySinh")
//
//                    );
//                    CacDocGia.add(docgias);
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return CacDocGia;
//        }
//        private ObservableList<DocGia> DanhSachDocGia;
//        public void HienThiDocGia(){
//            DanhSachDocGia = DuLieuDocGia();
//            cMaDocGia.setCellValueFactory(new PropertyValueFactory<DocGia, String>("MaDocGia"));
//            cTenDocGia.setCellValueFactory(new PropertyValueFactory<DocGia, String>("TenDocGia"));
//            cSDTDG.setCellValueFactory(new PropertyValueFactory<DocGia, String>("SDT"));
//            cSoThe.setCellValueFactory(new PropertyValueFactory<DocGia, String>("SoThe"));
//            cEmailDG.setCellValueFactory(new PropertyValueFactory<DocGia, String>("Email"));
//            cNgaySinhDG.setCellValueFactory(new PropertyValueFactory<DocGia, String>("NgaySinh"));
//            cGioiTinhDocGia.setCellValueFactory(new PropertyValueFactory<DocGia, Boolean>("GioiTinh"));
//            BangDocGia.setItems(DanhSachDocGia);
//            TimKiem_DocGia();
//        }
//
//        public void ChonDocGia_TTCT(){
//            DocGia dulieudocgia = BangDocGia.getSelectionModel().getSelectedItem();
//            int num = BangDocGia.getSelectionModel().getFocusedIndex();
//            if ((num - 1) < -1) {
//                return ;
//            }
//            NhapDocGia_MaDocGia.setText(dulieudocgia.getMaDocGia());
//            NhapDocGia_TenDocGia.setText(dulieudocgia.getTenDocGia());
//            NhapDocGia_SoThe.setText(dulieudocgia.getSoThe());
//            NhapDocGia_SDTDG.setText(dulieudocgia.getSDT());
//            NhapDocGia_EmailDG.setText(dulieudocgia.getEmail());
////            NhapDocGia_NgaySinhDG.setValue(LocalDate.parse(dulieudocgia.getNgaySinh().toLocaleString()));
////            ttct_MaDocGia.setText(dulieudocgia.getMaDocGia());
////            ttct_TenDocGia.setText(dulieudocgia.getTenDocGia());
////            ttct_SDTDG.setText(dulieudocgia.getSDT());
////            ttct_SoThe.setText(dulieudocgia.getSoThe());
////            ttct_EmailDG.setText(dulieudocgia.getEmail());
////            ttct_NgaySinhDG.setText(DateFormat.format(dulieudocgia.getNgaySinh()));
//            //ttct_NgayHetHan
//
//        }
//    public void TimKiem_DocGia(){
////        DanhSachDocGia = DuLieuDocGia();
////        cMaDocGia.setCellValueFactory(new PropertyValueFactory<DocGia, String>("MaDocGia"));
////        cTenDocGia.setCellValueFactory(new PropertyValueFactory<DocGia, String>("TenDocGia"));
////        cSDTDG.setCellValueFactory(new PropertyValueFactory<DocGia, String>("SDT"));
////        cSoThe.setCellValueFactory(new PropertyValueFactory<DocGia, String>("SoThe"));
////        cEmailDG.setCellValueFactory(new PropertyValueFactory<DocGia, String>("Email"));
//        //-----------------------------------------------------------------------------------------
//        // Wrap the ObservableList in a FilteredList (initially display all data).
//        FilteredList<DocGia> filteredData = new FilteredList<>(DanhSachDocGia, b -> true);
//        // 2. Set the filter Predicate whenever the filter changes.
//        TimKiem_DocGia.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(employee -> {
//                // If filter text is empty, display all persons.
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                // Compare first name and last name of every person with filter text.
//                String lowerCaseFilter = newValue.toLowerCase();
//                if (employee.getMaDocGia().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//                    return true; // Filter matches first name.
//                } else if (employee.getTenDocGia().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches last name.
//                }
//                else if (String.valueOf(employee.getEmail()).indexOf(lowerCaseFilter)!=-1)
//                    return true;
//                else
//                    return false; // Does not match.
//            });
//        });
//        // 3. Wrap the FilteredList in a SortedList.
//        SortedList<DocGia> sortedData = new SortedList<>(filteredData);
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        // 	  Otherwise, sorting the TableView would have no effect.
//        sortedData.comparatorProperty().bind(BangDocGia.comparatorProperty());
//        // 5. Add sorted (and filtered) data to the table.
//        BangDocGia.setItems(sortedData);
//    }
//        public void HuyDocGia(){
//            NhapDocGia_MaDocGia.setText("");
//            NhapDocGia_TenDocGia.setText("");
//            NhapDocGia_SDTDG.setText("");
//            NhapDocGia_EmailDG.setText("");
//            NhapDocGia_NgaySinhDG.setValue(null);
//            NhapDocGia_SoThe.setText("");
//            NhapDocGia_AnhDocGia.setImage(null);
//        }
//
//        public void ThemDocGia(){
//            try {
//                connect = ConnectSQL.connectDB();
//                String sql_Nhap = "INSERT INTO DocGia (MaDocGia, TenDocGia, SDT, SoThe, Email) VALUES ( ?, ?, ?, ?, ?)\n"; /*imgSach,*/
////                    }
//                //---------------------------------------------------
//                String NoiDungConThieu = "";
//                if (NhapDocGia_MaDocGia.getText().isEmpty()) {
//                    NoiDungConThieu += "Nhập Mã Độc Giả.\n";
//                } if (NhapDocGia_TenDocGia.getText().isEmpty()) {
//                    NoiDungConThieu += "Nhập Tên Độc Giả.\n";
//                } if (NhapDocGia_SDTDG.getText().isEmpty()) {
//                    NoiDungConThieu += "Nhập Số Điện Thoại Độc Giả.\n";
//                } if (NhapDocGia_SoThe.getText().isEmpty()) {
//                    NoiDungConThieu += "Nhập Số Thẻ.\n";
//                }if (NhapDocGia_EmailDG.getText().isEmpty()) {
//                    NoiDungConThieu += "Nhập Email.\n";
//                }if (!NoiDungConThieu.isEmpty()) {
//                    Alert alert = new Alert(AlertType.WARNING);
//                    alert.setTitle("Thông Báo Cho Bạn");
//                    alert.setHeaderText(null);
//                    alert.setContentText(NoiDungConThieu);
//                    alert.showAndWait();
//                }else {
//                    prepare = connect.prepareStatement(sql_Nhap);
//                    prepare.setString(1, NhapDocGia_MaDocGia.getText());
//                    prepare.setString(2, NhapDocGia_TenDocGia.getText());
//                    prepare.setString(3, NhapDocGia_SDTDG.getText());
//                    prepare.setString(4, NhapDocGia_SoThe.getText());
//                    prepare.setString(5, NhapDocGia_EmailDG.getText());
////                        prepare.setString(9, NhapSach_ChonTheLoai.getValue());
//                    System.out.println("Đã Thêm Thành Công Sách Có Mã: " + NhapDocGia_MaDocGia.getText());
//                    prepare.executeUpdate();
//                    alert = new Alert(AlertType.INFORMATION);
//                    alert.setTitle("Thông Báo Cho Bạn");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Đã Thêm Thành Công Sách Có Mã: " + NhapDocGia_MaDocGia.getText());
//                    alert.showAndWait();
//                    HienThiDocGia();
//                }
//            } catch (SQLException e){
//                e.printStackTrace();
//            }
//
//
//        }
//
//        public void SuaDocGia(){
//            try {
//                String sql = "UPDATE DocGia SET TenDocGia = ?, SDT = ?, SoThe = ?, Email = ? WHERE MaDocGia = ?";
//                connect = ConnectSQL.connectDB();
//                prepare = connect.prepareStatement(sql);
//
//                prepare.setString(5, NhapDocGia_MaDocGia.getText());
//                prepare.setString(1, NhapDocGia_TenDocGia.getText());
//                prepare.setString(2, NhapDocGia_SDTDG.getText());
//                prepare.setString(3,NhapDocGia_SoThe.getText());
//                prepare.setString(4,NhapDocGia_EmailDG.getText());
//
//
//                prepare.executeUpdate();
//                System.out.println("Đã Sửa Thành Công");
//                alert = new Alert(AlertType.INFORMATION);
//                alert.setTitle("Thông Báo Cho Bạn");
//                alert.setHeaderText(null);
//                alert.setContentText("Đã Sửa Thành Công");
//                alert.showAndWait();
//                HienThiDocGia();
//
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//        public void XoaDocGia(){
//            try {
//                String sql = "DELETE FROM DocGia WHERE MaDocGia = ?";
//                connect = ConnectSQL.connectDB();
//                prepare = connect.prepareStatement(sql);
//                prepare.setString(1, NhapDocGia_MaDocGia.getText());
//
//                int rowsAffected = prepare.executeUpdate();
//                if (rowsAffected > 0) {
//                    // Xóa thành công
//                    System.out.println("Đã xóa Độc Giả Có Mã: " + NhapDocGia_MaDocGia.getText());
//                    alert = new Alert(AlertType.INFORMATION);
//                    alert.setTitle("Thông Báo Cho Bạn");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Đã xóa Độc Giả Có Mã: "  + NhapDocGia_MaDocGia.getText());
//                    alert.showAndWait();
//                } else {
//                    // Không tìm thấy sách để xóa
//                    System.out.println("Không tìm thấy Độc Giả có mã : " + NhapDocGia_MaDocGia.getText());
//                    alert = new Alert(AlertType.INFORMATION);
//                    alert.setTitle("Thông Báo Cho Bạn");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Không tìm thấy Độc Giả có mã: " + NhapDocGia_MaDocGia.getText());
//                    alert.showAndWait();
//                }
//                HienThiNhanVien();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
////----------------------------------------------------------------------------------------------------------
//
//
//    //-----------------------------------------------------------------------------------------------------
//
//
//    //------------------------------------------------------------------------------------------------------------
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        // String MaDocGia, String TenDocGia, String SDT, String SoThe, String Email
//        HienThiDocGia();
//
//
//
//    }
//
//}
