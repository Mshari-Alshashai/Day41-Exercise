package com.example.day39exercise.DTO;

import com.example.day39exercise.Model.Address;
import com.example.day39exercise.Model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Data
public class TeacherDTO {

    private String name;

    private Integer age;

    private String email;

    private Integer salary;

    private AddressDTOOut address;

    private List<CourseDTO> courses;
}
