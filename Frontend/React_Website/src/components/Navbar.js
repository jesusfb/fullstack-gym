import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import AuthService from "../services/auth.service";
import {Link} from 'react-router-dom';

class Navbar extends Component {

    constructor(props) {
        super(props);
        this.logOut = this.logOut.bind(this);
    
        this.state = {
          showModeratorBoard: false,
          showAdminBoard: false,
          currentUser: undefined,
        };
      }

      componentDidMount() {
        const user = AuthService.getCurrentUser();
    
        if (user) {
          this.setState({
            currentUser: user,
            showModeratorBoard: user.roles.includes("ROLE_MODERATOR"),
            showAdminBoard: user.roles.includes("ROLE_ADMIN"),
          });
        }
      }

      logOut() {
        AuthService.logout();
        this.setState({
          showModeratorBoard: false,
          showAdminBoard: false,
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
                  <Link to={"/Home"} className="nav-link">
                    Home
                  </Link>
                </li>
    
                {currentUser && (
                  <li className="nav-item">
                    <Link to={"/Admin"} className="nav-link">
                      Admin
                    </Link>
                  </li>
                )}
              </div>
    
              {currentUser ? (
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
                    <Link to={"/LoginTest"} className="nav-link">
                      Login
                    </Link>
                  </li>
                </div>
              )}
            </nav>
        </div>
        )
    }
}

export default Navbar;