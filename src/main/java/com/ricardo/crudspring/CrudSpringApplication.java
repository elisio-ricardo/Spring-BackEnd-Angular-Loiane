package com.ricardo.crudspring;

import com.ricardo.crudspring.enums.Category;
import com.ricardo.crudspring.enums.Status;
import com.ricardo.crudspring.model.Course;
import com.ricardo.crudspring.model.Lesson;
import com.ricardo.crudspring.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }


    @Bean
   // @Profile("test")
    CommandLineRunner initDatabase(CourseRepository courseRepository) {
        return args -> extracted(courseRepository);
    }

    private void extracted(CourseRepository courseRepository) {
        //courseRepository.deleteAll();
        for (int i = 1; i < 5; i++) {
            Course c = new Course();
            c.setName("Course " + i);
            c.setCategory(Category.FRONT_END);
            c.setStatus(Status.ACTIVE);

            for (int j = 1; j < 10; j++) {
                Lesson lesson = new Lesson();
                lesson.setName("Lesson " + j);
                lesson.setYoutubeUrl("Fj3Zvf-N4bk");
                c.addLesson(lesson);
            }

            //System.out.println("Testando add " + c.toString());
            courseRepository.save(c);
        }
    }


}
