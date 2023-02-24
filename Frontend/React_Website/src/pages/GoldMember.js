import React, {useState, useEffect} from "react";
import axios from "axios";
import "../components/OurTeam.css";
import Footer from '../components/Footer';

const GoldMember = () => {
    const [courses, setCourse] = useState([]);

    const loadCourses=async()=> {
      const result = await axios.get("http://localhost:8080/api/plans/courses/all?plan_id=2");
      setCourse(result.data);
    }

    useEffect(() => {
        loadCourses();
        },[]);
    
    return (
      <>
      <div className="our-team-container">
        <div className="card-grid">
          {courses.map((course, index) => (
              <div className="card" key={index}>
                  <img src={course.image_url} style={{width: "100%"}} />
                  <h1>{course.course_name}</h1>
                  <p className="title">{course.course_description} </p> 
                  <p className="title"> Instructor name : {course.instructor_name} </p> 
              </div>
          ))}
        </div>
      </div>
      <Footer />
      </>
    );
};

export default GoldMember;