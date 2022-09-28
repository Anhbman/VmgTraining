import http from "../http-common";

class CategoryService {
    getAll() {
        return http.get('/categories');
    }
}

export default new CategoryService();