package c_xmlConfig;

import java.util.List;

/**
 * Created by yangjing on 2018/1/2
 */
public class BlankDisk implements CompactDisk {

    private String title;
    private String artist;
    private List<String> tracks;

    public BlankDisk(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
    }

    @Override
    public void play() {
        System.out.println("通过c_xmlConfig显示：");
        System.out.println("Playing " + title + " by " + artist);
        for (String track : tracks) {
            System.out.println("-Track：" + track);
        }
    }
}
