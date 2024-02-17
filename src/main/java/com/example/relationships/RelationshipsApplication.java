package com.example.relationships;

import com.example.relationships.entity.Instructor;
import com.example.relationships.entity.InstructorDetail;
import com.example.relationships.service.RelationshipService;
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
    public CommandLineRunner commandLineRunner(RelationshipService relationshipService){
        return runner ->{
//            createInstructor(relationshipService);
//            System.out.println(relationshipService.findById(2));
//            relationshipService.deleteById(1);
            findInstructorDetail(relationshipService, 3);
            relationshipService.deleteInstructorDetailById(3);
        };
    }

    private void createInstructor(RelationshipService relationshipService) {
        var instructor = new Instructor("Allen", "Davies", "davialen@g.mail");
        var instructorDetail = new InstructorDetail("https://youtube.com/", "I love Coding");
        instructor.setInstructorDetail(instructorDetail);

        System.out.println(instructor);
        relationshipService.save(instructor);
        System.out.println("\nSaved!!");
    }

    private void findInstructorDetail(RelationshipService relationshipService, int id) {
        var instructorDetail = relationshipService.findInstructorDetailById(id);
        System.out.println(instructorDetail);
        System.out.println("Instructor for detail----- \n" + instructorDetail.getInstructor());
    }
}
