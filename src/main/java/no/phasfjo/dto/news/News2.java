package no.phasfjo.dto.news;

import no.phasfjo.constraint.Description;
import no.phasfjo.dto.comment.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static no.phasfjo.dto.news.News2.GET_ALL_NEWS_TWO;

/**
 * Created by phasf on 27.01.2017.
 */

@Entity
@NamedQuery(name = GET_ALL_NEWS_TWO, query = "select n from News2 n")
@SequenceGenerator(name = "SEQ_N2", initialValue = 50)
public class News2 {

    public static final String GET_ALL_NEWS_TWO = "News2.getAll";

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_N2")
    private int id;

    @NotNull
    @Description
    private String content;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @OrderBy("postedDate DESC")
    private List<Comment> comments;

    // ======================================
    // =            Constructors            =
    // ======================================

    public News2() {

    }

    public News2(String content) {
        this.content = content;
    }

    public News2(String content, List<Comment> comments) {
        this.content = content;
        this.comments = comments;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        if (comments == null)
            comments = new ArrayList<>();
        comments.add(comment);
    }


    // ======================================
    // =          Getters & Setters         =
    // ======================================

    @Override
    public String toString() {
        return "News2{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                '}';
    }
}
