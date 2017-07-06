package no.phasfjo.infrastructure.news.news2;

import no.phasfjo.dto.news.News2;

import java.util.List;

/**
 * Created by phasf on 27.01.2017.
 */
public interface News2Dao {

    News2 persist(News2 news);

    boolean delete(int id);

    boolean update(News2 news);

    List<News2> getAll();

    News2 findById(int id);
}
