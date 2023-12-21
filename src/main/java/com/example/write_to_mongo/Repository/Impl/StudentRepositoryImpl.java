package com.example.write_to_mongo.Repository.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators.Lt;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.write_to_mongo.Entity.Student;
import com.example.write_to_mongo.Entity.TimeStamp;
import com.example.write_to_mongo.Repository.StudentRepository;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

    @Autowired
    public MongoCollection<Student> mongoCollection;
    
    @Autowired
    public MongoCollection<TimeStamp> lastDateTimeCollection;

    @Override
    public void saveEntry(Student student) {
        mongoCollection.insertOne(student);
    } 
  
	@Override
	public void saveLastUpdateTime(String fieldName,TimeStamp timeStamp) {
		if(lastDateTimeCollection.countDocuments()==0) {
			lastDateTimeCollection.insertOne(timeStamp);
		}else {
			DBObject dbObject=BasicDBObjectBuilder.start("$lt",timeStamp.getDate()).get();
			BasicDBObject basicDBObject=new BasicDBObject();
			basicDBObject.append(fieldName,dbObject);
			lastDateTimeCollection.findOneAndReplace(basicDBObject,timeStamp);
		}
	}
	
    
}
