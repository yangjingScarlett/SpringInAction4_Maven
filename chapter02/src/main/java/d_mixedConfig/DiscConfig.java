package d_mixedConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangjing on 2018/1/3
 */
@Configuration
public class DiscConfig {

    @Bean
    public CompactDisc compactDisc() {
        return new SgtPeppersDisc();
    }

}
