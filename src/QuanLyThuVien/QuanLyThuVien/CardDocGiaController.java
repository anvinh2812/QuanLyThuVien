package QuanLyThuVien.QuanLyThuVien;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.time.format.DateTimeFormatter;

public class CardDocGiaController {

    @FXML
    private AnchorPane Card_BoxDocGia;

    @FXML
    private ImageView Card_IMGDocGia;

    @FXML
    private Label Card_NgaySinh;

    @FXML
    private Label Card_SoThe;

    @FXML
    private Label Card_TenDocGia;

    private String colors[] = {"#EF9A9A", "#E1BEE7", "#C8E6C9","#C0392B", "#D35400", "#F39C12", "#2980B9","#BDC3C7"};
     public void setdatadocgia(DocGia docGia){
         Card_TenDocGia.setText(docGia.getTenDocGia());
         Card_SoThe.setText(docGia.getSoThe());
         Card_NgaySinh.setText(docGia.getNgaySinh().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
         Card_BoxDocGia.setStyle("-fx-background-color: " + colors[(int)(Math.random() * colors.length)] + ";" +
                 "-fx-background-radius: 15px;" +
                 "-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0), 10, 0, 0, 10 );");
     }

}


