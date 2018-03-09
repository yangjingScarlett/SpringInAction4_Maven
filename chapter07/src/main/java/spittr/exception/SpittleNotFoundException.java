package spittr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yangjing on 2018/2/5
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Spittle not Found")
public class SpittleNotFoundException extends RuntimeException {
}
