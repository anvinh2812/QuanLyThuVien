package QuanLyThuVien.QuanLyThuVien;

public class TacGia {
    private String MaTacGia;
    private String TenTacGia;

    public TacGia(String maTacGia, String tenTacGia) {
        MaTacGia = maTacGia;
        TenTacGia = tenTacGia;
    }

    public String getMaTacGia() {
        return MaTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        MaTacGia = maTacGia;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        TenTacGia = tenTacGia;
    }
}
