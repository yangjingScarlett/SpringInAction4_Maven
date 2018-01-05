package a_autoConfig;

import org.springframework.stereotype.Component;

/**
 * Created by yangjing on 2018/1/2
 */
@Component
public class SgtPeppersDisk implements CompactDisk {

    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("通过a_autoConfig显示：Playing " + title + " by " + artist);
    }

}
