package test.oopnhom6;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import data.*;
import helper.LoadFileAndSetData;
import helper.SearchAndSort;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerNewsDetail extends ControllerButton implements Initializable {
    @FXML
    Button homeButton;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField searchText;
    @FXML
    private TextFlow textFlow;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    Button backButton;
    @FXML
    Button newsButton;
    @FXML
    Button authorButton;
    @FXML
    Button searchButton;
    private int currentRedIndex=0;
    String word=new String();
    Cabinet c=new Cabinet();
    String milestone=new String();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final String[] previousKeyword = {""};
        makeHighlight();
        searchText.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String keyword = searchText.getText().toLowerCase();
                // Kiểm tra nếu keyword hiện tại khác với keyword trước đó
                if (!keyword.equals(previousKeyword[0])) {
                    currentRedIndex = 0; // Reset currentRedIndex về giá trị ban đầu
                    previousKeyword[0] = keyword; // Cập nhật keyword trước đó
                }
                highlightKeyword(keyword); // Gọi hàm highlightKeyword
                currentRedIndex++;
            }
        });
    }
    private void highlightKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return;
        }

        // Lấy nội dung văn bản từ TextFlow trước khi clear các children
        StringBuilder textBuilder = new StringBuilder();
        for (Node node : textFlow.getChildren()) {
            if (node instanceof Text) {
                textBuilder.append(((Text) node).getText());
            } else if (node instanceof Hyperlink) {
                textBuilder.append(((Hyperlink) node).getText());
                word=((Hyperlink)node).getText();
            }
        }
        String text = textBuilder.toString();

        textFlow.getChildren().clear();

        int lastIndex = 0;
        int index = text.toLowerCase().indexOf(keyword.toLowerCase());
        int keywordCount = 0;
        boolean foundRedKeyword = false;

        while (index >= 0) {
            if (lastIndex < index) {
                String preKeywordText = text.substring(lastIndex, index);
                addTextNodes(preKeywordText,word);
            }

            String highlightText = text.substring(index, index + keyword.length());
            Text highlight = new Text(highlightText);

            if (!foundRedKeyword && keywordCount == currentRedIndex) {
                highlight.setFill(Color.RED);
                foundRedKeyword = true;
                final int scrollToIndex = textFlow.getChildren().size();

                PauseTransition pause = new PauseTransition(Duration.millis(100));
                pause.setOnFinished(event -> {
                    if (scrollToIndex < textFlow.getChildren().size()) {
                        Node targetNode = textFlow.getChildren().get(scrollToIndex);
                        scrollPane.layout();
                        scrollPane.setVvalue(targetNode.getBoundsInParent().getMinY() / textFlow.getBoundsInParent().getHeight());
                    }
                });
                pause.play();
            } else {
                highlight.setFill(Color.ORANGE);
            }

            textFlow.getChildren().add(highlight);

            lastIndex = index + keyword.length();
            index = text.toLowerCase().indexOf(keyword.toLowerCase(), lastIndex);
            keywordCount++;
        }

        if (lastIndex < text.length()) {
            String postKeywordText = text.substring(lastIndex);
            addTextNodes(postKeywordText,word);
        }

        if (currentRedIndex >= keywordCount) {
            currentRedIndex = -1;
        }
    }

    private void addTextNodes(String text,String s) {
        int hyperlinkStart = text.indexOf(s);
        if (hyperlinkStart == -1) {
            textFlow.getChildren().add(new Text(text));
        } else {
            String preHyperlinkText = text.substring(0, hyperlinkStart);
            String postHyperlinkText = text.substring(hyperlinkStart + s.length());
            textFlow.getChildren().add(new Text(preHyperlinkText));
            textFlow.getChildren().add(linkToAuthor(s));
            textFlow.getChildren().add(new Text(postHyperlinkText));
        }
    }
    private Hyperlink linkToAuthor(String s){
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
            AuthorCabinet ac = new AuthorCabinet();
            ArrayList<Author> author = ac.searchAuthor(s, ac.fullAuthorList());
            HistoryObject historyObject = new HistoryObject(author.getFirst());
            ControllerHistory controllerHistory1 = loaderHistory1.getController();
            controllerHistory1.setTableHistory(historyObject);
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
    private Hyperlink linkArticleToAuthor(Article article) throws IOException{
        String s=article.getAuthorView();
        return linkToAuthor(s);
    }


    public void setDetail(Article a)throws IOException{
        milestone=a.getTitle();
        Text textTitle=new Text("     Title: "+a.getTitle()+"\n");
        Text textAuthor=new Text("     Author: ");
        Text textDate=new Text("\n     Creation Date: "+a.getCreationDate()+"\n");
        Text textSummary=new Text("     Summary: "+a.getSummary()+"\n");
        String detailWithSpaces=a.getDetailContent().replaceAll("\n","\n     ");
        Text textContent=new Text(detailWithSpaces);
        Text text=new Text("\n\n");
        Text textTag=new Text("     Hashtag: "+a.getTags());
        textFlow.getChildren().addAll(textTitle,textAuthor,linkArticleToAuthor(a),textDate,textSummary,textContent,text,textTag);
    }
    @FXML
    void getArticleToLaunch(ActionEvent event) throws IOException {
        searchText.clear();
        c.setBox(c.loadData());
        if(c.getBox()!=null){
            for(Article article: c.getBox()){
                if(milestone.equalsIgnoreCase(article.getTitle())){
                    textFlow.getChildren().clear();
                    setDetail(article);
                    break;
                }
            }
        } else System.out.println(-1);
    }
}
