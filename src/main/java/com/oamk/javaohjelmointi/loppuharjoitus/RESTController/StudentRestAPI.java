package com.oamk.javaohjelmointi.loppuharjoitus.RESTController;

import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.Student;
import com.oamk.javaohjelmointi.loppuharjoitus.DataService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentRestAPI {
    @Autowired
    StudentService studentService;
    String backToMain = "<br><br><a href='http://localhost:8080'><button>Palaa pääsivulle</button></a>";
    //Get all students
    @GetMapping("students")
    public String getStudents() {
        StringBuilder studentString = new StringBuilder();
        for(Student student : studentService.getStudents())
        {
            studentString.append("<br><br>Oppilas ID: ").append(student.getId());
            studentString.append("<br>Etunimi: ").append(student.getFirstName());
            studentString.append("<br>Sukunimi: ").append(student.getLastName());
            studentString.append("<br>Sähköposti: ").append(student.getEmail());
            studentString.append("<br>Puhelin: ").append(student.getPhone());
        }
        if(studentString.isEmpty()){
            studentString.append("<br><br>Ei opiskelijoita");
        }
        return "" + studentString + backToMain;
    }

    //Add one student
    @PostMapping("addstudent")
    public String addStudent(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email,
                             @RequestParam String phone) {
        Student student = new Student(firstName, lastName, email, phone);
        return studentService.addStudent(student) + backToMain;
    }

    //Edit student (params: id)
    @PutMapping("student/{id}")
    public String editStudent(@PathVariable int id, @RequestBody Student student) {
        return studentService.editStudent(id, student) + backToMain;
    }
}
