import React from 'react';
import './AboutPage.css';
import ImageSlider from '../components/ImageSlider';
import { SliderData } from '../components/SliderData';
import AboutImage from '../media/gympic.png';
import Footer from '../components/Footer';

const AboutPage = () => {
    return (
        <>
        <div className='about-page-container'>
            <div className='about-image-container'>
                <img src={AboutImage} style={{width: "40%" }}/>
                {/* style={{width: "40%" }} */}
            </div>
        </div>
        <Footer />
        </>
    );
};

export default AboutPage;

