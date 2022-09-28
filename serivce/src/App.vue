<template>
  <div>
    <div v-for="post in posts" v-bind:key="post.id">
      <h2>{{post.title}}</h2>
      <p>{{post.body}}</p>
    </div>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld'

export default {
  name: 'App',
  data() {
    return {
    };
  },
  methods: {
    // async getData() {
    //   try {
    //     // const response = await this.$http.get(
    //     //   "http://jsonplaceholder.typicode.com/posts"
    //     // );
    //     // this.posts = response.data;
    //     let response = await fetch("http://jsonplaceholder.typicode.com/posts");
    //     this.posts = await response.json();
    //     console.log(posts);
    //   } catch (error) {
    //     console.log(error);
    //   }
    // }
  },
  computed: {
    posts() {
      return this.$store.getters.posts;
    }
  },
  created() {
    console.log(this.$store);
    this.$store.dispatch('loadPosts');
    // this.getData();
  },
  components: {
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
