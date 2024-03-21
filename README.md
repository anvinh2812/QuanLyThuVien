# Hướng dẫn cài đặt và chạy dự án

## Bước 1: Tạo cơ sở dữ liệu trong SQL Server qua file script.sql

- Đầu tiên, bạn cần mở SQL Server Management Studio hoặc công cụ quản lý cơ sở dữ liệu SQL Server khác.
- Tiếp theo, mở tệp script.sql và thực thi các lệnh trong tệp này để tạo cơ sở dữ liệu và bảng cho dự án của bạn.

## Bước 2: Cài đặt các thư viện cho IntelliJ IDEA

- Đảm bảo bạn đã cài đặt IntelliJ IDEA trên máy tính của mình.
- Mở IntelliJ IDEA và mở dự án của bạn.
- Cài đặt thư viện JavaFX: 
  - Nhấp vào File > Project Structure.
  - Chọn Modules ở cột bên trái.
  - Chọn dự án của bạn.
  - Ở tab Dependencies, nhấn nút "+" và chọn "JARs or directories".
  - Đi đến thư mục lib của JavaFX SDK và chọn thư mục lib.
- Cài đặt JDK và SDK:
  - Đảm bảo bạn đã cài đặt JDK (Java Development Kit) trên máy tính của mình.
  - Trong IntelliJ IDEA, nhấp vào File > Project Structure > Project.
  - Dưới phần Project SDK, chọn JDK đã cài đặt hoặc thêm một JDK mới nếu cần.
  - Đảm bảo SDK Platform đã cài đặt và SDK được chọn.
- Cài đặt thư viện JDBC (Java Database Connectivity) nếu cần thiết để kết nối với cơ sở dữ liệu SQL Server.

## Bước 3: Clone và chạy dự án trên IntelliJ IDEA

- Clone dự án từ repository git của bạn hoặc từ một repository khác:
