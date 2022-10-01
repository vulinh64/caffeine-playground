package com.vulinh.helper;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperWrapper {

  public ObjectMapper delegate() {
    return MAPPER;
  }

  public <T> String toMinimizedJSON(T t) {
    return toJSONString(t, false);
  }

  public <T> String toPrettyJSON(T t) {
    return toJSONString(t, true);
  }

  public <T> T convert(Object object, Class<T> clazz) {
    return MAPPER.convertValue(object, clazz);
  }

  @SneakyThrows
  private static <T> String toJSONString(T t, boolean isPretty) {
    return isPretty ? PRETTY_WRITER.writeValueAsString(t) : MAPPER.writeValueAsString(t);
  }

  private static final ObjectMapper MAPPER;
  private static final ObjectWriter PRETTY_WRITER;

  static {
    MAPPER = new ObjectMapper();
    MAPPER.registerModule(new JavaTimeModule());
    MAPPER.setDateFormat(new StdDateFormat());

    PRETTY_WRITER =
        MAPPER.writer(
            new DefaultPrettyPrinter()
                .withObjectIndenter(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE)
                .withArrayIndenter(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE));
  }
}
