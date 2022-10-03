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
      <template slot="header" slot-scope="scope">
        <Dropdown 
          :dataDropdown="dataDrop"
          v-on:clickDropdown="handleCategory"
          ></Dropdown>
        <el-input 
        placeholder="Please input" 
        v-model="search"
        size="small"
        @input="handleInputSearch"
        ></el-input>
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
  import category from '../services/category';
  import pagination from './Pagination.vue';
  import Dropdown from './Dropdown.vue';
  import Category from '../models/category';

  export default {
    // name: 'paginations',
    data() {
      return {
        tableData: [],
        search: '',
        dataPagination: {},
        currentPage: 1,
        input: '',
        dataDrop: {
          title: 'Category',
          data: [],
        },
        selectedCategory: '',
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
        console.log('search: ' + this.search);
        console.log('selectedCategory: ' + this.selectedCategory);
        this.currentPage = val - 1;
        if (this.search) {
          this.handleInputSearch(this.search, this.currentPage);
        } else if (this.selectedCategory) {
          this.handleCategory(this.selectedCategory, this.currentPage);
        } else {
          this.getAllBlogs(this.currentPage);
        }
      },
      handleInputSearch(val, page) {
        this.search = val;
        Blog.getBlogSearchTitle(val, page)
          .then(result => {
            console.log('title: ' + this.search);
            this.tableData = result.data.blogs;
            this.dataPagination = result.data;
          })
          .catch(error => {
            console.log(error);
          })
      },
      getCategories() {
        category.getAll()
          .then(result => {
            this.dataDrop.data = result.data;
          })
          .catch (error => {
            console.log(error);
          })
      },
      handleCategory(val, page) {
        this.selectedCategory = val;
        console.log('category: ' + this.selectedCategory);
        Blog.getBlogByCategory(this.selectedCategory, page)
          .then ( result => {
            this.tableData = result.data.blogs;
            this.dataPagination = result.data;
          })
          .catch(error => {
            console.log(error);
          })
        console.log(this.selectedCategory);
      }
    },
    components: {
    pagination,
    Dropdown
},
    created() {
      this.getAllBlogs();
      this.getCategories();
    },
  }
</script>