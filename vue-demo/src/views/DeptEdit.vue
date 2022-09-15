<template>
  <div>
    <el-dialog title="部门编辑" :visible.sync="visible" @close="closeHandle" :close-on-click-modal="false">
      <p>{{dataForm}}</p>
      <el-form ref="dataForm" :model="dataForm" :rules="rules" label-width="80px">
        <el-form-item label="部门名称" prop="dname">
          <el-input v-model="dataForm.dname"></el-input>
        </el-form-item>
        <el-form-item label="部门位置" prop="loc">
          <el-input v-model="dataForm.loc"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="visible = false">取 消</el-button>
        <el-button type="primary" @click="submitHandle">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "DeptEdit",
  data() {
    return {
      visible: false,
      dataForm: {
        deptno: null,
        dname: null,
        loc: null
      },
      rules: {
        dname: [
          {required: true, message: '部门名称不能为空', trigger: ['blur', 'change']}
        ],
        loc: [
          {required: true, message: '部门位置不能为空', trigger: ['blur', 'change']}
        ]
      }
    }
  },
  methods: {
    init(id){
      //部门编号传递过来之后，先做单条记录查询，为编辑回显数据
      console.log('id', id)
      this.dataForm.deptno = id;
      this.$ajax.get('/dept/get/'+id).then(res=>{
        if(res.code == 0){
          //表单数据赋值
          this.dataForm.deptno = res.data.deptno;
          this.dataForm.dname = res.data.dname;
          this.dataForm.loc = res.data.loc;
        }
      }).then(() => {
        //对话框显示
        this.visible = true;
      });
    },
    submitHandle() {
      this.$refs.dataForm.validate(valid => {
        if(valid){
          this.$ajax.put('/dept/edit', this.dataForm).then(res => {
            if(res.code == 0){
              this.$message.success('编辑成功');
              this.visible = false;
              this.$emit('refreshTable');
            }else{
              this.$message.error('编辑失败');
            }
          })
        }
      })
    },
    closeHandle() {
      console.log('表单数据清空...');
      //清空表单
      this.$refs.dataForm.resetFields();
    }
  }
}
</script>

<style scoped>

</style>