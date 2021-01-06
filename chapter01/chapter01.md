## Description of this Chapter01

#### Config of Logger: ch.qos.logback
Use logback as the logger of this chapter, it depends on the slf4j, so the required dependencies are:
1. slf4j-api
2. logback-core
3. logback-classic

The config file is logback.xml under the resources folder. Use below code at .class to get the Logger.
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RescueDamselQuest{
    //Since this chapter also has lombok dependency, the lombok provides annotations for @Slf4j 
    //The annotation automatically creates a slf4j Logger. So the below code can be replaced by only adding @Slf4j at the class
    private static final Logger logger = LoggerFactory.getLogger(RescueDamselQuest.class);
}
```

#### Config of Spring beans:
Use two ways to config beans:
1. classpath to define the application context: /spring/knight.xml
2. Autowired

#### Introduction of AOP
##### 什么是 AOP？
> AOP: aspect oriented programing 的缩写，意思是面向切面编程。通过**预编译方式**和**运行期动态代理**来实现
>程序功能的统一维护。

它主要的功能包含但不限于： 日志记录，性能统计，安全控制，事务处理，异常处理等。

如下图所示，一个系统中可能有很多业务功能，如订单管理，产品管理等。而且每一个业务功能中都需要进行日志记录和
安全验证等。如果把这些日志记录和安全验证都使用代码和业务功能融合，那么任何的修改都需要修改业务功能代码，修改
量很大且复杂。所以就诞生了切面，切面是与业务功能垂直的，独立于业务功能的处理。
![](aop.PNG)

##### AOP的实现方式
**1.预编译：** AspectJ

**2.运行期动态代理（JDK动态代理、CGLib 动态代理）：** SpringAOP、JbossAOP

几个重要的概念：

**1.切面（Aspect）：** 一个关注点的模块化，这个关注点可能会横切多个对象。比如，日志是我们的一个关注点，那么我们把日志
管理模块化，它会横切多个业务逻辑的实现对象，比如产品管理实现类就需要执行日志记录。

**2.连接点（Joinpoint）:** 程序执行过程中的某个特定的点。比如某个类中执行的方法的开始或者结束等。  
前置通知（before advice）： 在某个连接点之前执行的通知，但不能阻止连接点前的执行（除非抛出异常）。  
返回后通知（after returning advice）： 在某连接点正常完成后执行的通知。  
抛出异常后通知（after throwing advice）： 在异常点抛出异常退出时执行的通知。  
后通知（after advice）： 某连接点退出时执行的通知（无论是正常退出还是异常退出）。  
环绕通知（around advice）： 包围一个连接点的通知。 

**3.通知（Advice）：** 在切面的某个特定的连接点上执行的动作。比如，连接点是一个方法，那么通知就是在方法执行的
时候，需要额外执行的切面的动作。

**4.切入点（pointcut）：** 匹配连接点的断言，切入点表明我们如何在一个切面中去匹配连接点。在 AOP 中通知和一个
切入点表达式关联。  
有以下的示例：  
```
首先介绍各个通配符的含义： 
*  代表0到多个任意字符  
.. 放在方法参数中 ,代表任意个参数 ,放在包名后面表示当前包及其所有子包路径  
+  放在类名后,表示当前类及其子类,放在接口后,表示当前接口及其实现类  

