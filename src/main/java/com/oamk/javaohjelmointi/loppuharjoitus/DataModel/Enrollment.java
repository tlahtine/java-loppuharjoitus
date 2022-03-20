package com.oamk.javaohjelmointi.loppuharjoitus.DataModel;

import java.util.List;

public class Enrollment {
    private int id;
    private int courseID;
    private int studentID;

    private static int counter = 0;

    public Enrollment(int courseID, int studentID){
        this.courseID = courseID;
        this.studentID = studentID;
        this.id = counter++;
    }

    public Enrollment(){
        this(-1, -1);
    }

    public int getId() {
        return id;
    }

    public int getCourseID() {
        return courseID;
    }
    public int getStudentID(){
        return studentID;
    }
    public void setStudentID(int studentID){
        this.studentID = studentID;
    }
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
