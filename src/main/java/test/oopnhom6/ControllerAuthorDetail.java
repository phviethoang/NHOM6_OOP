package test.oopnhom6;

import data.*;
import helper.LoadFileAndSetData;
import helper.MakeTableView;
import helper.SearchAndSort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ControllerAuthorDetail extends ControllerButton{
    @FXML
    private Button homeButton;
    @FXML
    private TableView<Article> tableNews;
    @FXML
    private Button historyButton;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField searchText;
    @FXML
    private Label label;

    Cabinet c=new Cabinet();
    AuthorCabinet ac=new AuthorCabinet();

    // danh sach fixList la danh sach bai bao cua 1 tac gia; searchList la list hien thi len man hinh
    ObservableList<Article> fixedList = FXCollections.observableArrayList();

    public void setLabel(String name){
        label.setText(name);

    }
    void displayDetailAuthor(Author a){
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.appendText("   Name: "+a.getName()+"\n");
        textArea.appendText("   Work: "+a.getJob()+"\n");
        textArea.appendText("   Biography: "+a.getDetail()+"\n");
    }

    @FXML
    void search(KeyEvent event){
        if(c.getBox()!=null){
            String search = searchText.getText();
            ObservableList<Article> obs = FXCollections.observableArrayList();
            for (Article x : SearchAndSort.searchArticle(search, c.getBox())) {
                if(x.getAuthor().contains(label.getText())){
                    obs.add(x);
                }
            }
            tableNews.setItems(obs);
        }
    }

    void showAuthorArticle(){
        MakeTableView.makeTableAuthorNews(tableNews);
        c.setBox(LoadFileAndSetData
                .data_array("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\blockchainDigitaltrends (2).json"));

        //System.out.println(authorName.getText());
        for (Article x : c.getBox()) {
            if(x.getAuthor().contains(label.getText())){
                fixedList.add(x);
            }
        }
        tableNews.setItems(fixedList);
    }

    @FXML
    void displayDetailNews(MouseEvent event) throws IOException {
        if(event.getClickCount()==2){
            Article a=tableNews.getSelectionModel().getSelectedItem();
            if(a!=null){
                String keyword = a.getTitle();

                FXMLLoader loaderHistory = new FXMLLoader(getClass().getResource("historyScene.fxml"));
                loaderHistory.load();
                ControllerHistory controllerHistory = loaderHistory.getController();

                if(keyword!=null){
                    for(Article x : c.getBox()) {
                        if(keyword.equals(x.getTitle())) {
                            HistoryObject historyObject = new HistoryObject(x);
                            controllerHistory.setTableHistory(historyObject);

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailNews.fxml"));
                            Parent root = loader.load();
                            ControllerNewsDetail ctl = loader.getController();
                            ctl.setDetail(x);
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
    }

//    @FXML
//    void goHome(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
//        Scene scene = new Scene(root);
//        Stage primaryStage = (Stage)homeButton.getScene().getWindow();
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//    @FXML
//    void showNews(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("homeScene.fxml"));          // Nạp root trước khi load controller
//            Parent root = loader.load();
//            ControllerGUI ctl = loader.getController();
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) homeButton.getScene().getWindow();
//            stage.setScene(scene);
//            ctl.showNews(event);
//            stage.show();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    @FXML
//    void showHomeAuthors(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("homeScene.fxml"));          // Nạp root trước khi load controller
//            Parent root = loader.load();
//            ControllerGUI ctl = loader.getController();
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) homeButton.getScene().getWindow();
//            stage.setScene(scene);
//            ctl.showAuthors(event);
//            stage.show();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//    @FXML
//    void showHistory(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("historyScene.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) historyButton.getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
//    }
}
