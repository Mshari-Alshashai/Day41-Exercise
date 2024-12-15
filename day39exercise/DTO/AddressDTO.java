package com.example.day39exercise.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

    private Integer teacher_id;

    @NotEmpty(message = "area is required")
    private String area;

    @NotEmpty(message = "street is required")
    private String street;

    @NotNull(message = "Building number is required")
    private Integer buildingNumber;
}
