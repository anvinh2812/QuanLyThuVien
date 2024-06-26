USE [master]
GO
/****** Object:  Database [QuanLyThuVien1]    Script Date: 26/10/2023 7:45:53 CH ******/
CREATE DATABASE [QuanLyThuVien1]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuanLyThuVien1', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.PHAMQUAN\MSSQL\DATA\QuanLyThuVien1.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuanLyThuVien1_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.PHAMQUAN\MSSQL\DATA\QuanLyThuVien1_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QuanLyThuVien1] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyThuVien1].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyThuVien1] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyThuVien1] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyThuVien1] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuanLyThuVien1] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyThuVien1] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET RECOVERY FULL 
GO
ALTER DATABASE [QuanLyThuVien1] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyThuVien1] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyThuVien1] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyThuVien1] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyThuVien1] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLyThuVien1] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLyThuVien1] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'QuanLyThuVien1', N'ON'
GO
ALTER DATABASE [QuanLyThuVien1] SET QUERY_STORE = OFF
GO
USE [QuanLyThuVien1]
GO
/****** Object:  Table [dbo].[Sach]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sach](
	[MaSach] [varchar](10) NOT NULL,
	[TenSach] [nvarchar](50) NOT NULL,
	[MaTheLoai] [varchar](10) NOT NULL,
	[MaTacGia] [varchar](10) NOT NULL,
	[MaNXB] [varchar](10) NOT NULL,
	[SoLuong] [int] NOT NULL,
	[NamXB] [int] NULL,
	[imgSach] [image] NULL,
	[NgayNhap] [date] NULL,
 CONSTRAINT [PK_Sach] PRIMARY KEY CLUSTERED 
(
	[MaSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TheLoai]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheLoai](
	[MaTheLoai] [varchar](10) NOT NULL,
	[TenTheLoai] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_TheLoai] PRIMARY KEY CLUSTERED 
(
	[MaTheLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[SachTheoTheLoai]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[SachTheoTheLoai] AS
SELECT TL.TenTheLoai, COUNT(S.MaSach) AS SoLuongSach
FROM Sach S INNER JOIN TheLoai TL ON S.MaTheLoai = TL.MaTheLoai
GROUP BY TL.TenTheLoai
GO
/****** Object:  Table [dbo].[MuonSach]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MuonSach](
	[MaMuonSach] [varchar](10) NOT NULL,
	[MaSach] [varchar](10) NOT NULL,
	[NgayMuon] [datetime] NOT NULL,
	[MaDocGia] [varchar](10) NOT NULL,
	[SoLuong] [int] NOT NULL,
	[NgayTra] [date] NULL
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[SachMuonNhieuNhat]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[SachMuonNhieuNhat] AS
SELECT Sach.TenSach, SUM(MuonSach.SoLuong) AS TongSoLuongMuon 
FROM MuonSach JOIN Sach ON MuonSach.MaSach = Sach.MaSach 
GROUP BY Sach.TenSach 
;
GO
/****** Object:  Table [dbo].[TraSach]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TraSach](
	[MaTraSach] [varchar](10) NOT NULL,
	[MaDocGia] [varchar](10) NOT NULL,
	[MaNhanVien] [varchar](10) NOT NULL,
	[NgayTra] [datetime] NOT NULL,
	[MaSach] [varchar](10) NOT NULL,
	[SoLuong] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[TongSoSachDaTra]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[TongSoSachDaTra] AS
SELECT Sach.TenSach, SUM(TraSach.SoLuong) AS TongSoSachDaTra
FROM TraSach
JOIN Sach ON Sach.MaSach = TraSach.MaSach
GROUP BY Sach.TenSach;
GO
/****** Object:  View [dbo].[SachDangMuon]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[SachDangMuon] AS
SELECT ms.MaSach, s.TenSach, ms.SoLuong - COALESCE(SUM(ts.SoLuong), 0) AS SoLuongDangMuon
FROM MuonSach ms
INNER JOIN Sach s ON ms.MaSach = s.MaSach
LEFT JOIN TraSach ts ON ms.MaSach = ts.MaSach
GROUP BY ms.MaSach, s.TenSach, ms.SoLuong
HAVING ms.SoLuong - COALESCE(SUM(ts.SoLuong), 0) > 0
GO
/****** Object:  View [dbo].[TongSoSachDangMuon]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[TongSoSachDangMuon] AS
SELECT  s.TenSach, ms.SoLuong - COALESCE(SUM(ts.SoLuong), 0) AS SoLuongDangMuon
FROM MuonSach ms
INNER JOIN Sach s ON ms.MaSach = s.MaSach
LEFT JOIN TraSach ts ON ms.MaSach = ts.MaSach
GROUP BY  s.TenSach, ms.SoLuong
HAVING ms.SoLuong - COALESCE(SUM(ts.SoLuong), 0) > 0
GO
/****** Object:  View [dbo].[TongSoSachSauKhiMuon]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[TongSoSachSauKhiMuon] AS
SELECT SUM(s.SoLuong - COALESCE(m.SoLuongMuon, 0)) AS TongSoSachSauKhiMuon
FROM Sach s
LEFT JOIN (
    SELECT MaSach, SUM(SoLuong) AS SoLuongMuon
    FROM MuonSach
    GROUP BY MaSach
) m ON s.MaSach = m.MaSach

GO
/****** Object:  Table [dbo].[TacGia]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TacGia](
	[MaTacGia] [varchar](10) NOT NULL,
	[TenTacGia] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_TacGia] PRIMARY KEY CLUSTERED 
(
	[MaTacGia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[SoLuongSachTheoTacGia]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[SoLuongSachTheoTacGia] as
SELECT TacGia.TenTacGia, COUNT(Sach.MaSach) AS SoLuongSach
FROM Sach
JOIN TacGia ON Sach.MaTacGia = TacGia.MaTacGia
GROUP BY TacGia.TenTacGia
GO
/****** Object:  Table [dbo].[NXB]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NXB](
	[MaNXB] [varchar](10) NOT NULL,
	[TenNXB] [nvarchar](50) NOT NULL,
	[Email] [nchar](50) NOT NULL,
	[SDT] [nchar](10) NOT NULL,
 CONSTRAINT [PK_NXB] PRIMARY KEY CLUSTERED 
(
	[MaNXB] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[SoSachTheoNXB]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[SoSachTheoNXB] as
SELECT NXB.TenNXB, COUNT(Sach.MaSach) AS SoLuongSach
FROM Sach
JOIN NXB ON Sach.MaNXB = NXB.MaNXB
GROUP BY NXB.TenNXB
GO
/****** Object:  View [dbo].[TongSachSauKhiMuon]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[TongSachSauKhiMuon] AS
SELECT SUM(SoLuong) AS TongSoLuongSachSauKhiMuon
FROM Sach
WHERE MaSach IN (SELECT MaSach FROM MuonSach)
GO
/****** Object:  Table [dbo].[DocGia]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DocGia](
	[MaDocGia] [varchar](50) NOT NULL,
	[TenDocGia] [nvarchar](50) NOT NULL,
	[SDT] [varchar](50) NOT NULL,
	[SoThe] [varchar](50) NULL,
	[Email] [nvarchar](50) NULL,
	[GioiTinh] [nvarchar](50) NULL,
	[NgaySinh] [date] NULL,
	[TenDangNhap] [varchar](50) NULL,
	[ImgDocGia] [nvarchar](500) NULL
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[SoLuongDocGiaDaTraSach]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[SoLuongDocGiaDaTraSach] AS
SELECT MuonSach.MaDocGia, DocGia.TenDocGia, COUNT(*) AS SoSachDaTra, Sach.TenSach
FROM MuonSach
INNER JOIN Sach ON MuonSach.MaSach = Sach.MaSach
INNER JOIN DocGia ON MuonSach.MaDocGia = DocGia.MaDocGia
WHERE MuonSach.NgayMuon IS NOT NULL
GROUP BY MuonSach.MaDocGia, DocGia.TenDocGia, Sach.TenSach;
GO
/****** Object:  View [dbo].[SoSachConLaiSauKhiDuocMuon]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[SoSachConLaiSauKhiDuocMuon] as
SELECT Sach.TenSach, SUM(Sach.SoLuong - ISNULL(MS.SoLuongDaMuon, 0)) AS SoSachConLai
FROM Sach LEFT JOIN 
    (SELECT MaSach, SUM(SoLuong) AS SoLuongDaMuon 
     FROM MuonSach 
     GROUP BY MaSach) AS MS 
     ON Sach.MaSach = MS.MaSach
GROUP BY Sach.TenSach
GO
/****** Object:  View [dbo].[ViewDocGiaMuonSachNhieu]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[ViewDocGiaMuonSachNhieu] AS

SELECT D.MaDocGia, D.TenDocGia, D.SDT, D.SoThe, D.Email, D.GioiTinh, COUNT(*) AS SoLuotMuon
FROM DocGia D
JOIN MuonSach M ON D.MaDocGia = M.MaDocGia
GROUP BY D.MaDocGia, D.TenDocGia, D.SDT, D.SoThe, D.Email, D.GioiTinh
HAVING COUNT(*) > 3
GO
/****** Object:  View [dbo].[ViewSach]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[ViewSach] AS
SELECT S.MaSach, S.TenSach, tl.TenTheLoai, tg.TenTacGia, nxb.TenNXB, S.NamXB
FROM Sach S
JOIN TheLoai tl ON S.MaTheLoai = tl.MaTheLoai
JOIN TacGia tg ON S.MaTacGia = tg.MaTacGia
JOIN NXB nxb ON S.MaNXB = nxb.MaNXB;
GO
/****** Object:  View [dbo].[ViewSachMuonMin]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[ViewSachMuonMin] AS

SELECT TOP 1 WITH TIES S.MaSach, S.TenSach, tl.TenTheLoai, tg.TenTacGia, nxb.TenNXB, S.NamXB, COUNT(*) AS SoLuotMuon
FROM Sach S
JOIN MuonSach M ON S.MaSach = M.MaSach
JOIN TheLoai tl ON S.MaTheLoai = tl.MaTheLoai
JOIN TacGia tg ON S.MaTacGia = tg.MaTacGia
JOIN NXB nxb ON S.MaNXB = nxb.MaNXB
GROUP BY S.MaSach, S.TenSach, tl.TenTheLoai, tg.TenTacGia, nxb.TenNXB, S.NamXB
ORDER BY SoLuotMuon ASC
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNhanVien] [varchar](10) NOT NULL,
	[TenNhanVien] [nvarchar](50) NOT NULL,
	[NgaySinh] [date] NOT NULL,
	[SDT] [int] NOT NULL,
	[TenDangNhap] [nvarchar](50) NOT NULL,
	[GioiTinh] [nvarchar](10) NULL,
	[imgNhanVien] [image] NULL,
 CONSTRAINT [PK__NhanVien__77B2CA47348C0EAF] PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[TenDangNhap] [varchar](50) NULL,
	[MatKhau] [varchar](50) NULL,
	[Quyen] [nvarchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TheThuVien]    Script Date: 26/10/2023 7:45:53 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheThuVien](
	[SoThe] [varchar](10) NOT NULL,
	[NgayBatDau] [date] NOT NULL,
	[NgayKetThuc] [date] NOT NULL,
	[MSSV] [int] NULL,
	[TenDangNhap] [varchar](50) NULL,
 CONSTRAINT [PK_TheThuVien] PRIMARY KEY CLUSTERED 
(
	[SoThe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[MuonSach]  WITH CHECK ADD  CONSTRAINT [FK_MuonSach_Sach] FOREIGN KEY([MaSach])
REFERENCES [dbo].[Sach] ([MaSach])
GO
ALTER TABLE [dbo].[MuonSach] CHECK CONSTRAINT [FK_MuonSach_Sach]
GO
ALTER TABLE [dbo].[Sach]  WITH NOCHECK ADD  CONSTRAINT [FK__Sach__MaNXB__6477ECF3] FOREIGN KEY([MaNXB])
REFERENCES [dbo].[NXB] ([MaNXB])
GO
ALTER TABLE [dbo].[Sach] CHECK CONSTRAINT [FK__Sach__MaNXB__6477ECF3]
GO
ALTER TABLE [dbo].[Sach]  WITH CHECK ADD  CONSTRAINT [FK__Sach__MaTheLoai__07C12930] FOREIGN KEY([MaTheLoai])
REFERENCES [dbo].[TheLoai] ([MaTheLoai])
GO
ALTER TABLE [dbo].[Sach] CHECK CONSTRAINT [FK__Sach__MaTheLoai__07C12930]
GO
ALTER TABLE [dbo].[Sach]  WITH NOCHECK ADD  CONSTRAINT [fk_sach] FOREIGN KEY([MaTacGia])
REFERENCES [dbo].[TacGia] ([MaTacGia])
GO
ALTER TABLE [dbo].[Sach] CHECK CONSTRAINT [fk_sach]
GO
ALTER TABLE [dbo].[TraSach]  WITH CHECK ADD  CONSTRAINT [FK_TraSach_NhanVien] FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[NhanVien] ([MaNhanVien])
GO
ALTER TABLE [dbo].[TraSach] CHECK CONSTRAINT [FK_TraSach_NhanVien]
GO
ALTER TABLE [dbo].[TraSach]  WITH CHECK ADD  CONSTRAINT [FK_TraSach_Sach] FOREIGN KEY([MaSach])
REFERENCES [dbo].[Sach] ([MaSach])
GO
ALTER TABLE [dbo].[TraSach] CHECK CONSTRAINT [FK_TraSach_Sach]
GO
USE [master]
GO
ALTER DATABASE [QuanLyThuVien1] SET  READ_WRITE 
GO