（1） spring AOP 和 AspectJ 都支持的断言
execution(public * *(..)) // 切入点为执行所有的 public 方法时  
execution(* set*(..)) // 切入点为执行所有以 set 开始的方法时  
execution(* com.learn.spring.dAOP.AOPBean.*(..)) // 切入点为执行AOPBean类中的任何方法时  
execution(* com.learn.spring.dAOP..(..)) // 切点为执行com.learn.spring.dAOP包下的任何方法时  
execution(* com.learn.spring.dAOP...(..)) // 切点为执行com.learn.spring.dAOP包及其子包下的任何方法时  
（2） 仅 spring aop 支持的断言  
within(com.learn.spring.dAOP.service.*) // within 用于匹配指定类型内的方法执行
this(com.learn.spring.dAOP.service.AOPService) // this 用于匹配当前AOP代理对象类型的执行方法
```

**5.引入（introduction）：** 在不修改类代码的前提下，为类添加新的属性和方法。比如，aspectJ 可以在编译期动态的
修改 class 文件来为类添加新的方法和属性，但是却不需要修改源代码。

**6.目标对象（target object）：** 被一个或者多个切面通知的对象。 比如，我们有一个订单的service，还有一个产品的
service，切面会通知所有的 service 在执行数据库操作时需要加上对事务的控制，那么 这两个 service 就是目标对象。

**7.AOP代理（AOP proxy）：** AOP框架创建的对象，用来实现切面契约（aspect contract）（包括通知方法执行等功能）。
它是由 AOP框架所创建的，对编程者透明，我们在执行的过程中，并不会知道这个对象是什么样的。  
有接口和无接口的 spring AOP 实现的区别：  
（1） Spring aop 默认使用标准的 JavaSE 动态代理作为AOP 代理，这使得任何接口或者接口集都可以被代理。  
（2） Spring aop 中也可以使用 CGLIB 代理（如果一个对象没有实现任何接口）。  

**8.织入（weaving）：** 把切面代码连接到应用程序类型或者对象上，并创建一个被通知的对象。分为：编译时织入、
类加载时织入、执行时织入。

**9.顾问（advisor）：** 其实就是通知的一个封装和延伸，可以将通知以更为复杂的方式织入到某些方法中。

##### AOP 在代码中的实现（基于 Maven方便添加 dependencies）
通过上面的介绍我们知道 AOP 实现的关键在于 **AOP 框架自动创建的 AOP 代理**。 AOP 代理可以分为两大类： 静态
代理和动态代理。  
**静态代理：** 使用 AOP 框架提供的命令进行编译，从而在编译阶段就可以生成 AOP 代理类，因此也成为编译时增强。  
**动态代理：** 在运行时借助于 JDK 动态代理、CGLIB等，在内存中“临时”生成 AOP 动态代理类，因此也被称为运行时增强。  

现在AOP有两个主要的流行框架： SpringAOP 和 Spring + AspectJ  
那么这两个框架有什么不同呢？  

Spring AOP：  
1. 基于动态代理实现，只能在运行时织入，默认如果使用接口用JDK动态代理实现，如果是方法则使用CGLIB实现。  
2. 依赖IOC容器来管理，并且只能作用于spring容器，使用纯java代码实现。  
3. 在性能上，由于是使用动态代理实现的，在容器启动时需要生成代理实例，在方法调用上也会加深栈的深度，所以使用
spring AOP 的性能不如 AspectJ好。  
4. 需要的依赖：  
```xml
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring-version}</version>
        </dependency>
```

AspectJ:  
1. AspectJ 来自于 eclipse 基金会。  
2. 属于静态织入，不能在运行时织入，通过修改代码来实现，有如下几个织入的时机：  
    》 编译时织入： 如类A使用AspectJ添加了一个属性，类B引用了它，这个场景就需要编译时就进行织入，否则没法编译类B。  
    》 编译后织入： 也就是已经生成了 .class 文件，或者已经打成了 jar 包，这种时候需要增强处理的话，就要使用编译后织入。  
    》 类加载时织入： 指的是加载类时进行织入，有几种实现方法：  
        . 自定义类加载器来做这个事情，这个是最容易想到的办法，在被织入类加载到 JVM 之前对它进行加载，这样就可以在
        加载的时候定义行为了。  
        . 在 JVM 启动的时候指定 AspectJ 提供的agent： -javaagent:xxx/xxx/aspectjweaver.jar.  
3. 需要的依赖：  
```xml
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring-version}</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.5</version>
        </dependency>
