package com.example.relationships.service;

import com.example.relationships.entity.Instructor;
import com.example.relationships.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RelationshipServiceImpl implements RelationshipService {
    private final EntityManager entityManager;

    public RelationshipServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Instructor instructor = findById(id);
        if(instructor == null) return;
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        var detail = findInstructorDetailById(id);
        detail.getInstructor().setInstructorDetail(null);
        entityManager.remove(detail);
    }
}
