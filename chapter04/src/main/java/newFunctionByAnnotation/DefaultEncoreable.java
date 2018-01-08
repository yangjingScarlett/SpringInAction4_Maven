package newFunctionByAnnotation;

/**
 * Created by yangjing on 2018/1/8
 */
public class DefaultEncoreable implements Encoreable {

    @Override
    public void performEncore() {
        System.out.println("Thanks for watching our performance!");
    }

}
