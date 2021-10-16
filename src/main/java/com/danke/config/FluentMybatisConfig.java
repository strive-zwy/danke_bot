package com.danke.config;

import cn.org.atool.fluent.mybatis.spring.MapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FluentMybatisConfig {
    @Bean
    public MapperFactory mapperFactory() {
        return new MapperFactory();
    }
}