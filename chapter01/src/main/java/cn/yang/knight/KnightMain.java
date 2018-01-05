package cn.yang.knight;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangjing on 2018/1/2
 */
public class KnightMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/knight.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }

}
