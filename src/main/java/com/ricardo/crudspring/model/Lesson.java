package com.ricardo.crudspring.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

//Aula
@Entity
@Data
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String name;


    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String youtubeUrl;

    //dessa forma não vai criar uma tabela adicional e vai criar uma coluna na entidade lesson
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    //Para resolver o problema de dependencia circular, vai apenas fazer set dos cursos
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Course course;
}
