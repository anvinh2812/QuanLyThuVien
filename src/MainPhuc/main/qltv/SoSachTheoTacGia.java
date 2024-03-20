package MainPhuc.main.qltv;

public class SoSachTheoTacGia {
    String tenTacGia;
    int SoLuongSach;

    public SoSachTheoTacGia() {
    }

    public SoSachTheoTacGia(String tenTacGia, int soLuongSach) {
        this.tenTacGia = tenTacGia;
        SoLuongSach = soLuongSach;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public int getSoLuongSach() {
        return SoLuongSach;
    }

    public void setSoLuongSach(int soLuongSach) {
        SoLuongSach = soLuongSach;
    }
}
