package com.example.write_to_mongo.Config;

import java.time.LocalDateTime;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.write_to_mongo.Entity.Student;
import com.example.write_to_mongo.Entity.TimeStamp;
import com.example.write_to_mongo.Repository.StudentRepository;
import com.example.write_to_mongo.Repository.Impl.StudentRepositoryImpl;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



@Configuration
public class MongoConfig {

    @Value("${spring.mongodb.user}")
    private String user;

    @Value("${spring.mongodb.password}")
    private String password;

    // private String connectionString = "mongodb+srv://"+user+":"+password+"@cluster0.uni5bpo.mongodb.net/?retryWrites=true&w=majority";
    
    @Value("${spring.mongodb.dbName}")
    private String dbName;

    @Value("${spring.mongodb.tableName}")
    private String tableName;
    
    @Value("${spring.data.mongodb.database}")
    private String timeDb;
    
    @Value("${spring.data.mongodb.collection}")
    private String lastUpdateTimeDb;


    @Bean
    public MongoClient getMongoClient() {
    	final String connectionString = "mongodb+srv://"+user+":"+password+"@cluster0.uni5bpo.mongodb.net/?retryWrites=true&w=majority";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        
        MongoClient mongoClient= MongoClients.create(settings);
        return mongoClient;
    }
    @Bean
    public MongoCollection<Student> getStudentCollection(){

       MongoClient mongoClient=getMongoClient();
        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoDatabase mongoDatabase= mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);
        MongoCollection<Student> mongoCollection= mongoDatabase.getCollection(tableName,Student.class);

        return mongoCollection;

    }
    @Bean 
    public MongoCollection<TimeStamp> getTimeStampCollection(){
    	MongoClient mongoClient = getMongoClient();
    	CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
    	org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    	        
    	MongoDatabase mongoDatabase =  mongoClient.getDatabase(timeDb).withCodecRegistry(pojoCodecRegistry);
    	return mongoDatabase.getCollection(lastUpdateTimeDb,TimeStamp.class);
    }

    @Bean
    public StudentRepository studentRepository(){
        return new StudentRepositoryImpl();
    }
}
