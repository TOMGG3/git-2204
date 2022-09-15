<template>
  <div>
    <el-dialog :title="dataForm.id ? '编辑' : '新增'" :visible.sync="visible" :close-on-click-modal="false" @closed="closeDialogHandle">
      <el-form :model="dataForm" :rules="rules" ref="dataForm" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="dataForm.name" placeholder="名称"></el-input>
        </el-form-item>
        <el-form-item label="上级权限" prop="parentId">
          <el-cascader v-model="dataForm.parentId" :options="permissionTree" :props="treeProps" placeholder="上级权限"></el-cascader>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-radio-group v-model="dataForm.type">
            <el-radio label="D">目录</el-radio>
            <el-radio label="M">菜单</el-radio>
            <el-radio label="B">按钮</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="路径" prop="url">
          <el-input v-model="dataForm.url" placeholder="路径"></el-input>
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <el-input v-model="dataForm.icon" placeholder="图标"></el-input>
        </el-form-item>
        <el-form-item label="权限标识" prop="percode">
          <el-input v-model="dataForm.percode" placeholder="权限标识"></el-input>
        </el-form-item>
        <el-form-item label="显示顺序" prop="sort">
          <el-input-number v-model="dataForm.sort" placeholder="显示顺序" :min="0"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormHandle">确 定</el-button>
        <el-button @click="visible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {

    return {
      visible: false,
      dataForm: {
        id: 0,
        name: '',
        type: '',
        url: '',
        icon: '',
        percode: '',
        sort: 0,
        parentId: 0
      },
      rules: {
        name: [
          { required: true, message: '权限名称不能为空', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择权限类型', trigger: 'change' }
        ]
      },
      permissionTree: [],
      treeProps: {
        //hover触发子菜单
        expandTrigger: 'hover',
        //父子节点不互相关联
        checkStrictly: true,
        //只返回该节点的值
        emitPath: false,
        value: 'id',
        label: 'name'
      }
    }
  },
  methods: {
    initHandle(id){
      this.dataForm.id = id || 0;
      this.$ajax.get('/permission/tree').then(res => {
        if(res.code == 0){
          this.permissionTree = res.data;
        }
      }).then(() => {
        //打开窗体
        this.visible = true;
        //编辑时单查
        if(this.dataForm.id){
          this.$ajax.get('/permission/get/' + this.dataForm.id).then(res => {
            if(res.code == 0){
              this.dataForm.name = res.data.name;
              this.dataForm.type = res.data.type;
              this.dataForm.url = res.data.url;
              this.dataForm.icon = res.data.icon;
              this.dataForm.percode = res.data.percode;
              this.dataForm.sort = res.data.sort;
              this.dataForm.parentId = res.data.parentId;
            }
          }).catch(error => console.log(error));
        }
      }).catch(error => console.log(error));
    },
    //表单提交
    submitFormHandle() {
      this.$refs.dataForm.validate(valid => {
        if(valid){
          if(this.dataForm.id){
            this.$ajax.put('/permission/edit', this.dataForm).then(() => {
              this.$message.success('编辑成功');
              //关闭窗体
              this.visible = false;
              //调用父组件中的函数
              this.$emit('refreshTable');
            }).catch(error => console.log(error));
          }else{
            this.$ajax.post('/permission/save', this.dataForm).then(() => {
              this.$message.success('新增成功');
              //关闭窗体
              this.visible = false;
              //调用父组件中的函数
              this.$emit('refreshTable');
            }).catch(error => console.log(error));
          }
        }
      });
    },
    closeDialogHandle() {
      //清空表单
      this.$refs.dataForm.resetFields();
    }
  }
}
</script>

<style scoped>
.el-cascader, .el-input-number {
  display: block;
}
</style>