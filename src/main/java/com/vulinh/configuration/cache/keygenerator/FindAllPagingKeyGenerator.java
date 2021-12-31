package com.vulinh.configuration.cache.keygenerator;

import com.vulinh.constant.CacheConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component(CacheConstant.FIND_ALL_PAGING_GEN_BEAN)
@Slf4j
public class FindAllPagingKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        var pageable = (Pageable) params[0];
        
        var result = String.format("%s-%s-p%ss%s", target.getClass().getName(), method.getName(), pageable.getPageNumber(), pageable.getPageSize());

        log.debug("Generated key: {}", result);

        return result;
    }
}
