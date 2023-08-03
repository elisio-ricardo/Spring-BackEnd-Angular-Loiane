package com.ricardo.crudspring.controller;


import com.ricardo.crudspring.DTO.CourseDTO;
import com.ricardo.crudspring.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Valid
@RestController
@RequestMapping("/api/courses")
public class CursoController {

    private final CourseService courseService;

    public CursoController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public  List<CourseDTO> list() {
        return courseService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable @NotNull @Positive Long id) {
        return ResponseEntity.ok().body(courseService.findById(id));
    }

    @PostMapping
    //@ResponseStatus(code = HttpStatus.CREATED) //Dessa forma pode remover o responseEntity
    public ResponseEntity<CourseDTO> create(@RequestBody @Valid CourseDTO course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable @NotNull @Positive Long id, @RequestBody CourseDTO course) {
        return ResponseEntity.ok().body(courseService.update(id, course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().<Void>build();
    }

}
