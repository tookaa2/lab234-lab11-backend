package camt.se234.lab11.entity;

public class Student {
    String studentId;
    String name;
    String surName;
    Double gpa;

    public Student(String studentId, String name, String surName, Double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.surName = surName;
        this.gpa = gpa;
    }
  public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Double getGpa() {
        return gpa;
    }
    
}
