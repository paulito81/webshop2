package no.phasfjo.infrastructure.comment;

import no.phasfjo.dto.comment.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.comment.Comment.GET_ALL_COMMENTS;

/**
 * Created by phasf on 27.01.2017.
 */
public class JpaCommentDao implements CommentDao {


    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaCommentDao() {
    }

    public JpaCommentDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Comment persist(Comment comment) {
        if (comment == null)
            throw new IllegalArgumentException("No comments could be created!");
        entityManager.persist(comment);
        return comment;
    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            Comment comment = entityManager.find(Comment.class, id);
            entityManager.remove(comment);
            return true;
        }
        throw new IllegalArgumentException(String.format("Comment with id-nr:{%d] could not be deleted =C ", id));

    }

    @Override
    public boolean update(Comment comment) {
        if (comment == null)
            throw new IllegalArgumentException(("No comments were found"));
        if (!entityManager.contains(comment)) {
            entityManager.merge(comment);
        }
        return true;
    }

    @Override
    public List<Comment> getAll() {
        TypedQuery<Comment> query = entityManager.createNamedQuery(GET_ALL_COMMENTS, Comment.class);
        return query.getResultList();
    }

    @Override
    public Comment findById(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("No id was found!");
        return entityManager.find(Comment.class, id);
    }
}
