package QuanLyThuVien.QuanLyThuVien;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import Interface.Menu;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import java.sql.Date;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.List;
import javafx.scene.control.ChoiceBox;
import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.layout.HBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.StringConverter;

import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

public class DocGiaController implements Initializable {
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
    private TableView<DocGia> BangDocGia;

    @FXML
    private TableColumn<DocGia, Boolean> cGioiTinhDocGia;
    @FXML
    private TableColumn<DocGia, String> cEmailDG;

    @FXML
    private TableColumn<DocGia, String> cMaDocGia;

    @FXML
    private AnchorPane Card_BoxDocGia;

    @FXML
    private ImageView Card_IMGDocGia;
    @FXML
    private VBox Card_LayoutDocGia;
    @FXML
    private Label Card_NgaySinh;

    @FXML
    private Label Card_SoThe;

    @FXML
    private Label Card_TenDocGia;
    @FXML
    private TableColumn<DocGia, String> cNgayHetHan;

    @FXML
    private TableColumn<DocGia, String> cNgaySinhDG;

    @FXML
    private TableColumn<DocGia, String> cSDTDG;

    @FXML
    private TableColumn<DocGia, String> cSoThe;

    @FXML
    private TableColumn<DocGia, String> cTenDocGia;
    @FXML
    private Label ttct_EmailDG;

    @FXML
    private Label ttct_MaDocGia;

    @FXML
    private Label ttct_NgayHetHan;
    @FXML
    private Label ttct_NgaySinhDG;

    @FXML
    private Label ttct_SDTDG;

    @FXML
    private Label ttct_SoSachDaMuon;

    @FXML
    private Label ttct_TenDocGia;
    @FXML
    private Label ttct_SoThe;


    @FXML
    private TextField TimKiem_DocGia;
    @FXML
    private ImageView NhapDocGia_AnhDocGia;

    @FXML
    private TextField NhapDocGia_EmailDG;

    @FXML
    private TextField NhapDocGia_MaDocGia;

    @FXML
    private DatePicker NhapDocGia_NgaySinhDG;

    @FXML
    private TextField NhapDocGia_SDTDG;

    @FXML
    private TextField NhapDocGia_SoThe;

    @FXML
    private TextField NhapDocGia_TenDocGia;
//    @FXML
//    private ComboBox<String> NhapDocGia_GioiTinhDocGia;
    @FXML
    private ChoiceBox<String> NhapDocGia_GioiTinhDocGia;

    @FXML
    private Button BTN_HuyDocGia;

    @FXML
    private Button BTN_SuaDocGia;

    @FXML
    private Button BTN_ThemDocGia;

    @FXML
    private Button BTN_XoaDocGia;

