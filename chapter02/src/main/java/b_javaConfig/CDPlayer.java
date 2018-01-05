package b_javaConfig;

/**
 * Created by yangjing on 2018/1/2
 */
public class CDPlayer implements MediaPlayer {

    private CompactDisk cd;

    public CDPlayer(CompactDisk cd) {
        this.cd = cd;
    }

    @Override
    public void play() {
        cd.play();
    }
}
