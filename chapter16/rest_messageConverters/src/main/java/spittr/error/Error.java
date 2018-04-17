package spittr.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by yangjing on 2018/4/9
 */
@AllArgsConstructor
@Getter
public class Error {
    private int code;
    private String message;

}
