package MainPhuc.main.qltv;

public class SoSachTheoNXB {
    String tenNXB;
    int SoLuongSach;

    public SoSachTheoNXB() {
    }

    public SoSachTheoNXB(String tenNXB, int soLuongSach) {
        this.tenNXB = tenNXB;
        SoLuongSach = soLuongSach;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public int getSoLuongSach() {
        return SoLuongSach;
    }

    public void setSoLuongSach(int soLuongSach) {
        SoLuongSach = soLuongSach;
    }
}
