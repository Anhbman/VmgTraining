<template>
    <div class="container">
        <header class="jumbotron">
            <h3>{{content}}</h3>
        </header>
    </div>
</template>

<script>

import userService from '../services/user.service';
export default {
   name: 'home',
   data() {
    return {
        content:''
    }
   },
}
</script>