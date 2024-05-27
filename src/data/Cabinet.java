package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helper.LoadFileAndSetData;
import helper.SearchAndSort;

import java.util.*;

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

        for(Article article : arr) {
            for(int i = 0; i < article.getTags().size(); i++) {
                String tmp = article.getTags().get(i);
                article.getTags().set(i, standardTag(tmp));
            }
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
    public ArrayList<Article> searchArticle(String keyword, ArrayList<Article> x) {
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

    public String standardTag(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }
    public void sortByDateDescending () {
        Collections.sort(box, (a1, a2) -> a1.getStandardTime().compareTo(a2.getStandardTime()));
    }

    public void sortByDateAscending () {
        Collections.sort(box, (a1, a2) -> -a1.getStandardTime().compareTo(a2.getStandardTime()));
    }

}
