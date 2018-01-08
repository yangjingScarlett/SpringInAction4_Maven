package aop;

/**
 * Created by yangjing on 2018/1/8
 */
public class Dancing implements Performance {

    @Override
    public void perform() {
        System.out.println("Dancing with music!");
    }

}
