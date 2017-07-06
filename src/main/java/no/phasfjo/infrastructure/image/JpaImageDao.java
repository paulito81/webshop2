package no.phasfjo.infrastructure.image;

import no.phasfjo.dto.image.Image;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static no.phasfjo.dto.image.Image.GET_ALL_IMAGES;

/**
 * Created by paulhasfjord on 18.01.2017.
 */
@Stateless
public class JpaImageDao implements ImageDao {

    @PersistenceContext(unitName = "WebshopDemo")
    private EntityManager entityManager;

    public JpaImageDao() {
    }

    public JpaImageDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Image persist(Image image) {
        if (image == null)
            throw new IllegalArgumentException("No image could be created!");
        entityManager.persist(image);
        return image;
    }

    @Override
    public Boolean delete(int id) {
        if (id != 0) {
            Image image = entityManager.find(Image.class, id);
            entityManager.remove(image);
            return true;
        }
        throw new IllegalArgumentException(String.format("Account with id-nr:{%d] could not be deleted =C ", id));

    }

    @Override
    public Image findById(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("No id was found!");
        return entityManager.find(Image.class, id);
    }

    @Override
    public boolean update(Image image) {
        if (image == null)
            throw new IllegalArgumentException(("No image were found"));
        if (!entityManager.contains(image)) {
            entityManager.merge(image);
        }
        return true;
    }

    @Override
    public List<Image> getAll() {
        TypedQuery<Image> query = entityManager.createNamedQuery(GET_ALL_IMAGES, Image.class);
        return query.getResultList();
    }
}
