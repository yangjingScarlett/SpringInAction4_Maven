package cn.yang.knight;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * using xml file to set up spring context: ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:knight.xml");
 *
 * when we don't use @ContextConfiguration annotation, we don't need to use @RunWith annotation either
 */
public class DamselRescuingKnightTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        "classpath:knight.xml");

    @Test
    public void testEmbark() {
        Knight knight = (Knight) context.getBean("damselRescuingKnight");
        knight.embarkOnQuest();
    }

}
