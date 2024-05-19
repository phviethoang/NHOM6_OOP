package data;

import helper.LoadFileAndSetData;
import helper.SearchAndSort;

import java.util.ArrayList;

public class Cabinet {
    private ArrayList<Article> box;

    public ArrayList<Article> getBox() {
        return box;
    }

    public void setBox(ArrayList<Article> box) {
        this.box = box;
    }

    public ArrayList<Article> loadData() {
        return LoadFileAndSetData.data_array("C:\\Users\\Dell\\Desktop\\OOPnhom6\\json\\blockchainDigitaltrends (2).json");
    }
}
