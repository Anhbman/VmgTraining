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
      </el-form-item>
    </el-form>
  </div>
</template>
  <script>

    import User from '../models/user';

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
          user: new User('',''),
          loading: false,
          message:'',
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
        this.$refs[formName].validate((valid) => {
          console.log(valid);
          if (valid) {
            this.user.username = this.ruleForm.username;
            this.user.password = this.ruleForm.password;

            this.$store.dispatch('auth/login', this.user).then(
              () => {
                this.$router.push('/profile');
              },
              error => {
                this.loading = false;
                this.message = 
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
              }
            )

            // console.log(this.ruleForm);
            // AuthSerivce.login(this.ruleForm)
            //   .then(result => {
            //     console.log(result);
            //   })
            //   .catch(e => {
            //     console.log(e);
            //   })
          } else {
            console.log('error submit!!');
            this.loading = false;
            return false;
          }
        });
      },
      },
      computed: {
        loggedIn() {
          return this.$store.state.auth.status.loggedIn;
        }
      },
      created() {
        if (this.loggedIn) {
          this.$router.push('/profile');
        }
      }
    }
  </script>