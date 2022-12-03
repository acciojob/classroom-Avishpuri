package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

    private HashMap<String,Student> studentMapping;
    private HashMap<String,Teacher> teacherMapping;
    private HashMap<String, List<String>> studentTeacherMapping;

    public StudentRepository(){
        this.studentMapping=new HashMap<>();
        this.teacherMapping=new HashMap<>();
        this.studentTeacherMapping=new HashMap<>();
    }
   public void saveStudent(Student student){
        studentMapping.put(student.getName(),student);
   }
    public void saveTeacher(Teacher teacher){
        teacherMapping.put(teacher.getName(),teacher);
    }
    public void pairStuTeacher(String student,String teacher){
        if(studentMapping.containsKey(student) && teacherMapping.containsKey(teacher)) {
            studentMapping.put(student, studentMapping.get(student));
            teacherMapping.put(teacher, teacherMapping.get(teacher));
            List<String> pair = new ArrayList<>();
            if(studentTeacherMapping.containsKey(teacher))
                pair=studentTeacherMapping.get(teacher);
                pair.add(student);
                studentTeacherMapping.put(teacher,pair);
        }
    }
    public Student findStudent(String student){
        return studentMapping.get(student);
    }
    public Teacher findTeacher(String teacher){
        return teacherMapping.get(teacher);
    }
    public List<String> findStudentName(String teacher){
        List<String> stuNames=new ArrayList<>();
        if(studentTeacherMapping.containsKey(teacher))
            stuNames=studentTeacherMapping.get(teacher);
        return stuNames;
    }
    public List<String> findallStu(){
        List<String> allStudents=new ArrayList<>(studentMapping.keySet());
        return allStudents;
    }
    public void deleteStuTeacher(String teacher){
        List<String> teacherStuNames=new ArrayList<>();
        if(studentTeacherMapping.containsKey(teacher)){
            teacherStuNames=studentTeacherMapping.get(teacher);
            for(String search:teacherStuNames){
                if(studentMapping.containsKey(search)){
                    studentMapping.remove(search);
                }
            }
            studentTeacherMapping.remove(teacher);
        }
        if(teacherMapping.containsKey(teacher)){
            teacherMapping.remove(teacher);
        }
    }
    public void deleteAllData(){
      HashSet<String> delTeacher=new HashSet<>();
      for(String t:teacherMapping.keySet()){
          for(String s:studentTeacherMapping.get(t)){
              delTeacher.add(s);
          }
      }
      for(String s:delTeacher){
          if(studentMapping.containsKey(s)){
              studentMapping.remove(s);
          }
      }
      teacherMapping.clear();
    }
}
