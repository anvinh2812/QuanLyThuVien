package MainPhuc.main.qltv;

public class SachSoLuongConLai {
    String tenSach;
    int SoLuongSach;
    public SachSoLuongConLai(String tenSach, int SoLuongSach) {
        this.tenSach = tenSach;
        this.SoLuongSach = SoLuongSach;
    }

    public void setSoSachConLai(int SoLuongSach) {
        this.SoLuongSach = SoLuongSach;
    }

    public void setTenSach1(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenSach1() {
        return tenSach;
    }

    public int getSoLuongSach() {
        return SoLuongSach;

    }
}

