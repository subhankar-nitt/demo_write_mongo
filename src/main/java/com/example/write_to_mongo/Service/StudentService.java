package com.example.write_to_mongo.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.write_to_mongo.Entity.Student;
import com.example.write_to_mongo.Entity.TimeStamp;
import com.example.write_to_mongo.Repository.StudentRepository;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    public void saveStudentDetatils(Student student){
        studentRepository.saveEntry(student);
    }
    public void saveLastUpdateDate() {
    	
    	String fieldName="date";
    	Date now = new Date();
    	TimeStamp timeStamp= new TimeStamp();
    	timeStamp.setDate(now);
    	studentRepository.saveLastUpdateTime(fieldName, timeStamp);
    	
    }
}
