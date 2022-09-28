import axios from "axios";

const API_URL = 'http://localhost:8080/api/auth/';

class AuthService {
    login(user) {
        return axios.post(
            API_URL + 'signin', {
                username: user.username,
                password: user.password
            })
            .then(result => {
                if(result.data.token) {
                    localStorage.setItem('user', JSON.stringify(result.data));
                }

                return result.data;
            })
    }

    logout() {
        localStorage.removeItem('user');
    }

    register(user) {
        return axios.post(API_URL + 'signup', {
            username: user.username,
            password: user.password
        })
    }
}

export default AuthService();