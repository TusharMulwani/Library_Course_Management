import HeaderComponent from "./component/HeaderComponent";
import FooterComponent from "./component/FooterComponent";
import AddCourseComponent from "./component/AddCourseComponent";
import ListofLearningCourses from "./component/ListofLearningCourses";
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <div>
      <BrowserRouter>
        <HeaderComponent />
        <div className="container">
          <Routes>
            <Route path="/" element={<ListofLearningCourses />} />
            <Route path="/Course" element={<ListofLearningCourses />} />
            <Route path="/add-Course" element={<AddCourseComponent />} />
            <Route path="/add-Course/:id" element={<AddCourseComponent />} />
          </Routes>
        </div>
        <FooterComponent />
      </BrowserRouter>
    </div>
  );
}

export default App;
