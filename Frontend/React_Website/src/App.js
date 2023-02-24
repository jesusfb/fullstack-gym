import React from 'react';
import './App.css';
import Sidebar from './components/Sidebar';
import {BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ProfilePage from './pages/ProfilePage';
import Courses from './pages/Courses';
import Home from './pages/Home';
import OurTeam from './pages/OurTeam';
import GoldMember from './pages/GoldMember';
import SilverMember from './pages/SilverMember';
import BronzeMember from './pages/BronzeMember';
import AboutPage from './pages/AboutPage';
import Plans from './pages/Plans';
import Admin from './components/Admin';

function App() {
  return (
    <Router>
      <Sidebar />
      <Routes>
        <Route path='/Admin' element = {<Admin />} />
        <Route path='/Courses' element = {<Courses />} />
        <Route path='/' exact element = {<Home />} />
        <Route path='/Home' element = {<Home />} />
        <Route path='/OurTeam' element = {<OurTeam />} />
        <Route path='/GoldMember' element= {<GoldMember />}/>
        <Route path='/SilverMember' element= {<SilverMember />}/>
        <Route path='/BronzeMember' element= {<BronzeMember />}/>
        <Route path='/AboutPage' element= {<AboutPage />}/>
        <Route path='/Plans' element= {<Plans />}/>
      </Routes>
    </Router>
  );
}

export default App;
