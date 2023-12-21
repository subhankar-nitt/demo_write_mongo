package com.example.write_to_mongo.Repository;

import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.example.write_to_mongo.Entity.Student;
import com.example.write_to_mongo.Entity.TimeStamp;

@Repository
public interface StudentRepository {
    
    public void saveEntry(Student student);
    public void saveLastUpdateTime(String fieldName,TimeStamp timeStamp);
}
