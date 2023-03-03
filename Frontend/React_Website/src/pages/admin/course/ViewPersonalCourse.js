import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewPersonalUser() {
  const [course, setCourse] = useState({
    course_name: "",
    course_description: "",
    instructor_id: ""
  });

  const { course_id } = useParams();

  useEffect(() => {
    loadCourse();
  }, []);

  const loadCourse = async () => {
    const result = await axios.get(`http://localhost:8080/api/courses?course_id=${course_id}`);
    setCourse(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Course Details</h2>

           <div className="card">
            <div className="card-header">
              Details of course id : {course.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Name:</b>
                  {course.course_name}
                </li>
                <li className="list-group-item">
                  <b>Description:</b>
                  {course.course_description}
                </li>
                <li className="list-group-item">
                  <b>Instructor ID:</b>
                  {course.instructor_id}
                </li>
              </ul>
            </div>
          </div> 
          <Link className="btn btn-primary my-2" to={"/"}>
            Back
          </Link>
        </div>
      </div>
    </div>
  );
}