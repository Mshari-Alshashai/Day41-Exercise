package com.example.day39exercise.Controller;

import com.example.day39exercise.Api.ApiResponse;
import com.example.day39exercise.Model.Course;
import com.example.day39exercise.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getAllCourses() {
        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }

    @PostMapping("/add/{teacher_id}")
    public ResponseEntity addCourse(@PathVariable Integer teacher_id, @RequestBody @Valid Course course) {
        courseService.addCourse(teacher_id, course);
        return ResponseEntity.status(200).body(new ApiResponse("course added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable Integer id, @RequestBody @Valid Course course) {
        courseService.updateCourse(id, course);
        return ResponseEntity.status(200).body(new ApiResponse("course updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body(new ApiResponse("course deleted successfully"));
    }

    @GetMapping("/get-teacher-name/{Course_id}")
    public ResponseEntity getTeacherNameOfCourse(@PathVariable Integer Course_id) {
        String teacherName = courseService.getTeacherNameOfCourse(Course_id);
        return ResponseEntity.status(200).body(new ApiResponse(teacherName));
    }
}
