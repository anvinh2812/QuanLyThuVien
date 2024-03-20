package MainTrung;

import Interface.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class Controller2 implements Initializable  {

    @FXML
    private ComboBox<Integer> comboBoxThang;
    @FXML
    private ComboBox<Integer> comboBoxNam;
    @FXML
    private ComboBox<Integer> comboBoxThang2;
    @FXML
    private TableColumn<ThongKeSach, String> cLuotMuon_max;

    @FXML
    private TableColumn<ThongKeSach, String> cMaNXBMuon_max;

    @FXML
    private TableColumn<ThongKeSach, String> cMaSachMuon_max;

    @FXML
    private TableColumn<ThongKeSach, String> cMaTacGiaMuon_max;

    @FXML
    private TableColumn<ThongKeSach, String> cNamXB_max;

    @FXML
    private TableColumn<ThongKeSach, String> cTenSachMuon_max;

    @FXML
    private TableColumn<ThongKeSach, String> cTheLoaiMuon_max;

    @FXML
    private TableView<ThongKeSach> tableMuon_max;

    @FXML
    private Label soPhieuLabel;

    @FXML
    private TableColumn<ThongKeSach, String> cLuotMuon_min;
    @FXML
    private TableColumn<ThongKeSach, String> cMaNXBMuon_min;

    @FXML
    private TableColumn<ThongKeSach, String> cMaSachMuon_min;

    @FXML
    private TableColumn<ThongKeSach, String> cMaTacGiaMuon_min;

    @FXML
    private TableColumn<ThongKeSach, String> cNamXB_min;

    @FXML
    private TableColumn<ThongKeSach, String> cTenSachMuon_min;

    @FXML
    private TableColumn<ThongKeSach, String> cTheLoaiMuon_min;

    @FXML
    private TableView<ThongKeSach> tableMuon_min;
    @FXML
    private TableColumn<DocGiaMuonSach, String> cEmail_muonNhieu;

    @FXML
    private TableColumn<DocGiaMuonSach, String> cGioiTinh_muonNhieu;

    @FXML
    private TableColumn<DocGiaMuonSach, String> cLuotMuon_muonNhieu;

    @FXML
    private TableColumn<DocGiaMuonSach, String> cMaDocGia_muonNhieu;

    @FXML
    private TableColumn<DocGiaMuonSach, String> cSDT_muonNhieu;

    @FXML
    private TableColumn<DocGiaMuonSach, String> cSoThe_muonNhieu;

    @FXML
    private TableColumn<DocGiaMuonSach, String> cTenDocGia_muonNhieu;
    @FXML
    private TableView<DocGiaMuonSach> tableDocGiaMuonNhieu;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;



    //                                           TAP  1


    public ObservableList<ThongKeSach> LayThongTinMuonMax(int thang) {
        ObservableList<ThongKeSach> CacQuyenSachMuonMax = FXCollections.observableArrayList();
        connect = ConnectSQLServer.connectDB();
        try {
            ThongKeSach ThongKeMax;
            prepare = connect.prepareStatement("\n" +
                    "SELECT TOP 1 WITH TIES S.MaSach, S.TenSach, tl.TenTheLoai, tg.TenTacGia, nxb.TenNXB, S.NamXB, COUNT(*) AS SoLuotMuon\n" +
                    "FROM Sach S\n" +
                    "JOIN MuonSach M ON S.MaSach = M.MaSach\n" +
                    "JOIN TheLoai tl ON S.MaTheLoai = tl.MaTheLoai\n" +
                    "JOIN TacGia tg ON S.MaTacGia = tg.MaTacGia\n" +
                    "JOIN NXB nxb ON S.MaNXB = nxb.MaNXB\n" +
                    "WHERE MONTH(M.NgayMuon) = ?\n" +
                    "GROUP BY S.MaSach, S.TenSach, tl.TenTheLoai, tg.TenTacGia, nxb.TenNXB, S.NamXB\n" +
                    "ORDER BY SoLuotMuon DESC" );
            prepare.setInt(1, thang);
            result = prepare.executeQuery();
            while (result.next()) {
                ThongKeMax = new ThongKeSach(result.getString("MaSach"),
                        result.getString("TenSach"),
                        result.getString("TenTheLoai"),
                        result.getString("TenTacGia"),
                        result.getString("TenNXB"),
                        result.getString("NamXB"),
                        result.getString("SoLuotMuon")
                );
                CacQuyenSachMuonMax.add(ThongKeMax);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CacQuyenSachMuonMax;
    }

    private ObservableList<ThongKeSach> DanhSachMuonMax;

    public void HienThiMuonMax() {
        int selectedThang = comboBoxThang.getValue(); // Lấy tháng từ ComboBox
        DanhSachMuonMax = LayThongTinMuonMax(selectedThang);
        cMaSachMuon_max.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
        cTenSachMuon_max.setCellValueFactory(new PropertyValueFactory<>("TenSach"));
        cTheLoaiMuon_max.setCellValueFactory(new PropertyValueFactory<>("TenTheLoai"));
        cMaTacGiaMuon_max.setCellValueFactory(new PropertyValueFactory<>("TenTacGia"));
        cMaNXBMuon_max.setCellValueFactory(new PropertyValueFactory<>("TenNXB"));
        cNamXB_max.setCellValueFactory(new PropertyValueFactory<>("NamXB"));
        cLuotMuon_max.setCellValueFactory(new PropertyValueFactory<>("SoLuotMuon"));
        tableMuon_max.setItems(DanhSachMuonMax);

    }


    public ObservableList<ThongKeSach> LayThongTinMuonMin(int thang) {
        ObservableList<ThongKeSach> CacQuyenSachMuonMin = FXCollections.observableArrayList();
        connect = ConnectSQLServer.connectDB();
        try {
            ThongKeSach ThongKeMin;
            prepare = connect.prepareStatement("\n" +
                    "SELECT TOP 1 WITH TIES S.MaSach, S.TenSach, tl.TenTheLoai, tg.TenTacGia, nxb.TenNXB, S.NamXB, COUNT(*) AS SoLuotMuon\n" +
                    "FROM Sach S\n" +
                    "JOIN MuonSach M ON S.MaSach = M.MaSach\n" +
                    "JOIN TheLoai tl ON S.MaTheLoai = tl.MaTheLoai\n" +
                    "JOIN TacGia tg ON S.MaTacGia = tg.MaTacGia\n" +
                    "JOIN NXB nxb ON S.MaNXB = nxb.MaNXB\n" +
                    "WHERE MONTH(M.NgayMuon) = ?\n" +
                    "GROUP BY S.MaSach, S.TenSach, tl.TenTheLoai, tg.TenTacGia, nxb.TenNXB, S.NamXB\n" +
                    "ORDER BY SoLuotMuon ASC" );
            prepare.setInt(1, thang);
            result = prepare.executeQuery();
            while (result.next()) {
                ThongKeMin = new ThongKeSach(result.getString("MaSach"),
                        result.getString("TenSach"),
                        result.getString("TenTheLoai"),
                        result.getString("TenTacGia"),
                        result.getString("TenNXB"),
                        result.getString("NamXB"),
                        result.getString("SoLuotMuon")
                );
                CacQuyenSachMuonMin.add(ThongKeMin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CacQuyenSachMuonMin;
    }

    private ObservableList<ThongKeSach> DanhSachMuonMin;

    public void HienThiMuonMin() {
        int selectedThang = comboBoxThang2.getValue(); // Lấy tháng từ ComboBox
        DanhSachMuonMin = LayThongTinMuonMin(selectedThang);
        cMaSachMuon_min.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
        cTenSachMuon_min.setCellValueFactory(new PropertyValueFactory<>("TenSach"));
        cTheLoaiMuon_min.setCellValueFactory(new PropertyValueFactory<>("TenTheLoai"));
        cMaTacGiaMuon_min.setCellValueFactory(new PropertyValueFactory<>("TenTacGia"));
        cMaNXBMuon_min.setCellValueFactory(new PropertyValueFactory<>("TenNXB"));
        cNamXB_min.setCellValueFactory(new PropertyValueFactory<>("NamXB"));
        cLuotMuon_min.setCellValueFactory(new PropertyValueFactory<>("SoLuotMuon"));
        tableMuon_min.setItems(DanhSachMuonMin);

    }

    public void ChonThang() {
        try {
            connect = ConnectSQLServer.connectDB();
            String sql = "SELECT DISTINCT MONTH(NgayMuon) AS Thang FROM MuonSach";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList<Integer> list = FXCollections.observableArrayList();
            while (result.next()) {
                list.add(result.getInt("Thang"));
            }
            comboBoxThang.setItems(list);
            comboBoxThang2.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }










    //                                                 TAP 2
    public ObservableList<DocGiaMuonSach> LayThongTinDocGia(){
        ObservableList<DocGiaMuonSach> CacDocGia = FXCollections.observableArrayList();
        connect = ConnectSQLServer.connectDB();
        boolean found = false; // Biến kiểm tra

        try{
            DocGiaMuonSach DocGia;
            prepare = connect.prepareStatement("SELECT * FROM ViewDocGiaMuonSachNhieu");
            result = prepare.executeQuery();
            while (result.next()){
                found = true; // Đánh dấu đã tìm thấy kết quả
                DocGia = new DocGiaMuonSach(result.getString("MaDocGia"),
                        result.getString("TenDocGia"),
                        result.getString("SDT"),
                        result.getString("SoThe"),
                        result.getString("Email"),
                        result.getString("GioiTinh"),
                        result.getString("SoLuotMuon")
                );
                CacDocGia.add(DocGia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Kiểm tra biến kiểm tra và in ra thông báo
        if (!found) {
            System.out.println("Không tìm thấy độc giả mượn sách nhiều nhất.");
        }

        return CacDocGia;
    }


    private ObservableList<DocGiaMuonSach> CacDocGiaHayMuon;

    public void HienThiDocGiaMuon(){
        CacDocGiaHayMuon = LayThongTinDocGia();
        cMaDocGia_muonNhieu.setCellValueFactory(new PropertyValueFactory<>("MaDocGia"));
        cTenDocGia_muonNhieu.setCellValueFactory(new PropertyValueFactory<>("TenDocGia"));
        cSDT_muonNhieu.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        cSoThe_muonNhieu.setCellValueFactory(new PropertyValueFactory<>("SoThe"));
        cEmail_muonNhieu.setCellValueFactory(new PropertyValueFactory<>("Email"));
        cGioiTinh_muonNhieu.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        cLuotMuon_muonNhieu.setCellValueFactory(new PropertyValueFactory<>("SoLuotMuon"));
        tableDocGiaMuonNhieu.setItems(CacDocGiaHayMuon);
    }

    public void hienThiTongSoPhieuMuon() {
        try {
            connect = ConnectSQLServer.connectDB();

            // Thực hiện truy vấn để tính tổng số phiếu mượn
            String sql = "SELECT COUNT(*) AS TongSoPhieuMuon\n" +
                    "FROM MuonSach";
            prepare = connect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();

            // Kiểm tra xem có dữ liệu hay không
            if (rs.next()) {
                // Lấy giá trị tổng từ ResultSet
                int tongPhieuMuon = rs.getInt("TongSoPhieuMuon");

                // Thiết lập giá trị cho Label
                soPhieuLabel.setText( " "+tongPhieuMuon);
            }

            rs.close();
            prepare.close();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChonThang();
//        ChonNam();
    }

    @FXML
    private void LogOut(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/Login/DangNhap.fxml");
    }

    @FXML
    private void HomeUser(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/Interface/HomeUser.fxml");
    }

    @FXML
    private void Account(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/Interface/TaiKhoan.fxml");
    }

    @FXML
    private void Books(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Menu.openFXML("/MainTrung/MuonSach.fxml");
    }
}
