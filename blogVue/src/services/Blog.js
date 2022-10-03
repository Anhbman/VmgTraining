import http from "../http-common";
import authHeader from "./auth-header";

// const BLOG_API_BASE_URL = 'http://localhost:8080/api/blogs'
var user = JSON.parse(localStorage.getItem('user'));

class BlogService {

    getAll(page = 0) {
        return http.get('/blogs', {
            params: {
                page: page
            },
            headers: authHeader()
        });
    }

    getBlogSearchTitle(title, page = 0) {
        console.log('getBlogSearchTitle...');
        return http.get('/blogs', {
            params: {
                page: page,
                title: title,
            },
            headers: authHeader(),
        })
    }

    getBlogByCategory(category, page = 0) {
        return http.get('/blogs', {
            params: {
                category,
                page,
            },
            headers: authHeader(),
        })
    }

    getById(id) {
        return http.get(`/blogs/${id}`, {
            headers: authHeader()
        });
    }

    create(data) {
        return http.post('/blogs', data, {
            headers: {
                "Content-Type": "multipart/form-data",
                "Authorization": `Bearer ${user.token}`
            }
        });
    }

    update(id, data) {
        return http.put(`/blogs/${id}`, data);
    }

    delete(id) {
        return http.delete(`/blogs/${id}`, {
            headers: authHeader()
        });
    }

    findByTitle(title) {
        return http.get(`/blogs?title=${title}`, {
            headers: authHeader()
        });
    }

    findByAuthor(author) {
        return http.get(`/blogs?author=${author}`, {
            headers: authHeader(
            )
        });
    }
}

export default new BlogService();