package com.gujun.springboot02.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Configuration
public class SpringConfig01 {

    @Autowired
    private SqlSessionFactory sqlSessionFactory=null;

    //配置MapperScannerConfigurer来通过扫描来装配Mybatis所需组件；
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
        //此处的SqlSessionFactory，是由SpringBoot自动产生的；
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //定义扫描的包范围
        mapperScannerConfigurer.setBasePackage("com.gujun.springboot02.dao");
        mapperScannerConfigurer.setAnnotationClass(Repository.class);
        return mapperScannerConfigurer;
    }

}
