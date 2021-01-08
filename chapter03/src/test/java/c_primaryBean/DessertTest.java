package c_primaryBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yangjing on 2018/1/4
 * <p>
 * Spring 中的 @Autowired 是按照 bean type 来匹配 bean， 系统里共有3种type的 Dessert： Cake(无限定符),
 * Cookie（限定@Qulifier("greasyCookie")）, IceCream（限定：@Primary） 如果它们三个都没有限定符，
 * 那么系统会报错，因为@Autowired无法从三种同类型的bean中选择出一个 如果有限定符， @Autowired 会首先选择 @Primary
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
