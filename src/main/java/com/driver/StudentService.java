package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void addStudent(Student student){
        studentRepository.saveStudent(student);
    }
    public void addTeacher(Teacher teacher){
        studentRepository.saveTeacher(teacher);
    }
    public void addStudentTeacherPair(String student,String teacher){
        studentRepository.pairStuTeacher(student,teacher);
    }
    public Student getStudentByName(String student){
        return studentRepository.findStudent(student);
    }
    public Teacher getTeacherByName(String teacher){
        return studentRepository.findTeacher(teacher);
    }
    public List<String> getStudentsByTeacherName(String teacher){
        return studentRepository.findStudentName(teacher);
    }
    public List<String> getAllStudents(){
        return studentRepository.findallStu();
    }
    public void deleteTeacherByName(String teacher){
        studentRepository.deleteStuTeacher(teacher);
    }
    public void deleteAllTeachers(){
        studentRepository.deleteAllData();
    }


}
