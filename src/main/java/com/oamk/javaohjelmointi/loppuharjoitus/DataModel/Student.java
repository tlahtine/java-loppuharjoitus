package com.oamk.javaohjelmointi.loppuharjoitus;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private static int counter = 0;

    public Student(String firstName, String lastName, String email, String phone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.id = counter++;
    }

    public Student(){
        this("", "", "", "");
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
