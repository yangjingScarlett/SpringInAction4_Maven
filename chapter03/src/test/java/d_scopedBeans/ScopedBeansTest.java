package d_scopedBeans;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yangjing on 2018/1/4
 * <p>
 * Spring IOC 容器中的 bean 如果不指定 scope 则默认为 singleton， 意味着整个上下文中只有一个实例， 可以 用 @Scope() 来指定 bean 的
 * scope。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ComponentScanConfig.class)
public class ScopedBeansTest {

    @Autowired
    private NotePad notePad1;
    @Autowired
    private NotePad notePad2;
    @Autowired
    private SingletonBean singletonBean1;
    @Autowired
    private SingletonBean singletonBean2;

    @Test
    public void testPrototype() {
        Assert.assertNotEquals(notePad1, notePad2);//两者都为null则为true，否则比较notePad1.equals(notePad2)
        Assert.assertNotSame(notePad1, notePad2);//比较notePad1==notePad2
    }

    @Test
    public void testSingleton() {
        Assert.assertEquals(singletonBean1, singletonBean2);
        Assert.assertSame(singletonBean1, singletonBean2);
    }

}
