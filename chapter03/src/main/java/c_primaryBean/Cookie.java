package c_primaryBean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by yangjing on 2018/1/4
 */
@Component
@Qualifier("greasyCookie")
public class Cookie implements Dessert {

    @Override
    public String tasting() {
        return "The cookie tastes greasy!";
    }

}
