package MainPhuc.main.qltv;

public class SachDaTra {
    String tenSach;
    int tongSoSachDaTra;

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getTongSoSachDaTra() {
        return tongSoSachDaTra;
    }

    public void setTongSoSachDaTra(int tongSoSachDaTra) {
        this.tongSoSachDaTra = tongSoSachDaTra;
    }

    public SachDaTra(String tenSach, int tongSoSachDaTra) {
        this.tenSach = tenSach;
        this.tongSoSachDaTra = tongSoSachDaTra;
    }
}