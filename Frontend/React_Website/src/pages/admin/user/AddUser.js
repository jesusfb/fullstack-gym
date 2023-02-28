import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Footer from '../../../components/Footer';

export default function AddUser() {
  let navigate = useNavigate();

  const [user, setUser] = useState({
    user_name: "",
    user_lastname: "",
    user_email: "",
    plan_id: "",
    user_address: ""
  });

  const { user_name, user_lastname, user_email, plan_id, user_address } = user;

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:8080/api/plans/users/add?plan_id="+user.plan_id, user);
    navigate("/")
  };

  return (
    <>
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Register User</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Name" className="form-label">
                First Name
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter your first name"
                name="user_name"
                value={user_name}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="lname" className="form-label">
                Last Name
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter your last name"
                name="user_lastname"
                value={user_lastname}
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
                placeholder="Enter your e-mail address"
                name="user_email"
                value={user_email}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="plan" className="form-label">
                Plan
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter your plan's name"
                name="plan_id"
                value={plan_id}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="plan" className="form-label">
                Address
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter your plan's name"
                name="user_address"
                value={user_address}
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