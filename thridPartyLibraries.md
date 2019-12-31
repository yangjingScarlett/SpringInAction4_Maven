## 基础知识介绍： 

#### **1. IOC和DI**  

**IOC(Inversion Of Control): 控制反转**  
>即控制权的转移，将我们创建对象额方式反转了，以前对象的创建是由我们开发人员自己维护的，包括依赖关系也是自己注入。
使用了Spring之后，对象的创建和依赖关系的注入可以由Spring完成。控制反转也就是说从我们自己创建反转为由程序创建（Spring）。

**DI(Dependency Injection): 依赖注入**  
>Spring这个容器中，替你管理着一系列的类，前提是你需要将这些类交给Spring容器进行管理，然后在你需要的时候，不是自己去定义
而是直接向Spring容器索取，当Spring容器知道你的需求之后，就会去它管理的组件中进行查找，然后直接给你所需要的组件。这
也就是说组件之间的依赖关系由容器在`运行期`决定。

**实现IOC思想需要DI做支持：**  
注入方式： 1.set方式注入；2.构造方法注入；3.字段注入  
注入类型： 1.值类型注入；2.引用类型注入  

#### **2. Spring Bean 封装机制：**  
Spring从核心而言，是一个DI容器，它的设计意义是提供一种无侵入式的高扩展性框架。即无需代码中涉及Spring专有类，就能将
其纳入Spring容器进行管理。  
为了实现这一点，Spring 大量引入了 Java 的 Reflection 机制，通过动态调用的方式避免硬编码，并在此基础上建立了它的核心组件
BeanFactory，以此作为依赖注入机制实现的基础。org.springframework.beans 包中包括了这些核心组件的的实现类，
核心中的核心为 `BeanWrapper 和 BeanFactory 类`。  

**Bean Wrapper：**  
所谓依赖注入，就是在运行时期，由容器将依赖关系注入到组件之中。用 Spring 配置文件，将其他对象的引用通过组件提供的
Setter 方法进行设定。我们知道，如果动态的设置一个对象的属性，可以借助 Java 的 Reflection 机制实现：
```
Class cls = Class.forName("cn.yang.example.beans.User");
Method mtd = cls.getMethod("setName", new Class[]{String.class});
Object obj = (Object)cls.newInstance();
mtd.invoke(obj, new Object[]{"Erica"});
return obj;
```
上面我们通过动态加载 User 类，并通过 Reflection 调用 User.setName() 方法设置其属性，这里为了方便，我们将
类名和方法名都使用字符串硬编码，假设这些常量都是通过`配置文件`读入，那我们就实现了一个最简单的 BeanWrapper。
它的功能很简单，提供一个设置 JavaBean 属性的通用方法。Spring BeanWrapper 基于同样的原理，提供了一个更加完善的实现。
看看如何通过Spring BeanWrapper操作一个JavaBean：  
```
Object obj = Class.forName("cn.yang.example.beans.User").newInstance();
BeanWrapper bw = new BeanWrapperImpl(obj);
bw.setPropertyName("name", "Erica");
System.out.println("User name => " + bw.getPropertyValue("name"));
```
通过这样的方式设定 JavaBean 的属性确实是有点繁琐，但是它确实提供了一个通用的属性设定机制，而这样的机制正是 Spring 
依赖注入机制的基础。  

**Bean Factory:**  
Bean Factory 负责根据配置文件创建 Bean 实例，可以配置的项目有：  
1. Bean 属性值及依赖关系（对其他 Bean 的引用）。  
2. Bean 创建模式（是否 Singleton 模式，即是否维持全局唯一的实例）。  
3. Bean 初始化和销毁方法。  

