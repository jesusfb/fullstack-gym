import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

class AuthService {
  login(user_email, password) {
    return axios.post(API_URL + "authenticate", {
        user_email,
        password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }

}

export default new AuthService();
