//package hello;
//
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//
//import com.mongodb.MongoClient;
//
//@Configuration
//public class SpringMongoConfig1 {
//
//	public @Bean
//	MongoDbFactory mongoDbFactory() throws Exception {
//		return new SimpleMongoDbFactory(new MongoClient(), "grockr");
//	}
//
//	public @Bean
//	MongoTemplate mongoTemplate() throws Exception {
//		
//		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
//				
//		return mongoTemplate;
//		
//	}
//	ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig1.class);
//    MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
//
//}