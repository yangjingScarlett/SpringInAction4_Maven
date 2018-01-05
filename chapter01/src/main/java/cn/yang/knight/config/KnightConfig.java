package cn.yang.knight.config;

import cn.yang.knight.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by yangjing on 2018/1/2
 */
@Configuration
@ComponentScan("cn.yang.knight")
@EnableAspectJAutoProxy
public class KnightConfig {

    @Bean
    public Quest quest() {
        return new SlayDragonQuest(System.out);
    }

    @Bean
    public Knight knight(Quest quest) {
        return new BraveKnight(quest);
    }
}
