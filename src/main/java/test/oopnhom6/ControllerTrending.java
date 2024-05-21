package test.oopnhom6;

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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import data.*;
import helper.*;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;

public class ControllerTrending extends ControllerButton implements Initializable {

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
    private Button backButton;
    @FXML
    private Label label;
    @FXML
    private TableView<Pair<String,String>> tableView;
    private String keyword;

    Cabinet c = new Cabinet();
    AuthorCabinet ac=new AuthorCabinet();

    public static Stack<PrevState> prev = new Stack();


    String currentIndex;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //currentIndex="A";
        //prev.push(new PrevState(tableView.getColumns().get(1).getText(), tableView.getColumns().get(1).getText(), tableView.getItems()));
        this.c.setBox(LoadFileAndSetData
                .data_array("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\blockchainDigitaltrends (2).json"));
        this.ac.setAuthorBox(LoadFileAndSetData.data_author("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\authorsDigitalTrends.json"));
    }


    @FXML
    void showAuthor(ActionEvent event) {
//        try {
//            ArrayList<String> s = LoadFileAndSetData.popularAuth(c.getBox());
//            ObservableList<String> lst = FXCollections.observableArrayList();
//            for (int i = 0; i < 10; i++) {
//                lst.add(s.get(i));
//            }
//            listNews.setItems(lst);
//            label.setText("TOP 10 POPULAR AUTHORS");
//            currentIndex = "A";
//        }catch (Exception e){
//
//        }
        try {
            MakeTableView.makeTableAuthorTrending(tableView);
            Map<String,String> map = LoadFileAndSetData.popularAuth(c.getBox());
            ObservableList<Pair<String, String>> list = FXCollections.observableArrayList();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                list.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
            save();
            tableView.setItems(list);
            label.setText("TOP 10 POPULAR AUTHORS");
         //   currentIndex = "A";
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void showCategory(ActionEvent event) {
        try {
            MakeTableView.makeTableCategoryTrending(tableView);
            Map<String,String> map = LoadFileAndSetData.popularCategory(c.getBox());
            ObservableList<Pair<String, String>> list = FXCollections.observableArrayList();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                list.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
            save();
            tableView.setItems(list);
            label.setText("TOP 10 POPULAR CATEGORIES");
        //    currentIndex = "B";
        }catch (Exception e){

        }
    }

    @FXML
    void showHashTag(ActionEvent event) {
        try {
            MakeTableView.makeTableHashtagTrending(tableView);
            Map<String,String> map = LoadFileAndSetData.popularTags(c.getBox());
            ObservableList<Pair<String, String>> list = FXCollections.observableArrayList();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                list.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
            save();
            tableView.setItems(list);
            label.setText("TOP 10 POPULAR HASHTAGS");
        //    currentIndex = "B";
        }catch (Exception e){

        }
    }
    @FXML
    void back(ActionEvent event){
//        if(!prev.isEmpty())
//            tableView.setItems(prev.peek());
//        System.out.println(currentIndex);
//        if(currentIndex.equals("B")){
//            tableView.getColumns().get(1).setText("Category");
//            tableView.getColumns().get(2).setText("Number of articles");
//        }
//        if(currentIndex.equals("C")){
//            tableView.getColumns().get(1).setText("Hashtag");
//            tableView.getColumns().get(2).setText("Number of occurrences");
//        }

        if(!prev.isEmpty()) {
            PrevState pS = prev.pop();

            tableView.getColumns().get(1).setText(pS.getCol1());
            tableView.getColumns().get(2).setText(pS.getCol2());
            tableView.setItems(pS.getObservableList());
        }
    }

//    @FXML
//    void display(KeyEvent event) {
//
//    }
    public void save(){
        //prev.push(tableView.getItems());
        prev.push(new PrevState(tableView.getColumns().get(1).getText(), tableView.getColumns().get(2).getText(), tableView.getItems()));
    }
@FXML
void showDetails(MouseEvent event) {
    try {
        save();
        FXMLLoader loaderHistory = new FXMLLoader(getClass().getResource("historyScene.fxml"));
        loaderHistory.load();
        ControllerHistory controllerHistory = loaderHistory.getController();
        String currActivity = tableView.getColumns().get(1).getText();
        if(event.getClickCount()==2) {
            if (currActivity.equals("Name")) {
                String keyword = tableView.getSelectionModel().getSelectedItem().getKey();

                for (int i = 0; i < ac.getAuthorBox().size(); i++) {
                    if (keyword.equals(ac.getAuthorBox().get(i).getName())) {

                        HistoryObject historyObject = new HistoryObject(ac.getAuthorBox().get(i));
                        controllerHistory.setTableHistory(historyObject);

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailAuthor.fxml"));
                        Parent root = loader.load();
                        ControllerAuthorDetail ctl = loader.getController();
                        ctl.setLabel(keyword);
                        ctl.displayDetailAuthor(ac.getAuthorBox().get(i));
                        ctl.showAuthorArticle();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) homeButton.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                        break;
                    }
                }
            } else if (currActivity.equals("Category")) {
                String keyword = tableView.getSelectionModel().getSelectedItem().getKey();
                ObservableList<Pair<String,String>> list = FXCollections.observableArrayList();
                for (Article x : SearchAndSort.searchForTrends(keyword, c.getBox())) {
                    list.add(new Pair<>(x.getTitle(), x.getAuthorView()));
                }

                tableView.getColumns().get(1).setText("Article");
                tableView.getColumns().get(2).setText("Author");
                tableView.setItems(list);

            }else if(currActivity.equals("Hashtag")){
                String keyword = tableView.getSelectionModel().getSelectedItem().getKey();
                ObservableList<Pair<String,String>> list = FXCollections.observableArrayList();
                for (Article x : SearchAndSort.searchForTrends(keyword, c.getBox())) {
                    list.add(new Pair<>(x.getTitle(),x.getAuthorView()));
                }

                tableView.getColumns().get(1).setText("Article");
                tableView.getColumns().get(2).setText("Author");
                tableView.setItems(list);
            }
            else if (currActivity.equals("Article")) {
                String keyword = tableView.getSelectionModel().getSelectedItem().getKey();
                for (Article x : c.getBox()) {
                    if (keyword.equals(x.getTitle())) {

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
    }catch (Exception e){
        System.out.println(e.getMessage());
    }

//
}

@FXML
void search(KeyEvent keyEvent) {
//        searchText.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                for
//            }
//        });
}

//    @FXML
//    void goHome(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
//        Scene scene = new Scene(root);
//        Stage primaryStage = (Stage)homeButton.getScene().getWindow();
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
}
// if(event.getClickCount() == 2) {
//        if(currentIndex == "A") {
//            String keyword = tableView.getSelectionModel().getSelectedItem().getKey();
//            ObservableList<String> lst = FXCollections.observableArrayList();
//            for (Article x : SearchAndSort.searchForTrends(keyword, c.getBox())) {
//                lst.add(x.getTitle());
//            }
//            currentIndex = "B";
//            tableView.setItems(lst);
//        }
//        else if(currentIndex == "B") {
//            String keyword = tableView.getSelectionModel().getSelectedItem().getKey();
//            for(Article x : c.getBox()) {
//                if(keyword.equals(x.getTitle())) {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailNews.fxml"));
//                    Parent root = loader.load();
//                    ControllerNewsDetail ctl = loader.getController();
//                    ctl.setDetail(x);
//                    Scene scn = new Scene(root);
//                    Stage stage = (Stage) homeButton.getScene().getWindow();
//                    stage.setScene(scn);
//                    stage.show();
//                    break;
//                }
//            }
//
//        }