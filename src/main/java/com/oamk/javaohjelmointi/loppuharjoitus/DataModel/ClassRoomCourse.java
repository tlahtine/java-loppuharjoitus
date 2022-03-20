package com.oamk.javaohjelmointi.loppuharjoitus.DataModel;

public class ClassRoomCourse extends Course{
    private final String classCode;
    public ClassRoomCourse(String code, String name, int credits, String teacher, String classCode) {
        super(code, name, credits, teacher);
        this.classCode = classCode;
    }
    public ClassRoomCourse(){
        this("", "", 0, "", "");
    }

    public String getClassCode(){
        return classCode;
    }
}
