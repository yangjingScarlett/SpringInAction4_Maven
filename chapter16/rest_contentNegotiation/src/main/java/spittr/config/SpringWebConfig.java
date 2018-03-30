package spittr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by yangjing on 2018/3/26
 */
@Configuration
public class SpringWebConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringMVCConfig.class);
        servletContext.addListener(new ContextLoaderListener(context));
        context.setServletContext(servletContext);

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispather", new DispatcherServlet(context));
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");
        dynamic.setAsyncSupported(true);//开启异步推送，这是实现服务器端推送的一种方式
        dynamic.setMultipartConfig(new MultipartConfigElement("e:/upload/"));
    }

}
