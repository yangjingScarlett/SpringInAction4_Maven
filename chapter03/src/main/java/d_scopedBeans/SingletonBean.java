package d_scopedBeans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by yangjing on 2018/1/4
 */
@Getter
@Setter
@Component
public class SingletonBean {

    private String title;

}
