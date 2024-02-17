package com.example.relationships;

import com.example.relationships.entity.Instructor;
import com.example.relationships.entity.InstructorDetail;
import com.example.relationships.service.RelationshipServive;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RelationshipsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelationshipsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RelationshipServive relationshipServive){
        return runner ->{
            createInstructor(relationshipServive);
        };
    }

    private void createInstructor(RelationshipServive relationshipServive) {
        var instructor = new Instructor("Allen", "Davies", "davialen@g.mail");
        var instructorDetail = new InstructorDetail("https://youtube.com/", "I love Coding");
        instructor.setInstructorDetail(instructorDetail);

        System.out.println(instructor);
        relationshipServive.save(instructor);
        System.out.println("\nSaved!!");
    }
}
