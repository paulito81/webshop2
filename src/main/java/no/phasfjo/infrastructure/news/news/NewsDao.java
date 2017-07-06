package no.phasfjo.infrastructure.news.news;

import no.phasfjo.dto.news.News;

import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public interface NewsDao {

    News persist(News news);

    boolean delete(int id);

    boolean update(News news);

    List<News> getAll();
}
