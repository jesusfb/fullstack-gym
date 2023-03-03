import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditCourse() {
  let navigate = useNavigate();

  const { course_id } = useParams();

  const [course, setCourse] = useState({
    course_name: "",
    instructor_id: "",
    course_description:""
  });

  const { course_name, instructor_id, course_description} = course;

  const onInputChange = (e) => {
    setCourse({ ...course, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    loadCourse();
  }, []);

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8080/api/courses/update?course_id=${course_id}`, course);
    navigate("/");
  };

  const loadCourse = async () => {
    const result = await axios.get(`http://localhost:8080/api/courses?course_id=${course_id}`);
    setCourse(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Edit Course</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
              Name
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
              <label htmlFor="Name" className="form-label">
                Course Description
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
            <Link className="btn btn-outline-danger mx-2" to="/">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}