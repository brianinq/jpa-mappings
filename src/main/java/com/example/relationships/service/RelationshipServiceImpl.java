package com.example.relationships.service;

import com.example.relationships.entity.Course;
import com.example.relationships.entity.Instructor;
import com.example.relationships.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        
        // disassociate instructor with courses before delete
        List<Course> courses = instructor.getCourses();
        courses.forEach(course -> course.setInstructor(null));

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

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :id", Course.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i join fetch i.courses join fetch i.instructorDetail where i.id=:id", Instructor.class
        );
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public Instructor updateInstructor(Instructor instructor) {
        return entityManager.merge(instructor);
    }

}
