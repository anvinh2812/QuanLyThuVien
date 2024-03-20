package MainTrung;

import java.util.Date;

public class TraSach {
    private String MaMuonSach;
    private String MaSach;
    private Date NgayMuon;
    private String MaDocGia;
    private String MaNhanVien;
    private Integer SoLuong;

    private Date NgayTra;
    public TraSach(String MaMuonSach, String MaSach, Date NgayMuon, String MaDocGia,  Integer SoLuong, Date NgayTra) {
        this.MaMuonSach = MaMuonSach;
        this.MaSach = MaSach;
        this.NgayMuon = NgayMuon;
        this.MaDocGia = MaDocGia;
        this.SoLuong = SoLuong;
        this.NgayTra = NgayTra;
    }

    public Date getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(Date ngayTra) {
        NgayTra = ngayTra;
    }

    public String getMaMuonSach() {
        return MaMuonSach;
    }

    public void setMaMuonSach(String maMuonSach) {
        MaMuonSach = maMuonSach;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String maSach) {
        MaSach = maSach;
    }

    public Date getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        NgayMuon = ngayMuon;
    }

    public String getMaDocGia() {
        return MaDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        MaDocGia = maDocGia;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        MaNhanVien = maNhanVien;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer soLuong) {
        SoLuong = soLuong;
    }


}
