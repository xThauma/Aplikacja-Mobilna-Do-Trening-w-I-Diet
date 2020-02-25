package kamiltm.project_sm.home;

import java.util.GregorianCalendar;

/**
 * Created by Kamil Lenartowicz on 2017-12-27.
 */

public class Post {

    private Long id;
    private String title;
    private GregorianCalendar date;
    private String content;
    private Long id_users;

    public Post(Long id, String title, GregorianCalendar date, String content, Long id_users) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
        this.id_users = id_users;
    }

    public Long getId_users() {
        return id_users;
    }

    public void setId_users(Long id_users) {
        this.id_users = id_users;
    }

    public Post(String title, GregorianCalendar date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
