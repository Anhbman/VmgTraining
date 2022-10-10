<template>
    <div class="submit-form">
        <form id="add-blog" enctype="multipart/form-data">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" id="title" required v-model="blog.title" name="title">
            </div>
            <div class="form-group">
                <label for="">content</label>
                <input class="form-control" type="text" id="content" v-model="blog.content" name="content">
            </div>
            <div class="form-group">
                <label for="">Cover</label>
                <input class="form-control" type="file" id="file" multiple name="files">
            </div>
            <div class="form-group">
                <label for="">Category</label>
                <select class="form-control"  v-model="blog.category" name="category">
                    <option  v-for="(item) in cate" :key="item.id" :value="item.id">{{item.name}}</option>
                </select>
            </div>
            <button class="btn btn-success" v-on:click.prevent="saveBlog()">Save</button>
        </form>
    </div>
</template>

<script>

import Blog from '../services/Blog';
import category from '../services/category';
export default {
    name: 'add-blog',
    data() {
        return {
            blog: {
                id: null,
                title: '',
                content: '',
                files: [],
                category: Object,
            },
            cate: [],
            submitted: false,
        };
    },
    methods: {
        saveBlog() {
            let form = document.getElementById('add-blog');
            let formData = new FormData(form);
            console.log(formData);
            Blog.create(formData)
                .then(response => {
                    this.submitted = true;
                    this.$router.push('/');
                })
                .catch(e => {
                    console.log(e);
                })
        },

        newBlog() {
            this.submitted = false;
            this.blog = {};
        },
        getCategories() {
            category.getAll()
                .then(response => {
                    this.cate = response.data;
                })
        },
    },
    mounted() {
        console.log('add blog running...');
        this.getCategories();
    }
}
</script>
<!-- 
<template>
    <h1>add Blog</h1>
</template>

<script>
export default {
    name: 'add-blog'
}
</script> -->