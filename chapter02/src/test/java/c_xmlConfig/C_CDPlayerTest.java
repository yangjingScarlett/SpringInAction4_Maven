package c_xmlConfig;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangjing on 2018/1/2
 */
public class C_CDPlayerTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("c_xmlConfig_spring.xml");
        MediaPlayer mediaPlayer = context.getBean(CDPlayer.class);
        mediaPlayer.play();
        context.close();
    }
}
