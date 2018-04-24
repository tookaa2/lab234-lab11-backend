package camt.se234.lab11.service;


import camt.se234.lab11.dao.StudentDao;
import camt.se234.lab11.dao.StudentDaoImpl;
import camt.se234.lab11.dao.StudentDaoImpl2;
import camt.se234.lab11.entity.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class StudentServiceImplTest {
    StudentDao studentDao;
    StudentServiceImpl studentService;

    @Before
    public void setup(){
        studentDao = mock(StudentDao.class);
        studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
    }


    @Test
    public void testWithMock(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("123","A","temp",2.33));
        mockStudents.add(new Student("124","A","temp",2.33));
        mockStudents.add(new Student("223","A","temp",2.33));
        mockStudents.add(new Student("224","A","temp",2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentById("123"),is(new Student("123","A","temp",2.33)));
        assertThat(studentService.getAverageGpa(),is(closeTo(2.33,0.01)));


    }
    @Test
    public void testWithMock2(){

        List<Student> mockStudents = new ArrayList<>();

        mockStudents.add(new Student("123","A","temp",2.53));
        mockStudents.add(new Student("124","A","temp",2.53));
        mockStudents.add(new Student("223","A","temp",2.53));
        mockStudents.add(new Student("224","A","temp",2.53));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentById("224"),is(new Student("224","A","temp",2.53)));
        assertThat(studentService.getAverageGpa(),is(closeTo(2.53,0.01)));
    }

    @Test
    public void testWithMock3(){

        List<Student> mockStudents = new ArrayList<>();

        mockStudents.add(new Student("123","A","temp",4.00));
        mockStudents.add(new Student("124","A","temp",4.00));
        mockStudents.add(new Student("223","A","temp",4.00));
        mockStudents.add(new Student("224","A","temp",4.00));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentById("124"),is(new Student("124","A","temp",4.00)));
        assertThat(studentService.getAverageGpa(),is(closeTo(4.00,0.01)));
    }

    @Test
    public void testFindById() {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
        assertThat(studentService.findStudentById("123"),is(new Student("123","A","temp",2.33)));
        assertThat(studentService.findStudentById("124"),is(new Student("124","B","temp1",1.33)));
        assertThat(studentService.findStudentById("125"),is(new Student("125","C","temp2",3.33)));
        assertThat(studentService.findStudentById("126"),is(new Student("126","D","temp3",4.00)));


    }
    @Test
    public void testGetAverage(){
        StudentDaoImpl studentDao = new StudentDaoImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.setStudentDao(studentDao);
        assertThat(studentService.getAverageGpa(),is(closeTo(2.264,0.001)));

        StudentDaoImpl2 stu2 = new StudentDaoImpl2();
        studentService.setStudentDao(stu2);
        assertThat(studentService.getAverageGpa(),is(closeTo(2.464,0.001)));

}




    @Test
    public void testFindByPartOfId(){

        List<Student> mockStudents = new ArrayList<>();

        mockStudents.add(new Student("123","A","temp",2.33));
        mockStudents.add(new Student("124","B","temp",2.33));
        mockStudents.add(new Student("223","C","temp",2.33));
        mockStudents.add(new Student("224","D","temp",2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentByPartOfId("22"),hasItem(new Student("223","C","temp",2.33)));
        assertThat(studentService.findStudentByPartOfId("22"),hasItems(new Student("223","C","temp",2.33)
                , new Student("224","D","temp",2.33) ));

    }

    @Test
    public void testFindByPartOfIdMine(){

        List<Student> mockStudents = new ArrayList<>();

        mockStudents.add(new Student("111","A","temp",1.33));
        mockStudents.add(new Student("222","B","temp",4.00));
        mockStudents.add(new Student("223","C","temp",2.50));
        mockStudents.add(new Student("113","D","temp",3.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentByPartOfId("22"),hasItem(new Student("223","C","temp",2.50)));
        assertThat(studentService.findStudentByPartOfId("11"),hasItems(new Student("111","A","temp",1.33)
                , new Student("113","D","temp",3.33) ));

    }
    @Test(expected = NoDataException.class)
    public void testNoDataException(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("123","A","temp",2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentById("55"),nullValue());
    }

    @Test(expected = NoDataException.class)
    public void testNoDataExceptionZero(){
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student("123","A","temp",2.33));
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.findStudentByPartOfId("55"),nullValue());
    }

    @Test(expected = ArithmeticException.class)
    public void testNoDataExceptionGetAVG(){
        List<Student> mockStudents = new ArrayList<>();
        when(studentDao.findAll()).thenReturn(mockStudents);
        assertThat(studentService.getAverageGpa(),nullValue());
    }

}