下面列举了一个比较完整的 Bean 配置实例：  
```xml
<beans>
    <description>Spring Bean Configuration Sample</description>
    <!--id: Java Bean 在 Bean Factory 中的唯一标识，代码中通过这个标识获取 Java Bean 实例-->
    <!--class: Java Bean 类路径-->
    <!--singleton: 是否只维护一个实例-->
    <!--init-method: 初始化方法，此方法将在BeanFactory创建JavaBean实例之后，在向应用层返回引用之前执行。
            一般用于一些资源的初始化工作。-->
    <!--destroy-method: 销毁方法。此方法将在BeanFactory销毁的时候执行，一般用于资源释放。-->
    <!--depends-on: Bean依赖关系。一般情况下无需设定。Spring会根据情况组织各个依赖关系的构建工作
            只有某些特殊情况下，如JavaBean中的某些静态变量需要进行初始化（这是一种Bad Smell，应该在设计上应该避免）。
            通过depends-on指定其依赖关系可保证在此Bean加载之前，首先对depends-on所指定的资源进行加载。-->
    <bean id="TheAction" class="cn.yang.beans.UpperAction" singleton="true" init-method="init" 
    destroy-method="cleanup" depends-on="ActionManager">
        <property name="message">
            <value>HELLO</value>
        </property>
        <property name="desc">
            <null/>
        </property>
        <property>
            <!--ref: 指定属性对其他Bean的引用。Bean Factory 将在运行期创建dataSource bean实例，并将其引用传入TheAction Bean的dataSource属性-->
            <ref local="dataSource"/>
        </property>
    </bean>
    
    <bean id="dataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
        <property name="jndiName"> <value>java:comp/env/jdbc/sample</value> </property>
    </bean>
</beans>
``` 

**比较BeanWrapper 和 BeanFactory:**  
BeanWrapper 实现了对单个Bean 的属性设定操作。而BeanFactory 则是针对多个Bean 的管理容器，根据给定的配置文件，
BeanFactory 从中读取类名、属性名/值，然后通过reflection 机制进行Bean 加载和属性设定。

#### **3. ApplicationContext**  
BeanFactory 提供了针对Java Bean 的管理功能，而 ApplicationContext 提供了一个更加框架化的实现。ApplicationContext
覆盖了 BeanFactory 的所有功能，并提供了更多的特性，此外，ApplicationContext 为与现有应用框架相整合，提供了更为开放式
的实现（如对于 WEB 应用，我们可以在 web.xml 中对 ApplicationContext 进行配置）。  




{
    "org.springframework": {
        "spring-context": "",
        "spring-context-support": "",
        "spring-core": "",
        "spring-beans": "",
        "spring-expression": "",
        "spring-test": "",
        "spring-jdbc": "",
        "spring-web": "",
        "spring-webmvc": "",
        "spring-orm": ""
    },
    "org.springframework.data": {
        "spring-data-jpa": ""
    },
    "org.springframework.security": {
        "spring-security-core": "",
        "spring-security-config": "",
        "spring-security-web": "",
        "spring-security-crypto": ""
    },
    "junit": {
        "junit": "单元测试"
    },
    "commons-io": {
        "commons-io": ""
    },
    "commons-lang": {
        "commons-lang": ""
    },
    "commons-dbcp": {
        "commons-dbcp": ""
    },
    "javax.annotation": {
        "jsr250-api": ""
    },
    "javax.servlet": {
        "javax.servlet-api": "",
        "jstl": ""
    },
    "javax": {
        "javaee-api": ""
    },
    "org.hibernate": {
        "hibernate-entitymanager": "",
        "hibernate-validator": ""
    },
    "com.oracle": {
        "ojdbc14": ""
    },
    "com.alibaba": {
        "druid": ""
    },
    "com.mchange": {
        "c3p0": ""
    },
    "org.thymeleaf": {
        "thymeleaf": "",
        "thymeleaf-spring4": ""
    },
    "org.thymeleaf.extras": {
        "thymeleaf-extras-springsecurity4": ""
    },
    "org.projectlombok": {
        "lombok": ""
    },
    "ch.qos.logback": {
        "logback-core": "",
        "logback-classic": ""
    },
    "org.slf4j": {
        "slf4j-api": ""
    },
    "log4j": {
        "log4j": ""
    },
    "com.fasterxml.jackson.core": {
        "jackson-core": "",
        "jackson-annotations": "",
        "jackson-databind": ""
    },
    "org.apache.commons": {
        "commons-lang3": ""
    },
    "net.sf.ehcache": {
        "ehcache": "",
        "ehcache-core": ""
    },
    "org.mockito": {
        "mockito-core": ""
    }
};
