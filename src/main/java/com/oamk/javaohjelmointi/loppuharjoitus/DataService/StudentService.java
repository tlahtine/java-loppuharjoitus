package com.oamk.javaohjelmointi.loppuharjoitus.DataService;

import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.Student;
import com.oamk.javaohjelmointi.loppuharjoitus.FileService.StudentFileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();

    public StudentService(){
        StudentFileService studentFileService = new StudentFileService();
        students = studentFileService.readStudentsFromFile();
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudent(int id){
        Student student;
        if(id < students.size() && id >= 0){
            student = students.get(id);
        }
        else{
            student = null;
        }
        return student;
    }

    public String addStudent(Student student){
        students.add(student);
        //write to file
        StudentFileService studentFileService = new StudentFileService();
        studentFileService.writeStudentsToFile(students);
        return "Opiskelija lisätty";
    }

    public String editStudent(int id, Student student){
        if(id <  students.size() && id >= 0){
            students.set(id, student);
            //write to file
            StudentFileService studentFileService = new StudentFileService();
            studentFileService.writeStudentsToFile(students);
            return "Tiedot muutettu";
        }
        else{
            return "Opiskelijaa ei löydy";
        }
    }
}
