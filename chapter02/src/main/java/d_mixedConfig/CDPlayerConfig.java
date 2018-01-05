package d_mixedConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangjing on 2018/1/3
 */
@Configuration
public class CDPlayerConfig {

    @Bean
    //此时的cd 是通过SoundSystemConfig的ImportResource导入的在xml配置文件中的BlankDisc
    public MediaPlayer mediaPlayer(CompactDisc cd) {
        CDPlayer cdPlayer = new CDPlayer();
        cdPlayer.setCd(cd);
        return cdPlayer;
    }

}
