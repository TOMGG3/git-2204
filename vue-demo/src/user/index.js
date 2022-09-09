//用户模块文件
var username = '张三';
var password = '123';
var login = function (){
    console.log('登陆...')
}

var user = {
    username: 'admin',
    password: '123456',
    login: function(){
        console.log('admin登陆...')
    }
}

//普通模块导入，可以导出多个变量，但是需要使用大括号包裹
export {username, password, login};
//默认导入: 仅能一个数据，无需使用大括号包裹
export default user;