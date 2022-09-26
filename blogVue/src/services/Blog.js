import axios from "axios";

const BLOG_API_BASE_URL = 'http://localhost:8080/api/blogs'

class BlogService {
    getBlogs() {
        return axios.get(BLOG_API_BASE_URL);
    }
}

export default new BlogService();