    public ObservableList<DocGia> DocGiaDaThemGanDay(){
        ObservableList<DocGia> ls = FXCollections.observableArrayList();
        try {
            connect = ConnectSQL.connectDB();
            String sql = "SELECT * FROM DocGia";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                DocGia docgia = new DocGia();
                docgia.setTenDocGia(result.getString("TenDocGia"));
                docgia.setSoThe(result.getString("SoThe"));
                docgia.setNgaySinh(Date.valueOf(result.getString("NgaySinh")));
                ls.add(docgia);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ls;
    }
    private List<DocGia> DocGiaDaThemGanDay;
    public void HienThiDocGiaDaThemGanDay(){
        DocGiaDaThemGanDay = new ArrayList<>(DocGiaDaThemGanDay());

        try {
            for (int i = 0; i< DocGiaDaThemGanDay.size(); i++){
               FXMLLoader fxmlLoader = new FXMLLoader();
               fxmlLoader.setLocation(getClass().getResource("CardDocGia.fxml"));
               Card_BoxDocGia = fxmlLoader.load();
               CardDocGiaController carddocgiacontroller = fxmlLoader.getController();
               carddocgiacontroller.setdatadocgia(DocGiaDaThemGanDay.get(i));
               Card_LayoutDocGia.getChildren().add(Card_BoxDocGia);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public ObservableList<DocGia> DuLieuDocGia(){
        ObservableList<DocGia> CacDocGia = FXCollections.observableArrayList();
        String sql = "SELECT DocGia.*\n" +
                "FROM DocGia\n" +
                "INNER JOIN TheThuVien\n" +
                "ON DocGia.SoThe = TheThuVien.SoThe";
        connect = ConnectSQL.connectDB();
        try {
            DocGia docgias;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                docgias = new DocGia(result.getString("MaDocGia"),
                        result.getString("TenDocGia"),
                        result.getString("SDT"),
                        result.getString("SoThe"),
                        result.getString("Email"),
                        result.getString("GioiTinh"),
                        result.getString("ImgDocGia")
                        , result.getDate("NgaySinh")

                );
                CacDocGia.add(docgias);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return CacDocGia;
    }

    public void ChonGioiTinh() {
        ObservableList<String> list = FXCollections.observableArrayList("Nam", "Nu");
        NhapDocGia_GioiTinhDocGia.setItems(list);
    }
    private ObservableList<DocGia> DanhSachDocGia;
    public void HienThiDocGia(){
        HienThiDocGiaDaThemGanDay();
        DanhSachDocGia = DuLieuDocGia();
        cMaDocGia.setCellValueFactory(new PropertyValueFactory<DocGia, String>("MaDocGia"));
        cTenDocGia.setCellValueFactory(new PropertyValueFactory<DocGia, String>("TenDocGia"));
        cSDTDG.setCellValueFactory(new PropertyValueFactory<DocGia, String>("SDT"));
        cSoThe.setCellValueFactory(new PropertyValueFactory<DocGia, String>("SoThe"));
        cEmailDG.setCellValueFactory(new PropertyValueFactory<DocGia, String>("Email"));
        cNgaySinhDG.setCellValueFactory(new PropertyValueFactory<DocGia, String>("NgaySinh"));
        cGioiTinhDocGia.setCellValueFactory(new PropertyValueFactory<DocGia, Boolean>("GioiTinh"));
        BangDocGia.setItems(DanhSachDocGia);
        TimKiem_DocGia();
    }

    public void ChonDocGia_TTCT(){
        DocGia dulieudocgia = BangDocGia.getSelectionModel().getSelectedItem();
        int num = BangDocGia.getSelectionModel().getFocusedIndex();
        if ((num - 1) < -1) {
            return ;
        }
        NhapDocGia_MaDocGia.setText(dulieudocgia.getMaDocGia());
        NhapDocGia_TenDocGia.setText(dulieudocgia.getTenDocGia());
        NhapDocGia_SoThe.setText(dulieudocgia.getSoThe());
        NhapDocGia_SDTDG.setText(dulieudocgia.getSDT());
        NhapDocGia_EmailDG.setText(dulieudocgia.getEmail());
        NhapDocGia_GioiTinhDocGia.setValue(dulieudocgia.getGioiTinh());
        NhapDocGia_NgaySinhDG.setValue(dulieudocgia.getNgaySinh().toLocalDate());
//            NhapDocGia_NgaySinhDG.setValue(LocalDate.parse(dulieudocgia.getNgaySinh().toLocaleString()));
//            ttct_MaDocGia.setText(dulieudocgia.getMaDocGia());
//            ttct_TenDocGia.setText(dulieudocgia.getTenDocGia());
//            ttct_SDTDG.setText(dulieudocgia.getSDT());
//            ttct_SoThe.setText(dulieudocgia.getSoThe());
//            ttct_EmailDG.setText(dulieudocgia.getEmail());
//            ttct_NgaySinhDG.setText(DateFormat.format(dulieudocgia.getNgaySinh()));
        //ttct_NgayHetHan

    }
    public void TimKiem_DocGia(){
        FilteredList<DocGia> filteredData = new FilteredList<>(DanhSachDocGia, b -> true);
        TimKiem_DocGia.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (employee.getMaDocGia().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (employee.getTenDocGia().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (String.valueOf(employee.getEmail()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false; // Does not match.
            });
        });
        SortedList<DocGia> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(BangDocGia.comparatorProperty());
        BangDocGia.setItems(sortedData);
    }
    public void HuyDocGia(){

        if(NhapDocGia_MaDocGia.getText().isEmpty() && NhapDocGia_TenDocGia.getText().isEmpty() &&
                NhapDocGia_SoThe.getText().isEmpty() && NhapDocGia_SDTDG.getText().isEmpty()
                && NhapDocGia_EmailDG.getText().isEmpty()) {
            System.out.println("Không Có Gì Để Hủy Hết");
            alert = new Alert(AlertType.WARNING);
            alert.setTitle("Thông Báo Cho Bạn");
            alert.setHeaderText(null);
            alert.setContentText("Không Có Gì Để Hủy Hết");
            alert.showAndWait();
        } else {

            NhapDocGia_MaDocGia.setText("");
            NhapDocGia_TenDocGia.setText("");
            NhapDocGia_SDTDG.setText("");
            NhapDocGia_EmailDG.setText("");
            NhapDocGia_NgaySinhDG.setValue(null);
            NhapDocGia_SoThe.setText("");

            System.out.println("Đã Hủy Thành Công");
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông Báo Cho Bạn");
            alert.setHeaderText(null);
            alert.setContentText("Đã Hủy Thành Công");
            alert.showAndWait();
        }
    }

    public void ThemDocGia() {
        try {
            connect = ConnectSQL.connectDB();
            String sql_Nhap = "INSERT INTO DocGia (MaDocGia, TenDocGia, SDT, SoThe, Email, GioiTinh, NgaySinh) VALUES ( ?, ?, ?, ?,?,?, ?)\n"; /*imgSach,*/
            String sql_check = "SELECT * FROM DocGia Where MaDocGia = ?";
            prepare = connect.prepareStatement(sql_check);
            prepare.setString(1, NhapDocGia_MaDocGia.getText());
            result = prepare.executeQuery();

            String sql_checkSoThe = "SELECT * FROM TheThuVien Where SoThe = ?";
            PreparedStatement prepare1 = connect.prepareStatement(sql_checkSoThe);
            prepare1.setString(1, NhapDocGia_SoThe.getText());
            ResultSet result1 = prepare1.executeQuery();


            if (result.next()) {
                // Nếu không rỗng, tức là đã có dữ liệu trùng trong cơ sở dữ liệu
                // Hiển thị thông báo trùng bằng Alert hoặc Label
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo Cho Bạn");
//                alert.setHeaderText ("Dữ liệu trùng");
                alert.setContentText("Độc Giả đã tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            } else {
//                    if (result.next()) {
//                        // Nếu không rỗng, tức là đã có dữ liệu trùng trong cơ sở dữ liệu
//                        // Hiển thị thông báo trùng bằng Alert hoặc Label
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Thông Báo Cho Bạn");
////                alert.setHeaderText ("Dữ liệu trùng");
//                        alert.setContentText("Độc Giả đã tồn tại trong cơ sở dữ liệu");
//                        alert.showAndWait();
//                    }
//                    else {
                        //---------------------------------------------------
                        String NoiDungConThieu = "";
                        if (NhapDocGia_MaDocGia.getText().isEmpty()) {
                            NoiDungConThieu += "Nhập Mã Độc Giả.\n";
                        }
                        if (NhapDocGia_TenDocGia.getText().isEmpty()) {
                            NoiDungConThieu += "Nhập Tên Độc Giả.\n";
                        }
                        if (NhapDocGia_SDTDG.getText().isEmpty()) {
                            NoiDungConThieu += "Nhập Số Điện Thoại Độc Giả.\n";
                        }
                        if (NhapDocGia_SoThe.getText().isEmpty()) {
                            NoiDungConThieu += "Nhập Số Thẻ.\n";
                        }
                        if (NhapDocGia_EmailDG.getText().isEmpty()) {
                            NoiDungConThieu += "Nhập Email.\n";
                        }
                        if (!NoiDungConThieu.isEmpty()) {
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Thông Báo Cho Bạn");
                            alert.setHeaderText(null);
                            alert.setContentText(NoiDungConThieu);
                            alert.showAndWait();
                        }
                        else if (!NhapDocGia_SDTDG.getText().matches("\\d+") || Integer.parseInt(NhapDocGia_SDTDG.getText()) < 0) {
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Thông Báo Cho Bạn");
                                alert.setContentText("Số Điện Thoại Không Hợp Lệ");
                                alert.initOwner(NhapDocGia_SDTDG.getScene().getWindow()); // Set the parent window
                                alert.showAndWait();
                            });
                            return;
                        }
                        else if ( NhapDocGia_NgaySinhDG.getValue().isAfter(NgayThanhNamHienTai)) {
                            Platform.runLater(() -> {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Thông Báo Cho Bạn");
                                alert.setContentText("Ngày Sinh Không Hợp Lệ");
                                alert.initOwner(NhapDocGia_NgaySinhDG.getScene().getWindow()); // Set the parent window
                                alert.showAndWait();
                            });
                            return;
                        }
                        else {
                        prepare = connect.prepareStatement(sql_Nhap);
                        prepare.setString(1, NhapDocGia_MaDocGia.getText());
                        prepare.setString(2, NhapDocGia_TenDocGia.getText());
                        prepare.setString(3, NhapDocGia_SDTDG.getText());
                            if (!result1.next()) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Thông Báo Cho Bạn");
                                alert.setContentText("Độc Giả chưa có thẻ thư viện. Hoặc Số thẻ không hợp lệ");
                                alert.showAndWait();
                                NhapDocGia_SoThe.setText("");
                            }

                            prepare.setString(4, NhapDocGia_SoThe.getText());
                        prepare.setString(5, NhapDocGia_EmailDG.getText());
                        prepare.setString(6, NhapDocGia_GioiTinhDocGia.getValue());
                        prepare.setString(7, NhapDocGia_NgaySinhDG.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
//                        prepare.setString(9, NhapSach_ChonTheLoai.getValue());
                        System.out.println("Đã Thêm Thành Công Độc Giả Có Mã: " + NhapDocGia_MaDocGia.getText());
                        prepare.executeUpdate();
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Thông Báo Cho Bạn");
                        alert.setHeaderText(null);
                        alert.setContentText("Đã Thêm Thành Công Độc Giả Có Mã: " + NhapDocGia_MaDocGia.getText());
                        alert.showAndWait();
                        HienThiDocGia();
                        }
                    }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public void SuaDocGia(){
        try {
            String sql = "UPDATE DocGia SET TenDocGia = ?, SDT = ?, SoThe = ?, Email = ?, GioiTinh = ?, NgaySinh = ? WHERE MaDocGia = ?";
            connect = ConnectSQL.connectDB();
            prepare = connect.prepareStatement(sql);


            String sql_check = "SELECT * FROM DocGia WHERE MaDocGia = ?";
            PreparedStatement prepareCheck = connect.prepareStatement(sql_check);
            prepareCheck.setString(1, NhapDocGia_MaDocGia.getText());
            ResultSet resultCheck = prepareCheck.executeQuery();
            if (!resultCheck.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo Cho Bạn");
                alert.setContentText("Độc Giả Không tồn tại trong cơ sở dữ liệu");
                alert.showAndWait();
            } else {
                String NoiDungConThieu = "";
                if (NhapDocGia_MaDocGia.getText().isEmpty()) {
                    NoiDungConThieu += "Thiếu Mã Độc Giả Để Sửa.\n";
                }
                if (NhapDocGia_TenDocGia.getText().isEmpty()) {
                    NoiDungConThieu += "Thiếu Tên Độc Giả Để Sửa.\n";
                }
                if (NhapDocGia_SDTDG.getText() == null) {
                    NoiDungConThieu += "Thiếu SDT Độc Giả Để Sửa.\n";
                }
                if (NhapDocGia_SoThe.getText().isEmpty()) {
                    NoiDungConThieu += "Thiếu Số Thẻ Để Sửa.\n";
                }
                if (NhapDocGia_NgaySinhDG.getValue() == null) {
                    NoiDungConThieu += "Thiếu Ngày Sinh Để Sửa.\n";
                }
                if (!NoiDungConThieu.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText(NoiDungConThieu);
                    alert.showAndWait();
                } else if (!NhapDocGia_SDTDG.getText().matches("\\d+") || Integer.parseInt(NhapDocGia_SDTDG.getText()) < 0) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Thông Báo Cho Bạn");
                        alert.setContentText("Số Điện Thoại Không Hợp Lệ");
                        alert.initOwner(NhapDocGia_SDTDG.getScene().getWindow()); // Set the parent window
                        alert.showAndWait();
                    });
                    return;
                } else if ( NhapDocGia_NgaySinhDG.getValue().isAfter(NgayThanhNamHienTai)) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Thông Báo Cho Bạn");
                        alert.setContentText("Ngày Sinh Không Hợp Lệ");
                        alert.initOwner(NhapDocGia_NgaySinhDG.getScene().getWindow()); // Set the parent window
                        alert.showAndWait();
                    });
                    return;
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(7, NhapDocGia_MaDocGia.getText());
                    prepare.setString(1, NhapDocGia_TenDocGia.getText());
                    prepare.setString(2, NhapDocGia_SDTDG.getText());
                    prepare.setString(3, NhapDocGia_SoThe.getText());
                    prepare.setString(4, NhapDocGia_EmailDG.getText());
                    prepare.setString(5, NhapDocGia_GioiTinhDocGia.getValue());
                    prepare.setString(6, NhapDocGia_NgaySinhDG.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
                }
                //---------------------------
                int rowsAffected = prepare.executeUpdate();
                if (rowsAffected > 0) {
                    // Xóa thành công
                    System.out.println("Đã Sửa Độc Giả Có Mã: " + NhapDocGia_MaDocGia.getText());
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Đã Sửa Độc Giả Có Mã: " + NhapDocGia_MaDocGia.getText());
                    alert.showAndWait();
                } else {
                    // Không tìm thấy Độc Giả để xóa
                    System.out.println("Không tìm thấy Độc Giả có mã : " + NhapDocGia_MaDocGia.getText());
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Thông Báo Cho Bạn");
                    alert.setHeaderText(null);
                    alert.setContentText("Không tìm thấy Độc Giả có mã: " + NhapDocGia_MaDocGia.getText());
                    alert.showAndWait();
                }
                //------------------------------
                HienThiDocGia();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void XoaDocGia(){
        try {
            String sql = "DELETE FROM DocGia WHERE MaDocGia = ?";
            connect = ConnectSQL.connectDB();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, NhapDocGia_MaDocGia.getText());

            int rowsAffected = prepare.executeUpdate();
            if (rowsAffected > 0) {
                // Xóa thành công
                System.out.println("Đã xóa Độc Giả Có Mã: " + NhapDocGia_MaDocGia.getText());
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Thông Báo Cho Bạn");
                alert.setHeaderText(null);
                alert.setContentText("Đã xóa Độc Giả Có Mã: "  + NhapDocGia_MaDocGia.getText());
                alert.showAndWait();
            } else {
                // Không tìm thấy Độc Giả để xóa
                System.out.println("Không tìm thấy Độc Giả có mã : " + NhapDocGia_MaDocGia.getText());
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Thông Báo Cho Bạn");
                alert.setHeaderText(null);
                alert.setContentText("Không tìm thấy Độc Giả có mã: " + NhapDocGia_MaDocGia.getText());
                alert.showAndWait();
            }
            HienThiDocGia();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HienThiDocGia();

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
    private void Admin(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/QuanLyThuVien/QuanLyThuVien/NhanVien.fxml");
    }

    @FXML
    private void LogOut(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/Login/DangNhap.fxml");
    }

}
