import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import AuthService from "./services/auth.service";
import './App.css';
import Sidebar from './components/Sidebar';
import {BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import {Link} from 'react-router-dom';
import ProfilePage from './pages/ProfilePage';
import Courses from './pages/Courses';
import Home from './pages/Home';
import OurTeam from './pages/OurTeam';
import GoldMember from './pages/GoldMember';
import SilverMember from './pages/SilverMember';
import BronzeMember from './pages/BronzeMember';
import AboutPage from './pages/AboutPage';
import Plans from './pages/Plans';
import Admin from './pages/admin/Admin';
import AddUser from './pages/admin/user/AddUser';
import EditUser from './pages/admin/user/EditUser';
import ViewUsers from './pages/admin/user/ViewUsers';
import ViewPersonalUser from './pages/admin/user/ViewPersonalUser';
import AddInstructor from './pages/admin/instructor/AddInstructor';
import EditInstructor from './pages/admin/instructor/EditInstructor';
import ViewInstructors from './pages/admin/instructor/ViewInstructors';
import ViewPersonalInstructor from './pages/admin/instructor/ViewPersonalInstructor';
import AddCourse from './pages/admin/course/AddCourse';
import EditCourse from './pages/admin/course/EditCourse';
import ViewCourses from './pages/admin/course/ViewCourses';
import ViewPersonalCourse from './pages/admin/course/ViewPersonalCourse';
import Login from './pages/Login';
import LoginTest from './pages/LoginTest';
import BoardUser from "./components/board-user.component";


class AppTest extends Component {
    
    constructor(props) {
        super(props);
        this.logOut = this.logOut.bind(this);

    this.state = {
        currentUser: undefined,
        };
    }

    componentDidMount() {
        const user = AuthService.getCurrentUser();
    
    if (user) {
        this.setState({
            currentUser: user,
        });
        }
    }

    logOut() {
        AuthService.logout();
        this.setState({
          currentUser: undefined,
        });
      }

      render() {
        const { currentUser } = this.state;

        return (
            <div>
                <nav className="navbar navbar-expand navbar-dark bg-dark">
                    <Link to={"/Home"} className="navbar-brand">
                        GYM
                    </Link>

                    <div className="navbar-nav mr-auto">
                        <li className="nav-item">
                            <Link to={"/"} className="nav-link">
                                Home
                            </Link>
                            </li>
                            {/* {currentUser && (
                            <li className="qnav-item">
                                <Link to={"/Admin"} className="nav-link">
                                Admin
                                </Link>
                            </li>
                        )} */}
                    </div>

                    {/* {currentUser ? (
                        <div className="navbar-nav ml-auto">
                            <li className="nav-item">
                                <Link to={"/profile"} className="nav-link">
                                    {currentUser.username}
                                </Link>
                            </li>
                            <li className="nav-item">
                                <a href="/login" className="nav-link" onClick={this.logOut}>
                                    LogOut
                                </a>
                            </li>
                     </div>
                    ) : (
                        <div className="navbar-nav ml-auto">
                            <li className="nav-item">
                                <Link to={"/login"} className="nav-link">
                                Login
                                </Link>
                            </li>
                        </div>
                    )}                                */}
                </nav>
                <div className="container mt-3">
                    <Router>
                        <Routes>
                            <Route path='/Admin' element = {<Admin />} />

                            <Route path='/AddUser' element = {<AddUser />} />
                            <Route path='/EditUser/:user_id' element = {<EditUser />} />
                            <Route path='/ViewUsers' element = {<ViewUsers />} />
                            <Route path='/ViewPersonalUser/:user_id' element = {<ViewPersonalUser />} />

                            <Route path='/AddInstructor' element = {<AddInstructor />} />
                            <Route path='/EditInstructor/:id' element = {<EditInstructor />} />
                            <Route path='/ViewInstructors' element = {<ViewInstructors />} />
                            <Route path='/ViewPersonalInstructor/:id' element = {<ViewPersonalInstructor />} />

                            <Route path='/AddCourse' element = {<AddCourse />} />
                            <Route path='/EditCourse/:id' element = {<EditCourse />} />
                            <Route path='/ViewCourses' element = {<ViewCourses />} />
                            <Route path='/ViewPersonalCourse/:id' element = {<ViewPersonalCourse />} />

                            <Route path='/LoginTest' element = {<LoginTest />} />
                            <Route path='/Login' element = {<Login />} />
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
                </div>
            </div>
        )
      }
}

export default AppTest;
