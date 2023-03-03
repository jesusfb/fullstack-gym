import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Footer from '../../../components/Footer';

export default function AddCourse() {
  let navigate = useNavigate();

  const [course, setCourse] = useState({
    course_name: "",
    instructor_id: "",
    course_description: ""
  });

  const { course_name, instructor_id, course_description} = course;

  const onInputChange = (e) => {
    setCourse({ ...course, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:8080/api/instructors/courses/add?instructor_id="+course.instructor_id, course);
    navigate("/")
  };

  return (
    <>
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Register Course</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
                Course Name
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter course's name"
                name="course_name"
                value={course_name}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="lname" className="form-label">
                Instructor ID
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter instructor's ID"
                name="instructor_id"
                value={instructor_id}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Email" className="form-label">
                Description
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter course's description"
                name="course_description"
                value={course_description}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/Admin">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
    <Footer />
    </>
  );
}