package QuanLyThuVien.QuanLyThuVien;


import java.sql.Date;
public class DocGia {
    private String MaDocGia;
    private String TenDocGia;
    private String SDT;
    private String SoThe;
    private String Email;
    private String GioiTinh;
    private Date NgaySinh;
    private String ImgDocGia;
    public String getImgDocGia() {
        return ImgDocGia;
    }

    public void setImgDocGia(String imgDocGia) {
        ImgDocGia = imgDocGia;
    }



    //Khởi Tạo

    public DocGia() {
    }
    public DocGia(String MaDocGia, String TenDocGia, String SDT, String SoThe, String Email, String GioiTinh,String ImgDocGia, Date NgaySinh){
        this.MaDocGia = MaDocGia;
        this.TenDocGia = TenDocGia;
        this.SDT = SDT;
        this.SoThe = SoThe;
        this.Email = Email;
        this.GioiTinh = GioiTinh;
        this.ImgDocGia = ImgDocGia;
        this.NgaySinh = NgaySinh;
    }
    public Date getNgaySinh(){
        return NgaySinh;
    }
    public void setNgaySinh(Date NgaySinh){
        this.NgaySinh = NgaySinh;
    }
    public String getMaDocGia() {
        return MaDocGia;
    }

    public void setMaDocGia(String MaDocGia) {
        this.MaDocGia = MaDocGia;
    }

    public String getTenDocGia() {
        return TenDocGia;
    }

    public void setTenDocGia(String tenDocGia) {
        TenDocGia = tenDocGia;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getSoThe() {
        return SoThe;
    }

    public void setSoThe(String soThe) {
        SoThe = soThe;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

}
