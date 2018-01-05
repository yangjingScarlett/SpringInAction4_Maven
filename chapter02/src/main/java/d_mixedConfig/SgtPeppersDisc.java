package d_mixedConfig;

/**
 * Created by yangjing on 2018/1/3
 */
public class SgtPeppersDisc implements CompactDisc {

    private String title = "轮回";
    private String artist = "五月天";

    @Override
    public void play() {
        System.out.println("通过d_mixedConfig显示：Playing " + title + " by " + artist);
    }

}
