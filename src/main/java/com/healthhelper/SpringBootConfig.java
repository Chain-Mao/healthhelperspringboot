package com.healthhelper;

import com.healthhelper.inteceptors.PortalLoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
public class SpringBootConfig implements WebMvcConfigurer {

    @Autowired
    PortalLoginCheckInterceptor portalLoginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> excludeUrl = new ArrayList<>();
        excludeUrl.add("/portal/user/login.do");
        excludeUrl.add("/portal/user/register.do");
            registry.addInterceptor(portalLoginCheckInterceptor).addPathPatterns("/portal/user/**") //添加需要拦截的路径，**代表拦截user下多个路径
                .excludePathPatterns(excludeUrl); //排除不需要拦截的路径
    }
}

