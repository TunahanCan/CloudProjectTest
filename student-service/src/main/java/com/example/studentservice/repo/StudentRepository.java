package com.example.studentservice.repo;


import com.example.studentservice.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository  extends JpaRepository<StudentModel, Long> {
    List<StudentModel> findBySchoolName(String schoolName);
}
