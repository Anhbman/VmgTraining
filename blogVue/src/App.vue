<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <router-link to="/" class="navbar-brand">Blog</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/" class="nav-link">Blogs</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/add" class="nav-link">Add</router-link>
        </li>
        <li class="nav-item right">
          <router-link class="nav-link" to="/login">Login</router-link>
        </li>
      </div>
    </nav>
    <div class="container mt3">
      <router-view></router-view>
    </div>
    <!-- <blog></blog> -->
  </div>
</template>

<script>

import Blog from './components/Blog.vue';

export default {
  name: 'App',
  components: {
    Blog,
  }
}
</script>

<style>
</style>
