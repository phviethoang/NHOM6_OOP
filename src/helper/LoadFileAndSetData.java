package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import application.NewsToHome;
import data.Article;
import data.Author;
import javafx.scene.Scene;

public abstract class LoadFileAndSetData {
    public static Stack<Scene> prevScene = new Stack();

    // A. Functions for loading data

    // A1. Read json file and return string
    public String read_data(String filename) {
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
    // B. Update Trendings
    // Sắp xếp theo tần suất và trả về 1 danh sách
    public Map<String, String> getDataToShow(Map<String, Integer> mp) {
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
}
