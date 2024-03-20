package MainPhuc.main.qltv;

public class SachDangMuon {
    String tenSach;
    int TongSoSachDangMuon;

    public SachDangMuon(String tenSach, int tongSoSachDangMuon) {
        this.tenSach = tenSach;
        this.TongSoSachDangMuon = tongSoSachDangMuon;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getTongSoSachDangMuon() {
        return TongSoSachDangMuon;
    }

    public void setTongSoSachDangMuon(int tongSoSachDangMuon) {
        TongSoSachDangMuon = tongSoSachDangMuon;
    }
}
