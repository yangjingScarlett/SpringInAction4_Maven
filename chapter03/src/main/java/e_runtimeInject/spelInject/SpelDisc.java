package e_runtimeInject.spelInject;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by yangjing on 2018/1/5
 */
@Getter
@Component
public class SpelDisc {

    private String title;
    private String artist;

    // systemEnvironment 默认是指运行时的 Environment Variables
    // 所以运行前需要先在 Environment Variables 中设置 title=xxx;artist=xxx 否则它们的值会是 null
    public SpelDisc(@Value("#{systemEnvironment['title']}") String title,
        @Value("#{systemEnvironment['artist']}") String artist) {
        this.title = title;
        this.artist = artist;
    }

}
