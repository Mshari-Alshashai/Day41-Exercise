package com.example.day39exercise.DTO;

import com.example.day39exercise.Model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddressDTOOut {

    private String area;

    private String street;

    private Integer buildingNumber;

}
