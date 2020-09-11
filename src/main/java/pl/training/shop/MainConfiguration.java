package pl.training.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianExporter;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@Configuration
public class MainConfiguration {

    /*@Bean(name = "/users")
    public HttpInvokerServiceExporter userService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(new ArrayListUserService());
        exporter.setServiceInterface(UserService.class);
        return exporter;
    }*/

    /*@Bean
    public HttpInvokerProxyFactoryBean userServiceRemote() {
        HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceInterface(UserService.class);
        proxyFactoryBean.setServiceUrl("http://localhost:8080/users");
        return proxyFactoryBean;
   }*/

   @Bean(name = "/users")
    public HessianServiceExporter userService() {
       HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(new ArrayListUserService());
        exporter.setServiceInterface(UserService.class);
        return exporter;
    }

    @Bean
    public HessianProxyFactoryBean userServiceRemote() {
        HessianProxyFactoryBean proxyFactoryBean = new HessianProxyFactoryBean();
        proxyFactoryBean.setServiceInterface(UserService.class);
        proxyFactoryBean.setServiceUrl("http://localhost:8080/users");
        return proxyFactoryBean;
    }

}
