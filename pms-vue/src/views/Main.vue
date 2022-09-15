<template>
  <div>
    <el-container class="main-container">
      <el-header>
        <el-menu default-active="1" mode="horizontal" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="1" class="system-info">后台管理系统</el-menu-item>
          <el-submenu index="2" class="system-setting">
            <template slot="title">当前登录用户: {{username}}</template>
            <el-menu-item index="2-1">个人信息</el-menu-item>
            <el-menu-item index="2-2">修改密码</el-menu-item>
            <el-menu-item index="2-3" @click="logoutHandle">退出系统</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-header>
      <el-container>
        <el-aside width="208px">
          <el-menu default-active="home" router class="left-menu" background-color="#545c64"
            text-color="#fff" active-text-color="#ffd04b">
            <el-menu-item index="home">
              <i class="el-icon-s-home"></i>
              <span slot="title">主页</span>
            </el-menu-item>
            <!--动态菜单-->
            <el-submenu v-for="menu1 in navTree" :key="menu1.id" :index="menu1.url">
              <template slot="title">
                <i :class="menu1.icon"></i>
                <span>{{menu1.name}}</span>
              </template>
              <el-menu-item v-for="menu2 in menu1.children" :key="menu2.id" :index="menu2.url">
                {{menu2.name}}
              </el-menu-item>
            </el-submenu>
            <!--静态菜单-->
            <!-- <el-submenu index="system">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>系统管理</span>
              </template>
              <el-menu-item index="user">用户列表</el-menu-item>
              <el-menu-item index="role">角色列表</el-menu-item>
              <el-menu-item index="permission">权限列表</el-menu-item>
              <el-menu-item index="menu">菜单列表</el-menu-item>
            </el-submenu>
            <el-submenu index="hr">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>人力资源</span>
              </template>
              <el-menu-item index="emp">员工列表</el-menu-item>
              <el-menu-item index="dept">部门列表</el-menu-item>
            </el-submenu> -->
          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import {dataToTree} from '@/utils';
export default {
  data() {
    return {
      username: '用户',
      navTree: []
    }
  },
  methods: {
    nav() {
      this.$ajax.get('/permission/nav').then(res => {
        if(res.code == 0){
          this.navTree = dataToTree(res.data);
          console.log(this.navTree);
        }
      }).catch(error => console.log(error));
    },
    logoutHandle() {
      this.$confirm('确定要退出系统吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        //点击确定按钮
        this.$ajax.get('/auth/logout').then(() => {
          //清除缓存
          sessionStorage.removeItem('loginUser');
          this.$router.push({name: 'login'});
        }).catch(error => console.log(error));
      }).catch(() => {
        //点击取消按钮       
      });
    }
  },
  mounted() {
    var user = JSON.parse(sessionStorage.getItem('loginUser'));
    if(user){
      this.username = user.nickname;
    }
    this.nav();
  }
}
</script>

<style scoped>
.main-container {
  height: 100vh;
}
.el-header {
  padding: 0;
}
.system-info {
  font-size: 28px;
  font-family: "楷体";
}
.system-setting {
  float: right;
}
.el-aside {
  background-color: #545c64;
  overflow: hidden;
}
.left-menu {
  width: 208px;
}
</style>