package com.oamk.javaohjelmointi.loppuharjoitus.DataModel;

public class OnlineCourse extends Course{

    public OnlineCourse(String code, String name, int credits, String teacher) {
        super(code, name, credits, teacher);
    }
    public OnlineCourse(){
        this("", "", 0, "");
    }
}
