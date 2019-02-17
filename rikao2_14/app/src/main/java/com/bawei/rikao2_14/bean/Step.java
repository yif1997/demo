package com.bawei.rikao2_14.bean;

/**
 * @author 王艺霏
 * @fileName Step
 * @package com.bawei.rikao2_14.bean
 **/
public class Step {
    private String author_name;
    private String thumbnail_pic_s;
    private String title;

    public Step(String author_name, String thumbnail_pic_s, String title) {
        this.author_name = author_name;
        this.thumbnail_pic_s = thumbnail_pic_s;
        this.title = title;
    }

    public Step() {
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Step{" +
                "author_name='" + author_name + '\'' +
                ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
