import Vue from 'vue'
import App from './App.vue'
import router from './router'
//导入封装号的axios工具
import request from '@/utils/request'
//导入ElementUI对象
import ElementUI from 'element-ui';
//导入ELementUI的核心样式文件
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false
Vue.use(ElementUI);

//使用用户模块中封装的数据
//1.导入:目录src可使用@替代，js后缀可省略，如果导入的文件是某个目录中index.js，文件名也可省略
//如果引入的数据为普通导出，需要使用大括号包裹，如果是默认导出则不需要
import {username, password, login} from '@/user';
import user from '@/user';
//2.使用
console.log('用户名：',username);
console.log('密码：',password);
login();
console.log('用户对象中的用户名：', user.username);
console.log('用户对象中的密码：', user.password);
user.login();

//将封装号的axios挂载到Vue对象下
Vue.prototype.$ajax=request;

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
