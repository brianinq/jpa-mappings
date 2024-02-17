package com.example.relationships.service;

import com.example.relationships.entity.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RelationshipServiceImpl implements RelationshipServive{
    private final EntityManager entityManager;

    public RelationshipServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }
}
