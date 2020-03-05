package com.waibao.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author zty
 * @email i@merryyou.cn
 * @since 1.0
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${filepath.video}")
    private String videoPath;
    @Value("${filepath.picture}")
    private String picturePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/video/**").addResourceLocations("file:"+videoPath);
        registry.addResourceHandler("/picture/**").addResourceLocations("file:"+picturePath);
        super.addResourceHandlers(registry);
    }
}