```

我们这一章主要使用的是 AspectJ, 所以单独介绍一下 AspectJ.
##### AspectJ 介绍
1. AspectJ 的风格类似于纯 java 注解的普通 java 类。
2. Spring 可以使用 AspectJ 来做切入点（pointcut） 解析，这是 Spring 对 AspectJ 的支持。
3. Spring AOP 在运行时依旧是纯的 Spring AOP， 对 AspectJ 的编译器或者 AspectJ 的织入形式没有任何依赖。

##### 如何在 Spring 中配置 AspectJ
首先配置 AspectJ 自然需要导入 AspectJ 的 jar 包：
```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>${aspectj-version}</version>
</dependency>
```

一、 对 AspectJ 的支持可以使用 XML 或者 Java 风格的配置。  
1. Java 风格的配置： 
    ```java
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.EnableAspectJAutoProxy;
    @Configuration
    @EnableAspectJAutoProxy
    public class AspectJConfig{
    }
    ``` 
2. XML 风格的配置：  
    ```xml
    <aop:aspectj-autoproxy />
    ```
二、 AspectJ 的应用  
1. Aspect 注解 
    - AspectJ 切面使用 @Aspect 注解来配置，拥有 @Aspect 注解的任何 bean 都将被 Spring 自动识别并应用为切面。
    - 用 @Aspect 注解的类可以有方法和字段，他们也可能包括切入点（pointcut）、通知（advice）和引入（introduction）的声明。
    - 需要特别注意的是，@Aspect 注解是**不能通过类路径自动检测发现**的，所以必须要配合**使用 @Component 注解，或者在
    xml 文件中配置**，将有 @Aspect 注解的类注册为 Bean。
    - 一个类如果有 @Aspect 注解就意味着它是一个切面，并且不再使用自动代理。
2. Pointcut 定义和注解
    - 一个切入点可以通过普通的方法定义来提供，并且使用 @Pointcut 注解，方法返回类型必须为 void。  
    示例： 定义一个名为 ‘anyOldTransfer’ 的切入点，它可以匹配任何名为 ‘transfer’ 的方法的执行。  
    ```
    @Pointcut("execution(* transfer(..))") // the pointcut expression
    public void anyOldTransfer(){} // the pointcut signature
    ```
    - 支持的切入点表达式：  
    execution： 匹配 **方法执行** 的连接点。  
    within： 匹配 **特殊类型** 的连接点。  
    this： 匹配特定 **连接点的 bean 引用是指定类型** 的实例。  
    target： 匹配特定 **连接点的目标对象是指定类型** 的实例。  
    args： 匹配特定 **连接点的参数是指定类型** 的实例。  
    @target： 匹配特定 **连接点的类执行对象要有指定类型的注解**。  
    @args： 匹配特定 **连接点的类执行对象实际传入的参数要有指定类型的注解**。  
    @within： 匹配特定 **连接点的类执行对象内具有指定类型的注解**。  
    @annotation： 匹配特定 **连接点的主体具有指定类型的注解**。  
    
    - 除了单独使用上述的切入点表达式外，还可以组合使用这些切入点表达式，以构造更为复杂的切入点表达式。  
    如通过 &&、 ||、 ！ 等来进行组合，也可以通过名字引用切入点表达式。  
    ```
    @Pointcut("expression(public * (..))")
    private void anyPublicOperation(){}
   
    @Pouintcut("within(com.learn.spring.eAspectWithXML.trading..)")
    private void inTrading(){}
   
    @Pointcut("anyPublicOperation() && inTrading()") // 这里就是一个组合切入点表达式，是anyPublicOperation() 和 inTrading() 的交集
    private void tradingOperation(){}
    ```
3. 如何定义良好的 pointcut  
    首先我们需要清楚，AspectJ 是编译时期的 AOP， 在编译期检查代码，然后去匹配连接点和切入点，这种代价是很昂贵的。
    所以一个好的切入点应该满足以下几点：
    - 选择特定类型的连接点。 如：在使用 execution 匹配连接点使，指定连接点的类型，如get、set、call、handler等。
    - 确定连接点的范围。 如： within， withincode 等。
    - 匹配上下文信息。 如： 使用 this， target， @annotation 等去尽可能地明确连接点的上下文。
    
4. AspectJ 的 Advice 定义  
    - Before Advice:  @Before("execution(......)")
    - After Returning Advice: @AfterReturning(pointcut="", returning="")  
    The pointcut parameter indicates the pointcut, the returning parameter saves the returned result of function.
    - After Throwing Advice: @AfterThrowing(pointcut="", throwing="")  
    The pointcut parameter indicates the pointcut, the throwing parameter gives the thrown exception.
    - After Advice: @After（"pointcut..."）
    after advice 是最终通知，相当于 try-catch 模块中的finally， 所以它必须准备处理正常和异常两种返回情况，
    最常用的用法是**释放资源**。
    - Around Advice: @Around("pointcut...")  
    我们之前就了解过，around advice **通知方法的第一个参数必须是 ProceedingJoinPoint**。  
    在通知方法内部调用 ProceedingJoinPoint 的 **proceed（） 方法会去执行真正的被关注的业务方法**，传入一个 Object[]
    对象，数组中的值将被作为参数传递给方法。
   
5. Advice 扩展  
    - 给 advice 传递参数：  可以在切面表达式中表明需要参数，然后把这个参数传递给切面方法，这种方法通常是用于
    在切面中获取业务方法的参数，然后做一些判断。  
    ```
   // 1. directly to define the before advice. its pointcut expression means when call dataAccess() 
   // function with account parameter. 
   // It can pass the account parameter to the validateAccount function, but the parameter name must be the same
   @Before("com.learn.spring.eAspectJWithXML.AccountBusiness.dataAccess() && args(account,..)")
   public void validateAccount(Account account){}

   // 2. below code is the same with above. Only define a pointcut seperately.
   @Pointcut("com.learn.spring.eAspectJWithXML.AccountBusiness.dataAccess() && args(account,..)")
   public void accountDataAccessOperation(Account account){}
   @Before("accountDataAccessOperation(account)")
   public void validateAccount(Account account){}
    ```
   
   - 自定义注解，通常用于两种情况： 一种是通过注解做一些记录，记录有哪些方法使用了这个注解； 另一种是判断方法是否应用了
   某个注解或者方法上应用的注解的值是哪些值。  
   具体应用参考代码：   
   · 自定义一个注解类 MethodAnnotation。  
   · 在 MockBiz 类中创建 saveWithAnnotation 方法，给这个方法添加自定义的注解 @MethodAnnotation  
   · 在 MockAspect 类中添加 before advice。  
   · 在测试类 TestAspectAOP 中添加 testCustomizedAnnotation 进行测试。  