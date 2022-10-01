package com.vulinh.configuration.cache.keygenerator;

import com.vulinh.constant.CacheConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component(CacheConstant.FIND_BY_ID_GEN_BEAN)
@Slf4j
public class FindByIdKeyGenerator implements KeyGenerator {

  @Override
  public Object generate(Object target, Method method, Object... params) {
    var id = String.valueOf(params[0]);

    var result = String.format("%s-%s-id-%s", target.getClass().getName(), method.getName(), id);

    log.debug("Generated key: {}", result);

    return result;
  }
}
