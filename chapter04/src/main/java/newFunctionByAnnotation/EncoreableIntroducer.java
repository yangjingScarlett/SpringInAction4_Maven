package newFunctionByAnnotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * Created by yangjing on 2018/1/8
 */
@Aspect
public class EncoreableIntroducer {

    // @DeclareParents 注解实际上是一种 introduction， 它可以为给定value内的对象添加父接口，并用 defaultImpl 指定默认的父类
    // 这里是指为 aop 包里的 Performance 接口及它所有的实现类添加父接口 encoreable， 并且默认的父类是 DefaultEncoreable.class
    @DeclareParents(value = "aop.Performance+", defaultImpl = DefaultEncoreable.class)
    public static Encoreable encoreable;

}
