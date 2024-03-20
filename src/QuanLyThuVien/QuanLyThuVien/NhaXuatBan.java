package QuanLyThuVien.QuanLyThuVien;

public class NhaXuatBan {
    private String MaNXB;
    private String TenNXB;
    private String Email;
    private String SDT;
    public NhaXuatBan() {
    }
    public NhaXuatBan(String MaNXB, String TenNXB, String Email, String SDT) {
        this.MaNXB = MaNXB;
        this.TenNXB = TenNXB;
        this.Email = Email;
        this.SDT = SDT;
    }
    public String getMaNXB() {
        return MaNXB;
    }

    public void setMaNXB(String maNXB) {
        MaNXB = maNXB;
    }

    public String getTenNXB() {
        return TenNXB;
    }

    public void setTenNXB(String tenNXB) {
        TenNXB = tenNXB;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
            this.SDT = SDT;
    }


}
