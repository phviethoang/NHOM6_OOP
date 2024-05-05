package test.oopnhom6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import data.*;
import helper.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerTrending extends GUIController implements Initializable {

    @FXML
    private Button author;

    @FXML
    private Button category;

    @FXML
    private Button downButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button firstButton;

    @FXML
    private Button hashTag;

    @FXML
    private Button homeButton;

    @FXML
    private ImageView homeImage;

    @FXML
    private Button lastButton;

    @FXML
    private ListView<String> listNews = new ListView<String>();

    @FXML
    private AnchorPane scenePane;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchText;

    @FXML
    private Button trendButton;

    @FXML
    private Button upButton;
    @FXML
    private Label label;

    Cabinet c = new Cabinet();

    String currentIndex = new String();
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        currentIndex = "A";
        this.c.setBox(LoadFileAndSetData
                .data_array("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\blockchain (1).json"));
    }


    @FXML
    void showAuthor(ActionEvent event) {
        try {
            ArrayList<String> s = LoadFileAndSetData.popularAuth(c.getBox());
            ObservableList<String> lst = FXCollections.observableArrayList();
            for (int i = 0; i < 10; i++) {
                lst.add(s.get(i));
            }
            listNews.setItems(lst);
            label.setText("TOP 10 PUPULAR AUTHORS");
            currentIndex = "A";
        }catch (Exception e){

        }
    }

    @FXML
    void showCategory(ActionEvent event) {
        try {
            ArrayList<String> s = LoadFileAndSetData.popularCategory(c.getBox());

            ObservableList<String> lst = FXCollections.observableArrayList();

            for (int i = 0; i < 10; i++) {
                lst.add(s.get(i));
            }
            listNews.setItems(lst);
            label.setText("TOP 10 PUPULAR CATEGORIES");
            currentIndex = "A";
        }catch (Exception e){

        }
    }

    @FXML
    void showHashTag(ActionEvent event) {
        try {
            ArrayList<String> s = LoadFileAndSetData.popularTags(c.getBox());

            ObservableList<String> lst = FXCollections.observableArrayList();

            for (int i = 0; i < 10; i++) {
                lst.add(s.get(i));
            }
            listNews.setItems(lst);
            label.setText("TOP 10 PUPULAR HASHTAGS");
            currentIndex = "A";
        }catch (Exception e){

        }
    }

//    @FXML
//    void display(KeyEvent event) {
//
//    }
@FXML
void showDetails(MouseEvent event) throws IOException {

    if(event.getClickCount() == 2) {
        if(currentIndex == "A") {
            String keyword = listNews.getSelectionModel().getSelectedItem();
            ObservableList<String> lst = FXCollections.observableArrayList();
            for (Article x : SearchAndSort.searchForTrends(keyword, c.getBox())) {
                lst.add(x.getTitle());
            }
            currentIndex = "B";
            listNews.setItems(lst);
        }
        else if(currentIndex == "B") {
            String keyword = listNews.getSelectionModel().getSelectedItem();
            for(Article x : c.getBox()) {
                if(keyword.equals(x.getTitle())) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailNews.fxml"));
                    Parent root = loader.load();
                    ControllerDetail ctl = loader.getController();

                    ctl.setT(x.getDetailContent());

                    Scene scn = new Scene(root);
                    Stage stage = (Stage) homeButton.getScene().getWindow();
                    stage.setScene(scn);
                    stage.show();
                    break;
                }

            }

        }
    }
}
    @FXML
    void goHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage)homeButton.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
