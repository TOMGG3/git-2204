<template>
  <div>
    <el-dialog :title="dataForm.id ? '编辑' : '新增'" :visible.sync="visible" :close-on-click-modal="false" @closed="closeDialogHandle">
      <el-form ref="dataForm" :model="dataForm" label-width="80px" :rules="rules">
        <el-form-item label="角色名称" prop="rolename">
          <el-input v-model="dataForm.rolename" placeholder="角色名称"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="permissionIdList">
          <!-- <el-select v-model="dataForm.roleIdList" multiple placeholder="点击选中权限">
            <el-option v-for="permission in permissionList" :key="permission.id" :label="permission.name" :value="permission.id"></el-option>
          </el-select> -->
          <!--check-strictly破坏父子节点的关联性-->
          <el-tree ref="permissionTree" :data="permissionTree" :props="treeProps" node-key="id" show-checkbox check-strictly
              :default-expanded-keys="dataForm.permissionIdList" :default-checked-keys="dataForm.permissionIdList"></el-tree>
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
//导入工具
import {dataToTree} from '@/utils';
export default {
  data() {

    return {
      visible: false,
      dataForm: {
        id: 0,
        rolename: '',
        permissionIdList: []
      },
      rules: {
        rolename: [
          {required: true, message: '角色名称不能为空', trigger: 'blur'}
        ]
      },
      permissionTree: [],
      treeProps: {
        label: 'name'
      }
    }
  },
  methods: {
    initHandle(id){
      this.dataForm.id = id || 0;
      this.$ajax.get('/permission/list').then(res => {
        if(res.code == 0){
          this.permissionTree = dataToTree(res.data);
          console.log(this.permissionTree)
        }
      }).then(() => {
        //打开窗体
        this.visible = true;
        //编辑时单查
        if(this.dataForm.id){
          this.$ajax.get('/role/get/' + this.dataForm.id).then(res => {
            if(res.code == 0){
              this.dataForm.rolename = res.data.rolename;
              this.dataForm.permissionIdList = res.data.permissionIdList;
            }
          }).catch(error => console.log(error));
        }
      }).catch(error => console.log(error));
    },
    //表单提交
    submitFormHandle() {
      this.$refs.dataForm.validate(valid => {
        if(valid){
          this.dataForm.permissionIdList = this.$refs.permissionTree.getCheckedKeys();
          if(this.dataForm.id){
            this.$ajax.put('/role/edit', this.dataForm).then(() => {
              this.$message.success('编辑成功');
              //关闭窗体
              this.visible = false;
              //调用父组件中的函数
              this.$emit('refreshTable');
            }).catch(error => console.log(error));
          }else{
            this.$ajax.post('/role/save', this.dataForm).then(() => {
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
.el-select {
  display: block;
}
</style>