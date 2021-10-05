package cn.com.yujiangjun.myim;

import cn.com.yujiangjun.myim.netty.Server;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.net.InetSocketAddress;

@SpringBootApplication
public class MyImApplication implements ApplicationRunner, ApplicationListener<ContextClosedEvent>, ApplicationContextAware {

    @Autowired
    private Server server;

    public static void main(String[] args) {
        SpringApplication.run(MyImApplication.class, args);
    }

    private ApplicationContext applicationContext;

    @Bean
    public CorsFilter corsFilter() {

        //创建CorsConfiguration对象后添加配置
        CorsConfiguration config = new CorsConfiguration();
        //设置放行哪些原始域
        config.addAllowedOrigin("*");
        //放行哪些原始请求头部信息
        config.addAllowedHeader("*");
        //暴露哪些头部信息
        config.addExposedHeader("*");
        //放行哪些请求方式
        config.addAllowedMethod("GET");     //get
        config.addAllowedMethod("PUT");     //put
        config.addAllowedMethod("POST");    //post
        config.addAllowedMethod("DELETE");  //delete
        config.addAllowedMethod("*");     //放行全部请求

        //是否发送Cookie
//        config.setAllowCredentials(true);

        //2. 添加映射路径
        UrlBasedCorsConfigurationSource corsConfigurationSource =
                new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);
        //返回CorsFilter
        return new CorsFilter(corsConfigurationSource);
    }

    @Autowired
    Server nettyServer;

    @Value("${netty.port}")
    private int port;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        nettyServer.start(new InetSocketAddress(port));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

    }
}
