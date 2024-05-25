package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helper.LoadFileAndSetData;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class AuthorCabinet extends LoadFileAndSetData{
    private ArrayList<Author> authorBox;
    private String filename = "C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\authorsDigitalTrends.json";

    public ArrayList<Author> getAuthorBox() {
        return authorBox;
    }

    public void setAuthorBox(ArrayList<Author> authorBox) {
        this.authorBox = authorBox;
    }
    public ArrayList<Author> searchAuthor(String keyword, ArrayList<Author> x) {
        ArrayList<Author> result = new ArrayList<>();
        for (Author auth : x) {
            if (auth.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(auth);
            }
        }
        return result;
    }


    public ArrayList<Author> fullAuthorList()
    {
        String jsonString = read_data(filename);
        ArrayList<Author> arr = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        Author[] authors = gson.fromJson(jsonString, Author[].class);
        for (Author author : authors) {
            arr.add(author);
        }
        return arr;
    }

    //
    public Map<String, String> popularAuth(ArrayList<Article> Articles) {

        Map<String, Integer> map = new TreeMap<>();
        for (Article article : Articles) {
            for (String x : article.getAuthor()) {
                if (map.containsKey(x)) {
                    int freq = map.get(x);
                    freq++;
                    map.put(x, freq);
                } else map.put(x, 1);
            }
        }
        return getDataToShow(map);
    }


}
