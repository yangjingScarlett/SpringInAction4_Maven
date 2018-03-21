package com.methodSecurity.service.impl;

import com.methodSecurity.domain.Spittle;
import com.methodSecurity.service.SpittleService;
import org.springframework.security.access.annotation.Secured;

/**
 * Created by yangjing on 2018/3/19
 */
public class SpittleServiceSecuredImpl implements SpittleService {

    @Override
    @Secured({"ROLE_SPITTER", "ROLE_ADMIN"})
    public void addSpittle(Spittle spittle) {
        System.out.println("Method was called successfully!");
    }

}
