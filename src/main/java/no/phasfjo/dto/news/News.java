package no.phasfjo.dto.news;

import no.phasfjo.constraint.Description;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import static no.phasfjo.dto.news.News.GET_ALL_NEWS_ONE;

/**
 * Created by phasf on 27.01.2017.
 */
@Entity
@NamedQuery(name = GET_ALL_NEWS_ONE, query = "select n from News n")
public class News {


    public static final String GET_ALL_NEWS_ONE = "News.getAll";

    // ======================================
    // =             Attributes             =
    // ======================================

    @EmbeddedId
    @NotNull
    private NewsId id;
    @Description
    private String content;

    // ======================================
    // =            Constructors            =
    // ======================================

    public News() {

    }

    public News(NewsId id, String content) {
        this.id = id;
        this.content = content;
    }


    // ======================================
    // =            GET AND SET            =
    // ======================================


    public NewsId getId() {
        return id;
    }

    public void setId(NewsId id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "title=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
