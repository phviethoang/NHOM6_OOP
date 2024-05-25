package test.oopnhom6;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import data.*;
import helper.LoadFileAndSetData;
import helper.SearchAndSort;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class ControllerNewsDetail extends ControllerButton implements Initializable {
    @FXML
    Button homeButton;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField searchField;
    @FXML
    private TextFlow textFlow=new TextFlow();
    @FXML
    Button backButton;
    @FXML
    Button newsButton;
    @FXML
    Button authorButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeHighlight();
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String keyword = searchField.getText().toLowerCase();
                highlightKeywords(keyword);
                System.out.println("Tach mon");
            }
        });
    }

    @FXML
    void back(ActionEvent event) {
    }
//    @FXML
//    void goHome(ActionEvent event) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
//        Scene scene = new Scene(root);
//        Stage primaryStage = (Stage)homeButton.getScene().getWindow();
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//    @FXML
//    void showNews(ActionEvent event) {
//       try {
//           FXMLLoader loader = new FXMLLoader(getClass().getResource("homeScene.fxml"));          // Nạp root trước khi load controller
//           Parent root = loader.load();
//           ControllerGUI ctl = loader.getController();
//           Scene scene = new Scene(root);
//           Stage stage = (Stage) newsButton.getScene().getWindow();
//           stage.setScene(scene);
//           ctl.showNews(event);
//           stage.show();
//       }catch (Exception e){
//           System.out.println(e.getMessage());
//       }
//
//    }
//    void showAuthors(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("homeScene.fxml"));          // Nạp root trước khi load controller
//            Parent root = loader.load();
//            ControllerGUI ctl = loader.getController();
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) authorButton.getScene().getWindow();
//            stage.setScene(scene);
//            ctl.showAuthors(event);
//            stage.show();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }

    private Hyperlink linkToAuthor(Article article) throws IOException{
        String s=article.getAuthorView();
        Hyperlink link=new Hyperlink(s);
        link.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailAuthor.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            ControllerAuthorDetail ctl = loader.getController();
            FXMLLoader loaderHistory1 = new FXMLLoader(getClass().getResource("historyScene.fxml"));
            try {
                loaderHistory1.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            HistoryObject historyObject = new HistoryObject(article);
            ControllerHistory controllerHistory1 = loaderHistory1.getController();
            controllerHistory1.setTableHistory(historyObject);

            AuthorCabinet ac = new AuthorCabinet();
            ArrayList<Author> author = ac.searchAuthor(s, ac.fullAuthorList());
            ctl.setLabel(author.getFirst().getName());
            ctl.displayDetailAuthor(author.getFirst());
            ctl.showAuthorArticle();
            Scene scene = new Scene(root);
            Stage stage = (Stage) authorButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        });
        return link;
    }
    public void setDetail(Article a)throws IOException{
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.appendText("     Title: "+a.getTitle()+"\n");
        textArea.appendText("     Author: "+ linkToAuthor(a)+"\n");
        textArea.appendText("     Creation Date: "+a.getCreationDate()+"\n");
        textArea.appendText("     Summary: "+a.getSummary()+"\n");
        String detailWithSpaces=a.getDetailContent().replaceAll("\n","\n     ");
        textArea.appendText(detailWithSpaces);
        textArea.appendText("\n\n");
        textArea.appendText("     Hashtag: "+a.getTags());
    }
    private void highlightKeywords(String keyword) {
        String text = textArea.getText().toLowerCase();
        textFlow.getChildren().clear();

        if (keyword.isEmpty()) {
            textFlow.getChildren().add(new Text(text));
            return;
        }

        int lastIndex = 0;
        int index = text.indexOf(keyword);

        while (index >= 0) {
            if (lastIndex < index) {
                textFlow.getChildren().add(new Text(text.substring(lastIndex, index)));
            }

            Text highlightText = new Text(text.substring(index, index + keyword.length()));
            highlightText.setFill(Color.YELLOW);
            textFlow.getChildren().add(highlightText);

            lastIndex = index + keyword.length();
            index = text.indexOf(keyword, lastIndex);
            System.out.println("ngu");
            //Vãi nhái sa
            //o /l/ai]]] t test chay mai khong duoc cai hieu ung
        }

        if (lastIndex < text.length()) {
            textFlow.getChildren().add(new Text(text.substring(lastIndex)));
        }
    }
//    void searchKeyword(){
//        try {
//            if(textArea.getText()!=null){
//                searchField.setOnKeyPressed(event -> {
//                    if (event.getCode() == KeyCode.ENTER) {
//                        String keyword = searchField.getText().toLowerCase();
//                        highlightKeywords(textArea, keyword, textFlow);
//                    }
//                });
//
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    private void highlightKeywords(TextArea textArea, String keyword, TextFlow textFlow) {
//        String text = textArea.getText().toLowerCase();
//        textFlow.getChildren().clear();
//        if (keyword.isEmpty()) {
//            textFlow.getChildren().add(new Text(text));
//            return;
//        }
//        int index = text.indexOf(keyword);
//        int lastIndex = 0;
//        while (index >= 0) {
//            if (lastIndex < index) {
//                textFlow.getChildren().add(new Text(text.substring(lastIndex, index)));
//            }
//            Text highlightText = new Text(keyword);
//            highlightText.setFill(Color.YELLOW);
//            textFlow.getChildren().add(highlightText);
//            lastIndex = index + keyword.length();
//            index = text.indexOf(keyword, lastIndex);
//        }
//        if (lastIndex < text.length()) {
//            textFlow.getChildren().add(new Text(text.substring(lastIndex)));
//        }
//    }
}
