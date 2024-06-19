import axios from "axios";

const BASE_URL = "http://localhost:8080/course";
class CourseService {
  //**Method to get all Course from our api or database */
  getAllCourse() {
    return axios.get(BASE_URL);
  }
  /**MEthod to save Course */
  saveCourse(CourseData) {
    return axios.post(BASE_URL, CourseData);
  }
  updateCourse(id, CourseData) {
    return axios.put(`${BASE_URL}/${id}`, CourseData);
  }
  getCourseById(id) {
    return axios.get(`${BASE_URL}/${id}`);
  }
  deleteCourse(id) {
    return axios.delete(BASE_URL + "/" + id);
  }
}
export default new CourseService();
