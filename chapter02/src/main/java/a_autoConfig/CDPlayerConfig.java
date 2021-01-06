package a_autoConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangjing on 2018/1/2
 * <p>
 * Config spring context using @ComponentScan, it will automatically scan the a_autoConfig package
 * to set up beans
 */
@Configuration
@ComponentScan("a_autoConfig")
public class CDPlayerConfig {
}
