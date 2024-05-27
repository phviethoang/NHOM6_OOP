package test.oopnhom6;

import data.Article;
import data.Cabinet;
import data.General;
import helper.LoadFileAndSetData;
import helper.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Animation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

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
    private TableView<?> tableView;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Button backButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private AnchorPane anchorPane2;
    @FXML
    private Button sortButton;
    Animation animation=new Animation();
    Cabinet c=new Cabinet();

    public static Stack<Scene> prevScene = new Stack();

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
    void setBackground(){

        anchorPane1.getStyleClass().add("anchor-pane1");
        // Liên kết tệp CSS với AnchorPane
        String cssFilePath1 = getClass().getResource("/style.css").toExternalForm();
        anchorPane1.getStylesheets().add(cssFilePath1);

        anchorPane2.getStyleClass().add("anchor-pane2");
        // Liên kết tệp CSS với AnchorPane
        String cssFilePath2 = getClass().getResource("/style.css").toExternalForm();
        anchorPane2.getStylesheets().add(cssFilePath2);

        scenePane.getStyleClass().add("anchor-pane3");
        // Liên kết tệp CSS với AnchorPane
        String cssFilePath3 = getClass().getResource("/style.css").toExternalForm();
        scenePane.getStylesheets().add(cssFilePath3);

        anchorPane.getStyleClass().add("anchor-pane4");
        // Liên kết tệp CSS với AnchorPane
        String cssFilePath4 = getClass().getResource("/style.css").toExternalForm();
        anchorPane.getStylesheets().add(cssFilePath4);
    }
    @FXML
    void goHome(ActionEvent event) throws IOException {
        prevScene.push(homeButton.getScene());
        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage)homeButton.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void showNews(ActionEvent event) {
        try {
            prevScene.push(homeButton.getScene());
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
            prevScene.push(homeButton.getScene());
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
        prevScene.push(homeButton.getScene());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("trendingScene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) trendButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showHistory(ActionEvent event) throws IOException {
        prevScene.push(homeButton.getScene());
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
    void back(){
        if(!prevScene.isEmpty()) {
            Stage stg = (Stage) backButton.getScene().getWindow();
            stg.setScene(prevScene.pop());
        }
    }
    @FXML
    void scrollToFirst(ActionEvent event) {
        tableView.scrollTo(0);
    }
    @FXML
    void scrollToLast(ActionEvent event) {
        tableView.scrollTo(500);
    }

}
