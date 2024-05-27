package data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryObject {
    private String item;
    private String time;
    private String typeOfObject;
    public HistoryObject(General article)
    {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        item = article.getGeneralTitle();
        this.time = dateTime.format(formatter);
        typeOfObject = "Article";
    }
    public LocalDateTime getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(time, formatter);
    }
    public HistoryObject(Author author)
    {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        item = author.getName();
        this.time = dateTime.format(formatter);
        typeOfObject = "Author";
    }
    public String getItem(){return item;}
    public String getTime(){return time;}
    public String getTypeOfObject(){return typeOfObject;}
}
