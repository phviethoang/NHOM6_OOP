package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helper.LoadFileAndSetData;
import helper.SearchAndSort;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Cabinet extends LoadFileAndSetData{
    private ArrayList<Article> box;

    private String filename =  "C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\blockchainDigitaltrends (2).json";

    public ArrayList<Article> getBox() {
        return box;
    }

    public void setBox(ArrayList<Article> box) {
        this.box = box;
    }

    public ArrayList<Article> loadData() {
        String jsonString = read_data(filename);
        ArrayList<Article> arr = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        Article[] articles = gson.fromJson(jsonString, Article[].class);
        for (Article article : articles) {
            arr.add(article);
        }
        return arr;
    }

    public Map<String, String> popularTags(ArrayList<Article> Articles) {
        // Thử với tag
        Map<String, Integer> map = new TreeMap<>();

        for (Article article : Articles) {
            for (String x : article.getTags()) {
                if (map.containsKey(x)) {
                    int freq = map.get(x);
                    freq++;
                    map.put(x, freq);
                } else
                    map.put(x, 1);
            }
        }

        return getDataToShow(map);
    }

    public Map<String, String> popularCategory(ArrayList<Article> Articles) {
        // Thử với category
        Map<String, Integer> map = new TreeMap<>();

        for (Article article : Articles) {
            if (map.containsKey(article.getCategory())) {
                // Nếu tồn tại, lấy giá trị hiện tại và tăng lên 1 đơn vị
                int value = map.get(article.getCategory());
                map.put(article.getCategory(), value + 1);
            } else {
                // Nếu không tồn tại, thêm key mới vào Map và đặt giá trị là 1
                map.put(article.getCategory(), 1);
            }
        }
        return getDataToShow(map);
    }
}
