package com.example.day39exercise.Service;

import com.example.day39exercise.Api.ApiException;
import com.example.day39exercise.DTO.AddressDTO;
import com.example.day39exercise.DTO.AddressDTOOut;
import com.example.day39exercise.Model.Address;
import com.example.day39exercise.Model.Teacher;
import com.example.day39exercise.Repository.AddressRepository;
import com.example.day39exercise.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;


    public void addAddress(AddressDTO addressDTO) {
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacher_id());
        if (teacher == null) throw new ApiException("teacher not found");

        Address address = new Address(null,addressDTO.getArea(),addressDTO.getStreet(),addressDTO.getBuildingNumber(),teacher);
        addressRepository.save(address);
    }

    public void updateAddress(AddressDTO addressDTO) {
        Address address= addressRepository.findAddressById(addressDTO.getTeacher_id());
        if (address == null) throw new ApiException("address not found");
        address.setStreet(addressDTO.getStreet());
        address.setArea(addressDTO.getArea());
        address.setBuildingNumber(addressDTO.getBuildingNumber());
        addressRepository.save(address);
    }

}
