<template>
  <div class="container">
    <el-form id="update-blog" :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
      <el-form-item label="Title" prop="title">
        <el-input type="text" v-model="ruleForm.title" autocomplete="off" name="title"></el-input>
      </el-form-item>
      <el-form-item label="content" prop="content">
        <el-input type="text" v-model="ruleForm.content" autocomplete="off" name="content"></el-input>
      </el-form-item>
      <el-form-item label="covers" prop="files">
        <img  v-for="(cover, i) in ruleForm.covers" :key="i"
          v-bind:src="'http://127.0.0.1:8080/image/' + cover.name" 
          width="50px"
        />
      </el-form-item>
      <el-form-item>
        <el-upload
        class="upload-demo"
        :on-preview="handlePreview"
        multiple="multiple"
        action=""
        :auto-upload="false"
        :on-remove="handleRemove"
        list-type="picture"
        name="files"
        >
        <el-button size="small" type="primary">Click to upload</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <Select 
          :options="categories"
          :selected="ruleForm.category"
          v-on:ClickSelect="handleClickSelect"  
        ></Select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">Submit</el-button>
        <el-button @click="resetForm('ruleForm')">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>

import Select from './Select.vue';
import category from '../services/category';
import Blog from '../services/Blog';

export default {
  data() {
    var checkTitle = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Please input the title'));
      }
      setTimeout(() => {
        if (value === '') {
          callback(new Error('Please input title'));
        } else {
          if (value.length < 6) {
            callback(new Error('title phải có ký tự hơn 6'));
          } else {
            callback();
          }
        }
      }, 1000);
    };
    var validateContent = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the content'));
      } else {
        if (value.length < 6) {
          callback(new Error('content phải có ký tự hơn 6'));
        }
        callback();
      }
    };
    return {
      ruleForm: {
        files: [],
        covers: [],
        title: '',
        content: '',
        category: {},
      },
      rules: {
        content: [
          { validator: validateContent, trigger: 'blur' }
        ],
        title: [
          { validator: checkTitle, trigger: 'blur' }
        ]
      },
      categories: [],
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let form = document.getElementById('update-blog');
          let formData = new FormData(form);
          for (const value of formData.values()) {
            console.log(value);
          }
          console.log("file: ",this.ruleForm.files);
          formData.append("id", this.ruleForm.id);
          formData.append("category.id", this.ruleForm.category.id);
          console.log(this.ruleForm.category);
          Blog.update(formData)
            .then(result => {
              console.log(result);
              // alert("Update thanh cong!!!");
              this.$router.push('/');
            })
            .catch(error => {
              console.log(error);
            })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    getCategories() {
      category.getAll()
        .then(result => {
          this.categories = result.data;
        })
        .catch(error => {
          console.log(error);
        })
    },
    getBlogByID(id) {
      Blog.getById(id)
        .then(result => {
          this.ruleForm = result.data;
          this.ruleForm.category = result.data.category;
        })
    },  
    handleClickSelect(val) {
      this.ruleForm.category = val
    },
  },
  components: {
    Select,
  },
  mounted() {
    this.getBlogByID(this.$route.params.id);
  },
  created() {
    this.getCategories();
  }
}
</script>

<style>
  .container{
    margin-top: 20px;
    width: 75%;
  }
</style>