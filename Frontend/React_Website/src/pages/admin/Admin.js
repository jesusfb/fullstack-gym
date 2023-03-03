import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import Footer from '../../components/Footer';
import { useLocalState } from "../../util/useLocalStorage";

export default function Admin() {
  const [users, setUsers] = useState([]);

  const [jwt, setJwt] = useLocalState("", "jwt");

  const { id } = useParams();

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async () => {
    const result = await axios.get("http://localhost:8080/users");
    setUsers(result.data);
  };

  const deleteUser = async (id) => {
    await axios.delete(`http://localhost:8080/user/${id}`);
    loadUsers();
  };

  return (
    <>
    <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col" >CHOOSE A CATEGORY</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row"> USER :
                <td>
                <Link
                    className="btn btn-primary mx-2"
                    to="/AddUser">
                    Add User
                  </Link>
                  <Link
                    className="btn btn-outline-primary mx-2"
                    to="/ViewUsers"
                  >
                    View User
                  </Link>
                </td>
              </th>
            </tr>
            <tr>
              <th scope="row"> INSTRUCTOR :
                <td>
                <Link
                    className="btn btn-primary mx-2"
                    to="/AddInstructor">
                    Add Instructor
                  </Link>
                  <Link
                    className="btn btn-outline-primary mx-2"
                    to="/ViewInstructors"
                  >
                    View Instructor
                  </Link>
                </td>
              </th>
            </tr>
            <tr>
              <th scope="row"> COURSE :
                <td>
                <Link
                    className="btn btn-primary mx-2"
                    to="/AddCourse">
                    Add Course
                  </Link>
                  <Link
                    className="btn btn-outline-primary mx-2"
                    to="/ViewCourses"
                  >
                    View Course
                  </Link>
                </td>
              </th>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <Footer />
    </>
  );
}