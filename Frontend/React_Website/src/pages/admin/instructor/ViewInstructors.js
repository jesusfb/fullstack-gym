import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

export default function ViewInstructors() {
  const [instructors, setInstructors] = useState([]);

  const { instructor_id } = useParams();

  useEffect(() => {
    loadInstructors();
  }, []);

  const loadInstructors = async () => {
    const result = await axios.get("http://localhost:8080/api/instructors/all");
    setInstructors(result.data);
  };

  const deleteInstructor = async (id) => {
    await axios.delete(`http://localhost:8080/api/instructors/delete?instructor_id=${id}`);
    loadInstructors();
  };


  return (
    <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">S.N</th>
              <th scope="col">Name</th>
              <th scope="col">Instr. Lastname</th>
              <th scope="col">Email</th>
              <th scope="col">Instr. Specialty</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {instructors.map((instructor, index) => (
              <tr>
                <th scope="row" key={index}>
                  {index + 1}
                </th>
                <td>{instructor.instructor_name}</td>
                <td>{instructor.instructor_lastname}</td>
                <td>{instructor.instructor_email}</td>
                <td>{instructor.instructor_specialty}</td>
                <td>
                  <Link
                    className="btn btn-primary mx-2"
                    to={`/ViewPersonalInstructor/${instructor.id}`}
                  >
                    View
                  </Link>
                  <Link
                    className="btn btn-outline-primary mx-2"
                    to={`/EditInstructor/${instructor.id}`}
                  >
                    Edit
                  </Link>
                  <button
                    className="btn btn-danger mx-2"
                    onClick={() => deleteInstructor(instructor.id)}
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