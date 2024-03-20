package MainPhuc.main.qltv;

public class SachMuonNhieuNhat {
     String tenSach;
     int tongSoLuotMuon;

    public SachMuonNhieuNhat(String tenSach, int tongSoLuotMuon) {
        this.tenSach = tenSach;
        this.tongSoLuotMuon = tongSoLuotMuon;
    }

    public int getTongSoLuotMuon() {
        return tongSoLuotMuon;
    }

    public void setTongSoLuotMuon(int tongSoLuotMuon) {
        this.tongSoLuotMuon = tongSoLuotMuon;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
}
