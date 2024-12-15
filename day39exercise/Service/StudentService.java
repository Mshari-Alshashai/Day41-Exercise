package com.example.day39exercise.Service;

import com.example.day39exercise.Api.ApiException;
import com.example.day39exercise.DTO.CourseDTO;
import com.example.day39exercise.DTO.StudentDTO;
import com.example.day39exercise.Model.Course;
import com.example.day39exercise.Model.Student;
import com.example.day39exercise.Repository.CourseRepository;
import com.example.day39exercise.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        return convertToDTO(students);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Integer id, Student student) {
        if (studentRepository.findStudentById(id) == null) throw new ApiException("Student not found");
        Student updatedStudent = studentRepository.findStudentById(id);

        updatedStudent.setName(student.getName());
        updatedStudent.setAge(student.getAge());
        updatedStudent.setCourses(student.getCourses());
        updatedStudent.setMajor(student.getMajor());

        studentRepository.save(updatedStudent);
    }

    public void deleteStudent(Integer id) {
        if (studentRepository.findStudentById(id) == null) throw new ApiException("Student not found");
        studentRepository.deleteById(id);
    }

    public List<StudentDTO> convertToDTO(Collection<Student> students) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        List<CourseDTO> courseDTOS = new ArrayList<>();

        for (Student student : students) {

            for (Course course : student.getCourses()) {
                courseDTOS.add(new CourseDTO(course.getName()));
            }
            studentDTOS.add(new StudentDTO(student.getName(),student.getAge(),student.getMajor(),courseDTOS));
        }
        return studentDTOS;
    }

    public void changeMajor(Integer id, String major) {
        Student student = studentRepository.findStudentById(id);

        if (studentRepository.findStudentById(id) == null) throw new ApiException("Student not found");
        if (student.getMajor().equals(major)) throw new ApiException("Major did not change");

        student.setMajor(major);
        student.setCourses(null);
        studentRepository.save(student);
    }

    public List<StudentDTO> getStudentsByCourse(Integer course_id) {
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) throw new ApiException("Course not found");
        return convertToDTO((course.getStudents()));
    }
}
