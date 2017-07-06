package no.phasfjo.dto.comment;

import no.phasfjo.constraint.Description;
import no.phasfjo.constraint.UserName;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by phasf on 27.01.2017.
 */

@Entity
@NamedQuery(name = "Comment.getAll", query = "select c from Comment c")
@SequenceGenerator(name = "SEQ_CC", initialValue = 50)
public class Comment {

    public static final String GET_ALL_COMMENTS = "Comment.getAll";

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CC")
    private int id;
    @UserName
    @NotNull
    private String nickname;
    @NotNull
    @Description
    private String content;
    @Min(1)
    @NotNull
    private Integer note;
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Comment() {
    }

    public Comment(String nickname, String content, Integer note, Date postedDate) {
        this.nickname = nickname;
        this.content = content;
        this.note = note;
        this.postedDate = postedDate;
    }

    public Comment(String nickname, String content, Integer note, String postedDate) {
        this.nickname = nickname;
        this.content = content;
        this.note = note;

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            this.postedDate = dateFormat.parse(postedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    // ======================================
    // =          TO STRING                 =
    // ======================================
/*
    @Override
    public String toString() {
        return
                "'" + nickname + '\'' +
                        ", posted='" + content + '\'' +
                        ", @at: " + postedDate;
    }
    */

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", note=" + note +
                ", postedDate=" + postedDate +
                '}';
    }
}
