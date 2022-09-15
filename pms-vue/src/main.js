//导入vue
import Vue from 'vue'
//导入入口页面
import App from './App.vue'
//导入路由
import router from './router'
//导入vuex
import store from './store'
//导入ElementUI
import ElementUI from 'element-ui'
//导入ElementUI核心样式文件
import 'element-ui/lib/theme-chalk/index.css'
//导入自己封装axios工具类
import http from '@/utils/http'

Vue.config.productionTip = false

//在vue中使用ElementUI
Vue.use(ElementUI)

//将axios工具类挂载在到全局:在所有的组件都可以访问Vue对象,将工具类挂载到Vue对象下,使其成为Vue对象的一个属性
Vue.prototype.$ajax = http;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
