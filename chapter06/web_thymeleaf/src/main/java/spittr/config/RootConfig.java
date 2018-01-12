package spittr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by yangjing on 2018/1/12
 */
@Configuration
@ComponentScan(basePackages = "spittr",
        excludeFilters = @ComponentScan.Filter(value = EnableWebMvc.class, type = FilterType.ANNOTATION))
public class RootConfig {
}
