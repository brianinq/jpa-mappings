package com.example.relationships.service;

import com.example.relationships.entity.Instructor;

public interface RelationshipService {
    void save(Instructor instructor);
    Instructor findById(int id);
    void deleteById(int id);
}
