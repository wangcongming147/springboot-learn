package com.learn.springboot.mongodb.multisource2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = "com.kxlist.statistics.domain.statis", mongoTemplateRef = "statisMongo")
public class StatisMongoMongoTemplate {
	
	@Autowired
	@Qualifier("statisMongoProperties")
	private MongoProperties mongoProperties;

	@Primary
	@Bean(name = "statisMongo")
	public MongoTemplate statisMongoTemplate() throws Exception {
		return new MongoTemplate(statisFactory(this.mongoProperties));
	}

	@Bean
	@Primary
	public MongoDbFactory statisFactory(MongoProperties mongoProperties)
			throws Exception {

		ServerAddress serverAdress = new ServerAddress(mongoProperties.getUri());
		return new SimpleMongoDbFactory(new MongoClient(serverAdress),
				mongoProperties.getDatabase());

	}
}