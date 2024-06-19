import React, { useState, useEffect } from "react";
import CourseService from "../service/CourseService";
import { Link, useNavigate, useParams } from "react-router-dom";

const AddCourseComponent = () => {
  /** Variables and method to collect and store inputes */
  const [courseName, setCourseName] = useState("");
  const [courseFaculty, setCourseFaculty] = useState("");
  const [courseDetails, setCourseDetails] = useState("");
  const navigate = useNavigate();

  const { id } = useParams();

  const CourseData = { courseName, courseFaculty, courseDetails }; //bundle the inpute from user

  /**send data to api and navigate when succesful */
  function saveCourse(e) {
    e.preventDefault();

    if (
      CourseData.courseName !== "" &&
      CourseData.courseFaculty !== "" &&
      CourseData.courseDetails != ""
    ) {
      /**If id is present in the parameter, it should update else it should save */
      if (id) {
        saveCourse = async (e) => {
          e.preventDefault();
          try {
            const response = await CourseService.updateCourse(id, CourseData);

            const status = response.data.statusCode; // Ensure you're getting the correct status code
            const message = response.data.message;
            if (status == 200) {
              alert(message);

              navigate("/Course");
            } else {
              alert("Error: " + message);
            }
          } catch (error) {
            console.error("There was an error!", error);
            alert("An error occurred while saving data");
          }
        };
      } else {
        saveCourse = async (e) => {
          e.preventDefault();
          try {
            const response = await CourseService.saveCourse(CourseData);

            const status = response.data.statusCode; // Ensure you're getting the correct status code
            const message = response.data.message;
            if (status == 200) {
              alert(message);

              navigate("/Course");
            } else {
              alert("Error: " + message);
            }
          } catch (error) {
            console.error("There was an error!", error);
            alert("An error occurred while saving data");
          }
        };
      }
    } else {
      alert("Please, fill in all inputes");
    }
  }

  function tile() {
    if (id) {
      return "Update Course";
    } else {
      return "Add Course";
    }
  }
  useEffect(() => {
    if (id) {
      CourseService.getCourseById(id)
        .then((res) => {
          setCourseName(res.data.courseName);
          setCourseFaculty(res.data.courseFaculty);
          setCourseDetails(res.data.courseDetails);
        })
        .catch((e) => console.log(e));
    }
  }, []);

  return (
    <div>
      <div className="container mt-5">
        <div className="row">
          <div className="card col-md-6 offset-md-3">
            <h2 className="text-center">{tile()}</h2>
            <div className="card-body">
              <form>
                <div className="form-group mb-2">
                  <input
                    className="form-control"
                    value={courseName}
                    onChange={(e) => setCourseName(e.target.value)}
                    type="text"
                    placeholder="Enter Course Name"
                  />
                </div>
                <div className="form-group mb-2">
                  <input
                    className="form-control"
                    value={courseFaculty}
                    onChange={(e) => setCourseFaculty(e.target.value)}
                    type="text"
                    placeholder="Enter Course Faculty"
                  />
                </div>
                <div className="form-group mb-2">
                  <input
                    className="form-control"
                    value={courseDetails}
                    onChange={(e) => setCourseDetails(e.target.value)}
                    type="courseDetails"
                    placeholder="Enter Course Details"
                  />
                </div>
                <button
                  onClick={(e) => saveCourse(e)}
                  className="btn btn-success"
                >
                  Save
                </button>{" "}
                <Link to={"/Course"} className="btn btn-danger" href="">
                  Cancel
                </Link>
                <br />
                <p>* double click on Save Button to Save the Details</p>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddCourseComponent;
