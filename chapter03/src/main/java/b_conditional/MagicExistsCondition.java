package b_conditional;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by yangjing on 2018/1/3
 */
public class MagicExistsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // The context.getEnvironment() will get current system environment,
        // so you must set it in the environment variables it you want to make it work
        System.out.println(context.getEnvironment().getProperty("magic"));
        return context.getEnvironment().containsProperty("magic");
    }

}
