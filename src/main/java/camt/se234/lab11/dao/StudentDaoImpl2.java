package camt.se234.lab11.dao;

import camt.se234.lab11.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl2 implements StudentDao {
    List<Student> students;
    public StudentDaoImpl2(){
        students = new ArrayList<>();
        students.add(new Student("123","A","temp",2.33));
        students.add(new Student("124","B","temp1",2.33));
        students.add(new Student("125","C","temp2",3.33));
        students.add(new Student("126","D","temp3",4.00));
        students.add(new Student("127","E","temp4",0.33));

    }

    @Override
    public List<Student> findAll() {
        return this.students;
    }
}
