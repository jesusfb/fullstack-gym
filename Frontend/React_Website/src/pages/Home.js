import React from 'react';
import ImageSlider from '../components/ImageSlider';
import VideoSlider from '../components/VideoSlider';
import { SliderData } from '../components/SliderData';
import Footer from '../components/Footer';
import CalendarContainer from '../components/CalendarContainer';
import styled from 'styled-components';

const Home = () => {
    return (
        <>
        {/* <ImageSlider slides={SliderData}/> */}
        <VideoSlider />
        <CalendarContainer />
        <Footer />
        </>
    );
};

export default Home;