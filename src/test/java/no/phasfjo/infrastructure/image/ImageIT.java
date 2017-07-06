package no.phasfjo.infrastructure.image;

import no.phasfjo.dto.image.Image;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

/**
 * Created by paulhasfjord on 18.01.2017.
 */
public class ImageIT {

    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private JpaImageDao jpaImageDao;

    private String filePathWin = "src\\main\\webapp\\images\\image0.jpg";
    private String filePathWin2 = "src\\main\\webapp\\images\\image1.jpg";
    private String filePathOsX = "images/image0.jpg";
    private String filePath1 = "images/image1.jpg";
    private String systemFilePath ;
    private String systemFilePath2 ;

    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory("TEST");
        entityManager = factory.createEntityManager();
        jpaImageDao = new JpaImageDao(entityManager);
        systemFilePath = "";
        systemFilePath2 = "";
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        factory.close();
    }

    private void imagePreviewTester(File picture) throws Exception {
        JFrame editorFrame = new JFrame("Image Tested successfully!");

        editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BufferedImage image = null;
        try {
            System.out.println(picture.getAbsolutePath());
            image = ImageIO.read(new File(picture.getAbsolutePath()));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

        editorFrame.pack();
        editorFrame.setLocationRelativeTo(null);
        editorFrame.setVisible(true);
        sleep(1000);

    }

    private String systemOSTest(String filePath) throws Exception {
        String osX = System.getProperty("os.name");

        switch (osX) {
            case "Windows 7":
                System.out.println("Windows 7 found: ");
                osX = filePath.replaceAll("/", File.separator);
                break;
            case "Windows 8":
                System.out.println("Windows 8 found: ");
                osX = filePath.replaceAll("/", File.separator);
                break;
            case "Windows 10":
                System.out.println("Windows 10 found: ");
                osX = filePath.replaceAll("/", File.separator);
                break;
            case "Mac OS X":
                System.out.println("OSX found: ");
                osX =  filePath.replaceAll("\\\\", File.separator);
                break;
            default:
                throw new IllegalArgumentException("A unrecognised system detected");
        }
        return osX;
    }

    @Test
    public void persistNewImageTestWithWinFilePath() throws Exception {
        systemFilePath = systemOSTest(filePathWin);
        Image newImage = new Image("The gift within", "An Android program helping people to find there gift in life.", new File(systemFilePath));


        entityManager.getTransaction().begin();
        Image persistImage = jpaImageDao.persist(newImage);
        entityManager.getTransaction().commit();

        assertTrue(persistImage.getId() > 0);
        assertEquals(50, persistImage.getId());
        assertEquals(systemFilePath, persistImage.getPicture().toString());
        assertFalse(persistImage.getPicture().getAbsolutePath().isEmpty());

        /*
        PICTURE TEST
         */
        imagePreviewTester(persistImage.getPicture());

    }

    @Test
    public void createANewPicureAndUpdatePicture() throws Exception {
        systemFilePath = systemOSTest(filePathWin);

        Image newImage = new Image("The gift within", "An Android program helping people to find there gift in life.", new File(systemFilePath));
        entityManager.getTransaction().begin();
        Image image = jpaImageDao.persist(newImage);
        entityManager.getTransaction().commit();
        imagePreviewTester(newImage.getPicture());

        Image updateAImage = jpaImageDao.findById(50);
        String aNewImage = systemFilePath;

        updateAImage.setPicture(new File(aNewImage));
        entityManager.getTransaction().begin();
        jpaImageDao.update(updateAImage);
        entityManager.getTransaction().commit();

        assertEquals(image.getPicture().getAbsolutePath(), updateAImage.getPicture().getAbsolutePath());
        imagePreviewTester(updateAImage.getPicture());
    }

    @Test
    public void createTwoPicturesAndDeleteOne() throws Exception {
        systemFilePath = systemOSTest(filePathWin);
        systemFilePath2 = systemOSTest(filePathWin2);

        Image newImage = new Image("The gift within", "An Android program helping people to find there gift in life.", new File(systemFilePath));
        Image newImage2 = new Image("Tic Tac Toe", "A game where you want to get three-in-a-row", new File(systemFilePath2));

        entityManager.getTransaction().begin();
        jpaImageDao.persist(newImage);
        jpaImageDao.persist(newImage2);
        entityManager.getTransaction().commit();


        Image findByID1 = jpaImageDao.findById(50);
        Image findByID2 = jpaImageDao.findById(51);

        assertEquals(50, findByID1.getId());
        assertEquals(51, findByID2.getId());
        assertNotEquals(findByID1, findByID2);
        assertTrue(findByID1.getPicture().exists());
        assertTrue(findByID2.getPicture().exists());

        assertNotNull(findByID1);
        assertNotNull(findByID2);
        // Picturetest
        imagePreviewTester(findByID1.getPicture());
        imagePreviewTester(findByID2.getPicture());

        entityManager.getTransaction().begin();
        boolean deleteImage = jpaImageDao.delete(findByID1.getId());
        entityManager.getTransaction().commit();

        assertTrue(deleteImage);
        assertEquals(null, jpaImageDao.findById(50));
        assertEquals(51, findByID2.getId());
    }

    @Test
    public void createTwoNewImagesAndGetAllTest() throws Exception {
        systemFilePath = systemOSTest(filePathWin);
        systemFilePath2 = systemOSTest(filePathWin2);
        List<Image> testList = new ArrayList<>();

        Image newImage = new Image("The gift within", "An Android program helping people to find there gift in life.", new File(systemFilePath));
        Image newImage2 = new Image("Tic Tac Toe", "A game where you want to get three-in-a-row", new File(systemFilePath2));

        entityManager.getTransaction().begin();
        jpaImageDao.persist(newImage);
        jpaImageDao.persist(newImage2);
        entityManager.getTransaction().commit();

        testList.add(newImage);
        testList.add(newImage2);

        List<Image> listFromMemory = jpaImageDao.getAll();

        assertEquals(testList.size(), listFromMemory.size());
    }

    @Test
    public void createANewImageAndUseFindByIdToGetThatImage() throws Exception {
        systemFilePath = systemOSTest(filePathWin);
        Image newImage = new Image("The gift within", "An Android program helping people to find there gift in life.", new File(systemFilePath));

        entityManager.getTransaction().begin();
        jpaImageDao.persist(newImage);
        entityManager.getTransaction().commit();

        Image findById = jpaImageDao.findById(newImage.getId());

        assertEquals(50, findById.getId());
        assertTrue(findById.getPicture().exists());
    }
}
