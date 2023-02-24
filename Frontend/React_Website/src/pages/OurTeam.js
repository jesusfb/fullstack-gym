import React, {useState, useEffect} from "react";
import axios from "axios";
import "../components/OurTeam.css";
import Footer from '../components/Footer';

const OurTeam = () => {
    const [instructors, setInstructor] = useState([]);

    const loadInstructors=async()=> {
      const result = await axios.get("http://localhost:8080/api/instructors/all");
      setInstructor(result.data);
    }

    useEffect(() => {
        loadInstructors();
        },[]);
    
    return (
      <>
      <div className="our-team-container">
        <div className="card-grid">
          {instructors.map((instructor, index) => (
              <div className="card" key={index}>
                  <img src={instructor.image_url} style={{width: "100%"}} />
                  <h1>{instructor.instructor_name} {instructor.instructor_lastname}</h1>
                  <p className="title">{instructor.instructor_email} </p> 
              </div>
          ))}
        </div>
      </div>
      <Footer />
      </>
    );
};

export default OurTeam;