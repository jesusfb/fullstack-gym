import React, {useState, useEffect} from "react";
import axios from "axios";
import "./Plans.css";
import Footer from '../components/Footer';

const Plans = () => {
    const [plans, setPlan] = useState([]);

    const loadPlans=async()=> {
      const result = await axios.get("http://localhost:8080/api/plans/all");
      setPlan(result.data);
    }

    useEffect(() => {
        loadPlans();
        },[]);
    
    return (
      <>
      <div className="plan-container">
        <div className="card-grid">
            {plans.map((plan, index) => (
                <div className="card" key={index}>
                    <img src={plan.image_url} style={{width: "100%"}} />
                    <h1>{plan.plan_type}</h1>
                    <p className="title">Plan Duration : {plan.plan_duration} </p> 
                    <p className="title">Plan Price : {plan.plan_price} </p> 
                </div>
            ))}
        </div>
      </div>
      <Footer />
      </>
    );
};

export default Plans;