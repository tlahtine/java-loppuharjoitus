package com.oamk.javaohjelmointi.loppuharjoitus.RESTController;

import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.ClassRoomCourse;
import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.Course;
import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.OnlineCourse;
import com.oamk.javaohjelmointi.loppuharjoitus.DataService.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseRestAPI {
    @Autowired
    CourseService courseService;
    String backToMain = "<br><br><a href='http://localhost:8080'><button>Palaa pääsivulle</button></a>";
    //Get all courses
    @GetMapping("courses")
    public String getCourses() {
        StringBuilder courseString = new StringBuilder();
        for(Course course : courseService.getCourses())
        {
            courseString.append("<br><br>Kurssi ID: ").append(course.getId());
            courseString.append("<br>Koodi: ").append(course.getCode());
            courseString.append("<br>Nimi: ").append(course.getName());
            courseString.append("<br>Opettaja: ").append(course.getTeacher());
            courseString.append("<br>Laajuus: ").append(course.getCredits());
            if(course instanceof ClassRoomCourse){
                courseString.append("<br>Luokka: ").append(((ClassRoomCourse) course).getClassCode());
            }
            else{
                courseString.append("<br>Luokka: Etänä");
            }
        }
        if(courseString.isEmpty()){
            courseString.append("<br><br>Ei kursseja");
        }
        return "" + courseString + backToMain;
    }

    //Add one course
    @PostMapping("addcourse")
    public String addCourse(@RequestParam String code, @RequestParam String name, @RequestParam String teacher,
                             @RequestParam String credits, @RequestParam String classCode) {
        Course course;
        if(classCode != ""){
            course = new ClassRoomCourse(code, name, Integer.parseInt(credits), teacher, classCode);
        }
        else
        {
            course = new OnlineCourse(code, name, Integer.parseInt(credits), teacher);
        }
        return courseService.addCourse(course) + backToMain;
    }

    //Edit course (params: id)
    @PutMapping("course/{id}")
    public String editCourse(@PathVariable int id, @RequestBody Course course) {
        return courseService.editCourse(id, course) + backToMain;
    }
}
