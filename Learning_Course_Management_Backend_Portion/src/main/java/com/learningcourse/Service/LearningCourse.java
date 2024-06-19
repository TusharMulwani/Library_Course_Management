package com.learningcourse.Service;

import java.util.List;
import java.util.Optional;

import com.learningcourse.DTO.ResponseDTO;
import com.learningcourse.model.*;

public interface LearningCourse {
	public ResponseDTO saveCourse(course Course);
    public Optional<course> getCourseById(int id);
    List<course> getAllCourse();
    ResponseDTO updateCourse(int id, course Course);
    void deleteCourse(int id);
}
