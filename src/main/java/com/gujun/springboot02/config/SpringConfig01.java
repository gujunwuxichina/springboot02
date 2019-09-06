package com.gujun.springboot02.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class) //让我们这个配置类在内置的配置类之后在配置
public class SpringConfig01 {

//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(){
//        SqlSessionTemplate sqlSessionTemplate=new SqlSessionTemplate(sqlSessionFactory);
//        return sqlSessionTemplate;
//    }

    //配置MapperScannerConfigurer来通过扫描来装配Mybatis所需组件；
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
//        //此处的SqlSessionFactory，是由SpringBoot自动产生的；
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
        //定义扫描的包范围
        mapperScannerConfigurer.setBasePackage("com.gujun.springboot02.dao");
        mapperScannerConfigurer.setAnnotationClass(Repository.class);
        return mapperScannerConfigurer;
    }

}
