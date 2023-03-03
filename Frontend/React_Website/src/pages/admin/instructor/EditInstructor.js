import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditInstructor() {
  let navigate = useNavigate();

  const { instructor_id } = useParams();

  const [instructor, setInstructor] = useState({
    instructor_name: "",
    instructor_lastname: "",
    instructor_email: "",
    instructor_specialty: ""
  });

  const { instructor_name, instructor_lastname, instructor_email, instructor_specialty } = instructor;

  const onInputChange = (e) => {
    setInstructor({ ...instructor, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    loadInstructor();
  }, []);

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8080/api/instructors/update?instructor_id=${instructor_id}`, instructor);
    navigate("/");
  };

  const loadInstructor = async () => {
    const result = await axios.get(`http://localhost:8080/api/instructors?instructor_id=${instructor_id}`);
    setInstructor(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Edit Instructor</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
              Name
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter instructor's name"
                name="instructor_name"
                value={instructor_name}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
                Last Name
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter instructor's last name"
                name="instructor_lastname"
                value={instructor_lastname}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Email" className="form-label">
                E-mail
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter instructor's e-mail address"
                name="instructor_email"
                value={instructor_email}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
                Specialty
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter instructor's specialty"
                name="instructor_specialty"
                value={instructor_specialty}
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