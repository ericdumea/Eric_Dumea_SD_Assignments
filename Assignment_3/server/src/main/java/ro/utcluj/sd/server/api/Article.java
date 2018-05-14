package ro.utcluj.sd.server.api;

public class Article {

    private String writer;
    private String title;
    private String content;
    private String abstractt;
    private String[] related_titles;
    private String image;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
       // this.abstractt = content.substring(0,20) + " ...";
    }

    public Article(String writer, String title) {
        this.writer = writer;
        this.title = title;
    }

    public Article(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Article{" +
                "writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getAbstractt() {
        return abstractt;
    }

    public void setAbstractt(String abstractt) {
        this.abstractt = abstractt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getRelated_titles() {
        return related_titles;
    }

    public void setRelated_titles(String[] related_titles) {
        this.related_titles = related_titles;
    }
}
