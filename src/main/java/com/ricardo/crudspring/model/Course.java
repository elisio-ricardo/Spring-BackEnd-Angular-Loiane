package com.ricardo.crudspring.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ricardo.crudspring.enums.Category;
import com.ricardo.crudspring.enums.Status;
import com.ricardo.crudspring.enums.converters.CategoryConverter;
import com.ricardo.crudspring.enums.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
//@Table(name = "cursos")
//Isso se chama soft delete, onde não exclui permanentemente o objeto,  deixa ele inativo
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    //@Length(max = 10)
    //@Pattern(regexp = "Back-end|Front-end")
    @Column(nullable = false)
    //@Enumerated(EnumType.ORDINAL)//a melhor é ordinal, pq se houver mudanças no nome tem que atualizar todo o banco de dados
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    // @Length(max = 10)
    //@Pattern(regexp = "Ativo|Inativo")
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    //melhor forma de mapear associação de classes
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course" )
   // @JoinColumn(name = "course_id")
    private List<Lesson> lessons = new ArrayList<>();

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        if (lessons == null) {
            throw new IllegalArgumentException("Lessons cannot be null.");
        }
        this.lessons.clear();
        this.lessons.addAll(lessons);
    }

    public void addLesson(Lesson lesson) {
        if (lesson == null) {
            throw new IllegalArgumentException("Lesson cannot be null.");
        }
        lesson.setCourse(this);
        this.lessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        if (lesson == null) {
            throw new IllegalArgumentException("Lesson cannot be null.");
        }
        lesson.setCourse(null);
        this.lessons.remove(lesson);
    }
}
