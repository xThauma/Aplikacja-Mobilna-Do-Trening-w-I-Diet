package kamiltm.project_sm.user;

import java.util.List;

/**
 * Created by Kamil Lenartowicz on 2018-01-17.
 */

public class Content {
    private String title, content;
    private List<Content> items;

    public Content(String title, String content, List<Content> items) {
        this.title = title;
        this.content = content;
        this.items = items;
    }

    public Content(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public List<Content> getItems() {
        return items;
    }

    public void setItems(List<Content> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return title.toString();
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


}
