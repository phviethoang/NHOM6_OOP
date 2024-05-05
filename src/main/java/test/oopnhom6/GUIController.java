package test.oopnhom6;

import data.Article;
import data.Cabinet;
import helper.LoadFileAndSetData;
import helper.SearchAndSort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GUIController implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    public AnchorPane scenePane;
    @FXML
    private Button authorButton;
    @FXML
    private Button downButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button firstButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button homeButton;
    @FXML
    private ImageView homeImage;
    @FXML
    private Button lastButton;
    @FXML
    private Button memberButton;
    @FXML
    private Button newsButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchText;
    @FXML
    private Button trendButton;
    @FXML
    private Button upButton;
    @FXML
    private ListView<String> listNews;
    Cabinet c=new Cabinet();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
    @FXML
    void display(KeyEvent event) {
        if(c.getBox()!=null){
            String search = new String();

            search = searchText.getText();
            ObservableList<String> obs = FXCollections.observableArrayList();
            for (Article x : SearchAndSort.searchArticle(search, c.getBox())) {
                obs.add(x.getTitle());
            }
            listNews.setItems(obs);
        }

    }
    @FXML
    void scrollToFirst(ActionEvent event) {
        listNews.scrollTo(0);
    }
    @FXML
    void scrollToLast(ActionEvent event) {
        listNews.scrollTo(500);
    }
    @FXML
    void showNews(ActionEvent event) throws IOException{
        c.setBox(LoadFileAndSetData
                .data_array("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\blockchain (1).json"));
        ObservableList<String> lst = FXCollections.observableArrayList();
        for (Article x : c.getBox()) {
            lst.add(x.getTitle());
        }
        listNews.setItems(lst);
        // String s = LoadFileAndSetData.read_data("blockchain.json");
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
    void showAuthors(ActionEvent event){}
    public void exitApp(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You're about to exit the application!");
        alert.setContentText("Have you made sure to close it? ");
        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }
    @FXML
    void searching(ActionEvent event) {

    }
    @FXML
    void detailNews(MouseEvent event) {
        try {
            if (event.getClickCount() == 2) {
                String u = listNews.getSelectionModel().getSelectedItem();
                setData(u, c.getBox());
            }
        } catch (Exception e){

        }
    }

    public void setData(String selectedItem, ArrayList<Article> arr) throws IOException {
        for (Article article : arr) {
            if (article.getTitle().equals(selectedItem)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailNews.fxml"));
                // Nạp root trước khi load controller
                Parent root = loader.load();
                ControllerDetail ctl = loader.getController();
                ctl.setT(article.getDetailContent());
                Scene scene = new Scene(root);
                Stage stage = (Stage) homeButton.getScene().getWindow();
                //stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);

                stage.show();
                break;
            }
        }
    }


}