package MainTrung;

public class ThongKeSach {
    private String MaSach;
    private String TenSach;
    private String TenTheLoai;
    private String TenTacGia;
    private String TenNXB;
    private String NamXB;
    private String SoLuotMuon;


    public ThongKeSach(String MaSach, String TenSach, String TenTheLoai, String TenTacGia, String TenNXB, String NamXB, String SoLuotMuon) {
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.TenTheLoai = TenTheLoai;
        this.TenTacGia = TenTacGia;
        this.TenNXB = TenNXB;
        this.NamXB = NamXB;
        this.SoLuotMuon = SoLuotMuon;

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

    public String getNamXB() {
        return NamXB;
    }

    public void setNamXB(String namXB) {
        NamXB = namXB;
    }

    public String getSoLuotMuon() {
        return SoLuotMuon;
    }

    public void setSoLuotMuon(String soLuotMuon) {
        SoLuotMuon = soLuotMuon;
    }
}
