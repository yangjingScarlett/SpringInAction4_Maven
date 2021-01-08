package parametersOfAdvice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yangjing on 2018/1/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DiscPlayConfig.class)
public class DiscPlayTest {

    @Autowired
    private CompactDisc compactDisc;

    @Test
    public void discPlayTest() {
        compactDisc.play(1);
        compactDisc.play(2);
        compactDisc.play(3);
        System.out.println("\n============Repeat 3 times to play the fourth track===========");
        compactDisc.play(4);
        compactDisc.play(4);
        compactDisc.play(4);
    }

}
