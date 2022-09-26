<template>
    <h3>
        {{index}} - {{task}}
    </h3>
</template>

<script>
    export default {
        name: 'Task',
        props: [
            'task', 'index'
         ]
    }
</script>

<style>
    h3{
        color: white;
        background-color: green;
        width: 300px;
        height: 30px;
        padding: 15px;
    }
</style>
