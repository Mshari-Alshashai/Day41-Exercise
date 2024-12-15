package com.example.day39exercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name is required")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "age is required")
    @Positive(message = "age must be positive")
    @Column(nullable = false)
    private Integer age;

    @NotEmpty(message = "email is required")
    @Email(message = "email should be in the right format")
    @Column(nullable = false)
    private String email;

    @NotNull(message = "salary is required")
    @Positive(message = "salary should be positive")
    @Column(nullable = false)
    private Integer salary;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<Course> courses;
}
