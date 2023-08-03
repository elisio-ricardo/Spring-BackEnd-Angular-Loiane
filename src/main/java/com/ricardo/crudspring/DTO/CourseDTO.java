package com.ricardo.crudspring.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;

//Classe record é imutavel, não tem set e não tem construtor vazio
public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotBlank @NotNull @Length(min = 5, max = 100) String name,
        @NotBlank @NotNull @Length(min = 5, max = 100) @Pattern(regexp = "Back-end|Front-end") String category,
        List<LessonDTO> lessons) {
}
