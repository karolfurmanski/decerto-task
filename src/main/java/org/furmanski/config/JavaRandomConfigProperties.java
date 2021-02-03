package org.furmanski.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.Min;

@Configuration
@ConfigurationProperties(prefix = "javarandom")
@Data
public class JavaRandomConfigProperties {

    @Min(1)
    private Integer max;
}
