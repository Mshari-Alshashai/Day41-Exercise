package com.example.day39exercise.DTO;

import com.example.day39exercise.Model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class StudentDTO {

    private String name;

    private Integer age;

    private String major;

    private List<CourseDTO> courses;
}
