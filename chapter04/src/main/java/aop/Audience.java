package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by yangjing on 2018/1/8
 * <p>
 * This is a aspect class because it has @Aspect annotation. But it is not bean here, need to setup
 * it as bean in context configuration: PerformanceConfig.class
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
    public Object watchPerformance(ProceedingJoinPoint joinPoint) {
        System.out.println("The critics enter!");
        System.out.println("The critics take seats!");
        try {
            Object res = joinPoint.proceed();
            System.out.println("The critics all say that this is a wonderful performance!");
            return res;
        } catch (Throwable throwable) {
            throw new RuntimeException(
                "The critics judge the performance as a terrible performance!!");
        }
    }

}
