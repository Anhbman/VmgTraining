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
                    <th scope="col"></th>
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
                    <td>
                        <a class="badge badge-warning btn btn-info" :href="'/blog/' + blog.id">Edit</a>
                        <button class="btn btn-warning" @click="deleteBlog(blog.id)">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>
        <pagination :dataPagination="dataPagination" v-on:clickPagination="onPaginationClick"></pagination>
    </div>
</template>

<script>

import Blog from '../services/Blog';
import Pagination from './Pagination.vue';

export default {
    name: "Blogs",
    data() {
        return {
            blogs: [],
            dataPagination: null,
            currentPage: 1,
        };
    },
    methods: {
        getAllBlogs(page = 0) {
            Blog.getAll(page)
                .then(response => {
                this.blogs = response.data.blogs;
                this.dataPagination = response.data;
                return this.blogs;
            })
                .catch(e => {
                console.log(e);
            });
        },
        deleteBlog(id) {
            Blog.delete(id)
                .then(response => {
                this.getAllBlogs();
            })
                .catch(e => {
                console.log(e);
            });
        },
        onPaginationClick(val) {
            this.currentPage = val - 1;
            this.getAllBlogs(this.currentPage);
        }
    },
    created() {
        this.getAllBlogs();
    },
    components: { Pagination }
}

</script>
