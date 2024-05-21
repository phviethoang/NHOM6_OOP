package test.oopnhom6;

import data.*;
import helper.LoadFileAndSetData;
import helper.MakeTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHistory extends ControllerButton implements Initializable {
    @FXML
    private TableView<HistoryObject> tableHistory;
    @FXML
    private TextField searchText;
    @FXML
    private Button homeButton;
    static ObservableList<HistoryObject> lst = FXCollections.observableArrayList();

    Cabinet c = new Cabinet();
    AuthorCabinet ac = new AuthorCabinet();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MakeTableView.makeTableHistory(tableHistory);
        searchText.clear();
        FXCollections.reverse(lst);
        tableHistory.setItems(lst);


        c.setBox(LoadFileAndSetData
                .data_array("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\blockchainDigitaltrends (2).json"));
        ac.setAuthorBox(LoadFileAndSetData.data_author("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\authorsDigitalTrends.json"));
    }

    void setTableHistory(HistoryObject object){
        lst.add(object);
    }

    @FXML
    void detailNews(MouseEvent mouseEvent) throws IOException {
        String keyword = tableHistory.getSelectionModel().getSelectedItem().getItem();
        String type = tableHistory.getSelectionModel().getSelectedItem().getTypeOfObject();

        FXMLLoader loaderHistory = new FXMLLoader(getClass().getResource("historyScene.fxml"));
        loaderHistory.load();
        ControllerHistory controllerHistory = loaderHistory.getController();

        if(type.equals("Article")) {

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
        else  {

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
