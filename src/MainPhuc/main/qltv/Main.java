package MainPhuc.main.qltv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ThongKeTong.fxml"));
        primaryStage.setTitle("Báo Cáo");
        primaryStage.setScene(new Scene(root,1250,750));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }



}
