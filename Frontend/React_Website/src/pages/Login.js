import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import {useLocalState } from "../util/useLocalStorage";

export default function Login() {

    let navigate = useNavigate();

    const[jwt, setJwt] = useLocalState("", "jwt");
    const [user_email, setUseremail] = useState("");
    const [password, setPassword] = useState("");

    function sendLoginRequest(){
      const reqBody ={
        user_email : user_email,
        password :password,
      };

      fetch("http://localhost:8080/api/auth/authenticate", {
        headers : {
          "Content-Type" : "application/json",
        },
        method: "post",
        body: JSON.stringify(reqBody)
      })
      .then((response) => {
        if(response.status === 200)
          return Promise.all([response.json(), response.headers]);
          else return Promise.reject("Invalid login attempt");
      })
      .then(([body, headers]) => {
        setJwt(headers.get("Authorization"));
      })
      navigate("/Admin");
    }
 
    return (
      <>
      <div className="container">
        <div className="row">
          <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
            <h2 className="text-center m-4">Login</h2>
  
            <form >
              <div className="mb-3">
                <label htmlFor="Name" className="form-label">
                  Email
                </label>
                <input
                  type="email"
                  className="form-control"
                  placeholder="Enter your email"
                  name="user_email"
                  value={user_email}
                  onChange={(e) => setUseremail(e.target.value)}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="password" className="form-label">
                  Password
                </label>
                <input
                  type={"password"}
                  className="form-control"
                  placeholder="Enter your password"
                  name="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
              </div>
              <button type="submit" className="btn btn-outline-primary" onClick={() => sendLoginRequest()} to="/Admin">
                Login
              </button>
              <Link className="btn btn-outline-danger mx-2" to="/">
                Cancel
              </Link>
            </form>
          </div>
        </div>
      </div>
      </>
    );

}