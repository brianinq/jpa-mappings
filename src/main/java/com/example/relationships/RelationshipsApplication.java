package com.example.relationships;

import com.example.relationships.entity.*;
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
//            findInstructorDetail(relationshipService, 3);
//            relationshipService.deleteInstructorDetailById(3);
//            createInstructorWithCourses(relationshipService);
//            findInstructorWithCourses(relationshipService, 1);
//            updateInstructor(relationshipService);
//            relationshipService.deleteById(2);
//            createCourseAndReviews(relationshipService);
//            System.out.println(relationshipService.findCourseAndReviewsByCourseId(11).getReviews());
            createCourseAndStudents(relationshipService);
            System.out.println(relationshipService.findCourseAndStudentsByCourseId(14).getStudents());
        };
    }

    private void createCourseAndStudents(RelationshipService relationshipService) {
        Course course = new Course("The ultimate guide to Dapps.");
        Student student1 = new Student("Alvin", "Franklin", "af@af.fa");
        Student student2 = new Student("Benson", "Gareth", "bg@bg.gb");
        course.addStudent(student1);
        course.addStudent(student2);
        relationshipService.createCourse(course);
    }

    private void createCourseAndReviews(RelationshipService relationshipService) {
//        var instructor = relationshipService.findById(1);
        var course = new Course("Introduction to Python3");
        var review1 = new Review("Great course good for beginners");
        var review2 = new Review("The instructor is very well detailed");
        course.addReview(review1);
        course.addReview(review2);
//        course.setInstructor(instructor);
        relationshipService.createCourse(course);
        System.out.println("Done saving course!");
    }

    private void updateInstructor(RelationshipService relationshipService) {
        var instructor = relationshipService.findById(1);
        instructor.setLastName("Alkaline");
        var in = relationshipService.updateInstructor(instructor);
        System.out.println(in);
    }

    private void findInstructorWithCourses(RelationshipService relationshipService, int id) {
        System.out.println("finding instructor join fetch id: " + id);
        var instructor = relationshipService.findInstructorByIdJoinFetch(id);
        if (instructor == null) {
            System.out.println("Not found: id= " + id);
            return;
        }
        System.out.println("courses ===>\n" + instructor.getCourses());

    }

    private void createInstructorWithCourses(RelationshipService relationshipService) {
        var instructor = new Instructor("Allen", "Davies", "davialen@g.mail");
        var instructorDetail = new InstructorDetail("https://youtube.com/", "I love Coding");
        instructor.setInstructorDetail(instructorDetail);
        var course1 = new Course("Getting Started with GIT -09");
        var course2 = new Course("Html for babies: Get your 20yrs experience");
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
