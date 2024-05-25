package test.oopnhom6;

import helper.LoadFileAndSetData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Animation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class ControllerButton implements Initializable {
    @FXML
    private Button homeButton;
    @FXML
    private Button newsButton;
    @FXML
    private Button authorButton;
    @FXML
    private Button trendButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button memberButton;
    @FXML
    private TableView tableNews;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Button backButton;
    Animation animation=new Animation();


    public void makeHighlight(){
        animation.buttonHighlight(homeButton);
        animation.buttonHighlight(newsButton);
        animation.buttonHighlight(authorButton);
        animation.buttonHighlight(trendButton);
        animation.buttonHighlight(historyButton);
        animation.buttonHighlight(memberButton);
        animation.buttonHighlight(exitButton);
    }

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
            ControllerGUI ctl = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(scene);
            ctl.showNews(event);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void showHomeAuthors(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homeScene.fxml"));
            // Nạp root trước khi load controller
            Parent root = loader.load();
            ControllerGUI ctl = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(scene);
            ctl.showAuthors(event);
            stage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void showTrendings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("trendingScene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) trendButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showHistory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("historyScene.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) historyButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void exitApp(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit the application!");
        alert.setContentText("Have you made sure to close it? ");
        if(alert.showAndWait().get() == ButtonType.OK){
            Stage stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }
    @FXML
    void back(ActionEvent event) {
        if (!LoadFileAndSetData.prevScene.isEmpty()) {
            Stage stg = (Stage) homeButton.getScene().getWindow();
            stg.setScene(LoadFileAndSetData.prevScene.peek());
            LoadFileAndSetData.prevScene.pop();
        }
    }


    @FXML
    void search(){}

    @FXML
    void searching(){}
    @FXML
    void scrollToFirst(ActionEvent event) {
        tableNews.scrollTo(0);
    }
    @FXML
    void scrollToLast(ActionEvent event) {
        tableNews.scrollTo(500);
    }
}
