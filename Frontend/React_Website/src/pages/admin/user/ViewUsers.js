import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

export default function ViewUsers() {
  const [users, setUsers] = useState([]);

  const { user_id } = useParams();

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async () => {
    const result = await axios.get("http://localhost:8080/api/users/all");
    setUsers(result.data);
  };

  const deleteUser = async (id) => {
    await axios.delete(`http://localhost:8080/api/users/delete?user_id=${id}`);
    loadUsers();
  };


  return (
    <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">S.N</th>
              <th scope="col">Name</th>
              <th scope="col">User Lastname</th>
              <th scope="col">Email</th>
              <th scope="col">User Address</th>
              <th scope="col">User Plan ID</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user, index) => (
              <tr>
                <th scope="row" key={index}>
                  {index + 1}
                </th>
                <td>{user.user_name}</td>
                <td>{user.user_lastname}</td>
                <td>{user.user_email}</td>
                <td>{user.user_address}</td>
                <td>{user.plan_id}</td>
                <td>
                  <Link
                    className="btn btn-primary mx-2"
                    to={`/ViewPersonalUser/${user.user_id}`}
                  >
                    View
                  </Link>
                  <Link
                    className="btn btn-outline-primary mx-2"
                    to={`/EditUser/${user.user_id}`}
                  >
                    Edit
                  </Link>
                  <button
                    className="btn btn-danger mx-2"
                    onClick={() => deleteUser(user.user_id)}
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