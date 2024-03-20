package QuanLyThuVien.QuanLyThuVien;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
public class CardController {
    @FXML
    private AnchorPane Card_Box;

    @FXML
    private ImageView Card_DanhGia;

    @FXML
    private ImageView Card_IMG;

    @FXML
    private Label Card_TenSach;

    @FXML
    private Label Card_TenTacGia;
    private String colors[] = {"#EF9A9A", "#E1BEE7", "#C8E6C9","#C0392B", "#D35400", "#F39C12", "#2980B9","#BDC3C7"};
    public void setDataSach(Sach sach) {
//        Image image = new Image(getClass().getResourceAsStream(sach.getImgSach()));
//        Card_IMG.setImage(image);
        Card_TenSach.setText(sach.getTenSach());
        Card_TenTacGia.setText(sach.getTenTacGia());
        Card_Box.setStyle("-fx-background-color: " + colors[(int)(Math.random() * colors.length)] + ";" +
                "-fx-background-radius: 15px;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0), 10, 0, 0, 10 );");
    }
}
