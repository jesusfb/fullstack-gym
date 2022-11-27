import React, {useState} from "react";
import './VideoSlider.css';

const VideoSlider = ({ slides }) => {
    
    return (
        <div className="videoslider">
            <video src="/videos/gym.mp4" autoPlay loop muted />
            <h1> THE MUSCLE PROJECT </h1>
        </div>
    )
}

export default VideoSlider;