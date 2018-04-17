package spittr.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by yangjing on 2018/4/9
 */
@Getter
@AllArgsConstructor
public class SpittleNotFoundException extends RuntimeException {

    private long spittleId;

}
