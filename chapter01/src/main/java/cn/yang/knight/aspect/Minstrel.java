package cn.yang.knight.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by yangjing on 2018/1/2
 */
@Component
@Aspect
@Slf4j
public class Minstrel {

    @Pointcut("@annotation(cn.yang.knight.aspect.MinstrelNeeding)")
    public void annotationPointcut() {

    }

    @Around("annotationPointcut()")
    public void aroundEmbark(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Fa la la, the knight is so brave!");
        proceedingJoinPoint.proceed();
        log.info("Tee hee hee, the brave knight did embark on a quest!");
    }

}
