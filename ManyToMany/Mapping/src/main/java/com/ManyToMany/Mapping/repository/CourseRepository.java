package com.ManyToMany.Mapping.repository;

import com.ManyToMany.Mapping.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findByCourseName(String name);
}
