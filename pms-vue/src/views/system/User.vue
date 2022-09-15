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
      <el-form-item prop="userType">
        <el-select v-model="searchForm.userType" placeholder="用户类型" clearable>
          <el-option label="用户" value="1"></el-option>
          <el-option label="学生" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="listHandle()">查询</el-button>
        <el-button type="primary" @click="addOrEditHandle()">新增</el-button>
        <el-button type="danger" :disabled="selectionList.length == 0" @click="removeBatchHandle">批量删除</el-button>
      </el-form-item>
    </el-form>
    <!--数据表格-->
    <el-table :data="dataTable" style="100%" stripe border @selection-change="selectionChangeHandle">
      <el-table-column type="selection" width="50" fixed="left"></el-table-column>
      <el-table-column prop="id" label="ID" header-align="center" width="50" fixed="left"></el-table-column>
      <el-table-column prop="username" label="用户名" header-align="center" fixed="left"></el-table-column>
      <el-table-column prop="mobile" label="手机" header-align="center" width="120"></el-table-column>
      <el-table-column prop="nickname" label="昵称" header-align="center"></el-table-column>
      <el-table-column prop="gender" label="性别" header-align="center" :formatter="formatterHandle"></el-table-column>
      <el-table-column prop="userType" label="类型" header-align="center">
        <template slot-scope="scope">
          {{scope.row.userType == '1' ? '用户' : '学生'}}
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" header-align="center">
        <template slot-scope="scope">
          {{scope.row.status == '1' ? '启用' : '禁用'}}
        </template>
      </el-table-column>
      <el-table-column label="操作" header-align="center" align="center" width="150" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" type="warning" @click="addOrEditHandle(scope.row.id)">编辑</el-button>
          <el-button size="mini" type="danger" @click="removeHandle(scope.row.id)">删除</el-button>
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
import UserAddEdit from './UserAddEdit.vue';
export default {
  data() {
    return {
      searchForm: {
        username: '',
        nickname: '',
        userType: ''
      },
      dataTable: [],
      selectionList: [],
      pageNumber: 1,
      pageSize: 10,
      total: 0,
      addEditVisible: false
    }
  },
  methods: {
    //复选框变化事件
    selectionChangeHandle(selection) {
      this.selectionList = selection;
    },
    //每页条数变化事件
    sizeChangeHandle(size) {
      this.pageSize = size;
      this.listHandle();
    },
    //当前页码变化事件
    currentChangeHandle(page) {
      this.pageNumber = page;
      this.listHandle();
    },
    //格式化性别数据
    formatterHandle(row, column, cellValue) {
      // console.log(row, column, cellValue);
      if(cellValue == '0'){
        return '男性';
      }else if(cellValue == '1'){
        return '女性';
      }else{
        return '保密';
      }
    },
    listHandle() {
      this.$ajax.get('/user/page', {
        //get请求与delete向后台传递的参数（名值对形式）
        params: {
          pageNumber: this.pageNumber,
          pageSize: this.pageSize,
          username: this.searchForm.username,
          nickname: this.searchForm.nickname,
          userType: this.searchForm.userType
        }
      }).then(res => {
        if(res.code == 0){
          this.dataTable = res.data.list;
          this.total = res.data.total;
        }
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
        this.$ajax.delete('/user/remove/' + id).then(res => {
          if(res.code == 0){
            this.$message.success('删除成功');
            this.listHandle();
          }
        }).catch(error => console.log(error));
      });
    },
    removeBatchHandle() {
      //获取当前用户选中的数据
      let idList = this.selectionList.map(item => item.id);
      this.$confirm('确定要删除数据吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax.delete('/user/remove', {
          params: {
            idList: idList.join()
          }
        }).then(res => {
          if(res.code == 0){
            this.$message.success('删除成功');
            this.listHandle();
          }
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