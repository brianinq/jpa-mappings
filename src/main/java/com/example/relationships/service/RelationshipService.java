package com.example.relationships.service;

import com.example.relationships.entity.Instructor;
import com.example.relationships.entity.InstructorDetail;

public interface RelationshipService {
    void save(Instructor instructor);
    Instructor findById(int id);
    void deleteById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);
}
