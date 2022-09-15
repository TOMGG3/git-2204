//导入axios
import axios from 'axios';

//创建axios对象，进行公共配置
var instance = axios.create({
    //基础路径
    baseURL: 'http://localhost:8080',
    //请求超时时间
    timeout: 10000
});

//设置发送请求时允许携带凭证(cookie)
instance.defaults.withCredentials=true;

//请求拦截器:在axios发送请求之前作出的处理
instance.interceptors.request.use(config => {
    console.log('请求的配置对象', config);
    //在请求中添加自定义的数据
    config.headers['myToken'] = 'hello axios';
    return config;
},error => Promise.reject(error));

//响应拦截器:在axios接收响应之后作出的处理
instance.interceptors.response.use(response => {
    //将服务器响应的数据从axios封装中取出，直接返回
    return response.data;
}, error => Promise.reject(error));

//导入自己创建并且已经做好配置的axios对象
export default instance;