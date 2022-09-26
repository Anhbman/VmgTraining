<template>
    <div>
        <div class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Title</th>
                    <th scope="col">Content</th>
                    <th scope="col" colspan="3">Cover</th>
                    <th scope="col">Category</th>
                <!-- <th scope="col">Author</th> -->
                </tr>
            </thead>
            <tbody v-for="(blog, index) in blogs" :key="blog.id">
                <tr>
                    <td>{{index}}</td>
                    <td>{{blog.title}}</td>
                    <td>{{blog.content}}</td>
                </tr>
            </tbody>
        </div>
    </div>
</template>

<script>

import BlogService from '../services/Blog';

export default {
    name: 'blog-list',
    data() {
        return {
            blogs:[]
        }
    },
    methods: {
        retrieveBlog() {
            BlogService.getAll()
                .then(response => {
                    console.log(response);
                })
                .catch(e => {
                    console.log(e);
                })
        }
    },
    mounted() {
        this.retrieveBlog();
    }
}
</script>