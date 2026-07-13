package net.java.spring.boot.controllers;

import net.java.spring.boot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    // GET Methods

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student("1", "Harshal", "Savaliya");
//        return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok().header("custom-header", "Harshal").body(student);
    }


    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();

        students.add(new Student("1", "Harshal", "Savaliya"));
        students.add(new Student("2", "Ramesh", "Sharma"));
        students.add(new Student("3", "Deepak", "Kumar"));
        students.add(new Student("4", "Priya", "Verma"));

        return ResponseEntity.ok(students);
    }

    // Rest API with Path Variable

    // http://localhost:8080/students/1/harshal/savaliya
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> getStudentDetails(@PathVariable("id") String rollNumber, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName) {
        Student student = new Student(rollNumber, firstName, lastName);

        return ResponseEntity.ok(student);
    }

    // Rest API with Request Parameters

    // http://localhost:8080/students/query?id=1&firstname=harshal&lastname=savaliya

    @GetMapping("query")
    public ResponseEntity<Student> getStudentByRollNumber(@RequestParam("id") String rollNumber, @RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
        Student student = new Student(rollNumber, firstName, lastName);

        return ResponseEntity.ok(student);
    }

    // POST Methods
    // http://localhost:8080/students/create

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getRollNumber());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // PUT Methods
    // http://localhost:8080/students/update/1
    @PutMapping("update/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") String rollNumber) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return ResponseEntity.ok(student);
    }

    // DELETE Methods
    // http://localhost:8080/students/delete/1
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") String rollNumber) {
        System.out.println(rollNumber);
        return ResponseEntity.ok("Student deleted Successfully");
    }
}
