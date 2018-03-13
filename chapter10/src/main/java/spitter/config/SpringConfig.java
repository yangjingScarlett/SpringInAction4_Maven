package spitter.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by yangjing on 2018/3/13
 */
@Configuration
@ComponentScan("spitter")
@Import(DataSourceConfig.class)
public class SpringConfig {
}
