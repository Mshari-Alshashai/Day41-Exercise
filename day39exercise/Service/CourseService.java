package com.example.day39exercise.Service;

import com.example.day39exercise.Api.ApiException;
import com.example.day39exercise.DTO.CourseDTO;
import com.example.day39exercise.DTO.StudentDTO;
import com.example.day39exercise.Model.Course;
import com.example.day39exercise.Model.Student;
import com.example.day39exercise.Model.Teacher;
import com.example.day39exercise.Repository.CourseRepository;
import com.example.day39exercise.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return convertToDTO(courses);
    }

    public void addCourse(Integer teacher_id, Course course) {

        Teacher teacher = teacherRepository.findTeacherById(teacher_id);

        if (teacher == null) throw new ApiException("Teacher not found");

        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public void updateCourse(Integer course_id,Course course) {
        if (courseRepository.findCourseById(course_id) == null) throw new ApiException("Course not found");

        Course oldCourse = courseRepository.findCourseById(course_id);

        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
    }

    public void deleteCourse(Integer id) {
        if (courseRepository.findCourseById(id) == null) throw new ApiException("Course not found");
        courseRepository.delete(courseRepository.findCourseById(id));
    }

    public String getTeacherNameOfCourse(Integer id) {
        Course course = courseRepository.findCourseById(id);
        if (course == null) throw new ApiException("Course not found");
        return course.getTeacher().getName();
    }

    public List<CourseDTO> convertToDTO(Collection<Course> courses) {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course : courses) {
            courseDTOList.add(new CourseDTO(course.getName()));
        }
        return courseDTOList;
    }


}
