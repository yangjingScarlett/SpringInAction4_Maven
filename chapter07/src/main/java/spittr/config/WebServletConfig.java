package spittr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by yangjing on 2018/1/16
 */
@Configuration
public class WebServletConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebMvcConfig.class);
        context.setServletContext(servletContext);

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
        dynamic.setAsyncSupported(true);//开启异步方法支持，这是实现服务器端推送的一种方式
        dynamic.setMultipartConfig(new MultipartConfigElement("E:/upload/", 2097152, 4194304, 0));
    }

}
