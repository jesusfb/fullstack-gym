import React from "react"
import "./Profile.css"

export default function Profile(props){
    return(
        <div className='Profile'>
            <div className='upper-container'>
                <div className='image-container'>
                    <img src="https://icons.veryicon.com/png/o/internet--web/prejudice/user-128.png" alt='' height="100px" width="100px" />
                </div>
            </div>
            <div className='lower-container'>
                <h3> {props.name} </h3>
                <h4> ID: {props.id} </h4>
                <p> <span>First - Name: </span> {props.name} </p>
                <p> <span>Last - Name: </span> {props.lname} </p>
                <p> <span>Address: </span> {props.address} </p>
                <p> <span>Phone number: </span> {props.phone}</p>
                <button>Edit profile</button>
            </div>
        </div>
    )
}