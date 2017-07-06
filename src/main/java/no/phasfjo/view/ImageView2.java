package no.phasfjo.view;

import no.phasfjo.dto.image.Image;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by paulhasfjord on 18.01.2017.
 */
@Model
public class ImageView2 {

    private List<String> imageTitle;
    private List<String> imageIndex;
    private Map<Integer, Image> imageList;

    private void populate() throws Exception {
        imageList = new HashMap<>();
        Image image0 = new Image("The Gift in You", "iOS app the gift in You",new File("images/image0.jpg"));
        Image image1 = new Image("Tic Tac Toe", "Android app, get three in-a-row", new File("images/image1.jpg"));
        Image image2 = new Image("Kims Play", "Android app, find the correct word", new File("images/image2.jpg"));
        Image image3 = new Image("Pauls elcar", "Android maps app for el cars", new File("images/image3.jpg"));
        Image image4 = new Image("Booking system", "Java EE7 webapp", new File("images/image4.jpg"));
        Image image5 = new Image("Shoe Converter", "iOS app , helps you to get the shoe size", new File("images/image5.jpg"));
        Image image6 = new Image("Post card", "iOS app, create your own postcard", new File("images/image6.jpg"));
        Image image7 = new Image("Dog year converter", "iOS app convert dog to human years", new File("images/image7.jpg"));
        Image image8 = new Image("A school game", "Learn school subjects with unity gaming", new File("images/image8.jpg"));

        imageList.put(0, image0);
        imageList.put(1, image1);
        imageList.put(2, image2);
        imageList.put(3, image3);
        imageList.put(4, image4);
        imageList.put(5, image5);
        imageList.put(6, image6);
        imageList.put(7, image7);
        imageList.put(8, image8);
    }

    @PostConstruct
    public void init() {

        try {
            populate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Image> images1 = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imageIndex = new ArrayList<>();
        for (int i = 0; i <= imageList.size() - 1; i++) {
            imageIndex.add("image" +i +".jpg");
            images1.add(imageList.get(i));

        }
        for(Image v : images1) {
            imageTitle.add(v.getName());
        }
    }

    public List<String> getImageTitle() {
        return imageTitle;
    }
    public List<String> getImageIndex(){
        return imageIndex;
    }
}
