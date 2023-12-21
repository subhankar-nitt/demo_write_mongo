package com.example.write_to_mongo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.write_to_mongo.Entity.Student;
import com.example.write_to_mongo.Service.StudentService;

@RestController
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @PostMapping(path="/home/saveData",consumes = MediaType.APPLICATION_JSON_VALUE, 
    produces = MediaType.APPLICATION_JSON_VALUE)
    public void saveStudentsData(@RequestBody Student student){
        System.out.println("In Controller....");
        studentService.saveStudentDetatils(student);
        studentService.saveLastUpdateDate();
    }
}
