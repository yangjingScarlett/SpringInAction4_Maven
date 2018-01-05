package a_autoConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yangjing on 2018/1/2
 */
@Component
public class CDPlayer implements MediaPlayer {

    @Autowired
    private CompactDisk cd;

    @Override
    public void play() {
        cd.play();
    }

}
