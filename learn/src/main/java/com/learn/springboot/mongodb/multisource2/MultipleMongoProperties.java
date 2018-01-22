package com.learn.springboot.mongodb.multisource2;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MultipleMongoProperties {
	
    @Bean(name="statisMongoProperties")
    @Primary
	@ConfigurationProperties(prefix = "spring.data.mongodb.statis")
    public MongoProperties statisMongoProperties() {
        System.out.println("-------------------- statisMongoProperties init ---------------------");
        return new MongoProperties();
    }

    @Bean(name="listMongoProperties")
    @ConfigurationProperties(prefix="spring.data.mongodb.list")
    public MongoProperties listMongoProperties() {
        System.out.println("-------------------- listMongoProperties init ---------------------");
        return new MongoProperties();
    }

}