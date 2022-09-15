<template>
  <div>
    <h1>部门列表</h1>
    <p>
      <el-button type="danger" @click="removeBatchHandle" :disabled="selectionList.length == 0">批量删除</el-button>
    </p>
    <el-table :data="deptList" border stripe @selection-change="selectionChangeHandle">
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="deptno" label="部门编号"></el-table-column>
      <el-table-column prop="dname" label="部门名称"></el-table-column>
      <el-table-column prop="loc" label="部门位置"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="warning" size="mini" @click="openEditHandle(scope.row.deptno)">编辑</el-button>
          <el-button type="danger" size="mini" @click="removeHandle(scope.row.deptno)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle"
        :current-page="pageNumber" :page-sizes="[5, 10, 20, 50]" :total="total"
        :page-size="pageSize" layout="sizes, prev, pager, next, ->, total, jumper">
    </el-pagination>
    <dept-edit ref="deptEdit" @refreshTable="listHandle"></dept-edit>
  </div>
</template>

<script>
//导入编辑组件
import deptEdit from '@/views/DeptEdit.vue'
export default {
  name: "DeptList",
  data(){
    return {
      //表格数据
      deptList: [],
      //分页数据
      pageNumber: 1,
      pageSize: 5,
      total: 0,
      //选中数据
      selectionList: []
    }
  },
  methods: {
    //表格选中项发生改变后的事件
    selectionChangeHandle(selection){
      // console.log('用户选中数据', selection)
      this.selectionList = selection;
    },
    //每页条数变化时事件
    sizeChangeHandle(size){
      this.pageSize = size;
      this.listHandle();
    },
    //当前页码变化时事件
    currentChangeHandle(page){
      this.pageNumber = page;
      this.listHandle();
    },
    listHandle() {
      this.$ajax.get('/dept/page', {
        params: {
          pageNumber: this.pageNumber,
          pageSize: this.pageSize
        }
      }).then(res => {
        if(res.code == 0){
          this.deptList = res.data.list;
          this.total = res.data.total;
        }
      }).catch(res => console.log(res));
    },
    openEditHandle(id){
      // console.log('行数据', id)
      //父组件通过子组件的名称（唯一标识）访问子组件中的数据或函数
      this.$refs.deptEdit.init(id);
    },
    removeHandle(id){
      this.$confirm('确定要删除吗?', '提示').then(() => {
        this.$ajax.delete('dept/remove/'+id).then(res => {
          if(res.code == 0){
            this.$message.success('删除成功');
            this.listHandle();
          }else{
            this.$message.error(res.message);
          }
        }).catch(res => console.log(res));
      });
    },
    removeBatchHandle(){
      this.$confirm('确定要删除吗?', '提示').then(() => {
        // var ids = [];
        // for(var i=0;i<this.selectionList.length;i++){
        //   ids.push(this.selectionList[i].deptno);
        // }
        // console.log('id数组', ids);
        //获取用户在表格中选中的数据
        var ids = this.selectionList.map(item => item.deptno);
        this.$ajax.delete('dept/remove', {
          params: {
            ids: ids.join()
          }
        }).then(res => {
          if(res.code == 0){
            this.$message.success('删除成功');
            this.listHandle();
          }else{
            this.$message.error(res.message);
          }
        }).catch(res => console.log(res));
      });
    }
  },
  mounted() {
    this.listHandle();
  },
  components: {
    deptEdit
  }
}
</script>

<style scoped>

</style>