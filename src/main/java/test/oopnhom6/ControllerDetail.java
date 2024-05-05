package test.oopnhom6;
import java.io.IOException;

import data.Article;
import helper.LoadFileAndSetData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import test.oopnhom6.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ControllerDetail extends GUIController {
    @FXML
    Button homeButton;
    @FXML
    private TextArea textArea;
    @FXML
    Button backButton;
    @FXML
    Button newsButton;

//    @FXML
//    void exitApp(ActionEvent event) {
//
//    }
//
//    @FXML
//    void showAuthors(ActionEvent event) {
//
//    }
//
//    @FXML
//    void showNews(ActionEvent event) {
//
//    }
//
//    @FXML
//    void showTrendings(ActionEvent event) {
//
//    }
//    public void back(Stage stage){
//        backButton.setOnAction(e->{
//             stage.close();
//        });
//    }
    @FXML
    void goHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage)homeButton.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @FXML
    void showNews(ActionEvent event) {
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("homeScene.fxml"));
           // Nạp root trước khi load controller
           Parent root = loader.load();
           GUIController ctl = loader.getController();

           Scene scene = new Scene(root);
           Stage stage = (Stage) newsButton.getScene().getWindow();
           stage.setScene(scene);
           ctl.showNews(event);
           stage.show();
       }catch (Exception e){
           System.out.println(e.getMessage());
       }

    }
    public void setT(String a) {
        textArea.setWrapText(true);
        textArea.setText(a);
        textArea.setEditable(false);
    }
}
