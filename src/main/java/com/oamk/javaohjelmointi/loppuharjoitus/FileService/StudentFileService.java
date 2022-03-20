package com.oamk.javaohjelmointi.loppuharjoitus.FileService;

import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.Student;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class StudentFileService {
    public void writeStudentsToFile(List<Student> students){
        try{
            FileWriter fileWriter = new FileWriter("students.txt", false);
            for(Student student : students){
                fileWriter.write(student.getFirstName() + "," + student.getLastName() + "," + student.getEmail()
                + "," + student.getPhone() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> readStudentsFromFile(){
        List<Student> students = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File("students.txt"));
            while(scanner.hasNextLine()){
                String[] student = scanner.nextLine().split(",");
                if(student.length == 4){
                    Student s = new Student(student[0], student[1], student[2], student[3]);
                    s.setId(students.size());
                    students.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }
}
