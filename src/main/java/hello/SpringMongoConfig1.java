package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.MongoClient;

@Configuration
public class SpringMongoConfig1 {

	public @Bean MongoTemplate mongoTemplate() throws Exception {

		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("localhost"), "grockr");
		return mongoTemplate;

	}

}