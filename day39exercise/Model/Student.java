package com.example.day39exercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty(message = "name is required")
    private String name;

    @NotNull(message = "age is required")
    @Column(nullable = false)
    private Integer age;

    @NotEmpty(message = "major is required")
    @Column(nullable = false)
    private String major;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;
}
