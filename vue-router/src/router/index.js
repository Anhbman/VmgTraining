import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/home'
import blog from '@/components/Blog'
import services from '@/components/Services'
import contact from '@/components/Contact'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/blog',
      name: 'blog',
      component: blog
    },
    {
      path: '/services',
      name: 'services',
      component: services
    },
    {
      path: '/contact',
      name: 'contact',
      component: contact
    },
  ]
})
