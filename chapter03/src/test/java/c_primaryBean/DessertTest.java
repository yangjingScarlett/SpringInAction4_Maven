package c_primaryBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yangjing on 2018/1/4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DessertConfig.class)
public class DessertTest {

    @Autowired//注释了@primary的bean
    private Dessert dessert;

    @Autowired
    @Qualifier("cake")//系统默认bean的id就是限定符
    private Dessert dessertCake;

    @Autowired
    @Qualifier("greasyCookie")//自定义的限定符
    private Dessert dessertCookie;

    @Test
    public void testDessert() {
        System.out.println(dessert.tasting());
    }

    @Test
    public void testQualifier() {
        System.out.println(dessertCake.tasting());
    }

    @Test
    public void testMyselfQualifier() {
        System.out.println(dessertCookie.tasting());
    }

}
