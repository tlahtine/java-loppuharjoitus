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
        boolean studentExists = false;
        for(Student student : students){
            if(student.getId() == studentID){
                studentExists = true;
                break;
            }
        }
        if(!studentExists){
            return "Opiskelijaa ei löydy";
        }
        boolean courseExists = false;
        for(Course course : courses){
            if(course.getId() == courseID){
                courseExists = true;
                break;
            }
        }
        if(!courseExists){
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
        boolean courseExists = false;
        String courseName = "";
        for(Course course : courses){
            if(course.getId() == courseID){
                courseExists = true;
                courseName = course.getName();
                break;
            }
        }
        if(!courseExists){
            return "Kurssia ei löydy";
        }
        StudentService studentService = new StudentService();
        List<Student> students = new ArrayList<>();
        for(Enrollment enrollment : enrollments){
            if(enrollment.getCourseID() == courseID){
                Student student = studentService.getStudent(enrollment.getStudentID());
                students.add(student);
            }
        }
        StringBuilder returnString = new StringBuilder();
        if(students.size() == 0){
            returnString.append("Kurssilla ei ole opiskelijoita");
        }
        else{
            for(Student student : students){
                returnString.append("<br><br>Opiskelija ID: ").append(student.getId());
                returnString.append("<br>Etunimi: ").append(student.getFirstName());
                returnString.append("<br>Sukunimi: ").append(student.getLastName());
                returnString.append("<br>Sähköposti: ").append(student.getEmail());
                returnString.append("<br>Puhelin: ").append(student.getPhone());
            }
        }
        return "<h2>" + courseName + "</h2>" + returnString;
    }
}
