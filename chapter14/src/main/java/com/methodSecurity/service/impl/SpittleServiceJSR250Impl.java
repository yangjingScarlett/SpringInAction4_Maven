package com.methodSecurity.service.impl;

import com.methodSecurity.domain.Spittle;
import com.methodSecurity.service.SpittleService;

import javax.annotation.security.RolesAllowed;

/**
 * Created by yangjing on 2018/3/19
 */
public class SpittleServiceJSR250Impl implements SpittleService {

    @Override
    @RolesAllowed({"ROLE_SPITTER", "ROLE_ADMIN"})
    public void addSpittle(Spittle spittle) {
        System.out.println("Method was called successfully by JSR250!");
    }

}
