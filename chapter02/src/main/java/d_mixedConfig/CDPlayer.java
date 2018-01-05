package d_mixedConfig;

/**
 * Created by yangjing on 2018/1/3
 */
public class CDPlayer implements MediaPlayer {

    private CompactDisc cd;

    @Override
    public void play() {
        cd.play();
    }

    public CompactDisc getCd() {
        return cd;
    }

    public void setCd(CompactDisc cd) {
        this.cd = cd;
    }

}
