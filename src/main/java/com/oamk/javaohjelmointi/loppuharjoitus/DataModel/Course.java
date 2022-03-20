package com.oamk.javaohjelmointi.loppuharjoitus.DataModel;

public abstract class Course {
    private int id;
    private final String code;
    private final String name;
    private final int credits;
    private final String teacher;

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

    public void setId(int id) {
        this.id = id;
    }
}
