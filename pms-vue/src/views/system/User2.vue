<template>
  <div>
    <!--条件查询表单-->
    <el-form ref="searchForm" :model="searchForm" :inline="true">
      <el-form-item>
        <el-input v-model="searchForm.username" placeholder="用户名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchForm.nickname" placeholder="昵称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="listHandle()">查询</el-button>
        <el-button type="primary" @click="addOrEditHandle()">新增</el-button>
        <el-button type="danger" :disabled="selectionList.length == 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <!--数据表格-->
    <el-table :data="dataTable" style="100%" stripe border @selection-change="selectionChangeHandle">
      <el-table-column type="selection" width="50" fixed="left"></el-table-column>
      <el-table-column prop="id" label="ID" width="50" fixed="left"></el-table-column>
      <el-table-column prop="username" label="用户名" fixed="left"></el-table-column>
      <el-table-column prop="mobile" label="手机"></el-table-column>
      <el-table-column prop="nickname" label="昵称"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="gender" label="性别"></el-table-column>
      <el-table-column label="操作" width="150" fixed="right">
        <template slot-scope="scope">
          <el-button type="warning" size="mini" @click="addOrEditHandle(scope.row.id)">编辑</el-button>
          <el-button type="danger" size="mini" @click="removeHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页-->
    <el-pagination :current-page="pageNumber" :page-size="pageSize" :total="total" :page-sizes="[10, 20, 50, 100]"
              @size-change="sizeChangeHandle" @current-change="currentChangeHandle"
              layout="sizes, prev, pager, next, ->, total, jumper"></el-pagination>
    <!--新增或编辑页面组件-->
    <user-add-edit ref="userAddEdit" v-if="addEditVisible" @refreshTable="listHandle"></user-add-edit>
  </div>
</template>

<script>
//导入新增或编辑组件
import UserAddEdit from './UserAddEdit2.vue';
// import axios from 'axios';
export default {
  data() {
    return {
      searchForm: {
        username: '',
        nickname: ''
      },
      dataTable: [],
      selectionList: [],
      pageNumber: 1,
      pageSize: 10,
      total: 105,
      addEditVisible: false
    }
  },
  methods: {
    //复选框变化事件
    selectionChangeHandle(selection) {
      this.selectionList = selection;
      console.log(this.selectionList);
    },
    //每页条数变化事件
    sizeChangeHandle(size) {
      this.pageSize = size;
      console.log('当前每页条数:', size);
    },
    //当前页码变化事件
    currentChangeHandle(page) {
      this.pageNumber = page;
      console.log('当前页码:', page);
    },
    listHandle() {
      // this.dataTable = [
      //   {id: 1, username: 'admin', mobile: '13012345678', nickname: '管理员', email: 'admin@qq.com', gender: '1'},
      //   {id: 2, username: 'zhangsan', mobile: '13112345678', nickname: '张三', email: 'zhangsan@qq.com', gender: '1'},
      //   {id: 3, username: 'lisi', mobile: '13212345678', nickname: '李四', email: 'lisi@qq.com', gender: '0'},
      //   {id: 4, username: 'tom', mobile: '13312345678', nickname: '汤姆', email: 'tom@qq.com', gender: '1'},
      //   {id: 5, username: 'jerry', mobile: '13412345678', nickname: '杰瑞', email: 'jerry@qq.com', gender: '0'}
      // ];
      /**
       * 其中then函数表示请求处理成功，成功回调函数，$.ajax()相当于success
       * 其中catch函数表示请求处理失败，失败回调函数，$.ajax()相当于error
       */
      // axios({
      //   url: 'http://localhost:3000/user',
      //   method: 'get'
      // }).then(res => {
      //   console.log(res);
      //   console.log(res.data);
      //   this.dataTable = res.data;
      // }).catch(error => {
      //   console.log(error);
      // });
      /**
       * axios工具
       * get请求与delete请求传参方式是一致的
       * 在配置项中带有一个params配置项，是传递参数的
       */
      this.$ajax.get('/user', {
        params: {
          username_like: this.searchForm.username
        }
      }).then(res => {
        console.log(res);
        this.dataTable = res;
      }).catch(error => console.log(error));
    },
    addOrEditHandle(id) {
      this.addEditVisible = true;
      //调用子组件中的方法
      this.$nextTick(() => {
        this.$refs.userAddEdit.initHandle(id);
      });
    },
    removeHandle(id) {
      this.$confirm('确定要删除数据吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax.delete('/user/' + id).then(() => {
          this.$message.success('删除成功');
          this.listHandle();
        }).catch(error => console.log(error));
      });
    }
  },
  mounted() {
    this.listHandle();
  },
  components: {
    UserAddEdit
  }
}
</script>

<style>

</style>