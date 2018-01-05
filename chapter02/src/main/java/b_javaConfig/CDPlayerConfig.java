package b_javaConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangjing on 2018/1/2
 */
@Configuration
public class CDPlayerConfig {

    @Bean
    public CompactDisk compactDisk(){
        return new SgtPeppersDisk();
    }

    @Bean
    public MediaPlayer mediaPlayer(){
        return new CDPlayer(compactDisk());
    }
}
