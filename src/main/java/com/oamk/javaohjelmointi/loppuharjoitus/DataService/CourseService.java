package com.oamk.javaohjelmointi.loppuharjoitus.DataService;

import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.ClassRoomCourse;
import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.Course;
import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.OnlineCourse;
import com.oamk.javaohjelmointi.loppuharjoitus.FileService.CourseFileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private List<Course> courses;

    public CourseService(){
        CourseFileService courseFileService = new CourseFileService();
        courses = courseFileService.readCoursesFromFile();
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public Course getCourse(int id){
        Course course;
        if(id < courses.size() && id >= 0){
            if(courses.get(id) instanceof ClassRoomCourse){
                course = new ClassRoomCourse();
            }
            else{
                course = new OnlineCourse();
            }
            course = courses.get(id);
        }
        else{
            course = null;
        }
        return course;
    }

    public String addCourse(Course course){
        courses.add(course);
        //write to file
        CourseFileService courseFileService = new CourseFileService();
        courseFileService.writeCoursesToFile(courses);
        return "Kurssi lisätty";
    }

    public String editCourse(int id, Course course){
        if(id <  courses.size() && id >= 0){
            courses.set(id, course);
            //write to file
            CourseFileService courseFileService = new CourseFileService();
            courseFileService.writeCoursesToFile(courses);
            return "Tiedot muutettu";
        }
        else{
            return "Kurssia ei löydy";
        }
    }
}
