import http from "../http-common";

// const BLOG_API_BASE_URL = 'http://localhost:8080/api/blogs'

class BlogService {
    getAll() {
        return http.get('/blogs');
    }

    getById(id) {
        return http.get(`/blogs/${id}`);
    }

    create(data) {
        return http.post('/blogs', data, {
            headers: {
                "Content-Type": "multipart/form-data",
            }
        });
    }

    update(id, data) {
        return http.put(`/blogs/${id}`, data);
    }

    delete(id) {
        return http.delete(`/blogs/${id}`);
    }

    findByTitle(title) {
        return http.get(`/blogs?title=${title}`);
    }

    findByAuthor(author) {
        return http.get(`/blogs?author=${author}`);
    }
}

export default new BlogService();