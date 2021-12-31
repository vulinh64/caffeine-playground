package com.vulinh.configuration.cache;

import com.vulinh.constant.CacheConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(CacheConstant.CAFFEINE_PROPERTIES)
@Data
public class CaffeineConfigurationProperties {

    private String cacheNames;
    private String spec;

}