import no.phasfjo.dto.image.Image;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.*;

/**
 * Created by phasf on 18.01.2017.
 */
public class Main {

    private static List<Image> images1;
    private static List<String> images;
    private static Map<Integer, Image> imageList;
    private static List<String> imageIndex;

    private static int generated_ID = 0;

    public static void main(String[] args) {

        System.out.println(osCheck());

        for(int i = 0; i < 10; i++){
           System.out.println(randomNumber());
        }

        try {
            populate();
          init();
            getImageTitle();
            getImageIndex();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(timeZone());


    }
    private static String timeZone(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

       return calendar.getTimeZone().getDisplayName();
    }
    private static String osCheck(){
        return  System.getProperty("os.name");
    }

    private static int randomNumber() {
        generated_ID++;
        return generated_ID;
    }

    private static void populate() throws Exception {
        imageList = new HashMap<>();
        Image image0 = new Image("The Gift in You", "iOS app the gift in You", new File("F:\\Java_prosjectX\\WebshopDemo5\\WebshopDemo\\src\\main\\webapp\\images\\image0.jpg"));
        Image image1 = new Image("Tic Tac Toe", "Android app, get three in-a-row", new File("F:\\Java_prosjectX\\WebshopDemo5\\WebshopDemo\\src\\main\\webapp\\images\\image1.jpg"));
        Image image2 = new Image("Kims Play", "Android app, find the correct word", new File("F:\\Java_prosjectX\\WebshopDemo5\\WebshopDemo\\src\\main\\webapp\\images\\image2.jpg"));
        Image image3 = new Image("Pauls elcar", "Android maps app for el cars", new File("F:\\Java_prosjectX\\WebshopDemo5\\WebshopDemo\\src\\main\\webapp\\images\\image3.jpg"));
        Image image4 = new Image("Booking system", "Java EE7 webapp", new File("F:\\Java_prosjectX\\WebshopDemo5\\WebshopDemo\\src\\main\\webapp\\images\\image4.jpg"));
        Image image5 = new Image("Shoe Converter", "iOS app , helps you to get the shoe size", new File("F:\\Java_prosjectX\\WebshopDemo5\\WebshopDemo\\src\\main\\webapp\\images\\image5.jpg"));
        Image image6 = new Image("Post card", "iOS app, create your own postcard", new File("F:\\Java_prosjectX\\WebshopDemo5\\WebshopDemo\\src\\main\\webapp\\images\\image6.jpg"));
        Image image7 = new Image("Dog year converter", "iOS app convert dog to human years", new File("F:\\Java_prosjectX\\WebshopDemo5\\WebshopDemo\\src\\main\\webapp\\images\\image7.jpg"));
        Image image8 = new Image("A school game", "Learn school subjects with unity gaming", new File("F:\\Java_prosjectX\\WebshopDemo5\\WebshopDemo\\src\\main\\webapp\\images\\image8.jpg"));

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
    public static void init() {

        images1 = new ArrayList<>();
        images = new ArrayList<>();
        imageIndex = new ArrayList<>();
        for (int i = 0; i <= imageList.size() - 1; i++) {
            imageIndex.add("image" + i + ".jpg");
            images1.add(imageList.get(i));
        }

    }

    private static List<String> getImageTitle() {
        for (Image v : images1) {
            images.add(v.getName());
        }
        System.out.println(images);
        return images;
    }

    private static List<String> getImageIndex() {
        imageIndex.forEach(System.out::println);
        return imageIndex;
    }


}
