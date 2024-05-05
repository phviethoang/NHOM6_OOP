package data;

import java.util.ArrayList;

import helper.LoadFileAndSetData;
import helper.SearchAndSort;
public class Article {
    private String links;
    private String title;
    private String detailContent;
    private String creationDate;
    private String author;
    private String category;
    private ArrayList<String> tags;
    //getter
    public String getLinks(){
        return links;
    }
    public String getTitle(){
        return title;
    }
    public String getDetailContent(){
        return detailContent;
    }
    public String getCreationDate(){
        return creationDate;
    }
    public String getAuthor(){
        return author;
    }
    public String getCategory(){
        return category;
    }
    public ArrayList<String> getTags(){
        return tags;
    }
   //setter
    public void setLinks(String links){
        this.links=links;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setDetailContent(String detailContent){
        this.detailContent=detailContent;
    }
    public void setCreationDate(String creationDate){
        this.creationDate=creationDate;
    }
    public void setAuthor(String author){
        this.author=author;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    //Phong TH
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (title != null) {
            builder.append("Title: ").append(title).append("\n");
        }
        if (author != null) {
            builder.append("Author: ").append(author).append("\n");
        }
        if (creationDate != null) {
            builder.append("Creation Date: ").append(creationDate).append("\n");
        }
        if (tags != null) {
            builder.append("Tags: ").append(tags).append("\n");
        }
        if (category != null) {
            builder.append("Category: ").append(category).append("\n");
        }
        if (links != null) {
            builder.append("Links: ").append(links).append("\n");
        }
        if (detailContent != null) {
            builder.append("Detail Content: ").append(detailContent).append("\n");
        }
        return builder.toString();
    }
}
