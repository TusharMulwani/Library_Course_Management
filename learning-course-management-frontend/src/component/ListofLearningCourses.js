import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import CourseService from "../service/CourseService";

const ListofLearningCourses = () => {
  const [CourseArray, setCourseArray] = useState([]);

  useEffect(() => {
    getAllCourse();
  }, []);

  function getAllCourse() {
    CourseService.getAllCourse()
      .then((res) => {
        setCourseArray(res.data);
        console.log(res);
      })
      .catch((e) => console.log(e));
  }
  function deleteCourse(e, id) {
    e.preventDefault();
    CourseService.deleteCourse(id)
      .then(getAllCourse())
      .catch((e) => console.log(e));
  }

  return (
    <div className="container">
      <Link to={"/add-Course"} className="btn btn-primary mb-2 mt-3" href="">
        Add New Course
      </Link>
      <h2 className="text-center mb-4">List of the Courses </h2>
      <table className="table table-bordered table striped">
        <thead>
          <th> Course ID</th>
          <th>Course Name</th>
          <th>Faculty Name</th>
          <th>Course Details</th>
          <th>Action</th>
        </thead>
        <tbody>
          {CourseArray.map((Course) => (
            <tr id={Course.id}>
              <td>{Course.id}</td>
              <td>{Course.courseName}</td>
              <td>{Course.courseFaculty}</td>
              <td>{Course.courseDetails}</td>
              <td>
                <Link
                  to={`/add-Course/${Course.id}`}
                  className="btn btn-info"
                  href=""
                >
                  Update
                </Link>{" "}
                <a
                  onClick={(e) => deleteCourse(e, Course.id)}
                  className="btn btn-danger"
                >
                  Delete
                </a>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ListofLearningCourses;
