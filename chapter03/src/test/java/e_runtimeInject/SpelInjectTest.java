package e_runtimeInject;

import e_runtimeInject.spelInject.SpelConfig;
import e_runtimeInject.spelInject.SpelDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yangjing on 2018/1/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpelConfig.class)
public class SpelInjectTest {

    @Autowired
    private SpelDisc spelDisc;

    @Test
    public void spelInjectTest() {
        System.out.println(spelDisc.getTitle() + " by " + spelDisc.getArtist());
    }

}
