package com.learningcourse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learningcourse.model.course;

public interface CourseRepository extends JpaRepository<course, Integer> {
}

