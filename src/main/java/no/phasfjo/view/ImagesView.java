package no.phasfjo.view;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulhasfjord on 12.01.2017.
 */
@Model
public class ImagesView {
    private List<String> images;

    @PostConstruct
    public void init() {
        images = new ArrayList<>();
        for (int i = 0; i <= 7; i++) {
            images.add("image"+ i + ".jpg");
        }
    }

    public List<String> getImages() {
        return images;
    }
}
