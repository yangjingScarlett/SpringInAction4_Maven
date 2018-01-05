package cn.yang.knight.aspect;

import java.lang.annotation.*;

/**
 * Created by yangjing on 2018/1/2
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MinstrelNeeding {

    String name() default "";

}
