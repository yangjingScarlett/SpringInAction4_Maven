package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by yangjing on 2018/1/8
 */
@Aspect
public class Audience {

    @Pointcut("execution(* *.perform(..))")
    public void performance() {

    }

    @Before("performance()")
    public void silenceCellPhone() {
        System.out.println("Please Silence your cellPhone!");
    }

    @Before("performance()")
    public void takeSeat() {
        System.out.println("Please take your seat!");
    }

    @AfterReturning("performance()")
    public void applause() {
        System.out.println("CLAP! CLAP! CLAP!");
    }

    @AfterThrowing("performance()")
    public void demandRefund() {
        System.out.println("Demanding a refund!");
    }

    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint joinPoint) {
        System.out.println("The critics enter!");
        System.out.println("The critics take seats!");
        try {
            joinPoint.proceed();
            System.out.println("The critics all say that this is a wonderful performance!");
        } catch (Throwable throwable) {
            System.out.println("The critics judge the performance as a terrible performance!!");
        }
    }

}
