import Vue from "vue";
import Router from "vue-router";
import blog from "@/components/Blog.vue"
import addBlog from "@/components/AddBlog.vue"
import editBlog from "@/components/EditBlog.vue"
import login from "@/components/Login.vue"
import register from "@/components/Register.vue"
import profile from "@/components/Profile.vue"
import boardUser from "@/components/BoardUser.vue"
import listBlog from "@/components/ListBlog.vue"
import updateBlog from "@/components/UpdateBlog.vue"

Vue.use(Router);

export const router = new Router ({
    mode: 'history',
    routes: [
        {
            path: '/',
            alias: '/blogs',
            name: 'blogs',
            component: listBlog,
        },
        {
            path: '/home',
            component: listBlog,
        },
        {
            path: '/add',
            alias: '/add-blog',
            name: 'add-blog',
            component: addBlog,
        },
        {
            path: '/blogs/:id',
            alias: '/edit-blog',
            name: 'edit-blog',
            component: editBlog,
        },
        {
            path: '/login',
            name: 'login',
            component: login,
        },
        {
            path: '/register',
            component: register,
        },
        {
            path: '/profile',
            component: profile,
        },
        {
            path: '/user',
            component: boardUser,
        },
        {
            path: '/update/:id',
            component: updateBlog,
        }
    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login', '/register'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    if (authRequired && !loggedIn) {
        next('/login');
    } else {
        next();
    }
})
