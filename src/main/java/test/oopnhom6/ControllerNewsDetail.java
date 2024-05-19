package test.oopnhom6;
import java.io.IOException;

import data.Article;
import helper.SearchAndSort;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class ControllerNewsDetail extends ControllerGUI {
    @FXML
    Button homeButton;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField searchField;
    private TextFlow textFlow=new TextFlow();
    @FXML
    Button backButton;
    @FXML
    Button newsButton;
    @FXML
    Button authorButton;
    @FXML
    void goHome(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage)homeButton.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @FXML
    void showNews(ActionEvent event) {
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("homeScene.fxml"));          // Nạp root trước khi load controller
           Parent root = loader.load();
           ControllerGUI ctl = loader.getController();
           Scene scene = new Scene(root);
           Stage stage = (Stage) newsButton.getScene().getWindow();
           stage.setScene(scene);
           ctl.showNews(event);
           stage.show();
       }catch (Exception e){
           System.out.println(e.getMessage());
       }

    }
    void showAuthors(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homeScene.fxml"));          // Nạp root trước khi load controller
            Parent root = loader.load();
            ControllerGUI ctl = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = (Stage) authorButton.getScene().getWindow();
            stage.setScene(scene);
            ctl.showAuthors(event);
            stage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void searchKeyword(KeyEvent event){
        try {
            if(textArea.getText()!=null){
                String searchText=searchField.getText().toLowerCase();
                if(searchText!=null&&!searchText.isEmpty()){
                    SearchAndSort.searchTextArea(textArea,searchText);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setDetail(Article a) {
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.appendText("     Title: "+a.getTitle()+"\n");
        textArea.appendText("     Author: "+a.getAuthorView()+"\n");
        textArea.appendText("     Creation Date: "+a.getCreationDate()+"\n");
        textArea.appendText("     Summary: "+a.getSummary()+"\n");
        String detailWithSpaces=a.getDetailContent().replaceAll("\n","\n     ");
        textArea.appendText(detailWithSpaces);
        textArea.appendText("\n\n");
        textArea.appendText("     Hashtag: "+a.getTags());
    }
}
