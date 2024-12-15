package com.example.day39exercise.Service;

import com.example.day39exercise.Api.ApiException;
import com.example.day39exercise.DTO.AddressDTOOut;
import com.example.day39exercise.DTO.CourseDTO;
import com.example.day39exercise.DTO.TeacherDTO;
import com.example.day39exercise.Model.Address;
import com.example.day39exercise.Model.Course;
import com.example.day39exercise.Model.Teacher;
import com.example.day39exercise.Repository.AddressRepository;
import com.example.day39exercise.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;
    private final CourseService courseService;

    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return convertTeacherListToTeacherDTOList(teachers);
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer id, Teacher teacher) {
        if (teacherRepository.findTeacherById(id) == null) throw new ApiException("teacher not found");

        Teacher oldTeacher = teacherRepository.findTeacherById(id);

        oldTeacher.setName(teacher.getName());
        oldTeacher.setEmail(teacher.getEmail());
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setAddress(teacher.getAddress());
        oldTeacher.setAddress(teacher.getAddress());
        teacherRepository.save(oldTeacher);
    }

    public void deleteTeacher(Integer id) {
        if (teacherRepository.findTeacherById(id) == null) throw new ApiException("teacher not found");

        teacherRepository.findTeacherById(id).setAddress(null);

        if (addressRepository.findAddressById(id) != null) {
            addressRepository.delete(addressRepository.findAddressById(id));
        }

        teacherRepository.delete(teacherRepository.findTeacherById(id));

    }

    public TeacherDTO getTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) throw new ApiException("teacher not found");
        return new TeacherDTO(teacher.getName(),teacher.getAge(),teacher.getEmail(),teacher.getSalary(),convertAddressToAddressDTO(teacher.getAddress()),courseService.convertToDTO(teacher.getCourses()));
    }

    public List<TeacherDTO> convertTeacherListToTeacherDTOList(List<Teacher> teacherList) {
        List<TeacherDTO> teacherDTOs = new ArrayList<>();
        List<CourseDTO> courseDTOs = new ArrayList<>();

        for (Teacher teacher : teacherList) {
            for (Course course : teacher.getCourses()) courseDTOs.add(new CourseDTO(course.getName()));
            teacherDTOs.add(new TeacherDTO(teacher.getName(),teacher.getAge(),teacher.getEmail(),teacher.getSalary(), convertAddressToAddressDTO(teacher.getAddress()),courseDTOs));
        }
        return teacherDTOs;
    }

    public AddressDTOOut convertAddressToAddressDTO(Address address) {
        if (address == null) return null;
        return new AddressDTOOut(address.getArea(),address.getStreet(),address.getBuildingNumber());
    }


}
