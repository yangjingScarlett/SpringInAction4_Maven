package aop;

import newFunctionByAnnotation.EncoreableIntroducer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by yangjing on 2018/1/8
 */
@Configuration
@EnableAspectJAutoProxy
public class PerformanceConfig {

    @Bean
    public Audience audience() {
        return new Audience();
    }

    @Bean
    @Qualifier("singing")
    public Performance performance1() {
        return new Singing();
    }

    @Bean
    @Qualifier("dancing")
    public Performance performance2() {
        return new Dancing();
    }

    @Bean
    public EncoreableIntroducer encoreableIntroducer(){
        return new EncoreableIntroducer();
    }

}
