//导入axios
import axios from 'axios';
import {Message} from 'element-ui';

//创建axios对象
var http = axios.create({
  //基础路径
  baseURL: 'http://localhost:8080',
  //请求超时时间
  timeout: 10000
});

//发送请求时携带cookie
http.defaults.withCredentials = true;

//请求拦截器: 前端在发请求之前拦截下来做出处理
//config为请求的配置对象
http.interceptors.request.use(config => {
  // 1.我们需要给每个请求添加一个header
  var token = sessionStorage.getItem('Authorization');
  if(token){
    config.headers["Authorization"] = token;
  }
  return config;
},error => {
  //当这个请求失败做什么 拒绝请求 报错信息
  return Promise.reject(error);
})

//响应的拦截: 前端接收到后台的响应之后，预先做出的处理
http.interceptors.response.use(response => {
  console.log(response);
  if(response.headers['authorization']){
    sessionStorage.setItem('Authorization', response.headers['authorization']);
  }
  // 一般做一个全局的响应码判断 200  500
  if(response.data && response.data.code != 0){
    Message.error(response.data.message);
  }
  return response.data;
}, error => {
  // 响应报错了
  //this.$message.error();
  Message.error("请求异常，请检查网络");
  return Promise.reject(error);
})

export default http;