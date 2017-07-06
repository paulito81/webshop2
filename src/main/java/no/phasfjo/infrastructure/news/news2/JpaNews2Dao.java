package no.phasfjo.infrastructure.news.news2;

import no.phasfjo.dto.news.News2;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.news.News2.GET_ALL_NEWS_TWO;

/**
 * Created by phasf on 27.01.2017.
 */
@Stateless
public class JpaNews2Dao implements News2Dao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaNews2Dao() {
    }

    public JpaNews2Dao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public News2 persist(News2 news) {
        if (news == null)
            throw new IllegalArgumentException("No news could be created!");
        entityManager.persist(news);
        return news;
    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            News2 news = entityManager.find(News2.class, id);
            entityManager.remove(news);
            return true;
        }
        throw new IllegalArgumentException(String.format("News with id-nr:{%d] could not be deleted =C ", id));

    }

    @Override
    public boolean update(News2 news) {
        if (news == null)
            throw new IllegalArgumentException(("No news were found"));
        if (!entityManager.contains(news)) {
            entityManager.merge(news);
        }
        return true;
    }

    @Override
    public List<News2> getAll() {
        TypedQuery<News2> query = entityManager.createNamedQuery(GET_ALL_NEWS_TWO, News2.class);
        return query.getResultList();
    }

    @Override
    public News2 findById(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("No id was found!");
        return entityManager.find(News2.class, id);
    }
}
