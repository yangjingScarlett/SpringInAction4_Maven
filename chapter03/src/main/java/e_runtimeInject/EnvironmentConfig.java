package e_runtimeInject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by yangjing on 2018/1/4
 */
@Configuration
//不加encoding设置的话，读取格式为UTF-8的properties文件时会中文乱码
@PropertySource(value = "classpath:e_runtimeInject/app.properties", encoding = "UTF-8")
public class EnvironmentConfig {

    private Environment environment;

    @Autowired
    public EnvironmentConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public BlankDisc blankDisc() {
        return new BlankDisc(environment.getProperty("title"), environment.getProperty("artist"));
    }

}
