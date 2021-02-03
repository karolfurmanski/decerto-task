package org.furmanski.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "randomorg")
@Data
public class RandomOrgConfigProperties {
    private String key;
    private Integer min;
    private Integer max;
    private String jsonrpc;
    private String method;
    private Integer id;
    private String url;
}
