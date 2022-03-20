package com.oamk.javaohjelmointi.loppuharjoitus.DataService;

import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.Course;
import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.Enrollment;
import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.Student;
import com.oamk.javaohjelmointi.loppuharjoitus.FileService.EnrollmentFileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {
    private final List<Enrollment> enrollments;

    public EnrollmentService(){
        EnrollmentFileService enrollmentFileService = new EnrollmentFileService();
        enrollments = enrollmentFileService.readEnrollmentsFromFile();
    }

    public String addStudentToCourse(int courseID, int studentID){
        StudentService studentService = new StudentService();
        List<Student> students = studentService.getStudents();
        CourseService courseService = new CourseService();
        List<Course> courses = courseService.getCourses();
        if(studentID >= students.size()){
            return "Opiskelijaa ei löydy";
        }
        if(courseID >= courses.size()){
            return "Kurssia ei löydy";
        }
        for(Enrollment enrollment : enrollments){
            if(enrollment.getCourseID() == courseID && enrollment.getStudentID() == studentID){
                return "Opiskelija on jo kurssilla";
            }
        }
        enrollments.add(new Enrollment(courseID, studentID));
        //write to file
        EnrollmentFileService enrollmentFileService = new EnrollmentFileService();
        enrollmentFileService.writeEnrollmentsToFile(enrollments);
        return "Opiskelija lisätty kurssille";
    }

    public String showStudentsOnCourse(int courseID){
        CourseService courseService = new CourseService();
        List<Course> courses = courseService.getCourses();
        if(courseID >= courses.size()){
            return "Kurssia ei löydy";
        }
        String courseName = courses.get(courseID).getName();
        StudentService studentService = new StudentService();
        List<Student> students = studentService.getStudents();
        StringBuilder returnString = new StringBuilder();
        boolean studentsExist = false;
        for(Enrollment enrollment : enrollments){
            if(enrollment.getCourseID() == courseID){
                Student student = students.get(enrollment.getStudentID());
                returnString.append("<br><br>Etunimi: ").append(student.getFirstName());
                returnString.append("<br>Sukunimi: ").append(student.getLastName());
                returnString.append("<br>Sähköposti: ").append(student.getEmail());
                returnString.append("<br>Puhelin: ").append(student.getPhone());
                studentsExist = true;
            }
        }
        if(!studentsExist){
            returnString.append("Kurssilla ei ole opiskelijoita");
        }
        return "<h2>" + courseName + "</h2>" + returnString;
    }
}
