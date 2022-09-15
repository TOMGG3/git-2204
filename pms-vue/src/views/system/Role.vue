<template>
  <div>
    <!--条件查询表单-->
    <el-form ref="searchForm" :model="searchForm" :inline="true">
      <el-form-item>
        <el-input v-model="searchForm.rolename" placeholder="角色名称" clearable></el-input>
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
      <el-table-column prop="rolename" label="角色名称" header-align="center" fixed="left"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" header-align="center" width="180"></el-table-column>
      <el-table-column prop="updateTime" label="更新时间" header-align="center" width="180"></el-table-column>
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
    <role-add-edit ref="roleAddEdit" v-if="addEditVisible" @refreshTable="listHandle"></role-add-edit>
  </div>
</template>

<script>
//导入新增或编辑组件
import RoleAddEdit from './RoleAddEdit.vue';
export default {
  data() {
    return {
      searchForm: {
        rolename: ''
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
    listHandle() {
      this.$ajax.get('/role/page', {
        params: {
          pageNumber: this.pageNumber,
          pageSize: this.pageSize,
          rolename: this.searchForm.rolename
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
        this.$refs.roleAddEdit.initHandle(id);
      });
    },
    removeHandle(id) {
      this.$confirm('确定要删除数据吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax.delete('/role/remove/' + id).then(() => {
          this.$message.success('删除成功');
          this.listHandle();
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
        this.$ajax.delete('/role/remove', {
          params: {
            idList: idList.join()
          }
        }).then(() => {
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
    RoleAddEdit
  }
}
</script>

<style>

</style>