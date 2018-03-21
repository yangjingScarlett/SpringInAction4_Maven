package test;

import com.methodSecurity.config.ExpressionSecurityConfig;
import com.methodSecurity.domain.Spitter;
import com.methodSecurity.domain.Spittle;
import com.methodSecurity.service.SpittleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangjing on 2018/3/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ExpressionSecurityConfig.class)
public class ExpressionSecurityTest {

    @Autowired
    private SpittleService spittleService;

    @Before
    public void clearContext() {
        SecurityContextHolder.clearContext();
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void test_noCredentials() {
        Spitter spitter = new Spitter(1L, "habuma", null, "Craig Walls", "craig@habuma.com", true);
        Spittle spittle = new Spittle(1L, "", new Date(), spitter);
        spittleService.addSpittle(spittle);
    }

    @Test(expected = AccessDeniedException.class)
    public void test_insufficientPrivilege() {
        setupUser();

        Spitter spitter = new Spitter(1L, "habuma", null, "Craig Walls", "craig@habuma.com", true);
        Spittle spittle = new Spittle(1L, "", new Date(), spitter);
        spittleService.addSpittle(spittle);
    }

    @Test
    public void test_sufficientPrivilege() {
        setupUser("ROLE_SPITTER");

        Spitter spitter = new Spitter(1L, "habuma", null, "Craig Walls", "craig@habuma.com", true);
        Spittle spittle = new Spittle(1L, "", new Date(), spitter);
        spittleService.addSpittle(spittle);
    }

    private void setupUser(String... privs) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String priv : privs) {
            authorities.add(new SimpleGrantedAuthority(priv));
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("user", "user", authorities);
        securityContext.setAuthentication(authenticationToken);
    }

}
