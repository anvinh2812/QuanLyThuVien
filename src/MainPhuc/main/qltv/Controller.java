package MainPhuc.main.qltv;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Interface.Menu;
import MainPhuc.main.qltv.KetNoiSQL;
import MainPhuc.main.qltv.SachDaTra;
import MainPhuc.main.qltv.SachDangMuon;
import MainPhuc.main.qltv.SachMuonNhieuNhat;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class Controller implements Initializable {
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<MainPhuc.main.qltv.SachMuonNhieuNhat> data1;
    private ObservableList<MainPhuc.main.qltv.SachDaTra> data2;
    private ObservableList<MainPhuc.main.qltv.SachDangMuon> data3;
    /////////////////////////////COLUMN//////////////////////
    @FXML
    private TableColumn<MainPhuc.main.qltv.SachDangMuon, String> cotSach_dangmuon;

    @FXML
    private TableColumn<MainPhuc.main.qltv.SachDangMuon, Integer> cotTenSach_dangmuon;

    @FXML
    private TableColumn<MainPhuc.main.qltv.SachDaTra, Integer> cotSach_datra;

    @FXML
    private TableColumn<MainPhuc.main.qltv.SachDaTra, String> cotTenSach_datra;

    @FXML
    private TableColumn<MainPhuc.main.qltv.SachMuonNhieuNhat, String> cotTenSach;

    @FXML
    private TableColumn<MainPhuc.main.qltv.SachMuonNhieuNhat, Integer> cotTongSoLuotMuon;
    /////////////LABEL////////////
    @FXML
    private Label TongDG;

    @FXML
    private Label TongSoSachSauKhiMuon;

    @FXML
    private Label Tongnvien;

    @FXML
    private Label TongNXB;

    ///////////////////TABLE////////////////////////
    @FXML
    private TableView<MainPhuc.main.qltv.SachMuonNhieuNhat> tableSachMuonNhieuNhat;

    @FXML
    private TableView<MainPhuc.main.qltv.SachDaTra> tableSachDaTra;

    @FXML
    private TableView<MainPhuc.main.qltv.SachDangMuon> tableSachDangMuon;

    /////////////////chart/////////////
    @FXML
    private PieChart piechartSoSach;

    @FXML
    private BarChart<String, Integer> barchart;

    @FXML
    private LineChart linechart;

    @FXML
    private AreaChart<String, Integer> areachart;
    @FXML
    private BarChart<String, Integer> barchart_NXB;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = KetNoiSQL.connectDB();
        setCellTable();
        data1 = FXCollections.observableArrayList();
        data2 = FXCollections.observableArrayList();
        data3 = FXCollections.observableArrayList();
//        loadSoSachConLaiSauKhiDuocMuon();
//        loadSachTheoTheLoai();
        loadTongSoSachDuocMuonNhieuNhat();
        loadSachDaTra();
        loadSachDangMuon();
        TongSoNhanVien();
        TongSoSach();
        TongSoDocGia();
        TongSoNXB();
//        SoSachTheoTacGia();
//        SachTheoNXB();
//        SoTrang();
    }

//    public void loadSachTheoTheLoai() {
//        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
//        try {
//            pst = conn.prepareStatement("select * from SachTheoTheLoai");
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                String tenTheLoai = rs.getString("tenTheLoai");
//                int SoLuongSach = rs.getInt("SoLuongSach");
//                pieChartData.add(new PieChart.Data(rs.getNString("tenTheLoai"), rs.getInt("SoLuongSach")));
//                for (PieChart.Data Data : piechartSoSach.getData()){
//                    Data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Data");
//                        alert.setContentText(Data.getName()+": "+Data.getPieValue());
//                        alert.showAndWait();
//                    });
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        piechartSoSach.setData(pieChartData);
//        piechartSoSach.setLegendVisible(true);
//        piechartSoSach.setPrefSize(1250, 550);
//        piechartSoSach.setStyle(".chart-legend:-fx-background-color: rgba(255, 255, 253, 0.1);");
//    }

