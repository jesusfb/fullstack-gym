import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewPersonalInstructor() {
  const [instructor, setInstructor] = useState({
    instructor_name: "",
    instructor_lastname: "",
    instructor_email: "",
    instructor_specialty: "",
  });

  const { instructor_id } = useParams();

  useEffect(() => {
    loadInstructor();
  }, []);

  const loadInstructor = async () => {
    const result = await axios.get(`http://localhost:8080/api/instructors?instructor_id=${instructor_id}`);
    setInstructor(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Instructor Details</h2>

           <div className="card">
            <div className="card-header">
              Details of instructor id : {instructor.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Name:</b>
                  {instructor.instructor_name}
                </li>
                <li className="list-group-item">
                  <b>Lastname:</b>
                  {instructor.instructor_lastname}
                </li>
                <li className="list-group-item">
                  <b>Email:</b>
                  {instructor.instructor_email}
                </li>
                <li className="list-group-item">
                  <b>Specialty:</b>
                  {instructor.instructor_specialty}
                </li>
              </ul>
            </div>
          </div> 
          <Link className="btn btn-primary my-2" to={"/"}>
            Back to Admin
          </Link>
        </div>
      </div>
    </div>
  );
}