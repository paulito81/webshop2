package no.phasfjo.infrastructure.image;

import no.phasfjo.dto.image.Image;

import java.util.List;

/**
 * Created by paulhasfjord on 18.01.2017.
 */
public interface ImageDao {

    Image persist(Image image);

    Boolean delete(int id);

    Image findById(int id);

    boolean update(Image image);

    List<Image> getAll();
}
