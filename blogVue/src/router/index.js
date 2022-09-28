import Vue from "vue";
import Router from "vue-router";
import blog from "@/components/Blog.vue"
import addBlog from "@/components/AddBlog.vue"
import editBlog from "@/components/EditBlog.vue"
import login from "@/components/Login.vue"

Vue.use(Router);

export default new Router ({
    mode: 'history',
    routes: [
        {
            path: '/',
            alias: '/blogs',
            name: 'blogs',
            component: blog,
        },
        {
            path: '/add',
            alias: '/add-blog',
            name: 'add-blog',
            component: addBlog,
        },
        {
            path: '/blog/:id',
            alias: '/edit-blog',
            name: 'edit-blog',
            component: editBlog,
        },
        {
            path: '/login',
            name: 'login',
            component: login,
        }
    ]
});