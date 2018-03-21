package com.methodSecurity.service.impl;

import com.methodSecurity.domain.Spittle;
import com.methodSecurity.service.SpittleService;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by yangjing on 2018/3/19
 */
public class SpittleServiceExpressionSecurityImpl implements SpittleService {

    @Override
    //#spittle部分引用方法中的同名参数。Spring Security能够检查传入方法的参数，并将这些参数用于认证决策的制定。
    @PreAuthorize("(hasRole('ROLE_SPITTER') and #spittle.text.length() le 140) or hasRole('ROLE_ADMIN')")
    public void addSpittle(Spittle spittle) {
        System.out.println("Method was called successfully by expressionSecurity! ");
    }

}
