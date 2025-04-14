package com.OneToMany.Mapping.Repository;

import com.OneToMany.Mapping.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
