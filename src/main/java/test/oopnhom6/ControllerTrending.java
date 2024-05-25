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
import service.Animation;

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
    private Button newsButton;
    @FXML
    private Button authorButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button memberButton;

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
    Animation animation =new Animation();


    String currentIndex;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //currentIndex="A";
        //prev.push(new PrevState(tableView.getColumns().get(1).getText(), tableView.getColumns().get(1).getText(), tableView.getItems()));
        this.c.setBox(c.loadData());
        this.ac.setAuthorBox(ac.fullAuthorList());
        makeHighlight();
        animation.buttonHighlight(author);
        animation.buttonHighlight(category);
        animation.buttonHighlight(hashTag);
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
            Table<Pair<String, String>> table = new Table<>(tableView);
            TableColumn<Pair<String, String>, Integer> colID = table.columnID(68);
            TableColumn<Pair<String, String>, String> colName = table.newColumn("Name",402.39996337890625, true,"key" );
            TableColumn<Pair<String, String>, String> colCount = table.newColumn("Number of articles contributed", true,"value" );
            table.addColumn(colID, colName, colCount);
            Map<String,String> map = ac.popularAuth(c.getBox());
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
            Table<Pair<String, String>> table = new Table<>(tableView);
            TableColumn<Pair<String, String>, Integer> colID = table.columnID(75);
            TableColumn<Pair<String, String>, String> colName = table.newColumn("Category",460.39996337890625, true,"key" );
            TableColumn<Pair<String, String>, String> colCount = table.newColumn("Number of articles", true,"value" );
            table.addColumn(colID, colName, colCount);
            Map<String,String> map = c.popularCategory(c.getBox());
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
            Table<Pair<String, String>> table = new Table<>(tableView);
            TableColumn<Pair<String, String>, Integer> colID = table.columnID(75);
            TableColumn<Pair<String, String>, String> colName = table.newColumn("Hashtag",434.39996337890625, true,"key" );
            TableColumn<Pair<String, String>, String> colCount = table.newColumn("Number of occurrences", true,"value" );
            table.addColumn(colID, colName, colCount);
            Map<String,String> map = c.popularTags(c.getBox());
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

//            if(pS.getCol1().equals("")) {
//                tableView.getColumns().get(0).setText("");
//            }
            label.setText(pS.getTitleOfTable());
            tableView.getColumns().get(0).setText(pS.getCol0());
            tableView.getColumns().get(1).setText(pS.getCol1());
            tableView.getColumns().get(2).setText(pS.getCol2());
            tableView.setItems(pS.getObservableList());
        }

        else {
            if(!LoadFileAndSetData.prevScene.isEmpty()) {
                Stage stg = (Stage) trendButton.getScene().getWindow();
                stg.setScene(LoadFileAndSetData.prevScene.peek());
                LoadFileAndSetData.prevScene.pop();
            }
        }
    }

    //    @FXML
//    void display(KeyEvent event) {
//
//    }
    public void save(){
        //prev.push(tableView.getItems());

        if (tableView.getColumns().isEmpty()) {
            ObservableList emptyList = FXCollections.observableArrayList();
            prev.push(new PrevState("","", "", "", emptyList));
        }

        else{
            prev.push(new PrevState(label.getText(), tableView.getColumns().get(0).getText(), tableView.getColumns().get(1).getText(), tableView.getColumns().get(2).getText(), FXCollections.observableArrayList(tableView.getItems())));
        }
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
                        LoadFileAndSetData.prevScene.push(label.getScene());
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
                        LoadFileAndSetData.prevScene.push(label.getScene());
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