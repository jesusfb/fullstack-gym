import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/';

class UserService {  

  getCoursesContent() {
    return axios.get(API_URL + 'courses/all');
  }

  getInstructorsContent() {
    return axios.get(API_URL + 'instructors/all');
  }

  getUsersContent() {
    return axios.get(API_URL + 'users/all');
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }
}

export default new UserService();
