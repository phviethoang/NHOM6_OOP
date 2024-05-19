package test.oopnhom6;

import data.*;
import helper.LoadFileAndSetData;
import helper.MakeTableView;
import helper.SearchAndSort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class ControllerGUI implements Initializable {
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
    @FXML
    private TableView<General> tableNews;

    Cabinet c=new Cabinet();
    AuthorCabinet ac=new AuthorCabinet();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(tableNews!=null) tableNews.setVisible(false);
    }
    @FXML
    void home(ActionEvent event){
        tableNews.setVisible(false);
        searchText.clear();
    }
    @FXML
    void search(KeyEvent event) {
//        if(c.getBox()!=null){
//            String search = searchText.getText();
//            ObservableList<Article> obs = FXCollections.observableArrayList();
//            for (Article x : SearchAndSort.searchArticle(search, c.getBox())) {
//                obs.add(x);
//            }
//            tableNews.setItems(obs);
//        }
//        void showNews(ActionEvent event) throws IOException{
//            tableNews.setVisible(true);
//            MakeTableView.makeTableNews(tableNews);
//            c.setBox(LoadFileAndSetData
//                    .data_array("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\blockchainDigitaltrends (2).json"));
//            ObservableList<Article> lst = FXCollections.observableArrayList();
//            for (Article x : c.getBox()) {
//                lst.add(x);
//            }
//            tableNews.setItems(lst);
//        }
        try{
            if(c.getBox()!=null){
                String search = searchText.getText();
                ObservableList<General> obs = FXCollections.observableArrayList();
                for (Article x : SearchAndSort.searchArticle(search, c.getBox())) {
                    obs.addAll(x);
                }
                tableNews.setItems(obs);
            }
            if(ac.getAuthorBox()!=null){
                String search = searchText.getText();
                ObservableList<General> obs = FXCollections.observableArrayList();
                for (Author x : SearchAndSort.searchAuthor(search, ac.getAuthorBox())) {
                    obs.addAll(x);
                }
                tableNews.setItems(obs);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void scrollToFirst(ActionEvent event) {
        tableNews.scrollTo(0);
    }
    @FXML
    void scrollToLast(ActionEvent event) {
        tableNews.scrollTo(500);
    }
//    @FXML
//    void showNews(ActionEvent event) throws IOException{
//        c.setBox(LoadFileAndSetData
//                .data_array("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\blockchainDigitaltrends (2).json"));
//        ObservableList<String> lst = FXCollections.observableArrayList();
//        for (Article x : c.getBox()) {
//            lst.add(x.getTitle());
//        }
//        listNews.setItems(lst);
//        // String s = LoadFileAndSetData.read_data("blockchain.json");
//    }
    @FXML
    void showNews(ActionEvent event) throws IOException{
//        tableNews.setVisible(true);
        MakeTableView.makeTableNews(tableNews);
        searchText.clear();
        c.setBox(LoadFileAndSetData
                .data_array("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\blockchainDigitaltrends (2).json"));
        ObservableList<General> lst = FXCollections.observableArrayList();
        for (Article x : c.getBox()) {
             lst.add(x);
        }
        tableNews.setItems(lst);
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
    void showAuthors(ActionEvent event){
        MakeTableView.makeTableAuthor(tableNews);
        searchText.clear();
        ac.setAuthorBox(LoadFileAndSetData
                .data_author("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\authorsDigitalTrends.json"));
        ObservableList<General> lst = FXCollections.observableArrayList();
        for (Author x : ac.getAuthorBox()) {
            lst.add(x);
        }
        tableNews.setItems(lst);

//        c.setBox(LoadFileAndSetData
//                .data_array("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\blockchainDigitaltrends (2).json"));
//        ObservableList<General> lst = FXCollections.observableArrayList();
//        for (Article x : c.getBox()) {
//            lst.add(x);
//        }
//        tableNews.setItems(lst);
    }
    @FXML
    void showHistory(ActionEvent event){

    }
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
                if(c.getBox()!=null){
                    General u = tableNews.getSelectionModel().getSelectedItem();
                    if(u!=null){
                        setData(u,c.getBox());
                    }
                }
                if(ac.getAuthorBox()!=null){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailAuthor.fxml"));
                    Parent root = loader.load();
                    ControllerAuthorDetail ctl = loader.getController();
                    General g= tableNews.getSelectionModel().getSelectedItem();
                    if(g instanceof Author a){
                        String name=a.getGeneralName();
                        ctl.setLabel(name);
                        ctl.displayDetailAuthor(a);
                        ctl.showAuthorArticle();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) authorButton.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setData(General selectedItem, ArrayList<Article> arr) throws IOException {
        for (Article article : arr) {
            if (article.getTitle().equals(selectedItem.getGeneralTitle())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailNews.fxml"));
                // Nạp root trước khi load controller
                Parent root = loader.load();
                ControllerNewsDetail ctl = loader.getController();
                ctl.setDetail(article);
                Scene scene = new Scene(root);
                Stage stage = (Stage) homeButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                break;
            }
        }
//        for(int i=0;i<tableNews.getItems().size();i++){
//            Object cellValue = colName.getCellData(i);
//            if (cellValue != null && cellValue.toString().equals(selectedItem.getTitle())) {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailNews.fxml"));
//                // Nạp root trước khi load controller
//                Parent root = loader.load();
//                ControllerNewsDetail ctl = loader.getController();
//                ctl.setT(selectedItem);
//                Scene scene = new Scene(root);
//                Stage stage = (Stage) homeButton.getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//                break;
//            }
//        }
    }

    void bigger(){
//        Animation a0 = new Animation();
//        Animation a1 = new Animation();
//        Animation a2 = new Animation();
//        Animation a3 = new Animation();
//        Animation a4 = new Animation();
//        Animation a5 = new Animation();
//        Animation a6 = new Animation();
//        Animation a7 = new Animation();
//        Animation a8 = new Animation();
//
//        a0.setBigger(trendButton);
//        a1.setBigger(authorButton);
//        a2.setBigger(exitButton);
//        a3.setBigger(firstButton);
//        a4.setBigger(historyButton);
//        a5.setBigger(homeButton);
//        a6.setBigger(lastButton);
//        a7.setBigger(memberButton);
//        a8.setBigger(newsButton);
    }



}