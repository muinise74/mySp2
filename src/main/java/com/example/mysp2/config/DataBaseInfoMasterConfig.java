package com.example.mysp2.config;

import com.example.mysp2.annotation.Master;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@ConfigurationProperties("test.datasource.master")
@MapperScan(annotationClass = Master.class, basePackages = "com.example.mysp2.proc", sqlSessionFactoryRef = "mySp2SqlSessionFactory")
public class DataBaseInfoMasterConfig extends HikariConfig {

    String mapperLocation = "mapper/*.xml";

    @Bean(name = "masterDataSource", destroyMethod = "close")
    public HikariDataSource masterDataSource() {
        return new HikariDataSource(this);
    }

    @Bean(name = "infoMasterSqlSessionFactory")
    @SneakyThrows
    public SqlSessionFactory infoMasterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) {
        return new mySp2SqlSessionFactory().getSqlFactory(masterDataSource, mapperLocation);
    }
}
