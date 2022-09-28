<template>
  <div>
    <el-form id="form-login" :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
      <el-form-item label="User name" prop="username">
      <el-input v-model="ruleForm.username"></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="pass">
      <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">Login</el-button>
      <!-- <el-button @click="resetForm('ruleForm')">Reset</el-button> -->
      </el-form-item>
    </el-form>
  </div>
</template>
  <script>

    import AuthSerivce from '../services/AuthService'

    export default {
      name: 'login',
      data() {
        var checkName = (rule, value, callback) => {
          if (value.length === 0) {
            return callback(new Error('Please input the User name'));
          } 
          setTimeout(() => {
            if (value.length < 6) {
              callback(new Error('Please input username than 6 letter'));
            } else {
              callback();
            }
          }, 1000);
        };
        var validatePass = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('Please input the password'));
          } else {
            callback();
          }
        };
        return {
          ruleForm: {
            password: '',
            username: ''
          },
          rules: {
            password: [
              { validator: validatePass, trigger: 'blur' }
            ],
            username: [
              { validator: checkName, trigger: 'blur' }
            ]
          }
        };
      },
      methods: {
        submitForm(formName) {
          console.log('submit running');
          // console.log(this.$refs[formName].validate);
        this.$refs[formName].validate((valid) => {
          console.log(valid);
          if (valid) {
            // alert('submit!');
            // let form = document.getElementById('form-login');
            // let formdata = new FormData(form);
            // console.log(formdata);
            // const fields = JSON.stringify(this.$refs.ruleForm.fields)
            console.log(this.ruleForm);
            AuthSerivce.login(this.ruleForm)
              .then(result => {
                console.log(result);
              })
              .catch(e => {
                console.log(e);
              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
        resetForm(formName) {
          this.$refs[formName].resetFields();
        }
      }
    }
  </script>