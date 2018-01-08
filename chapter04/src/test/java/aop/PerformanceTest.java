package aop;

import newFunctionByAnnotation.Encoreable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yangjing on 2018/1/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PerformanceConfig.class)
public class PerformanceTest {

    @Autowired
    @Qualifier("singing")
    private Performance performance;

    @Test
    public void performanceTest(){
        Encoreable encoreable = (Encoreable) performance;
        performance.perform();
        encoreable.performEncore();
    }

}
