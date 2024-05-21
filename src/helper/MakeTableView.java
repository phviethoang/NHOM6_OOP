package helper;

import data.Article;
import data.Author;
import data.General;
import data.HistoryObject;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import service.CenterAlignedCellFactory;

import java.util.ArrayList;
import java.util.Map;

public class MakeTableView {
    public static String word;
    public static void makeTableNews(TableView<General> tableView){
        clearTable(tableView);
        tableView.setVisible(true);
        TableColumn<General, String> colName = new TableColumn<>("Name");
        TableColumn<General, ArrayList<String>> colAuthor = new TableColumn<>("Author");
        TableColumn<General, Integer> colID = new TableColumn<>("ID");
        colID.setPrefWidth(68);
//        colID.setMaxWidth(5000);
//        colID.setMinWidth(10);
        colName.setPrefWidth(487.20001220703125);
//        colName.setMaxWidth(5000);
//        colName.setMinWidth(10);
        colAuthor.setPrefWidth(114.4000244140625);
//        colAuthor.setMaxWidth(5000);
//        colAuthor.setMinWidth(10);
        tableView.getColumns().addAll(colID,colName,colAuthor);
        colID.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(tableView.getItems().indexOf(cellData.getValue()) + 1).asObject()
        );
        colID.setCellFactory(new CenterAlignedCellFactory<>());
        colName.setCellValueFactory(new PropertyValueFactory<>("generalTitle"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("generalAuthor"));
    }
    public static void makeTableAuthor(TableView<General> tableView){
        clearTable(tableView);
        tableView.setVisible(true);
        TableColumn<General, Integer> colID = new TableColumn<>("ID");
        TableColumn<General, String> colName = new TableColumn<>("Name");
        colID.setPrefWidth(68);
        colName.setPrefWidth(567.1999282836914);
        tableView.getColumns().addAll(colID,colName);
        colID.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(tableView.getItems().indexOf(cellData.getValue()) + 1).asObject()
        );
        colID.setCellFactory(new CenterAlignedCellFactory<>());
        colName.setCellFactory(new CenterAlignedCellFactory<>());
        colName.setCellValueFactory(new PropertyValueFactory<>("generalName"));
    }
    public static void makeTableAuthorNews(TableView<Article> tableView){
        clearTable(tableView);
        tableView.setVisible(true);
        TableColumn<Article, String> colName = new TableColumn<>("Name");
        TableColumn<Article, ArrayList<String>> colDate = new TableColumn<>("Creation Date");
        TableColumn<Article, Integer> colID = new TableColumn<>("ID");
        colID.setPrefWidth(68);
//        colID.setMaxWidth(5000);
//        colID.setMinWidth(10);
        colName.setPrefWidth(487.20001220703125);
//        colName.setMaxWidth(5000);
//        colName.setMinWidth(10);
        colDate.setPrefWidth(114.4000244140625);
//        colAuthor.setMaxWidth(5000);
//        colAuthor.setMinWidth(10);
        TableColumn<Article,String> generalCol=new TableColumn<>("Author's Articles");
        generalCol.getColumns().addAll(colID,colName,colDate);
        tableView.getColumns().add(generalCol);
        colID.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(tableView.getItems().indexOf(cellData.getValue()) + 1).asObject()
        );
        colID.setCellFactory(new CenterAlignedCellFactory<>());
        colName.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
    }
    public static void makeTableAuthorTrending(TableView<Pair<String,String>> tableView){
        clearTable(tableView);
        tableView.setVisible(true);
        TableColumn<Pair<String,String>, Integer> colID = new TableColumn<>("ID");
        TableColumn<Pair<String,String>, String> colName = new TableColumn<>("Name");
        TableColumn<Pair<String,String>, String> colCount=new TableColumn<>("Number of articles contributed");
        colID.setPrefWidth(75);
        colName.setPrefWidth(326.39996337890625);
        colCount.setPrefWidth(267.20009765625);
        tableView.getColumns().addAll(colID,colName,colCount);
        colID.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(tableView.getItems().indexOf(cellData.getValue()) + 1).asObject()
        );
        colID.setCellFactory(new CenterAlignedCellFactory<>());
        colName.setCellFactory(new CenterAlignedCellFactory<>());
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
        colCount.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getValue()));
        colCount.setCellFactory(new CenterAlignedCellFactory<>());
        word=colName.getText();
    }
    public static void makeTableCategoryTrending(TableView<Pair<String,String>> tableView){
        clearTable(tableView);
        tableView.setVisible(true);
        TableColumn<Pair<String,String>, Integer> colID = new TableColumn<>("ID");
        TableColumn<Pair<String,String>, String> colName = new TableColumn<>("Category");
        TableColumn<Pair<String,String>, String> colCount=new TableColumn<>("Number of articles");
        colID.setPrefWidth(75);
        colName.setPrefWidth(326.39996337890625);
        colCount.setPrefWidth(267.20009765625);
        tableView.getColumns().addAll(colID,colName,colCount);
        colID.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(tableView.getItems().indexOf(cellData.getValue()) + 1).asObject()
        );
        colID.setCellFactory(new CenterAlignedCellFactory<>());
        colName.setCellFactory(new CenterAlignedCellFactory<>());
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
        colCount.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getValue()));
        colCount.setCellFactory(new CenterAlignedCellFactory<>());
        word=colName.getText();
    }
    public static void makeTableHashtagTrending(TableView<Pair<String,String>> tableView){
        clearTable(tableView);
        tableView.setVisible(true);
        TableColumn<Pair<String,String>, Integer> colID = new TableColumn<>("ID");
        TableColumn<Pair<String,String>, String> colName = new TableColumn<>("Hashtag");
        TableColumn<Pair<String,String>, String> colCount=new TableColumn<>("Number of occurrences");
        colID.setPrefWidth(75);
        colName.setPrefWidth(326.39996337890625);
        colCount.setPrefWidth(267.20009765625);
        tableView.getColumns().addAll(colID,colName,colCount);
        colID.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(tableView.getItems().indexOf(cellData.getValue()) + 1).asObject()
        );
        colID.setCellFactory(new CenterAlignedCellFactory<>());
        colName.setCellFactory(new CenterAlignedCellFactory<>());
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
        colCount.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getValue()));
        colCount.setCellFactory(new CenterAlignedCellFactory<>());
        word=colName.getText();
    }

    public static void makeTableHistory(TableView<HistoryObject> tableView){
        clearTable(tableView);
        tableView.setVisible(true);
        TableColumn<HistoryObject, String> colDateTime = new TableColumn<>("Date time");
        TableColumn<HistoryObject, String> colName = new TableColumn<>("Item");
        TableColumn<HistoryObject, String> colType = new TableColumn<>("Type");
        colDateTime.setPrefWidth(75);
        colName.setPrefWidth(326.39996337890625);
        tableView.getColumns().addAll(colName, colType, colDateTime);
        colDateTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colName.setCellValueFactory(new PropertyValueFactory<>("item"));
        colType.setCellValueFactory(new PropertyValueFactory<>("typeOfObject"));
        colDateTime.setCellFactory(new CenterAlignedCellFactory<>());
        colDateTime.setCellFactory(new CenterAlignedCellFactory<>());
        colName.setCellFactory(new CenterAlignedCellFactory<>());

    }

    public String getColumnName(String word){
        return word;
    }

    public static void clearTable(TableView<?> tableView) {
        tableView.getItems().clear();
        tableView.getColumns().clear();
    }
}
