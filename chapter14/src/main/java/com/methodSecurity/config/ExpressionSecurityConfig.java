package com.methodSecurity.config;

import com.methodSecurity.service.SpittleService;
import com.methodSecurity.service.impl.SpittleServiceExpressionSecurityImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * Created by yangjing on 2018/3/19
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ExpressionSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("user").roles("USER");
    }

    @Bean
    public SpittleService spittleService() {
        return new SpittleServiceExpressionSecurityImpl();
    }

}
