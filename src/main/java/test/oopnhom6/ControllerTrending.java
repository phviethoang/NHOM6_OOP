package test.oopnhom6;

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
import data.*;
import helper.*;
import javafx.util.Pair;
import service.Animation;

import java.net.URL;
import java.util.*;

public class ControllerTrending extends ControllerButton implements Initializable {

    @FXML
    private Button author;

    @FXML
    private Button category;

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
    private AnchorPane anchorPane;
    @FXML
    private Label label;
    @FXML
    private TableView<Pair<String, String>> tableView;
    private String keyword;

    Cabinet c = new Cabinet();
    AuthorCabinet ac = new AuthorCabinet();

    public static Stack<PrevState> prev = new Stack();
    Animation animation = new Animation();
    private Map<String,String> articleMap;

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
        tableView.setVisible(false);
        searchText.setVisible(false);
        searchButton.setVisible(false);
        anchorPane.setVisible(false);
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
            tableView.setVisible(true);
            searchText.setVisible(true);
            searchButton.setVisible(true);
            anchorPane.setVisible(true);
            Table<Pair<String, String>> table = new Table<>(tableView);
            TableColumn<Pair<String, String>, Integer> colID = table.columnID(68);
            TableColumn<Pair<String, String>, String> colName = table.newColumn("Name", 402.39996337890625, true, "key");
            TableColumn<Pair<String, String>, String> colCount = table.newColumn("Number of articles contributed", true, "value");
            table.addColumn(colID, colName, colCount);
            searchText.clear();
            Map<String, String> map = ac.popularAuth(c.getBox());
            ObservableList<Pair<String, String>> list = FXCollections.observableArrayList();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                list.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
            save();
            tableView.setItems(list);
            label.setText("TOP 10 POPULAR AUTHORS");
            //   currentIndex = "A";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showCategory(ActionEvent event) {
        try {
            tableView.setVisible(true);
            searchText.setVisible(true);
            searchButton.setVisible(true);
            anchorPane.setVisible(true);
            Table<Pair<String, String>> table = new Table<>(tableView);
            TableColumn<Pair<String, String>, Integer> colID = table.columnID(75);
            TableColumn<Pair<String, String>, String> colName = table.newColumn("Category", 460.39996337890625, true, "key");
            TableColumn<Pair<String, String>, String> colCount = table.newColumn("Number of articles", true, "value");
            table.addColumn(colID, colName, colCount);
            searchText.clear();
            Map<String, String> map = c.popularCategory(c.getBox());
            ObservableList<Pair<String, String>> list = FXCollections.observableArrayList();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                list.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
            save();
            tableView.setItems(list);
            label.setText("TOP 10 POPULAR CATEGORIES");
            //    currentIndex = "B";
        } catch (Exception e) {

        }
    }

    @FXML
    void showHashTag(ActionEvent event) {
        try {
            tableView.setVisible(true);
            searchText.setVisible(true);
            searchButton.setVisible(true);
            anchorPane.setVisible(true);
            Table<Pair<String, String>> table = new Table<>(tableView);
            TableColumn<Pair<String, String>, Integer> colID = table.columnID(75);
            TableColumn<Pair<String, String>, String> colName = table.newColumn("Hashtag", 434.39996337890625, true, "key");
            TableColumn<Pair<String, String>, String> colCount = table.newColumn("Number of occurrences", true, "value");
            table.addColumn(colID, colName, colCount);
            searchText.clear();
            Map<String, String> map = c.popularTags(c.getBox());
            ObservableList<Pair<String, String>> list = FXCollections.observableArrayList();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                list.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
            save();
            tableView.setItems(list);
            label.setText("TOP 10 POPULAR HASHTAGS");
            //    currentIndex = "B";
        } catch (Exception e) {

        }
    }
    @FXML
    void search(KeyEvent event) {
//        searchText.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                for
//            }
//        });
        if(tableView.getColumns().get(1).getText().equals("Name")){
            String search = searchText.getText();
            Map<String, String> map = ac.popularAuth(c.getBox());
            ObservableList<Pair<String, String>> list = FXCollections.observableArrayList();
            for (Map.Entry<String, String> entry : searchTrendingList(search,map).entrySet()) {
                list.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
            tableView.setItems(list);
            sortRow(tableView);
        }else if(tableView.getColumns().get(1).getText().equals("Category")){
            String search = searchText.getText();
            Map<String, String> map = c.popularCategory(c.getBox());
            ObservableList<Pair<String, String>> list = FXCollections.observableArrayList();
            for (Map.Entry<String, String> entry : searchTrendingList(search,map).entrySet()) {
                list.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
            tableView.setItems(list);
            sortRow(tableView);
        }else if(tableView.getColumns().get(1).getText().equals("Hashtag")){
            String search = searchText.getText();
            Map<String, String> map = c.popularTags(c.getBox());
            ObservableList<Pair<String, String>> list = FXCollections.observableArrayList();
            for (Map.Entry<String, String> entry : searchTrendingList(search,map).entrySet()) {
                list.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
            tableView.setItems(list);
            sortRow(tableView);
        }
        else if(tableView.getColumns().get(1).getText().equals("Article")){
            String search = searchText.getText();
            ObservableList<Pair<String, String>> list = FXCollections.observableArrayList();
            for (Map.Entry<String, String> entry : searchTrendingList(search,articleMap).entrySet()) {
                list.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
            tableView.setItems(list);
        }
    }
    @FXML
    void back(ActionEvent event) {
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

        if (!prev.isEmpty()) {
            PrevState pS = prev.pop();

//            if(pS.getCol1().equals("")) {
//                tableView.getColumns().get(0).setText("");
//            }
            label.setText(pS.getTitleOfTable());
            tableView.getColumns().get(0).setText(pS.getCol0());
            tableView.getColumns().get(1).setText(pS.getCol1());
            tableView.getColumns().get(2).setText(pS.getCol2());
            tableView.setItems(pS.getObservableList());
        } else {
            if (!prevScene.isEmpty()) {
                Stage stg = (Stage) trendButton.getScene().getWindow();
                stg.setScene(prevScene.peek());
                prevScene.pop();
            }
        }
    }

    //    @FXML
//    void display(KeyEvent event) {
//
//    }
    public void save() {
        //prev.push(tableView.getItems());
        if (tableView.getColumns().isEmpty()) {
            ObservableList emptyList = FXCollections.observableArrayList();
            prev.push(new PrevState("", "", "", "", emptyList));
        } else {
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
            if (event.getClickCount() == 2) {
                if (currActivity.equals("Name")) {
                    String keyword = tableView.getSelectionModel().getSelectedItem().getKey();

                    for (int i = 0; i < ac.getAuthorBox().size(); i++) {
                        if (keyword.equals(ac.getAuthorBox().get(i).getName())) {

                            HistoryObject historyObject = new HistoryObject(ac.getAuthorBox().get(i));
                            controllerHistory.setTableHistory(historyObject);
                            prevScene.push(label.getScene());
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
                    ObservableList<Pair<String, String>> list = FXCollections.observableArrayList();
                    for (Article x : trendingList(keyword, c.getBox())) {
                        list.add(new Pair<>(x.getTitle(), x.getAuthorView()));
                    }
                    articleMap=convertListToMap(list);
                    tableView.getColumns().get(1).setText("Article");
                    tableView.getColumns().get(2).setText("Author");
                    tableView.setItems(list);
                    searchText.clear();

                } else if (currActivity.equals("Hashtag")) {
                    String keyword = tableView.getSelectionModel().getSelectedItem().getKey();
                    ObservableList<Pair<String, String>> list = FXCollections.observableArrayList();
                    for (Article x : trendingList(keyword, c.getBox())) {
                        list.add(new Pair<>(x.getTitle(), x.getAuthorView()));
                    }
                    articleMap=convertListToMap(list);
                    tableView.getColumns().get(1).setText("Article");
                    tableView.getColumns().get(2).setText("Author");
                    tableView.setItems(list);
                    searchText.clear();
                } else if (currActivity.equals("Article")) {
                    String keyword = tableView.getSelectionModel().getSelectedItem().getKey();
                    for (Article x : c.getBox()) {
                        if (keyword.equals(x.getTitle())) {

                            HistoryObject historyObject = new HistoryObject(x);
                            controllerHistory.setTableHistory(historyObject);
                            prevScene.push(label.getScene());
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

//
    }

     Map<String,String> searchTrendingList(String keyword, Map<String,String> maps){
        Map<String,String> map=new HashMap<>();
        for(Map.Entry<String,String> x:maps.entrySet()){
            if(x.getKey()==null||x.getValue()==null) continue;
            if(x.getKey().toLowerCase().contains(keyword.toLowerCase())||x.getValue().toLowerCase().contains(keyword.toLowerCase())){
                map.put(x.getKey(),x.getValue());
            }
        }
        return map;
     }

     ArrayList<Article> trendingList(String keyword, ArrayList<Article> Articles) {
        ArrayList<Article> res = new ArrayList<>();
        for (Article x : Articles) {
            if (x.getAuthor()==null||x.getCategory()==null||x.getTags()==null||keyword==null) continue;
            if (x.getAuthor().contains(keyword) || keyword.equals(x.getCategory())||x.getTags().contains(keyword)) {
                res.add(x);
            }
        }
        return res;
    }
    Map<String, String> convertListToMap(ObservableList<Pair<String, String>> list) {
        Map<String, String> map = new HashMap<>();
        for (Pair<String, String> pair : list) {
            map.put(pair.getKey(), pair.getValue());
        }
        return map;
    }
    void sortRow(TableView<Pair<String,String>> tableView){
        tableView.getColumns().get(2).setComparator((s1,s2)->{
            try {
                Integer int1 = Integer.parseInt((String) s1);
                Integer int2 = Integer.parseInt((String) s2);
                return int1.compareTo(int2);
            } catch (NumberFormatException e) {
                return 0;
            }
        });
        tableView.getColumns().get(2).setSortType(TableColumn.SortType.DESCENDING);
        tableView.getSortOrder().add(tableView.getColumns().get(2));
        tableView.sort();
    }
}