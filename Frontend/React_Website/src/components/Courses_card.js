import React from "react"
import "./Courses_card.css"

export default function Courses_card(props){
    return(
        <div className="wrapper">
            <div className='courses'>
                <div className="courses__body">
                    <img className="courses__img" src={props.img}/>
                    <h2 className="courses__title">{props.title}</h2>
                    <p className="courses__description">{props.description}</p>
                </div>
                <button className="courses__btn">View course</button>
            </div>
        </div>
    )
}