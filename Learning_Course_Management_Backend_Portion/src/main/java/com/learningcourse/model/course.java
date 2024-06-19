package com.learningcourse.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name= "course")
public class course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "courseName")
    private String courseName;
    @Column(name = "courseFaculty")
    private String courseFaculty;
    @Column(name = "courseDetails")
    private String courseDetails;
    
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseFaculty() {
		return courseFaculty;
	}
	public void setCourseFaculty(String courseFaculty) {
		this.courseFaculty = courseFaculty;
	}
	public String getCourseDetails() {
		return courseDetails;
	}
	public void setCourseDetails(String courseDetails) {
		this.courseDetails = courseDetails;
	}
	
    

}

