import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {path: '/login', name: 'login', component: () => import(/* webpackChunkName: "about" */ '@/views/Login.vue')},
  {
    path: '/',
    name: 'main',
    component: () => import(/* webpackChunkName: "about" */ '@/views/Main.vue'),
    redirect: {name: 'home'},
    children: [
      {path: 'home', name: 'home', component: () => import(/* webpackChunkName: "about" */ '@/views/Home.vue')},
      {path: 'sys-user', name: 'user', component: () => import(/* webpackChunkName: "about" */ '@/views/system/User.vue')},
      {path: 'sys-role', name: 'role', component: () => import(/* webpackChunkName: "about" */ '@/views/system/Role.vue')},
      {path: 'sys-permission', name: 'permission', component: () => import(/* webpackChunkName: "about" */ '@/views/system/Permission.vue')},
      {path: 'sys-menu', name: 'menu', component: () => import(/* webpackChunkName: "about" */ '@/views/system/Menu.vue')},
      {path: 'hr-emp', name: 'emp', component: () => import(/* webpackChunkName: "about" */ '@/views/hr/Emp.vue')},
      {path: 'hr-dept', name: 'dept', component: () => import(/* webpackChunkName: "about" */ '@/views/hr/Dept.vue')}
    ],
    //路由独享守卫
    /**
     * beforeEnter 函数
     * @param to 对象，导航之后的路由对象
     * @param from 对象，导航之前的路由对象
     * @param next 函数，放行或拦截
     */
    beforeEnter: (to, from, next) => {
      var loginUser = sessionStorage.getItem('loginUser');
      if(loginUser){
        //next方法没有参数就是放行
        next();
      }else{
        //next方法传入参数，跳转路由的名称，拦截（不再向指定路由进行导航，而是跳转到指定的路由）
        next({name: 'login'});
      }
    }
  }
]

const router = new VueRouter({
  routes
})

/**
 * 全局导航守卫
 * beforeEach()
 * 参数是一个回调函数
 * 回调函数的三个参数
 * 1.to 将导航到的路由对象
 * 2.from 导航之前的路由对象
 * 3.next 导航函数，在守卫执行时，此函数必须执行一次，仅能执行一次
 * next()无参数，表示对导航的放行
 * next(跳转路径) 有参数, 表示对导航的拦截，并跳转到指定的路由
 */
router.beforeEach((to, from, next) => {
  // console.log('to:', to);
  // console.log('from:', from);
  // console.log('next:', next);
  next();
});

export default router
