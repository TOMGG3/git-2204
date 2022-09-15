<template>
  <div>
    <h1 class="dd">部门组件</h1>
    <table border="1" width="450" align="center">
      <tr>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>部门位置</th>
      </tr>
      <tr v-for="dept in deptList" :key="dept.deptno">
        <td>{{dept.deptno}}</td>
        <td>{{dept.dname}}</td>
        <td>{{dept.loc}}</td>
      </tr>
    </table>
  </div>
</template>

<script>
//导入axios
// import axios from 'axios';
//单页面组件的js部分也是一个导入的js
export default {
  name: "Dept",
  data(){
    return {
      deptList: []
    }
  },
  methods: {
    listHandle(){
      //模拟数据
      // this.deptList = [
      //   {deptno: 1, dname: '研发部', loc: '北京'},
      //   {deptno: 2, dname: '人力部', loc: '天津'},
      //   {deptno: 3, dname: '销售部', loc: '上海'},
      //   {deptno: 4, dname: '生产部', loc: '重庆'}
      // ];
      //发送异步请求，获取Java服务端数据
      /**
       * axios异步请求工具
       * axios(配置config)
       * axios.get(url, config)
       * axios.post(url, data, config)
       * axios.put(url, data, config)
       * axios.delete(url, config)
       *
       * 关于传递参数
       * get与delete是一致：在config中配置params（路径拼接传参）
       * post与put是一致：通过data参数实现，此参数能自动转换为json字符串，并且在请求头中添加Content-Type=application/json
       *
       * 关于响应
       * 通过then()函数实现接收响应，then()表示成功回调，catch()表示失败回调
       * 此两个方法使用的方法连续调用 axios.get().then().catch()
       */
      // axios({
      //   method: 'post',
      //   url: '/test'
      // });
      // axios.get('http://localhost:8080/dept/list', {
      //   params: {
      //     id: 10,
      //     name: 'tom'
      //   }
      // }).then(function (res){
      //   //then方法的参数并不是服务器的响应数据，而是axios的封装。数据被封装在data属性中
      //   // console.log('成功回调',res);
      //   // console.log(res.data.data)
      //   //注意：then()中函数会改变this关键字的指向
      //   this.deptList = res.data.data;
      // }).catch();
      /**
       * 箭头函数：类似于Java lamda表达式
       * 替代匿名函数
       * function(){}
       * () => {}
       *
       * function(id){}
       * id => {}
       *
       * function(id,name){}
       * (id,name) => {}
       *
       * function(){console.log('hello');}
       * () => console.log('hello');
       * 好处：不改变this指向
       */
      // axios.get('http://localhost:8080/dept/list', {
      //   params: {
      //     id: 10,
      //     name: 'tom'
      //   }
      // }).then(res => {
      //   this.deptList = res.data.data;
      // }).catch(error => console.log(error));
      this.$ajax.get('/dept/list').then(res => {
        console.log('java服务器端响应的数据', res)
        if(res.code == 0){
          this.deptList = res.data;
        }
      }).catch(error => console.log(error));
    }
  },
  computed: {},
  watch: {},
  mounted() {
    this.listHandle();
  }
}
</script>

<style scoped>
/*
scoped 表示当前组件内的样式独有，否则样式是整个项目中组件公共的
*/
</style>