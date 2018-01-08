package parametersOfAdvice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjing on 2018/1/8
 */
@Configuration
@EnableAspectJAutoProxy
public class DiscPlayConfig {

    @Bean
    public CompactDisc compactDisc(){
        SgtPeppers sgtPeppers = new SgtPeppers();
        List<String> trackList = new ArrayList<>();
        trackList.add("勇气");
        trackList.add("暖暖");
        trackList.add("大手牵小手");
        trackList.add("偶阵雨");
        trackList.add("分手快乐");
        trackList.add("最浪漫的事");
        sgtPeppers.setTitle("勇气");
        sgtPeppers.setArtist("梁静茹");
        sgtPeppers.setTracks(trackList);
        return sgtPeppers;
    }

    @Bean
    public TrackCounter trackCounter(){
        return new TrackCounter();
    }

}
