package Account;

import ConnectionDataBase.DBConnection;
import LogIn.DangNhap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChinhSuaTaiKhoan {
    @FXML
    private TextField EditAddress;

    @FXML
    private TextField EditDate;

    @FXML
    private TextField EditEmail;

    @FXML
    private TextField EditName;


//    @FXML
//    private void SaveSetting(ActionEvent event) {
//        String editName = EditName.getText();
//        String editEmail = EditEmail.getText();
//        String editDate = EditDate.getText();
//        String editAddress = EditAddress.getText();
//
//        String userSuccessfulLogin = DangNhap.getUserNameCorrect();
//
//        DBConnection connectionDB = new DBConnection();
//        String sql = "UPDATE DocGia SET TenDocGia = ?, Email = ?, SDT = ? , MaDocGia = ? WHERE TenDangNhap = ?";
//        try (PreparedStatement statement = connectionDB.prepareStatement(sql)) {
//            statement.setString(1, editName);
//            statement.setString(2, editEmail);
//            statement.setString(3, editDate);
//            statement.setString(4, editAddress);
//            statement.setString(5, userSuccessfulLogin);
//
//
//            statement.executeUpdate();
//
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.close();
//            DisplayProfile();
//
//        }catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Phương thức kiểm tra định dạng email hợp lệ
    private boolean isValidEmail(String email) {
        // Sử dụng biểu thức chính quy để kiểm tra định dạng email
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    // Phương thức kiểm tra số điện thoại hợp lệ
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Sử dụng biểu thức chính quy để kiểm tra định dạng số điện thoại
        String phoneRegex = "^\\d{10}$";
        return phoneNumber.matches(phoneRegex);
    }
    private boolean isValidFullName(String fullName) {
        // Kiểm tra xem họ tên có rỗng hay không
        if (fullName.isEmpty()) {
            return false;
        }

        // Kiểm tra xem họ tên có chứa ký tự đặc biệt hoặc số hay không
        String specialCharsRegex = ".*[^a-zA-Z\\sáàảãạÁÀẢÃẠăắằẳẵặĂẮẰẲẴẶâấầẩẫậÂẤẦẨẪẬéèẻẽẹÉÈẺẼẸêếềểễệÊẾỀỂỄỆíìỉĩịÍÌỈĨỊóòỏõọÓÒỎÕỌôốồổỗộÔỐỒỔỖỘơớờởỡợƠỚỜỞỠỢúùủũụÚÙỦŨỤưứừửữựƯỨỪỬỮỰýỳỷỹỵÝỲỶỸỴđĐ].*";
        if (fullName.matches(specialCharsRegex)) {
            return false;
        }

        // Kiểm tra xem họ tên có vượt quá một số ký tự nhất định hay không (ví dụ: 100 ký tự)
        int maxNameLength = 100;
        if (fullName.length() > maxNameLength) {
            return false;
        }

        return true;
    }
    public boolean checkUsernameExists(String username) {
        boolean exists = false;
        DBConnection connectionDB = new DBConnection();
        String sql = "SELECT COUNT(*) AS UserCount FROM DocGia WHERE TenDangNhap = ?";
        try (PreparedStatement statement = connectionDB.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int userCount = result.getInt("UserCount");
                exists = userCount > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }

    @FXML
    private void SaveSetting(ActionEvent event) {
        String editName = EditName.getText();
        String editEmail = EditEmail.getText();
        String editDate = EditDate.getText();


        String userSuccessfulLogin = DangNhap.getUserNameCorrect();

        boolean usernameExists = checkUsernameExists(userSuccessfulLogin);

        DBConnection connectionDB = new DBConnection();

        // Kiểm tra xem tên đăng nhập đã tồn tại hay chưa
        // Kiểm tra xem các ô nhập liệu đã được điền đầy đủ hay không
        if (editName.isEmpty() || editEmail.isEmpty() || editDate.isEmpty()) {
            // Hiển thị thông báo lỗi nếu có ô nhập liệu trống
            showAlert("Lỗi", "Vui lòng điền đầy đủ thông tin để cập nhật hồ sơ.");
            return;
        }

        // Kiểm tra tính hợp lệ của họ tên
        if (!isValidFullName(editName)) {
            // Hiển thị thông báo lỗi nếu họ tên không hợp lệ
            showAlert("Lỗi", "Vui lòng nhập một họ tên hợp lệ.");
            return;
        }

        // Kiểm tra định dạng email hợp lệ
        if (!isValidEmail(editEmail)) {
            // Hiển thị thông báo lỗi nếu email không hợp lệ
            showAlert("Lỗi", "Vui lòng nhập một địa chỉ email hợp lệ.");
            return;
        }

        // Kiểm tra số điện thoại có đúng định dạng không
        if (!isValidPhoneNumber(editDate)) {
            // Hiển thị thông báo lỗi nếu số điện thoại không hợp lệ
            showAlert("Lỗi", "Vui lòng nhập một số điện thoại hợp lệ (10 chữ số).");
            return;
        }else {
            if (!usernameExists) {
                // Tên đăng nhập chưa tồn tại, thực hiện thêm mới thông tin độc giả
                String maxMaDocGiaQuery = "SELECT MAX(MaDocGia) AS MaxMaDocGia FROM DocGia";
                String insertQuery = "INSERT INTO DocGia (TenDocGia, Email, SDT, MaDocGia, TenDangNhap) " +
                        "VALUES (?, ?, ?, ?, ?)";

                try (PreparedStatement maxMaDocGiaStatement = connectionDB.prepareStatement(maxMaDocGiaQuery);
                     PreparedStatement insertStatement = connectionDB.prepareStatement(insertQuery)) {

                    // Lấy mã độc giả lớn nhất
                    ResultSet maxMaDocGiaResult = maxMaDocGiaStatement.executeQuery();
                    String maxMaDocGia = "DG001"; // Mã độc giả mặc định nếu không tìm thấy kết quả
                    if (maxMaDocGiaResult.next()) {
                        String currentMaxMaDocGia = maxMaDocGiaResult.getString("MaxMaDocGia");
                        if (currentMaxMaDocGia != null) {
                            // Tăng mã độc giả lên 1 đơn vị
                            int numericPart = Integer.parseInt(currentMaxMaDocGia.substring(2));
                            numericPart++;
                            maxMaDocGia = "DG" + String.format("%03d", numericPart);
                        }
                    }

                    insertStatement.setString(1, editName);
                    insertStatement.setString(2, editEmail);
                    insertStatement.setString(3, editDate);
                    insertStatement.setString(4, maxMaDocGia);
                    insertStatement.setString(5, userSuccessfulLogin);

                    insertStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo cho bạn");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thông tin tài khoản thành công!");
                alert.showAndWait();

            } else {
                // Tên đăng nhập đã tồn tại, thực hiện cập nhật thông tin độc giả
                String updateQuery = "UPDATE DocGia SET TenDocGia = ?, Email = ?, SDT = ? WHERE TenDangNhap = ?";

                try (PreparedStatement updateStatement = connectionDB.prepareStatement(updateQuery)) {
                    updateStatement.setString(1, editName);
                    updateStatement.setString(2, editEmail);
                    updateStatement.setString(3, editDate);
                    updateStatement.setString(4, userSuccessfulLogin);

                    updateStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo cho bạn");
                alert.setHeaderText(null);
                alert.setContentText("Cập nhật thông tin tài khoản thành công!");
                alert.showAndWait();
            }
        }
    }



    @FXML
    private void CancelSetting(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
