<template>
  <div>
  <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      label="#"
      prop="#"
      type="index">
    </el-table-column>
    <el-table-column
      label="Title"
      prop="title">
    </el-table-column>
    <el-table-column
      label="Content"
      prop="content">
    </el-table-column>
    <el-table-column
      label="Cover"
      >
      <template slot-scope="props">
        <img  v-for="(cover, i) in props.row.covers" :key="i"
          v-bind:src="'http://127.0.0.1:8080/image/' + cover.name" 
          width="50px"
        />        
      </template>
    </el-table-column>
    <el-table-column
      label="Category"
      prop="category.name">
    </el-table-column>
    <el-table-column
      align="right">
      <template slot="header" slot-scope="">
        <el-input
          v-model="search"
          size="mini"
          placeholder="Type to search">
          </el-input>
      </template>
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
  <pagination :dataPagination="dataPagination" v-on:clickPagination="onPaginationClick"></pagination>
</div>

</template>

<script>

  import Blog from '../services/Blog';
  import pagination from './Pagination.vue';

  export default {
    data() {
      return {
        tableData: [],
        search: 'jjh',
        dataPagination: null,
        currentPage: 1,
      }
    },
    methods: {
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
        Blog.delete(row.id)
                .then(response => {
                this.getAllBlogs(this.currentPage);
            })
                .catch(e => {
                console.log(e);
            });
      },
      getAllBlogs(page = 0) {
        Blog.getAll(page)
          .then(response => {
          this.tableData = response.data.blogs;
          this.dataPagination = response.data;
          return this.blogs;
        })
          .catch(e => {
          console.log(e);
        });
      },
      onPaginationClick(val) {
        this.currentPage = val - 1;
        this.getAllBlogs(this.currentPage);
      }
    },
    components: {
      pagination,
    },
    created() {
      this.getAllBlogs();
    }
  }
</script>