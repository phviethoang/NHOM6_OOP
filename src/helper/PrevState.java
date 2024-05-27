package helper;

import javafx.collections.ObservableList;
import javafx.util.Pair;

public class PrevState {
    private String titleOfTable;
    private String col0;
    private String col1;
    private String col2;
    private ObservableList<Pair<String,String>> observableList;
    public PrevState(String titleOfTable, String col0, String col1, String col2, ObservableList<Pair<String,String>> observableList){
        this.titleOfTable = titleOfTable;
        this.col0 = col0;
        this.col1=col1;
        this.col2=col2;
        this.observableList=observableList;
    }

    public String getTitleOfTable() {
        return titleOfTable;
    }

    public String getCol0() {
        return  col0;
    }
    public String getCol1() {
        return col1;
    }

    public String getCol2() {
        return col2;
    }

    public ObservableList<Pair<String, String>> getObservableList() {
        return observableList;
    }

}
