package QuanLyThuVien.QuanLyThuVien;

import java.sql.Date;

public class NhanVien {
    private String MaNhanVien;
    private String TenNhanVien;
    private Date NgaySinh;
    private Integer SDT;
    private String TenDangNhap;
    private String GioiTinh;
    private String ImgNhanVien;

    public NhanVien(String maNhanVien, String tenNhanVien, Date ngaySinh, Integer SDT, String tenDangNhap, String gioiTinh) {
        MaNhanVien = maNhanVien;
        TenNhanVien = tenNhanVien;
        NgaySinh = ngaySinh;
        this.SDT = SDT;
        TenDangNhap = tenDangNhap;
        GioiTinh = gioiTinh;
    }

    public NhanVien() {
        this.MaNhanVien = MaNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.NgaySinh = NgaySinh;
        this.SDT = SDT;
        this.TenDangNhap = TenDangNhap;
        this.GioiTinh = GioiTinh;
        this.ImgNhanVien = ImgNhanVien;
    }
    public String getMaNhanVien() {
        return MaNhanVien;
    }
    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public Integer getSDT() {
        return SDT;
    }

    public void setSDT(Integer SDT) {
        this.SDT = SDT;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }
    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getImgNhanVien() {
        return ImgNhanVien;
    }

    public void setImgNhanVien(String imgNhanVien) {
        this.ImgNhanVien = ImgNhanVien;
    }

}
