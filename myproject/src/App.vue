<template>
  <div id="app">
    <h1 style="color: red;">Batman hehe</h1>
    <h2><u>To Do List</u></h2>
    <Task task="Run a mile" index="0"></Task>
    <Task task="Run a mile" index="1"></Task>
    <Task task="Run a mile" index="2"></Task>
    <Task task="Run a mile" index="3"></Task>
    <Task task="Run a mile" index="4"></Task>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld';
import Task from './components/Task.vue';

export default {
  name: 'App',
  components: {
    HelloWorld,
    Task,
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
