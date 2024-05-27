package test.oopnhom6;

import data.*;
import helper.LoadFileAndSetData;
import helper.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class ControllerHistory extends ControllerButton implements Initializable {
    @FXML
    private TableView<HistoryObject> tableHistory;
    @FXML
    private TextField searchText;
    @FXML
    private Button homeButton;
    @FXML
    Button backButton;
    static ObservableList<HistoryObject> list = FXCollections.observableArrayList();
    Cabinet c = new Cabinet();
    AuthorCabinet ac = new AuthorCabinet();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Table<HistoryObject> table = new Table<>(tableHistory);
        makeHighlight();
        TableColumn<HistoryObject, String> colDatetime = table.newColumn("Date time", "time",95, true );
        TableColumn<HistoryObject, String> colName = table.newColumn("Item", "item", 326.39996337890625,true );
        TableColumn<HistoryObject, String> colType = table.newColumn("Type", "typeOfObject", true );
        table.addColumn(colName,colType, colDatetime);
        searchText.clear();
//        FXCollections.reverse(lst);
        tableHistory.setItems(list);
        c.setBox(c.loadData());
        ac.setAuthorBox(ac.fullAuthorList());
        //Sap xep danh sach history theo thoi gian truy cap
        colDatetime.setComparator((s1, s2) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            try {
                LocalDateTime dateTime1 = LocalDateTime.parse(s1, formatter);
                LocalDateTime dateTime2 = LocalDateTime.parse(s2, formatter);
                return dateTime2.compareTo(dateTime1); // Descending order
            } catch (DateTimeParseException e) {
                // Xử lý nếu có lỗi khi chuyển đổi
                e.printStackTrace();
                return 0; // Trả về 0 để bảo toàn thứ tự gốc
            }
        });
        colDatetime.setSortType(TableColumn.SortType.DESCENDING);
        tableHistory.getSortOrder().add(colDatetime);
        tableHistory.sort();
    }

    void setTableHistory(HistoryObject object){
        list.add(object);
    }

    @FXML
    void search(KeyEvent keyEvent){
        searchText.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyword = searchText.getText().toLowerCase();
                ObservableList<HistoryObject> showSearchingList = FXCollections.observableArrayList();
                for( HistoryObject i : ControllerHistory.list){
                    if(i.getItem().toLowerCase().contains(keyword)){
                        showSearchingList.add(i);
                        tableHistory.setItems(showSearchingList);
                    }
                }
            }
        });
    }
    @FXML
    void detailNews(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getClickCount()==2){
            HistoryObject picker=tableHistory.getSelectionModel().getSelectedItem();
            if(picker!=null) {
                String keyword = tableHistory.getSelectionModel().getSelectedItem().getItem();
                String type = tableHistory.getSelectionModel().getSelectedItem().getTypeOfObject();
                prevScene.push(homeButton.getScene());
                FXMLLoader loaderHistory = new FXMLLoader(getClass().getResource("historyScene.fxml"));
                loaderHistory.load();
                ControllerHistory controllerHistory = loaderHistory.getController();
                if (keyword != null) {
                    if (type.equals("Article")) {

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
                    } else {

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
                    }
                }
            }
        }
    }

}
