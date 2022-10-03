import axios from "axios";
import authHeader from "./auth-header";

const API_URL = 'http://localhost:8080/api/auth/';

class UserService {

    register(data) {
        return axios.post(API_URL + 'signup', data);
    }

}

export default new UserService();
