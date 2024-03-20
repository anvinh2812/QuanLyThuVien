package QuanLyThuVien.QuanLyThuVien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;


public class Main extends Application {
        public static void main(String[] args) {
            launch();
        }
        @Override
        public void start(Stage stage) throws IOException {
            try {
                LocalDate NgayThanhNamHienTai = LocalDate.now();
                int NamHienTai = NgayThanhNamHienTai.getYear();
                System.out.println("Ngày tháng năm hiện tại:" + NgayThanhNamHienTai);
                System.out.println("Năm hiện tại: " + NamHienTai);
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Sach.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1250, 750);
                stage.setTitle("Hello!");
                stage.setScene(scene);
                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }


        }
}
