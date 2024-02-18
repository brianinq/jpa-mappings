package com.example.relationships;

import com.example.relationships.entity.Course;
import com.example.relationships.entity.Instructor;
import com.example.relationships.entity.InstructorDetail;
import com.example.relationships.service.RelationshipService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
//            findInstructorDetail(relationshipService, 3);
//            relationshipService.deleteInstructorDetailById(3);
//            createInstructorWithCourses(relationshipService);
            findInstructorWithCourses(relationshipService, 1);
        };
    }

    private void findInstructorWithCourses(RelationshipService relationshipService, int id) {
        System.out.println("finding instructor id: " + id);
        var instructor = relationshipService.findById(id);
        if (instructor == null) {
            System.out.println("Not found: id= " + id);
            return;
        }
        System.out.println("instructor ----\n" + instructor);
        List<Course> courses = relationshipService.findCoursesByInstructorId(id);
        instructor.setCourses(courses);
        System.out.println("courses ===>\n" + instructor.getCourses());

    }

    private void createInstructorWithCourses(RelationshipService relationshipService) {
        var instructor = new Instructor("Allen", "Davies", "davialen@g.mail");
        var instructorDetail = new InstructorDetail("https://youtube.com/", "I love Coding");
        instructor.setInstructorDetail(instructorDetail);
        var course1 = new Course("Getting Started with GIT");
        var course2 = new Course("Html for babies: Get yur 20yrs experience");
        instructor.addCourse(course1);
        instructor.addCourse(course2);
        System.out.println("Saving Courses to db\n\n");
        //this also saves courses due to persist cascade
        relationshipService.save(instructor);
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
