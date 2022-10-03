import http from "../http-common";
import authHeader from "../services/auth-header";

class CategoryService {
    getAll() {
        return http.get('/categories', {
            headers: authHeader()
        });
    }
}

export default new CategoryService();