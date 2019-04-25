package com.cloudsell.apigetway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 跨域配置
 * @author Haiyu
 * @date 2019/4/19 22:20
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        // 支持Cookie跨域
        config.setAllowCredentials(true);
        // 支持哪些原始域,"*"表示全支持
        config.setAllowedOrigins(Arrays.asList("*"));
        // 支持哪些请求方法
        config.setAllowedMethods(Arrays.asList("*"));
        // 支持哪些头
        config.setAllowedHeaders(Arrays.asList("*"));
        // 缓存时间，在该时间段内，对于相同的跨域请求就不会进行检查了
        config.setMaxAge(300L);
        // /**表示所有路径
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }
}
