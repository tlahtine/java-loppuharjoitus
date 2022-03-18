package com.oamk.javaohjelmointi.loppuharjoitus;

public abstract class Course {
    private int id;
    private String code;
    private String name;
    private int credits;
    private String teacher;

    private static int counter = 0;

    public Course(String code, String name, int credits, String teacher){
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.teacher = teacher;
        this.id = counter++;
    }

    public int getId(){
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getCredits() {
        return credits;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }
}
