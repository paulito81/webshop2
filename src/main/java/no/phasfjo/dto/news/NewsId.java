package no.phasfjo.dto.news;

import no.phasfjo.constraint.Description;
import no.phasfjo.constraint.Title;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by phasf on 27.01.2017.
 */
@Embeddable
public class NewsId implements Serializable{
    // ======================================
    // =             Attributes             =
    // ======================================

    @NotNull
    @Title
    private String title;
    @NotNull
    @Description
    private String language;

    // ======================================
    // =            Constructors            =
    // ======================================

    public NewsId() {
    }

    public NewsId(String title, String language) {
        this.title = title;
        this.language = language;
    }

    // ======================================
    // =            GET AND SET            =
    // ======================================

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    // ======================================
    // =           Hash , equals, toString  =
    // ======================================


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsId newsId = (NewsId) o;

        return (title != null ? title.equals(newsId.title) : newsId.title == null) && (language != null ? language.equals(newsId.language) : newsId.language == null);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NewsId{" +
                "title='" + title + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
