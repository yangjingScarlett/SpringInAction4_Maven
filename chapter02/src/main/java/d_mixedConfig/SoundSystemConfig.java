package d_mixedConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by yangjing on 2018/1/3
 */
@Configuration
@Import(CDPlayerConfig.class)
@ImportResource(locations = "classpath:d_mixedConfig_spring.xml")
public class SoundSystemConfig {
}
