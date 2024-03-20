package MainPhuc.main.qltv;

public class BarChartData {
    String tenSach;
    int SoSachConLai;

    public BarChartData(String tenSach, int soSachConLai) {
        this.tenSach = tenSach;
        this.SoSachConLai = soSachConLai;
    }

    public BarChartData() {
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoSachConLai() {
        return SoSachConLai;
    }

    public void setSoSachConLai(int soSachConLai) {
        SoSachConLai = soSachConLai;
    }
}
