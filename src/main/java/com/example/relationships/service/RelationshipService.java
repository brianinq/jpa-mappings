package com.example.relationships.service;

import com.example.relationships.entity.Course;
import com.example.relationships.entity.Instructor;
import com.example.relationships.entity.InstructorDetail;

import java.util.List;

public interface RelationshipService {
    void save(Instructor instructor);
    Instructor findById(int id);
    void deleteById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    Instructor updateInstructor(Instructor instructor);

    void createCourse(Course course);

    Course findCourseAndReviewsByCourseId(int id);

}
