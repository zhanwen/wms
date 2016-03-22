package phoenics.wms.config;

import java.util.ArrayList;
import java.util.List;

//import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;



@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"phoenics.wms.controller"})
public class WebConfig extends WebMvcConfigurerAdapter{
	@Autowired
	LocalContainerEntityManagerFactoryBean entityManagerFactory;
	@Bean
    public InternalResourceViewResolver jspviewResolver(){
        InternalResourceViewResolver inter = new InternalResourceViewResolver();
       inter.setPrefix("/WEB-INF/jsp/");
        inter.setSuffix(".jsp");
        return inter;
    }
	//注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	OpenEntityManagerInViewInterceptor o=new OpenEntityManagerInViewInterceptor();
    	o.setEntityManagerFactory(entityManagerFactory.getObject());
    	registry.addWebRequestInterceptor(o);
    }
    /*
    //自定义拦截器
    @Bean
    public UserInterceptor userInterceptor(){
        return new UserInterceptor();
    }
    */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/p/**").addResourceLocations("/p/");
        registry.addResourceHandler("/s/**").addResourceLocations("/s/");
    }
    @Bean
    public ViewResolver jsonViewResolver() {
        return   new JsonViewResolver();
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
    	/*这个地方很重要,如果没有这里,在没有任何controller的时候，html类的静态资源不能访问
    	*/
    	configurer.enable();    	
    }
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
    }
    @Bean
    public ViewResolver contentNegotiatingViewResolver( ContentNegotiationManager manager) {
    	 ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
         resolver.setContentNegotiationManager(manager);
         List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
         resolvers.add(jspviewResolver());
         resolvers.add(jsonViewResolver());
         resolver.setViewResolvers(resolvers);
         return resolver;
    }
}