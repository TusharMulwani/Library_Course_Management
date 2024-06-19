package com.learningcourse.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningcourse.DTO.ResponseDTO;
import com.learningcourse.Exception.ResourceNotFoundException;
import com.learningcourse.Service.LearningCourse;
import com.learningcourse.model.course;
import com.learningcourse.util.ResponseUtil;

@RestController
@RequestMapping("/course")
@CrossOrigin("*")
public class CourseController {

    @Autowired
    private LearningCourse courseService;
    
    @Autowired
    private  ResponseUtil Responseutil;
    @GetMapping
    public List<course> getAllCourse() {
        return courseService.getAllCourse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<course> getCourseById(@PathVariable(value = "id") int id) {
        course course = courseService.getCourseById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + id));
        return ResponseEntity.ok().body(course);
    }

    @PostMapping
    public ResponseEntity<?> saveCourse(@RequestBody course course) throws Exception{
        ResponseDTO resp =new ResponseDTO();
        ResponseEntity<?> res = null;
        try {
        	resp = courseService.saveCourse(course);
        	if(resp.getStatusCode()==HttpStatus.OK.value())
        	{
        		res = ResponseEntity.status(HttpStatus.OK).body(resp);
        		
        	}
        	else 
        	{
        		res = Responseutil.createResponseEntity(HttpStatus.BAD_REQUEST,"Error Creating The CourseDetails");
        	}
        }
    	 catch(Exception ex) {
    		 res = Responseutil.createResponseEntity(HttpStatus.BAD_REQUEST,ex.getMessage());
    	 }
        return res;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable(value = "id") int id, @RequestBody course courseDetails) throws Exception {
    	ResponseDTO resp =new ResponseDTO();
        ResponseEntity<?> res = null;
        try {
        	resp = courseService.updateCourse(id, courseDetails);
        	if(resp.getStatusCode()==HttpStatus.OK.value())
        	{
        		res = ResponseEntity.status(HttpStatus.OK).body(resp);
        		
        	}
        	else 
        	{
        		res = Responseutil.createResponseEntity(HttpStatus.BAD_REQUEST,"Error In  Updating The CourseDetails");
        	}
        } 
        catch(Exception ex)
        {
        	res = Responseutil.createResponseEntity(HttpStatus.BAD_REQUEST,ex.getMessage());
        	
        }
        return res;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteCourse(@PathVariable(value = "id") int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
