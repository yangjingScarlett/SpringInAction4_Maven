#### web.xml 详解

#### 一、 概述  
当启动 Web 项目时，Web 容器（无论是 JBoss、 Tomcat 还是别的 web 容器）首先会去读取 web.xml 中的配置。  

#### 二、 web.xml 配置内容（配置节点）  
web.xml 文件中可以配置的内容有很多，但主要的配置节点有以下几个：  
- context-param
- listener
- filter
- servlet

对于 web.xml 中的配置而言，有些配置节点的加载顺序就是它们在 web.xml 文件中的先后顺序，而上面提到的这几个
主要配置节点则不然，它们被加载的顺序与在 web.xml 文件中的先后顺序无关，固定的加载顺序是：
```text
context-param -> listener -> filter -> servlet
```
但是有一些配置节点，以 filter 为例， 它通常的配置是这样的：  
```xml
<filter>
  <filter-name>login</filter-name>
  <filter-class>com.test.servlet.LoginFilter</filter-class>
</filter>
<filter-mapping>
  <filter-name>login</filter-name>
  <url-pattern>*.xul</url-pattern>
</filter-mapping>
```
web.xml 中可以定义多个 filter，与 filter 对应的一个配置节是 filter-mapping，
这里一定要注意，对于拥有相同 filter-name 的 filter 和 filter-mapping 配置节而言， filter-mapping 必须在 filter
后面，否则当解析到 filter-mapping 时，它所对应的 filter-name 还未定义。 Web 容器启动时初始化每个 filter 时，
是按照 filter 配置节出现的顺序来初始化的，当请求资源匹配多个 filter-mapping 时，filter 拦截资源是按照 
filter-mapping 配置节出现的顺序来依次调用 doFilter() 方法的。  

#### 三、 Web 加载过程  
一个 Web 项目的加载顺序如下：  
1. 启动 Web 项目时，web 容器会去读取 web.xml 配置文件，首先读取 <context-param> 和 <listener> 两个配置节点。
2. 紧接着，容器创建一个 ServletContext （也就是 servlet 的上下文），这个 web 项目的所有部分都将共享这个上下文。
3. 容器将 <context-param> 转换为键值对，并交给 ServletContext。
4. 容器创建 <listener> 中的类实例，根据配置的 class 类路径 <listener-class> 来创建监听器，这个类可以是
  自定义的类，但是必须继承 ServletContextListener。  
  在监听器中会有一个 contextInitialized(ServletContextEvent args) 
  初始化方法，在这个方法中可以获得：  
  ```
  ServletContext = ServletContextEvent.getServletContext(); // 获取 ServletContext
  context-param的值 = ServletContext.getInitParameter("context-param的键"); // 获取 context-param 中定义的值
  ``` 
  在这个类中还必须有一个contextDestroyed（ServletContextEvent event）销毁方法，用于关闭资源应用前释放资源，
  比如数据库的连接关闭。  
5. 得到 context-param 之后，就可以用它做一些操作了，注意这个时候 WEB 项目还没有完全启动完成，这个动作比
  所有的 Servlet 都要早。换句话说,这个时候,你对<context-param>中的键值做的操作,将在你的WEB项目完全启动之前被执行。
  举例：你可能想在项目启动之前就打开数据库，那么就可以在 context-param 中定义数据库的连接方式，然后在监听类
  中初始化数据库连接。
6. 容器会读取<filter></filter>，根据指定的类路径来实例化过滤器。
7. 所有版本的 WEB 容器都可以定义自启动的 Servlet。

#### 四、 web.xml 基本标签（配置节点）
首先我们需要知道，web.xml 文件对于 web 项目而言不是必须的，如果你不需要用到里面的配置信息就不用创建 web.xml，
但是在大型的 web 工程下使用它进行一些配置是很方便的。  

web.xml的模式文件是由Sun公司定义的，每个web.xml文件的根元素<web-app>中都必须标明这个web.xml使用的是哪个模式文件。 
下面来介绍一个 web.xml 中的基本标签：  
- welcome-file-list 标签：指定欢迎页面  
  下面的例子指定了两个欢迎页，显示时按顺序从第一个找起，如果第一个存在就显示第一个，后面的不起作用。  
  ```xml
    <welcome-file-list>
      <welcome-file>index.jsp</welcome-file>
      <welcome-file>index1.jsp</welcome-file>
    </welcome-file-list>
    ```
  对于 Tomcat 而言，当你指定了 web 根路径（**一定要注意，<welcome-file> 里面定义的路径是相对于 web 根路径的，
  根路径是在 module 里面的 web resource directory 中定义**）时，
  如果 web.xml 有欢迎页，就用它作为欢迎页，如果没有指定，默认查找顺序是:  
  > index.html -> index.jsp  
  
  如果既没有在 web.xml 指定欢迎页，也没有 index.html 和 index.jsp，那么 Tomcat就不知道要用哪个页面作为欢迎页了，
  它就会报错： The requested resource (/XXX) is not available。其中XXX表示web的根名。
  
- servlet 标签： 定义 servlet 初始化参数和 servlet 加载类  
  下面示例中，配置了一个名为 servlet1 的 servlet，指定它的加载类为 net.test.TestServlet，这是一个自定义的类。  
  <init-param> 标签定义了初始化这个 servlet 时的一些参数，经过这个配置，在servlet中能够调用下面的方法来
  获得参数名对应的值，也就是 Tommy。
  > getServletConfig().getInitParameter("userName")
  
  而 <servlet-mapping> 标签是用来指定某个 servlet 处理的请求路径，这里是指请求中所有的.do文件都会经过
  TestServlet处理。
  ```xml
  <servlet>
    <servlet-name>servlet1</servlet-name>
    <servlet-class>net.test.TestServlet</servlet-class>
    <init-param>
      <param-name>userName</param-name>
      <param-value>Tommy</param-value>
    </init-param>
    <init-param>
      <param-name>E-mail</param-name>
      <param-value>Tommy@163.com</param-value>
    </init-param>
  </servlet>
  <servlet-mapping>
    <servlet-name>servlet1</servlet-name>
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>
  ```
 
- context-param 标签： 定义整个 web 应用范围内的初始化参数  
  在 servlet 中可以通过下面方法来获取参数的值：
  > getServletContext().getInitParameter("param1')

  ```xml
  <context-param>
    <param-name>param1</para-name>
    <param-value>test</param-value>
    <description>It is a test parameter.</description>
  </context-param>
  ```

- filter 标签： 设置过滤器，比如设置一个编码过滤器，过滤所有资源  
  ```xml
  <filter>
    <filter-name>XXXCharaSetFilter</filter-name>
    <filter-class>net.test.CharSetFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>XXXCharaSetFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  ```
  
- listener 标签： 设置监听器，用来监听 web 应用的事件，如服务器启动和停止。  
  ```xml
  <listener>
    <listener-class>监听器类的完整路径</listener-class>
  </listener>
  ```

- session-config 标签： 用来设置session过期时间，时间以分钟为单位  
  ```xml
  <session-config>
    <session-timeout>60</session-timeout>
  </session-config>
  ```