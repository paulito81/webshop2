package no.phasfjo.infrastructure.news.news;

import no.phasfjo.dto.news.News;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.news.News.GET_ALL_NEWS_ONE;

/**
 * Created by phasf on 27.01.2017.
 */
@Stateless
public class JpaNewsDao implements NewsDao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaNewsDao() {
    }

    public JpaNewsDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public News persist(News news) {
        if (news == null)
            throw new IllegalArgumentException("No news could be created!");
        entityManager.persist(news);
        return news;
    }

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            News news = entityManager.find(News.class, id);
            entityManager.remove(news);
            return true;
        }
        throw new IllegalArgumentException(String.format("News with id-nr:{%d] could not be deleted =C ", id));

    }

    @Override
    public boolean update(News news) {
        if (news == null)
            throw new IllegalArgumentException(("No news were found"));
        if (!entityManager.contains(news)) {
            entityManager.merge(news);
        }
        return true;
    }

    @Override
    public List<News> getAll() {
        TypedQuery<News> query = entityManager.createNamedQuery(GET_ALL_NEWS_ONE, News.class);
        return query.getResultList();
    }
}
