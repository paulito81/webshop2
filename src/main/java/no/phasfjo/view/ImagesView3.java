package no.phasfjo.view;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phasf on 25.01.2017.
 */
@Model
public class ImagesView3 {
    private List<String> images;

    @PostConstruct
    public void init() {
        images = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            images.add("login"+ i + ".png");
        }
    }

    public List<String> getImages() {
        return images;
    }
}
