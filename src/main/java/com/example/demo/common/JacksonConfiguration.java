package com.example.demo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;

/**
 * Jackson配置
 */
@Configuration
public class JacksonConfiguration {
    /**
     * Jackson配置
     */
    @Bean
    public HttpMessageConverters customConverters() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                //.serializationInclusion(JsonInclude.Include.NON_NULL)
                .indentOutput(true) //缩进输出
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))    //日期时间格式化
                .serializerByType(Long.TYPE, new ToStringSerializer())  //long转String
                .serializerByType(Long.class, new ToStringSerializer());    //Long转String
        ObjectMapper objectMapper = builder.build();
        return new HttpMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper));
    }
}
