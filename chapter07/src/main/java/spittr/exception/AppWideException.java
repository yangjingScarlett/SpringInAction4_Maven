package spittr.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by yangjing on 2018/2/6
 */
@ControllerAdvice
public class AppWideException {

    @ExceptionHandler(DuplicateSpittleException.class)
    public String duplicateException() {
        return "error/duplicate";
    }

}
