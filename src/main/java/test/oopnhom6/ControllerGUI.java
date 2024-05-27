package test.oopnhom6;

import data.*;
import helper.LoadFileAndSetData;
import helper.SearchAndSort;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

public class ControllerGUI extends ControllerButton implements Initializable {
    @FXML
    public AnchorPane scenePane;
    @FXML
    private Button authorButton;
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
    private Button sortButton;

    @FXML
    private TableView<General> tableView;
    @FXML
    private AnchorPane anchorPane;
    private Stack<TableView> tableStack = new Stack<>();
    @FXML
    private Button backButton;
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private AnchorPane anchorPane2;



    Cabinet c = new Cabinet();
    AuthorCabinet ac=new AuthorCabinet();
    // Tạo một Image

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(tableView!=null) tableView.setVisible(false);
        makeHighlight();
        searchText.setVisible(false);
        searchButton.setVisible(false);
        anchorPane.setVisible(false);
        backButton.setVisible(false);
        setBackground();
    }

    @FXML
    void home(ActionEvent event){
        tableView.setVisible(false);
        searchText.clear();
        searchText.setVisible(false);
        searchButton.setVisible(false);
        anchorPane.setVisible(false);
        backButton.setVisible(false);
    }
    @FXML
    void search(KeyEvent event) {
        try{
            if(c.getBox()!=null){
                String search = searchText.getText();
                ObservableList<General> obs = FXCollections.observableArrayList();
                for (Article x : c.searchArticle(search, c.getBox())) {
                    obs.addAll(x);
                }
                tableView.setItems(obs);
            }
            if(ac.getAuthorBox()!=null){
                String search = searchText.getText();
                ObservableList<General> obs = FXCollections.observableArrayList();
                for (Author x : ac.searchAuthor(search, ac.getAuthorBox())) {
                    obs.addAll(x);
                }
                tableView.setItems(obs);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void showNews(ActionEvent event){
//        tableView.setVisible(true);
        save();
        backButton.setVisible(true);
        searchText.setVisible(true);
        searchButton.setVisible(true);
        anchorPane.setVisible(true);
        Table<General> table = new Table<>(tableView);
        TableColumn<General, Integer> colID = table.columnID(68);
        TableColumn<General, String> colName = table.newColumn("Name", "generalTitle",487.20001220703125, false );
        TableColumn<General, String> colAuthor = table.newColumn("Author", "authorView", false );
        table.addColumn(colID, colName,colAuthor);
        searchText.clear();
        c.setBox(c.loadData());
        ObservableList<General> lst = FXCollections.observableArrayList();
        for (Article x : c.getBox()) {
             lst.add(x);
        }
        tableView.setItems(lst);
    }

    @FXML
    void showAuthors(ActionEvent event){
        save();
        backButton.setVisible(true);
        searchText.setVisible(true);
        searchButton.setVisible(true);
        anchorPane.setVisible(true);
        Table<General> table = new Table<>(tableView);
        TableColumn<General, Integer> colID = table.columnID(68);
        TableColumn<General, String> colName = table.newColumn("Name", "generalName",487.20001220703125, true );
        table.addColumn(colID, colName);
        searchText.clear();
        ac.setAuthorBox(ac.fullAuthorList());
        ObservableList<General> lst = FXCollections.observableArrayList();
        for (Author x : ac.getAuthorBox()) {
            lst.add(x);
        }
        tableView.setItems(lst);

//        c.setBox(LoadFileAndSetData
//                .data_array("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\blockchainDigitaltrends (2).json"));
//        ObservableList<General> lst = FXCollections.observableArrayList();
//        for (Article x : c.getBox()) {
//            lst.add(x);
//        }
//        tableView.setItems(lst);
    }
//    @FXML
//    void showHistory(ActionEvent event) throws IOException {
//       Parent root = FXMLLoader.load(getClass().getResource("historyScene.fxml"));
//       Scene scene = new Scene(root);
//       Stage stage = (Stage) historyButton.getScene().getWindow();
//       stage.setScene(scene);
//       stage.show();
//    }
//    public void exitApp(ActionEvent event){
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Exit");
//        alert.setHeaderText("You're about to exit the application!");
//        alert.setContentText("Have you made sure to close it? ");
//        if(alert.showAndWait().get() == ButtonType.OK){
//           Stage stage = (Stage) scenePane.getScene().getWindow();
//            stage.close();
//        }
//    }

    @FXML
    void detailNews(MouseEvent event) {
        try {
            if (event.getClickCount() == 2) {
                save();
                prevScene.push(authorButton.getScene());

                FXMLLoader loaderHistory = new FXMLLoader(getClass().getResource("historyScene.fxml"));
                loaderHistory.load();
                ControllerHistory controllerHistory = loaderHistory.getController();
                if(c.getBox()!=null){
                    General u = tableView.getSelectionModel().getSelectedItem();
                    if(u!=null){
                        HistoryObject historyObject = new HistoryObject(u);
                        controllerHistory.setTableHistory(historyObject);
                        setData(u,c.getBox());
                    }
                }
                if(ac.getAuthorBox()!=null){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailAuthor.fxml"));
                    Parent root = loader.load();
                    ControllerAuthorDetail ctl = loader.getController();
                    General g= tableView.getSelectionModel().getSelectedItem();
                    if(g instanceof Author a){

                        FXMLLoader loaderHistory1 = new FXMLLoader(getClass().getResource("historyScene.fxml"));
                        loaderHistory1.load();
                        HistoryObject historyObject = new HistoryObject(a);
                        ControllerHistory controllerHistory1 = loaderHistory1.getController();
                        controllerHistory1.setTableHistory(historyObject);

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
    }
    @FXML
    void back(ActionEvent event) {
        if (!tableStack.isEmpty()) {
//            tableView.getItems().clear();
//            tableView.getColumns().clear();

            TableView preT = tableStack.pop();
            tableView.getColumns().setAll(preT.getColumns());
            tableView.setItems(preT.getItems());
        }

        else if(! prevScene.isEmpty()) {
            Scene pS = prevScene.peek();
            prevScene.pop();
            Stage stg = (Stage) homeButton.getScene().getWindow();
            stg.setScene(pS);
        }
        else {
            tableView.setVisible(false);
            backButton.setVisible(false);
            searchText.setVisible(false);
            searchButton.setVisible(false);
            anchorPane.setVisible(false);
        }
    }
    public void save() {
        TableView<General> cur = new TableView<>();
        ObservableList<General> itemsCopy = FXCollections.observableArrayList(tableView.getItems());
        cur.setItems(itemsCopy);
        ObservableList<TableColumn<General, ?>> columnsCopy = FXCollections.observableArrayList(tableView.getColumns());
        cur.getColumns().setAll(columnsCopy);
        tableStack.push(cur);
    }
    @FXML
    void sortByDate(MouseEvent mouseEvent){
        Table<General> table = new Table<>(tableView);
        TableColumn<General, Integer> colID = table.columnID(68);
        TableColumn<General, String> colName = table.newColumn("Name", "generalTitle",487.20001220703125, false );
        TableColumn<General, String> colAuthor = table.newColumn("Author", "authorView", false );
        table.addColumn(colID, colName,colAuthor);
        c.setBox(c.loadData());

//        if(temp.equals("Cũ nhất")) {
//            c.sortByDateAZ();
//        }
//        else if(temp.equals("Mới nhất")) {
//            c.sortByDateZA();
//        }

        if(mouseEvent.getClickCount() == 2) {
            c.sortByDateAscending();
        }
        else if(mouseEvent.getClickCount() == 1) {
            c.sortByDateDescending();
        }

//            System.out.println(temp);
//
//
        ObservableList<General> list = FXCollections.observableArrayList();
        for (Article x : c.getBox()) {
            list.add(x);
        }
        tableView.setItems(list);
        tableView.refresh();
    }

}