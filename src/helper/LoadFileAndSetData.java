package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import application.NewsToHome;
import data.Article;
import data.Author;

public class LoadFileAndSetData {

    // A. Functions for loading data

    // A1. Read json file and return string
    public static String read_data(String filename) {
        String result=new String();
        // arr for saving the result
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();
//            String jsonS = jsonBuilder.toString();
//            result = jsonS;
            result=jsonBuilder.toString();
            // Parse JSON array to an array of objects

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    //A2. Divide string into information for objects and return an array of objects
    public static ArrayList<Article> data_array(String filename) {
        String jsonString = read_data(filename);
        ArrayList<Article> arr = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        Article[] articles = gson.fromJson(jsonString, Article[].class);
        for (Article article : articles) {
            arr.add(article);
        }
        return arr;
    }
    public static ArrayList<Author> data_author(String filename) {
        String jsonString = read_data(filename);
        ArrayList<Author> arr = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        Author[] authors = gson.fromJson(jsonString, Author[].class);
        for (Author author : authors) {
            arr.add(author);
        }
        return arr;
    }

    // Test author
//    public static Set<String> displayAuthor(ArrayList<Article> x) {
//        Set<String> auth = new TreeSet<String>();
//        for (Article article : x) {
//            auth.add(article.getAuthor());
//        }
//        return auth;
//    }

    // B. Update Trendings
    public static Map<String, String> popularAuth(ArrayList<Article> Articles) {

        Map<String, Integer> map = new TreeMap<>();
        for(Article article: Articles){
            for(String x : article.getAuthor()){
                if(map.containsKey(x)){
                    int freq=map.get(x);
                    freq++;
                    map.put(x,freq);
                }else map.put(x,1);
            }
        }
        return getDataToShow(map);
//        for (Article article : Articles) {
//            if (map.containsKey(article.getAuthor())) {
//                // Nếu tồn tại, lấy giá trị hiện tại và tăng lên 1 đơn vị
//                int value = map.get(article.getAuthor());
//                map.put(article.getAuthor(), value + 1);
//            } else {
//                // Nếu không tồn tại, thêm key mới vào Map và đặt giá trị là 1
//                map.put(article.getAuthor(), 1);
//            }
//        }
    }

    public static Map<String, String> popularTags(ArrayList<Article> Articles) {
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

    // Thử với category
    public static Map<String, String> popularCategory(ArrayList<Article> Articles) {
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

    // Sắp xếp theo tần suất và trả về 1 danh sách
    public static Map<String, String> getDataToShow(Map<String, Integer> mp) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(mp.entrySet());

        // Sử dụng Comparator để sắp xếp danh sách theo value
        list.sort(new Comparator<>() {
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });

        LinkedHashMap<String, String> sortedMap = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue().toString());
        }

//        for (Entry<String, Integer> x : thu.entrySet()) {
//            System.out.println(x.getKey() + " " + x.getValue());
//        }

//        ArrayList<String> sortedKeys = new ArrayList<>();
//        for (Map.Entry<String, Integer> entry : list) {
//            sortedKeys.add(entry.getKey());
//        }

        return sortedMap;
    }

//	public static ArrayList<Article> data_array1(String filename){
//	       String jsonString = read_data(filename);
//	       ArrayList<Article> arr = new ArrayList<>();
//	        Gson gson = new GsonBuilder().create();
//	       Article[] objects = gson.fromJson(jsonString, Article[].class);
//	               for(Article object : objects){
//	                       arr.add(object);
//	               }
//	           return arr;
//
//	   }
//
//	//B. update giao diện người dùng

// Load file Json lên

//	public static ArrayList<Article> readArticlesFromJson(String filePath) {
//        ArrayList<Article> articles = new ArrayList<>();
//        try (FileReader reader = new FileReader(filePath)) {
//            // Sử dụng Gson để đọc dữ liệu từ tệp JSON và chuyển đổi nó thành một danh sách các đối tượng Article
//            Gson gson = new GsonBuilder().create();
//            java.lang.reflect.Type listType = new TypeToken<ArrayList<Article>>() {}.getType();
//            articles = gson.fromJson(reader, listType);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return articles;
//    }

}
