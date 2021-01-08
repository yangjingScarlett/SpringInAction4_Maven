package b_conditional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by yangjing on 2018/1/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MagicConfig.class)
public class MagicTest {

    @Autowired
    private MagicBean magicBean;

    @Test
    // Will fail if the magicBean doesn't match the condition
    public void magicTest() {
        assertNotNull(magicBean);
        System.out.println("Name : " + magicBean.getName());
        System.out.println("Desc : " + magicBean.getDesc());
    }

}
