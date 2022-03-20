package com.oamk.javaohjelmointi.loppuharjoitus.FileService;

import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.ClassRoomCourse;
import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.Course;
import com.oamk.javaohjelmointi.loppuharjoitus.DataModel.OnlineCourse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CourseFileService {
    public void writeCoursesToFile(List<Course> courses){
        try{
            FileWriter fileWriter = new FileWriter("courses.txt", false);
            for(Course course : courses){
                if(course instanceof ClassRoomCourse) {
                    fileWriter.write(course.getCode() + "," + course.getName() + "," + course.getCredits()
                            + "," + course.getTeacher() + "," + ((ClassRoomCourse) course).getClassCode()
                            + System.lineSeparator());
                }
                else{
                    fileWriter.write(course.getCode() + "," + course.getName() + "," + course.getCredits()
                            + "," + course.getTeacher() + System.lineSeparator());
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Course> readCoursesFromFile(){
        List<Course> courses = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File("courses.txt"));
            while(scanner.hasNextLine()){
                String[] course = scanner.nextLine().split(",");
                if(course.length == 4){
                    OnlineCourse onlineCourse = new OnlineCourse(course[0], course[1], Integer.parseInt(course[2]), course[3]);
                    onlineCourse.setId(courses.size());
                    courses.add(onlineCourse);
                }
                else if(course.length == 5){
                    ClassRoomCourse classRoomCourse = new ClassRoomCourse(course[0], course[1], Integer.parseInt(course[2]), course[3], course[4]);
                    classRoomCourse.setId(courses.size());
                    courses.add(classRoomCourse);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
