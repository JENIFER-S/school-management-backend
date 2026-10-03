package com.school.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // 1. Get all students (with optional grade & section filter)
    @GetMapping
    public List<Student> getAllStudents(
            @RequestParam(required = false) String grade,
            @RequestParam(required = false) String section) {
        if (grade != null && section != null) {
            return studentRepository.findByGradeAndSection(grade, section);
        }
        return studentRepository.findAll();
    }

    // 2. Add New Student (POST)
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // 3. Update Status / Edit (PUT)
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable String id, @RequestBody Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setStatus(studentDetails.getStatus());
        student.setName(studentDetails.getName());
        return studentRepository.save(student);
    }

    // 4. Delete Student (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}