package no.phasfjo.infrastructure.comment;

import no.phasfjo.dto.comment.Comment;

import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public interface CommentDao {

    Comment persist(Comment comment);

    boolean delete(int id);

    boolean update(Comment comment);

    List<Comment> getAll();

    Comment findById(int id);
}
