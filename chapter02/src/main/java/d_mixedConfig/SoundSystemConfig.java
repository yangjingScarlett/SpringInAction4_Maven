package d_mixedConfig;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by yangjing on 2018/1/3
 * <p>
 * Config spring context using mixed ways: java code, xml.
 * <p>
 * Based on the @Configuration, we can add other spring configurations. Use @Import to add java code
 * based config. Use @ImportResource to add xml based config.
 */
@Configuration
@Import(DiscConfig.class)
@ImportResource(locations = "classpath:d_mixedConfig_spring.xml")
public class SoundSystemConfig {

    private CompactDisc compactDisc1;
    private CompactDisc compactDisc2;

    // Must use @Resource here, because @Autowired set up beans only by bean type, we have two beans at the same type CompactDisc
    @Resource(name = "compactDisc") // This bean is from DiscConfig.class
    public void setCompactDisc1(CompactDisc compactDisc1) {
        this.compactDisc1 = compactDisc1;
    }

    @Resource(name = "blankDisc") // This bean is from d_mixedConfig_spring.xml
    public void setCompactDisc2(CompactDisc compactDisc2) {
        this.compactDisc2 = compactDisc2;
    }

    // We can setup another bean here by using @Bean annotation
    @Bean
    public MediaPlayer configMediaPlayer() {
        CDPlayer cdPlayer = new CDPlayer();
        cdPlayer.setCd(compactDisc2); // You can change to another compactDisc when test
        return cdPlayer;
    }
}
