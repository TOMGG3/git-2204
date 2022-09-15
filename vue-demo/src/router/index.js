//导入vue对象
import Vue from 'vue'
//导入VueRouter对象
import VueRouter from 'vue-router'
//导入Home组件
import Home from '../views/Home.vue'
//导入员工组件
import Emp from '@/views/Emp.vue'

Vue.use(VueRouter)

//路由列表
//var,let 声明变量,const 声明常量
const routes = [
  {path: '/', name: 'Home', component: Home},
  //组件立即加载
  {path: '/emp', name: 'Emp', component: Emp},
  //组件懒加载
  {path: '/about', name: 'About', component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')},
  {path: '/dept', name: 'Dept', component: () => import(/* webpackChunkName: "dept" */ '@/views/Dept.vue')},
  {path: '/deptSave', name: 'DeptSave', component: () => import(/* webpackChunkName: "dept" */ '@/views/DeptSave.vue')},
  {path: '/deptList', name: 'DeptList', component: () => import(/* webpackChunkName: "dept" */ '@/views/DeptList.vue')}
]

const router = new VueRouter({
  routes
})

export default router
