package com.learn.springboot.mongodb.multisource2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = "com.kxlist.statistics.domain.list", mongoTemplateRef = "listMongo")
public class ListMongoTemplate {
	@Autowired
	@Qualifier("listMongoProperties")
	private MongoProperties mongoProperties;

	@Bean(name = "listMongo")
	public MongoTemplate listTemplate() throws Exception {
		return new MongoTemplate(listFactory(this.mongoProperties));
	}

	@Bean
	public MongoDbFactory listFactory(MongoProperties mongoProperties)
			throws Exception {

		ServerAddress serverAdress = new ServerAddress(mongoProperties.getUri());

		return new SimpleMongoDbFactory(new MongoClient(serverAdress),
				mongoProperties.getDatabase());

	}
}