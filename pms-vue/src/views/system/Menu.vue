<template>
  <div>
    <!--条件查询表单-->
    <el-form ref="searchForm" :inline="true">
      <el-form-item>
        <el-button type="primary" @click="addOrEditHandle()">新增</el-button>
      </el-form-item>
    </el-form>
    <!--树形数据表格：1.数据为树形结构数据 2.row-key="id"配置-->
    <el-table :data="dataTable" style="100%" stripe border row-key="id">
      <el-table-column prop="name" label="权限名称" header-align="center" fixed="left" width="200"></el-table-column>
      <!-- <el-table-column prop="parentName" label="上级菜单" header-align="center" width="120"></el-table-column> -->
      <el-table-column prop="type" label="类型" header-align="center" width="120">
        <template slot-scope="scope">
          <el-tag size="small" :type="scope.row.type === 'D' ? 'default' : scope.row.type === 'M' ? 'success' : 'info'">
            {{scope.row.type === 'D' ? '目录' : scope.row.type === 'M' ? '菜单' : '按钮'}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="url" label="路径" header-align="center" width="200"></el-table-column>
      <el-table-column prop="icon" label="图标" header-align="center">
        <template slot-scope="scope">
          <i :class="scope.row.icon || ''"></i>
        </template>
      </el-table-column>
      <el-table-column prop="percode" label="权限标识" header-align="center"></el-table-column>
      <el-table-column prop="sort" label="显示顺序" header-align="center"></el-table-column>
      <el-table-column label="操作" header-align="center" align="center" width="150" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" type="warning" @click="addOrEditHandle(scope.row.id)">编辑</el-button>
          <el-button size="mini" type="danger" @click="removeHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--新增或编辑页面组件-->
    <permission-add-edit ref="userAddEdit" v-if="addEditVisible" @refreshTable="listHandle"></permission-add-edit>
  </div>
</template>

<script>
//导入新增或编辑组件
import PermissionAddEdit from './PermissionAddEdit.vue';
export default {
  data() {
    return {
      dataTable: [],
      addEditVisible: false
    }
  },
  methods: {
    listHandle() {
      this.$ajax.get('/permission/tree').then(res => {
        if(res.code == 0){
          this.dataTable = res.data;
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
        this.$ajax.delete('/permission/remove/' + id).then(() => {
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
    PermissionAddEdit
  }
}
</script>

<style>

</style>