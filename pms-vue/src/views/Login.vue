<template>
  <div class="login-page">
    <div class="login-form">
      <el-form ref="loginForm" :model="dataForm" :rules="rules">
        <h3>请登录</h3>
        <el-form-item prop="username">
          <el-input v-model="dataForm.username" placeholder="用户名" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <el-form-item prop="userpwd">
          <el-input v-model="dataForm.userpwd" placeholder="密码" show-password prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="loginHandle">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dataForm: {
        username: '',
        userpwd: ''
      },
      rules: {
        //某个表单元素的校验规则,其中名为表单元素双向绑定的数据名称
        username: [
          {required: true, message: '用户名不能为空', trigger: ['blur', 'change']}
        ],
        userpwd: [
          {required: true, message: '密码不能为空', trigger: ['blur', 'change']}
        ]
      }
    }
  },
  methods: {
    loginHandle() {
      //1.对表单的数据进行校验
      this.$refs.loginForm.validate(valid => {
        if(valid){
          //2.数据提交
          this.$ajax.post('/auth/login', this.dataForm).then(res => {
            if(res.code == 0){
              //3.缓存数据
              /**
               * 前端的缓存
               * 会话：浏览器不关闭一直有效
               * sessionStorage
               * 本地：一直有效，直到自己手动删除
               * localStorage
               *
               * 方法
               * setItem(key, value)
               * getItem(key)
               * removeItem(key)
               */
              sessionStorage.setItem('loginUser', JSON.stringify(res.data));
              //4.页面跳转
              this.$router.push({name: 'main'});
            }
          }).catch(error => console.log(error));
        }
      });
      
    }
  }
}
</script>

<style scoped>
.login-page {
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  background-image: url(~@/assets/login_bg.jpg);
  background-repeat: no-repeat;
  background-size: cover;
}
.login-form {
  background-color: #ffffff;
  width: 350px;
  text-align: center;
  /* height: 400px; */
  border-radius: 10px;
  position: absolute;
  top: 50%;
  left: 75%;
  margin-top: -250px;
  margin-left: -175px;
  padding: 10px 40px;
}
.el-button {
  width: 100%;
}
</style>