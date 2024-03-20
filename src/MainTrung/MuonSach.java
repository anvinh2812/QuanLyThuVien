package MainTrung;

public class MuonSach {
    private String MaSach;
    private String TenSach;
    private String TenTheLoai;
    private String TenTacGia;
    private String TenNXB;
    private int NamXB;

    public MuonSach(String maSach, String tenSach, String tenTheLoai, String tenTacGia, String tenNXB, int namXB) {
        MaSach = maSach;
        TenSach = tenSach;
        TenTheLoai = tenTheLoai;
        TenTacGia = tenTacGia;
        TenNXB = tenNXB;
        NamXB = namXB;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String maSach) {
        MaSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getTenTheLoai() {
        return TenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        TenTheLoai = tenTheLoai;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        TenTacGia = tenTacGia;
    }

    public String getTenNXB() {
        return TenNXB;
    }

    public void setTenNXB(String tenNXB) {
        TenNXB = tenNXB;
    }

    public int getNamXB() {
        return NamXB;
    }

    public void setNamXB(int namXB) {
        NamXB = namXB;
    }
}
