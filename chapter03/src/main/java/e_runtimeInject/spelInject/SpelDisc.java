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

    //systemEnvironment的值设置在运行时的Environment Variables中
    public SpelDisc(@Value("#{systemEnvironment['title']}") String title,
                    @Value("#{systemEnvironment['artist']}") String artist) {
        this.title = title;
        this.artist = artist;
    }

}
