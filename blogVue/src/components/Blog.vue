<template>
    <div class="container">
        <h1 class="text-center">Blog list</h1>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Title</th>
                    <th scope="col">Content</th>
                    <th scope="col" colspan="2">Cover</th>
                    <th scope="col">Category</th>
                <!-- <th scope="col">Author</th> -->
                </tr>
            </thead>
            <tbody v-for="(blog, index) in blogs" :key="blog.id">
                <tr>
                    <td>{{index}}</td>
                    <td>{{blog.title}}</td>
                    <td>{{blog.content}}</td>
                    <td colspan="2">
                        <img  v-for="(cover, i) in blog.covers" :key="i"
                         v-bind:src="'http://127.0.0.1:8080/image/' + cover.name" 
                         width="50px">
                    </td>
                    <td>{{blog.category ? blog.category.name : ''}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>

import Blog from '../services/Blog';

export default {
    name: 'Blogs',
    data() {
        return {
            blogs: [],
        }
    },
    methods: {
        getBlogs() {
            Blog.getBlogs()
                .then(response => {
                    console.log('getData blogs');
                    this.blogs = response.data
                    this.blogs.forEach(item => {
                        console.log(item);
                    })
                    return this.blogs;
                })
                .catch(e => {
                    console.log(e);
                })
        }
    },
    created() {
        this.getBlogs();
    },
}

</script>
