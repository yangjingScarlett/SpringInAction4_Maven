package c_xmlConfig;

/**
 * Created by yangjing on 2018/1/2
 */
public class SgtPeppersDisk implements CompactDisk {

    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("通过c_xmlConfig显示：Playing " + title + " by " + artist);
    }

}
