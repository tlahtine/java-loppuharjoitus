package com.oamk.javaohjelmointi.loppuharjoitus.DataModel;

import java.util.List;

public class CourseEnrollment {
    private int id;
    private int courseID;
    private List<Integer> students;

    private static int counter = 0;

    public CourseEnrollment(int courseID, int studentID){
        this.courseID = courseID;
        students.add(studentID);
    }

    public CourseEnrollment(){
        this(-1, -1);
    }

    public int getId() {
        return id;
    }

    public int getCourseID() {
        return courseID;
    }
    public List<Integer> getStudents(int courseID){
        return students;
    }
    public void setStudents(List<Integer> students){
        this.students = students;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
