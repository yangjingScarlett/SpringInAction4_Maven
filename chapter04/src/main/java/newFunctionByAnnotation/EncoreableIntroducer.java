package newFunctionByAnnotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * Created by yangjing on 2018/1/8
 */
@Aspect
public class EncoreableIntroducer {

    @DeclareParents(value = "aop.Performance+",defaultImpl = DefaultEncoreable.class)
    public static Encoreable encoreable;

}
