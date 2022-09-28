import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router ({
    mode: 'history',
    routes: [
        {
            path: '/',
            alias: '/blogs',
            name: 'blogs',
            component: () => import('./components/Blog.vue'),
        },
        {
            path: '/blog/',
            alias: '/add-blog',
            name: 'add-blog',
            component: () => import('./components/AddBlog.vue'),
        },
        {
            path: '/blog/:id',
            alias: '/edit-blog',
            name: 'edit-blog',
            component: () => import('./components/EditBlog.vue'),
        }
    ]
});