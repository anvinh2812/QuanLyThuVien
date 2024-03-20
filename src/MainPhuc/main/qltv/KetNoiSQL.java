package MainPhuc.main.qltv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class KetNoiSQL {

    public static Connection connectDB() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;database=QuanLyThuVien1;user=sa;password=Phamquan2003;encrypt=true;trustServerCertificate=true";            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    public static void main(String[] args) {
        try {
            connectDB();
            System.out.println("cnt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}















//    private void iniPieChart(){
//        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
//                new PieChart.Data("Phuc",21),
//                new PieChart.Data("Vinh",28),
//                new PieChart.Data("Trung",17),
//                new PieChart.Data("Lebron",10),
//                new PieChart.Data("Quan",60)
//        );
//        pieChart.setData(pieChartData);
//    }
