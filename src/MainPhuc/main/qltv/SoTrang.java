package MainPhuc.main.qltv;

public class SoTrang {
    String tenSach;
    int SoTrang;

    public SoTrang() {
    }

    public SoTrang(String tenSach, int soTrang) {
        this.tenSach = tenSach;
        SoTrang = soTrang;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoTrang() {
        return SoTrang;
    }

    public void setSoTrang(int soTrang) {
        SoTrang = soTrang;
    }
}
