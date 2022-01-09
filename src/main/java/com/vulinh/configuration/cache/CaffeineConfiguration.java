package com.vulinh.configuration.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.vulinh.constant.CacheConstant;
import com.vulinh.constant.PropertyConstant;
import com.vulinh.helper.ObjectMapperWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableConfigurationProperties(CaffeineConfigurationProperties.class)
@ConditionalOnProperty(
    value = CacheConstant.CAFFEINE_PROPERTIES + ".enabled",
    havingValue = PropertyConstant.TRUE
)
@Slf4j
@RequiredArgsConstructor
public class CaffeineConfiguration {

    private final ObjectMapperWrapper objectMapperWrapper;

    @Bean
    public Caffeine<Object, Object> caffeine(CaffeineConfigurationProperties properties) {
        return Caffeine.from(properties.getSpec());
    }

    @Bean
    public CacheManager caffeineCacheManager(
        Caffeine<Object, Object> caffeine,
        CaffeineConfigurationProperties caffeineConfigurationProperties
    ) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();

        var cacheNames = caffeineConfigurationProperties.getCacheNames();
        
        caffeineCacheManager.setCacheNames(StringUtils.isBlank(cacheNames) ? null : Arrays.asList(cacheNames.trim().split(",")));
        caffeineCacheManager.setCaffeine(caffeine);

        log.info("Properties: {}", objectMapperWrapper.toPrettyJSON(caffeineConfigurationProperties));
        log.info("Config: {}", objectMapperWrapper.toPrettyJSON(caffeineCacheManager));

        return caffeineCacheManager;
    }
}