package a_autoConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yangjing on 2018/1/2
 */
@Component
public class CDPlayer implements MediaPlayer {

    private CompactDisk cd;

    @Autowired
    public CDPlayer(CompactDisk cd) {
        this.cd = cd;
    }

    @Override
    public void play() {
        cd.play();
    }

}
