package test.oopnhom6;

import javafx.collections.ObservableList;
import javafx.util.Pair;

public class PrevState {
    private String col1;
    private String col2;
    private ObservableList<Pair<String,String>> observableList;
    public PrevState(String col1, String col2, ObservableList<Pair<String,String>> observableList){
        this.col1=col1;
        this.col2=col2;
        this.observableList=observableList;
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
