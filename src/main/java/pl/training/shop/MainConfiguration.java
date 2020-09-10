package pl.training.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianExporter;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
public class MainConfiguration {

    @Bean(name = "/users")
    public RemoteExporter userService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(new ArrayListUserService());
        exporter.setServiceInterface(UserService.class);
        return exporter;
    }

    @Bean
    public RemoteExporter userServiceRemote() {
        HttpInvokerProxyFactoryBean proxyFactoryBean = new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceInterface(UserService.class);
        proxyFactoryBean.setServiceUrl("http://localhost:8080/users");
        return proxyFactoryBean;
   }

}
