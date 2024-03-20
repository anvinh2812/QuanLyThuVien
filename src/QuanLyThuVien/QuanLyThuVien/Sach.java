package QuanLyThuVien.QuanLyThuVien;

import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.sql.Date;


public class Sach {
    private  String MaSach;
    private  String TenSach;
    private  String MaTheLoai;
    private String TenTheLoai;
    private  String MaTacGia;
    private  String TenTacGia;
    private  String MaNXB;
    private  int NamXB;
    private  int SoLuong;
    private Date NgayNhap;
    private static String imgsach;
    public Sach() {
    }
    public Sach(String MaSach, String TenSach, String MaTheLoai,String TenTheLoai, String MaTacGia,String TenTacGia, String MaNXB,  int NamXB,int SoLuong, Date NgayNhap,String imgsach) {
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.MaTheLoai = MaTheLoai;
        this.TenTheLoai = TenTheLoai;
        this.MaTacGia = MaTacGia;
        this.TenTacGia = TenTacGia;
        this.MaNXB = MaNXB;
        this.SoLuong = SoLuong;
        this.NamXB = NamXB;
        this.NgayNhap = NgayNhap;
        this.imgsach = imgsach;
    }


    //Ma So Sach
    public String getMaSach(){
        return MaSach;
    }
    public void setMaSach(String MaSach){
        this.MaSach = MaSach;
    }
    // Ten Sach
    public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }
    public  String getTenSach() {
        return TenSach;
    }
    // Ma the loai
    public void setMaTheLoai(String MaTheLoai){
        this.MaTheLoai = MaTheLoai;

    }
    public String getMaTheLoai(){
        return MaTheLoai;
    }
    // Ma Tac Gia
    public void setMaTacGia(String MaTacGia) {
        this.MaTacGia = MaTacGia;
    }
    public String getMaTacGia() {
        return MaTacGia;
    }
    //
    public void setTenTacGia(String TenTacGia){
        this.TenTacGia = TenTacGia;
    }
    public String getTenTacGia(){
        return TenTacGia;
    }
    // Ma NXB
    public void setMaNXB(String MaNXB) {
        this.MaNXB = MaNXB;
    }
    public String getMaNXB() {
        return MaNXB;
    }
    // Ten NXB

    // Nam XB
    public void setNamXB(int NamXB) {
        this.NamXB = NamXB;
    }
    public int getNamXB() {
        return NamXB;
    }
    // So Luong
    public void setSoLuong(int Soluong){
        this.SoLuong = Soluong;
    }
    public int getSoLuong(){
        return SoLuong;
    }
    //Ngay Nhap
    public void setNgayNhap(Date NgayNhap){
        this.NgayNhap = NgayNhap;
    }
    public Date getNgayNhap(){
        return NgayNhap;
    }
    public String getTenTheLoai() {
        return TenTheLoai;
    }
    public void setTenTheLoai(String TenTheLoai) {
        this.TenTheLoai = TenTheLoai;
    }
    // ImgSRC
    public void setimgsach(String imgsach){
        this.imgsach = imgsach;
    }
    public String getimgsach(){
        return imgsach;
    }

    public Sach(String maTheLoai, String tenTheLoai) {
        MaTheLoai = maTheLoai;
        TenTheLoai = tenTheLoai;
    }
}
