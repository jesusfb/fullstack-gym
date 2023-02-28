import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewPersonalUser() {
  const [user, setUser] = useState({
    user_name: "",
    user_lastname: "",
    user_email: "",
    plan_id: "",
    user_address: "",
    registered_date: ""
  });

  const { user_id } = useParams();

  useEffect(() => {
    loadUser();
  }, []);

  const loadUser = async () => {
    const result = await axios.get(`http://localhost:8080/api/users?user_id=${user_id}`);
    setUser(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">User Details</h2>

           <div className="card">
            <div className="card-header">
              Details of user id : {user.user_id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Name:</b>
                  {user.user_name}
                </li>
                <li className="list-group-item">
                  <b>Lastname:</b>
                  {user.user_lastname}
                </li>
                <li className="list-group-item">
                  <b>Email:</b>
                  {user.user_email}
                </li>
                <li className="list-group-item">
                  <b>Address:</b>
                  {user.user_address}
                </li>
                <li className="list-group-item">
                  <b>Plan:</b>
                  {user.plan_id}
                </li>
                <li className="list-group-item">
                  <b>Registered day:</b>
                  {user.registered_date}
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