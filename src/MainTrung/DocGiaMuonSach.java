package MainTrung;

import java.util.BitSet;

public class DocGiaMuonSach {
    private String MaDocGia;
    private String TenDocGia;
    private String SDT;
    private String SoThe;
    private String Email;
    private String GioiTinh;
    private String SoLuotMuon;

    public DocGiaMuonSach(String MaDocGia, String TenDocGia, String SDT, String SoThe, String Email, String GioiTinh, String SoLuotMuon) {
        this.MaDocGia = MaDocGia;
        this.TenDocGia = TenDocGia;
        this.SDT = SDT;
        this.SoThe = SoThe;
        this.Email = Email;
        this.GioiTinh = GioiTinh;
        this.SoLuotMuon = SoLuotMuon;
    }

    public String getMaDocGia() {
        return MaDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        MaDocGia = maDocGia;
    }

    public String getTenDocGia() {
        return TenDocGia;
    }

    public void setTenDocGia(String tenDocGia) {
        TenDocGia = tenDocGia;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getSoThe() {
        return SoThe;
    }

    public void setSoThe(String soThe) {
        SoThe = soThe;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getSoLuotMuon() {
        return SoLuotMuon;
    }

    public void setSoLuotMuon(String soLuotMuon) {
        SoLuotMuon = soLuotMuon;
    }
}
