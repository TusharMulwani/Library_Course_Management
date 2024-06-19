package com.learningcourse.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.learningcourse.DTO.ResponseDTO;
import com.learningcourse.Exception.BadRequestException;
import com.learningcourse.Exception.ResourceNotFoundException;
import com.learningcourse.Repository.CourseRepository;
import com.learningcourse.Service.LearningCourse;
import com.learningcourse.model.course;

@Service
public class LearningCourseImpl implements LearningCourse {
	@Autowired
    private CourseRepository courseRepository;
	@Override
	public ResponseDTO saveCourse(course Course) throws BadRequestException {
		ResponseDTO response = new ResponseDTO();
		try {
		     
			courseRepository.save(Course);
			response.setMessage("Course Details Saved Succesfully");
			response.setStatusCode(HttpStatus.OK.value());
		}catch(DataIntegrityViolationException e)
		{
			throw new BadRequestException("Course Details Already Exist");
		}
	
		return response;
	}

	@Override
	public Optional<course> getCourseById(int id) {
		
		return courseRepository.findById(id);
	}

	@Override
	public List<course> getAllCourse() {
		// TODO Auto-generated method stub
		  return courseRepository.findAll();
//		List<course> courses = new ArrayList<>();
//		courseRepository.findAll().forEach(courses::add);
//		return courses;
	}

	@Override
	public  ResponseDTO updateCourse(int id, course Course) throws BadRequestException {
		course CourseToUpdate = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + id));
		ResponseDTO response = new ResponseDTO();
		try {
			CourseToUpdate.setCourseName(Course.getCourseName());
	        CourseToUpdate.setCourseFaculty(Course.getCourseFaculty());
	        CourseToUpdate.setCourseDetails(Course.getCourseDetails());
	        courseRepository.save(CourseToUpdate);
	        response.setMessage("Course Details Updated Succesfully");
			response.setStatusCode(HttpStatus.OK.value());
		}
		catch(DataIntegrityViolationException e)
		{
			throw new BadRequestException("Course Details Already Exist");
		}
	
		return response;
	}

	@Override
	public void deleteCourse(int id) {
//		ResponseDTO response = new ResponseDTO();
//		course course = courseRepository.findById(id).orElseThrow();
		course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + id));
		 courseRepository.delete(course);
		// TODO Auto-generated method stub
		
	}

}
