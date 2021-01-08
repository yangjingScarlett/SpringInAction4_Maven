package parametersOfAdvice;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangjing on 2018/1/8
 */
@Getter
@Setter
public class SgtPeppers implements CompactDisc {

    private String title;
    private String artist;
    private List<String> tracks;

    @Override
    public void play(int trackNum) {
        System.out
            .println("Play " + title + " by " + artist + "! -Track : " + tracks.get(trackNum - 1));
    }

}
