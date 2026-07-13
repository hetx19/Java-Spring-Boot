package net.java.spring.boot.controllers;

import net.java.spring.boot.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent() {
        Student student = new Student("1", "Harshal", "Savaliya");
        return student;
    }

    // http://localhost:8080/students
    @GetMapping("students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("1", "Harshal", "Savaliya"));
        students.add(new Student("2", "Ramesh", "Sharma"));
        students.add(new Student("3", "Deepak", "Kumar"));
        students.add(new Student("4", "Priya", "Verma"));

        return students;
    }

    // Rest API with Path Variable

    // http://localhost:8080/students/1/harshal/savaliya
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student getStudentDetails(@PathVariable("id") String rollNumber, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName) {
        return new Student(rollNumber, firstName, lastName);
    }

    // Rest API with Request Parameters

    // http://localhost:8080/students/query?id=1&firstname=harshal&lastname=savaliya

    @GetMapping("students/query")
    public Student getStudentByRollNumber(@RequestParam("id") String rollNumber, @RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
        return new Student(rollNumber, firstName, lastName);
    }
}
