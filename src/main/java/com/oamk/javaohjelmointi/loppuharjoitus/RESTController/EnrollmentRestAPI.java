package com.oamk.javaohjelmointi.loppuharjoitus.RESTController;

import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.Enrollment;
import com.oamk.javaohjelmointi.loppuharjoitus.DataService.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EnrollmentRestAPI {
    @Autowired
    EnrollmentService enrollmentService;
    String backToMain = "<br><br><a href='http://localhost:8080'><button>Palaa pääsivulle</button></a>";
    //Get all enrollments
    @PostMapping("enrollments")
    public String getEnrollments(int courseID) {
        return enrollmentService.showStudentsOnCourse(courseID) + backToMain;
    }

    //Add one enrollment
    @PostMapping("addenrollment")
    public String addEnrollment(@RequestParam String courseID, @RequestParam String studentID) {
        return enrollmentService.addStudentToCourse(Integer.parseInt(courseID), Integer.parseInt(studentID)) + backToMain;
    }
}