//    public void loadSoSachConLaiSauKhiDuocMuon() {
//        XYChart.Series<String, Integer> linechartdata = new XYChart.Series<>();
//
//        try {
//            pst = conn.prepareStatement("SELECT * FROM SoSachConLaiSauKhiDuocMuon");
//            rs = pst.executeQuery();
//            while (rs.next()) {
//
//                linechartdata.getData().add(new XYChart.Data(rs.getNString("TenSach"), rs.getInt("SoSachConLai")));
//            }
//            linechart.getData().add(linechartdata);
//        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        linechart.lookup(".chart-plot-background").setStyle("-fx-background-color:transparent;");
//        linechart.lookupAll(".chart-line-symbol").forEach((symbol) -> {
//            symbol.setStyle("-fx-background-color: #ffbc74;");
//        });
//        linechart.setId("my-line");
//
//    }

    public void setCellTable() {
        cotTenSach.setCellValueFactory(new PropertyValueFactory<>("tenSach"));
        cotTongSoLuotMuon.setCellValueFactory(new PropertyValueFactory<>("tongSoLuotMuon"));
        cotSach_datra.setCellValueFactory(new PropertyValueFactory<>("tongSoSachDaTra"));
        cotTenSach_datra.setCellValueFactory(new PropertyValueFactory<>("tenSach"));
        cotTenSach_dangmuon.setCellValueFactory(new PropertyValueFactory<>("tenSach"));
//        cotSach_dangmuon.setCellValueFactory(new PropertyValueFactory<>("SoLuongDangMuon"));
        cotSach_dangmuon.setCellValueFactory(new PropertyValueFactory<>("TongSoSachDangMuon"));
    }

    public void loadTongSoSachDuocMuonNhieuNhat() {
        try {
            pst = conn.prepareStatement("select * from SachMuonNhieuNhat");
            rs = pst.executeQuery();
            while (rs.next()) {
                data1.add(new SachMuonNhieuNhat(rs.getString("tenSach"), rs.getInt("tongSoLuongMuon")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableSachMuonNhieuNhat.setItems(data1);
    }

    public void loadSachDaTra() {
        try {
            pst = conn.prepareStatement("select * from TongSoSachDaTra");
            rs = pst.executeQuery();
            while (rs.next()) {
                data2.add(new SachDaTra(rs.getString("tenSach"), rs.getInt("tongSoSachDaTra")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableSachDaTra.setItems(data2);
    }

    public void loadSachDangMuon() {
        try {
//            pst = conn.prepareStatement("select * from TongSoSachDangMuon");
            pst = conn.prepareStatement("SELECT  s.TenSach, ms.SoLuong - COALESCE(SUM(ts.SoLuong), 0) AS SoLuongDangMuon\n" +
                    "FROM MuonSach ms\n" +
                    "INNER JOIN Sach s ON ms.MaSach = s.MaSach\n" +
                    "LEFT JOIN TraSach ts ON ms.MaSach = ts.MaSach\n" +
                    "GROUP BY  s.TenSach, ms.SoLuong\n" +
                    "HAVING ms.SoLuong - COALESCE(SUM(ts.SoLuong), 0) > 0");
            rs = pst.executeQuery();
            while (rs.next()) {
                data3.add(new SachDangMuon(rs.getString("tenSach"), rs.getInt("SoLuongDangMuon")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableSachDangMuon.setItems(data3);
    }

    public void TongSoNhanVien() {
        try {
            pst = conn.prepareStatement("SELECT COUNT(*) as TongSoNhanVien FROM NhanVien");
            rs = pst.executeQuery();
            if (rs.next()) {
                int data = rs.getInt("TongSoNhanVien");
                Tongnvien.setText(String.valueOf(data));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void TongSoSach() {
        try {
            pst = conn.prepareStatement("select * from TongSoSachSauKhiMuon");
            rs = pst.executeQuery();
            if (rs.next()) {
                int data = rs.getInt("TongSoSachSauKhiMuon");
                TongSoSachSauKhiMuon.setText(String.valueOf(data));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TongSoDocGia() {
        try {
            pst = conn.prepareStatement("SELECT COUNT(*) AS TongSoDocGia FROM DocGia");
            rs = pst.executeQuery();
            if (rs.next()) {
                int data = rs.getInt("TongSoDocGia");
                TongDG.setText(String.valueOf(data));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TongSoNXB() {
        try {
            pst = conn.prepareStatement("SELECT COUNT(*) AS TongSoNXB FROM NXB");
            rs = pst.executeQuery();
            if (rs.next()) {
                int data = rs.getInt("TongSoNXB");
                TongNXB.setText(String.valueOf(data));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public void SoSachTheoTacGia(){
//        try {
//            XYChart.Series chartData = new XYChart.Series();
//            pst = conn.prepareStatement("select * from SoLuongSachTheoTacGia");
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                chartData.getData().add(new XYChart.Data(rs.getString(1),rs.getInt(2)));
//            }
//            barchart.getData().add(chartData);
//            barchart.lookup(".chart-plot-background").setStyle("-fx-background-color:transparent;");
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public void SachTheoNXB() {
//        try {
//            XYChart.Series NXB = new XYChart.Series();
//            pst = conn.prepareStatement("select * from SoSachTheoNXB");
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                NXB.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
//            }
//
//            barchart_NXB.getData().add(NXB);
//            barchart_NXB.lookup(".chart-plot-background").setStyle("-fx-background-color:transparent;");
//        } catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public void SoTrang(){
//        try {
//            XYChart.Series<String, Integer> SoTrangdata = new XYChart.Series<>();
//            pst = conn.prepareStatement("SELECT TenSach, SoTrang FROM Sach");
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                SoTrangdata.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
//            }
//            Tooltip tooltip = new Tooltip();
//            tooltip.getText();
//            areachart.getData().add(SoTrangdata);
//            areachart.lookup(".chart-plot-background").setStyle("-fx-background-color:#AE8E75;");
//
//        }catch (SQLException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    private void LogOut(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Interface.Menu.openFXML("/Login/DangNhap.fxml");
    }

    @FXML
    private void Books(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Interface.Menu.openFXML("/QuanLyThuVien/QuanLyThuVien/Sach.fxml");
    }

    @FXML
    private void DocGia(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Interface.Menu.openFXML("/QuanLyThuVien/QuanLyThuVien/DocGia.fxml");
    }

    @FXML
    private void ThongKe(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Interface.Menu.openFXML("/MainPhuc/main/qltv/ThongKeTong.fxml");
    }

    @FXML
    private void Admin(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Interface.Menu.openFXML("/QuanLyThuVien/QuanLyThuVien/NhanVien.fxml");
    }


}
