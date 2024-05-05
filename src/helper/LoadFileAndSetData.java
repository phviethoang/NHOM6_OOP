package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

//import application.NewsToHome;
import data.Article;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;

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

    // Test author
    public static Set<String> displayAuthor(ArrayList<Article> x) {
        Set<String> auth = new TreeSet<String>();
        for (Article article : x) {
            auth.add(article.getAuthor());
        }
        return auth;
    }

    // B. Update Trendings
    public static ArrayList<String> popularAuth(ArrayList<Article> Articles) {

        Map<String, Integer> map = new TreeMap<>();

        for (Article article : Articles) {
            if (map.containsKey(article.getAuthor())) {
                // Nếu tồn tại, lấy giá trị hiện tại và tăng lên 1 đơn vị
                int value = map.get(article.getAuthor());
                map.put(article.getAuthor(), value + 1);
            } else {
                // Nếu không tồn tại, thêm key mới vào Map và đặt giá trị là 1
                map.put(article.getAuthor(), 1);
            }
        }
        return getDataToShow(map);
        // Chuyển các entry từ Map thành một danh sách
//		List<Map.Entry<String, Integer>> list = new ArrayList<>(map3.entrySet());
//
//		// Sử dụng Comparator để sắp xếp danh sách theo value
//		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
//			@Override
//			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//				return o1.getValue().compareTo(o2.getValue());
//			}
//		});
//
//		LinkedHashMap<String, Integer> thu = new LinkedHashMap<String, Integer>();
//
//		for (Map.Entry<String, Integer> entry : list) {
//			thu.put(entry.getKey(), entry.getValue());
//		}
//
//		for (Entry<String, Integer> x : thu.entrySet()) {
//			System.out.println(x.getKey() + " " + x.getValue());
//
//		}
//
//		ArrayList<String> sortedKeys = new ArrayList<>();
//		for (Map.Entry<String, Integer> entry : list) {
//			sortedKeys.add(entry.getKey());
//		}
    }

    public static ArrayList<String> popularTags(ArrayList<Article> Articles) {
        // Thử với tag
        Map<String, Integer> mp = new TreeMap<>();

        for (Article article : Articles) {
//					if (map.containsKey(article.getTags())) {
//						// Nếu tồn tại, lấy giá trị hiện tại và tăng lên 1 đơn vị
//						int value = map2.get(article.getTags());
//						map2.put(article.getTags(), value + 1);
//					} else {
//						// Nếu không tồn tại, thêm key mới vào Map và đặt giá trị là 1
//						map2.put(article.getTags(), 1);
//					}

            for (String x : article.getTags()) {
                if (mp.containsKey(x)) {
                    int freq = mp.get(x);
                    freq++;
                    mp.put(x, freq);
                } else
                    mp.put(x, 1);
            }
        }

        return getDataToShow(mp);
    }

    // Thử với category
    public static ArrayList<String> popularCategory(ArrayList<Article> Articles) {
        // Thử với category
        Map<String, Integer> mp = new TreeMap<>();

        for (Article article : Articles) {
            if (mp.containsKey(article.getCategory())) {
                // Nếu tồn tại, lấy giá trị hiện tại và tăng lên 1 đơn vị
                int value = mp.get(article.getCategory());
                mp.put(article.getCategory(), value + 1);
            } else {
                // Nếu không tồn tại, thêm key mới vào Map và đặt giá trị là 1
                mp.put(article.getCategory(), 1);
            }
        }
        return getDataToShow(mp);
    }

    // Sắp xếp theo tần suất và trả về 1 danh sách
    public static ArrayList<String> getDataToShow(Map<String, Integer> mp) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(mp.entrySet());

        // Sử dụng Comparator để sắp xếp danh sách theo value
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return - o1.getValue().compareTo(o2.getValue());
            }
        });

        LinkedHashMap<String, Integer> thu = new LinkedHashMap<String, Integer>();

        for (Map.Entry<String, Integer> entry : list) {
            thu.put(entry.getKey(), entry.getValue());
        }

        for (Entry<String, Integer> x : thu.entrySet()) {
            System.out.println(x.getKey() + " " + x.getValue());

        }

        ArrayList<String> sortedKeys = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedKeys.add(entry.getKey());
        }

        return sortedKeys;
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
