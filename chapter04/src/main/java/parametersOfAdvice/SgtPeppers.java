package parametersOfAdvice;

import java.util.List;

/**
 * Created by yangjing on 2018/1/8
 */
public class SgtPeppers implements CompactDisc {

    private String title;
    private String artist;
    private List<String> tracks;

    @Override
    public void play(int trackNum) {
        System.out.println("Play " + title + " by " + artist + "! -Track : " + tracks.get(trackNum-1));
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

}
