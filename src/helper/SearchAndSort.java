package helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import helper.LoadFileAndSetData;
import data.Article;
import data.Author;
import javafx.scene.control.TextArea;

public class SearchAndSort {
    public static ArrayList<Article> searchForTrends(String keyword, ArrayList<Article> Articles) {
        ArrayList<Article> res = new ArrayList<>();
        for (Article x : Articles) {
            if (x.getAuthor()==null||x.getCategory()==null||x.getTags()==null||keyword==null) continue;
            if (x.getAuthor().contains(keyword) || keyword.equals(x.getCategory())||x.getTags().contains(keyword)) {
                res.add(x);
            }
        }
        return res;
    }


    public static ArrayList<Article> searchArticle(String keyword, ArrayList<Article> x) {
        ArrayList<Article> result = new ArrayList<>();
        for (Article article : x) {
            for(int i=0;i<article.getAuthor().size();i++) {
                if (article.getTitle().toLowerCase().contains(keyword.toLowerCase())||article.getAuthor().get(i).toLowerCase().contains(keyword.toLowerCase())) {
                    result.add(article);
                }
            }
        }
        return result;
    }
    public static ArrayList<Author> searchAuthor(String keyword, ArrayList<Author> x) {
        ArrayList<Author> result = new ArrayList<>();
        for (Author auth : x) {
            if (auth.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(auth);
            }
        }
        return result;
    }
    public static void searchTextArea(TextArea textArea, String searchText){
        String content = textArea.getText().toLowerCase();
        textArea.setStyle("-fx-highlight-fill: lightgray; -fx-highlight-text-fill: firebrick;");
        textArea.deselect();
        int index = content.length();
        while ((index = content.lastIndexOf(searchText.toLowerCase(), index - 1)) >= 0) {
            textArea.selectRange(index, index + searchText.length());
        }

//        int lastIndex = content.length();
//        while (lastIndex >= 0) {
//            lastIndex = content.lastIndexOf(searchText.toLowerCase(), lastIndex);
//            if (lastIndex >= 0) {
//                textArea.selectRange(lastIndex, lastIndex + searchText.length());
//                lastIndex -= 1;
//            }
//        }

//        int index = 0;
//        while (index < content.length() && index >= 0) {
//            index = content.indexOf(searchText.toLowerCase(), index);
//            if (index >= 0) {
//                textArea.selectRange(index, index + searchText.length());
//                index += searchText.length();
//            }
//        }

//        // Tạo một Pattern để tìm kiếm các từ khóa trong nội dung văn bản
//        Pattern pattern = Pattern.compile(searchText.toLowerCase());
//        Matcher matcher = pattern.matcher(content);
//
//        // Duyệt qua tất cả các kết quả khớp và đánh dấu vùng tìm thấy
//        while (matcher.find()) {
//            int start = matcher.start();
//            int end = matcher.end();
//            textArea.selectRange(start, end);
//        }

//        int index = content.indexOf(searchText);
//        if(index >= 0 && !searchText.isEmpty()) {
//            textArea.selectRange(index, index + searchText.length());
//        }
    }
    public static ArrayList<Author> searchForAuthors(String keyword, ArrayList<Author> Authors) {
        ArrayList<Author> res = new ArrayList<>();
        for (Author x : Authors) {
            if (x.getName()==null||keyword==null) continue;
            if (keyword.equals(x.getName())) {
                res.add(x);
            }
        }
        return res;
    }
}
