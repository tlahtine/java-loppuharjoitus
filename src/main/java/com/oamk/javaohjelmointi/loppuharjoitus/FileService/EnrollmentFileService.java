package com.oamk.javaohjelmointi.loppuharjoitus.FileService;

import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.Enrollment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class EnrollmentFileService {
    //write enrollments to file
    public void writeEnrollmentsToFile(List<Enrollment> enrollments) {
        try {
            FileWriter fileWriter = new FileWriter("enrollments.txt", false);
            for (Enrollment enrollment : enrollments) {
                fileWriter.write(enrollment.getCourseID() + "," + enrollment.getStudentID() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //read enrollments from file
    public List<Enrollment> readEnrollmentsFromFile(){
        List<Enrollment> enrollments = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File("enrollments.txt"));
            while(scanner.hasNextLine()){
                String[] enrollment = scanner.nextLine().split(",");
                Enrollment courseEnrollment = new Enrollment();
                if(enrollment.length == 2){
                    courseEnrollment.setCourseID(Integer.parseInt(enrollment[0]));
                    courseEnrollment.setStudentID(Integer.parseInt(enrollment[1]));
                }
                enrollments.add(courseEnrollment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enrollments;
    }
}
