package c_primaryBean;

import org.springframework.stereotype.Component;

/**
 * Created by yangjing on 2018/1/4
 */
@Component
public class Cake implements Dessert {

    @Override
    public String tasting() {
        return "The cake tastes sweet!";
    }

}
