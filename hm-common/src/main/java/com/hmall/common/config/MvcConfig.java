package com.hmall.common.config;

import com.hmall.common.interceptors.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
//没加这个注解之前启动网关，发现报错了！，查看错误信息，发现文件找不到：
//WebMvcConfigurer，这玩意怎么会在网关里面报错呢？ 难道网关里也引用了hm-common
//查看pom文件,果然网关里也引了，网关也引了，说明网关里面也有MvcConfig了，但是它报错了！
//为什么？ 因为WebMvcConfigurer属于SpringMVC包下的，而网关的底层不是SpringMVC那一套，基于的是WebFlux
//所以报错
@ConditionalOnClass(DispatcherServlet.class)
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new UserInfoInterceptor());
    }
}
