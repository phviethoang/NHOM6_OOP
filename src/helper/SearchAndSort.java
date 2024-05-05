package helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Normalizer;
import java.util.ArrayList;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import helper.LoadFileAndSetData;
import data.Article;

public class SearchAndSort {
    public static ArrayList<Article> searchForTrends(String keyword, ArrayList<Article> Articles) {
        ArrayList<Article> res = new ArrayList<>();
        for (Article x : Articles) {
            if (x.getAuthor()==null||x.getCategory()==null||x.getTags()==null||keyword==null) continue;
            if (keyword.equals(x.getAuthor()) || keyword.equals(x.getCategory())||x.getTags().contains(keyword)) {
                res.add(x);
            }
        }
        return res;
    }


    public static ArrayList<Article> searchArticle(String keyword, ArrayList<Article> x) {
        ArrayList<Article> result = new ArrayList<>();
        for (Article article : x) {
            if (article.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    article.getAuthor().toLowerCase().contains(keyword.toLowerCase())||
                    article.getDetailContent().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(article);
            }
        }
        return result;
    }

}
