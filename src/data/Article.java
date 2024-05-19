package data;

import java.util.ArrayList;

public class Article implements General{
    private String links;
    private String title;
    private String detailContent;
    private String creationDate;
    private String summary;
    private ArrayList<String> author;
    private String authorView;
    private String category;
    private ArrayList<String> tags;
//    public Article(String author) {
//        this.authorView = author;
//    }

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

    public String getSummary() {
        return summary;
    }

    @Override
    public String getGeneralName() {
        return null;
    }
    @Override
    public String getGeneralAuthor(){
        return String.join(", ", author);
    }
    @Override
    public String getGeneralTitle(){return title;}

    public ArrayList<String> getAuthor(){
        return author;
    }
    public String getAuthorView(){
        return String.join( ", ", author);
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

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setAuthor(ArrayList<String> author){
        this.author=author;
    }

    public void setAuthorView(String authorView) {
        this.authorView = authorView;
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
