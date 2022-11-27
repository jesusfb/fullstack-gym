import React, {useState} from "react";
import { SliderData } from "./SliderData";
import * as FaIcons from 'react-icons/fa';
import * as IoIosIcons from 'react-icons/io';
import styled from 'styled-components';
import './ImageSlider.css';

const slider = styled.div`
    height : 100vh;
    display : flex;
    justify-content : center;
    align-items : center;
    width: 100%;
    `;


const ImageSlider = ({ slides }) => {
    const [current, setCurrent] = useState(0);
    const length = slides.length;

    const nextSlide = () => {
        setCurrent(current === length -1 ? 0 : current + 1);
    }

    const prevSlide = () => {
        setCurrent(current === length -1 ? 0 : current - 1);
    }

    console.log(current);

    if(!Array.isArray(slides) || slides.length <=0 ){
        return null;
    }

    return (
        <div className="slider">
            <IoIosIcons.IoIosArrowDropleft className="left-arrow" onClick={prevSlide}/>
            <IoIosIcons.IoIosArrowDropright className="right-arrow" onClick={nextSlide}/>
        {SliderData.map((slide, index) => {
            return (
                <div className={index === current ? 'slide active' : 'slide'} key={index}>
                    {index === current && (<img src={slide.image} alt="gymImage" className="image"/>)}
                </div>
            )
        })}
        </div>
    )
}

export default ImageSlider;