import React from 'react';
import './App.css';
import Sidebar from './components/Sidebar';
import {BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import FreeGroupCourses from './pages/FreeGroupCourses';
import PremiumGroupCourses from './pages/PremiumGroupCourses';
import ProfilePage from './pages/ProfilePage';
import Courses from './pages/Courses';
import Home from './pages/Home';

function App() {
  return (
    <Router>
      <Sidebar />
      <Routes>
        <Route path='/FreeGroupCourses' element= {<FreeGroupCourses />}/>
        <Route path='/PremiumGroupCourses' element= {<PremiumGroupCourses />}/>
        <Route path='/ProfilePage' element = {<ProfilePage />} />
        <Route path='/Courses' element = {<Courses />} />
        <Route path='/' exact element = {<Home />} />
        <Route path='/Home' element = {<Home />} />
      </Routes>
    </Router>
  );
}

export default App;
