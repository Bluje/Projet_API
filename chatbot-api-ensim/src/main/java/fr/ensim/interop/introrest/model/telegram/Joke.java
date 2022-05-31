package fr.ensim.interop.introrest.model.telegram;

public class Joke {
    private Integer id;
    private String title;
    private String text;
    private Integer rating;

    public Joke(Integer id, String title, String text, Integer rating) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
