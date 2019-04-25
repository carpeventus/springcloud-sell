package com.cloudsell.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author Haiyu
 * @date 2019/3/28 10:30
 */
@Component
@ConfigurationProperties(prefix = "person")
@RefreshScope
@Data
public class PersonConfig {
    private String name;
    private Integer age;
}
