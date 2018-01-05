package c_primaryBean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Created by yangjing on 2018/1/4
 */
@Component
@Primary
public class IceCream implements Dessert {

    @Override
    public String tasting() {
        return "The iceCream tastes cold and sweet!";
    }

}
