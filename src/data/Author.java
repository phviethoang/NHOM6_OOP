package data;

import java.util.ArrayList;

public class Author implements General {
    private String link;
    private String name;
    private String workingAs;
    private String detail;

    public String getDetail() {
        return detail;
    }

    public String getJob() {
        return workingAs;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getGeneralName() {
        return name;
    }

    @Override
    public String getGeneralAuthor() {
        return null;
    }

    @Override
    public String getGeneralTags() {
        return null;
    }

    @Override
    public String getGeneralCategory() {
        return null;
    }

    @Override
    public String getGeneralTitle() {
        return null;
    }



    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setJob(String workingAs) {
        this.workingAs = workingAs;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }
}
