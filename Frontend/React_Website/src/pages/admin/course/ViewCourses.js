import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

export default function ViewCourses() {
  const [courses, setCourses] = useState([]);

  const { course_id } = useParams();

  useEffect(() => {
    loadCourses();
  }, []);

  const loadCourses = async () => {
    const result = await axios.get("http://localhost:8080/api/courses/all");
    setCourses(result.data);
  };

  const deleteCourse = async (id) => {
    await axios.delete(`http://localhost:8080/api/courses/delete?course_id=${id}`);
    loadCourses();
  };


  return (
    <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">S.N</th>
              <th scope="col">Name</th>
              <th scope="col">Description</th>
              <th scope="col">Instructor ID</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {courses.map((course, index) => (
              <tr>
                <th scope="row" key={index}>
                  {index + 1}
                </th>
                <td>{course.course_name}</td>
                <td>{course.course_description}</td>
                <td>{course.instructor_id}</td>
                <td>
                  <Link
                    className="btn btn-primary mx-2"
                    to={`/ViewPersonalCourse/${course.id}`}
                  >
                    View
                  </Link>
                  <Link
                    className="btn btn-outline-primary mx-2"
                    to={`/EditCourse/${course.id}`}
                  >
                    Edit
                  </Link>
                  <button
                    className="btn btn-danger mx-2"
                    onClick={() => deleteCourse(course.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}