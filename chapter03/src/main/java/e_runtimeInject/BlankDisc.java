package e_runtimeInject;

import lombok.Getter;

/**
 * Created by yangjing on 2018/1/4
 */
@Getter
public class BlankDisc {

    private final String title;
    private final String artist;

    public BlankDisc(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

}
