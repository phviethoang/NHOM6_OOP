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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SearchAndSort {



//    public static ArrayList<Author> searchAuthor(String keyword, ArrayList<Author> x) {
//        ArrayList<Author> result = new ArrayList<>();
//        for (Author auth : x) {
//            if (auth.getName().toLowerCase().contains(keyword.toLowerCase())) {
//                result.add(auth);
//            }
//        }
//        return result;
//    }



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
