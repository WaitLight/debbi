package org.dl.debbi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * swagger配置
 *
 * @author Dean
 * @version 0.0.1
 */
@Component
@ConfigurationProperties(prefix = "swagger")
@Data
public class SwaggerSetting {
    private String title;
}
