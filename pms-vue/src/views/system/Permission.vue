<template>
  <div>
    <!--条件查询表单-->
    <el-form ref="searchForm" :model="searchForm" :inline="true">
      <el-form-item>
        <el-input v-model="searchForm.name" placeholder="权限名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchForm.type" placeholder="权限类型" clearable>
          <el-option label="目录" value="D"></el-option>
          <el-option label="菜单" value="M"></el-option>
          <el-option label="按钮" value="B"></el-option>
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
      <el-table-column prop="name" label="权限名称" header-align="center" fixed="left" width="120"></el-table-column>
      <el-table-column prop="parentName" label="上级菜单" header-align="center" width="120"></el-table-column>
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
    <!--分页-->
    <el-pagination :current-page="pageNumber" :page-size="pageSize" :total="total" :page-sizes="[10, 20, 50, 100]"
              @size-change="sizeChangeHandle" @current-change="currentChangeHandle"
              layout="sizes, prev, pager, next, ->, total, jumper"></el-pagination>
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
      searchForm: {
        name: '',
        type: ''
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
      this.$ajax.get('/permission/page', {
        params: {
          pageNumber: this.pageNumber,
          pageSize: this.pageSize,
          name: this.searchForm.name,
          type: this.searchForm.type
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
        this.$ajax.delete('/permission/remove/' + id).then(() => {
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
        this.$ajax.delete('/permission/remove', {
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
    PermissionAddEdit
  }
}
</script>

<style>

</style>