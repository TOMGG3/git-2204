<template>
  <div>
    <el-dialog :title="dataForm.id ? '编辑' : '新增'" :visible.sync="visible" :close-on-click-modal="false" @closed="closeDialogHandle">
      <el-form ref="dataForm" :model="dataForm" label-width="80px" :rules="rules">
        <el-form-item label="用户名" prop="username" :rules="dataForm.id ? [] : usernameRule">
          <el-input v-model="dataForm.username" placeholder="用户名" :readonly="dataForm.id != 0"></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="mobile">
          <el-input v-model="dataForm.mobile" placeholder="手机"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="dataForm.nickname" placeholder="昵称"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="dataForm.gender" placeholder="性别">
            <el-option label="男性" value="0"></el-option>
            <el-option label="女性" value="1"></el-option>
            <el-option label="保密" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item label="用户类型" prop="userType">
          <el-select v-model="dataForm.userType" placeholder="用户类型">
            <el-option label="用户" value="1"></el-option>
            <el-option label="学生" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-switch v-model="dataForm.status" active-text="启用" inactive-text="禁用"
                    active-value="1" inactive-value="2"></el-switch>
        </el-form-item>
        <el-form-item label="角色" prop="roleIdList">
          <el-select v-model="dataForm.roleIdList" multiple placeholder="点击选中角色">
            <el-option v-for="role in roleList" :key="role.id" :label="role.rolename" :value="role.id"></el-option>
          </el-select>
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
    var checkUsername = (rule, value, callback) => {
      this.$ajax.get('/user/getByUsername/' + value).then(res => {
        if(res.code == 0){
          if(res.data){
            callback(new Error('用户名已存在'));
          }else{
            callback();
          }
        }
      }).catch(error => console.log(error));
    }

    return {
      visible: false,
      dataForm: {
        id: 0,
        username: '',
        mobile: '',
        nickname: '',
        gender: '',
        email: '',
        userType: '',
        status: '1',
        roleIdList: []
      },
      rules: {
        mobile: [
          {required: true, message: '手机号不能为空', trigger: 'blur'}
        ],
        nickname: [
          {required: true, message: '昵称不能为空', trigger: 'blur'}
        ],
        userType: [
          {required: true, message: '请选择用户类型', trigger: 'change'}
        ]
      },
      usernameRule: [
        {required: true, message: '用户名不能为空', trigger: 'blur'},
        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'},
        {validator: checkUsername, trigger: 'blur'}
      ],
      roleList: []
    }
  },
  methods: {
    initHandle(id){
      this.dataForm.id = id || 0;
      //保证异步请求的顺序，在ajax中嵌套ajax
      //查询关联角色列表数据
      this.$ajax.get('/role/list').then(res => {
        if(res.code == 0){
          this.roleList = res.data;
        }
      }).then(() => {
        //打开窗体
        this.visible = true;
        //编辑时单查
        if(this.dataForm.id){
          this.$ajax.get('/user/get/' + this.dataForm.id).then(res => {
            if(res.code == 0){
              this.dataForm.username = res.data.username;
              this.dataForm.mobile = res.data.mobile;
              this.dataForm.nickname = res.data.nickname;
              this.dataForm.gender = res.data.gender;
              this.dataForm.email = res.data.email;
              this.dataForm.userType = res.data.userType;
              this.dataForm.status = res.data.status;
              this.dataForm.roleIdList = res.data.roleIdList;
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
            this.$ajax.put('/user/edit', this.dataForm).then(() => {
              this.$message.success('编辑成功');
              //关闭窗体
              this.visible = false;
              //调用父组件中的函数
              this.$emit('refreshTable');
            }).catch(error => console.log(error));
          }else{
            this.$ajax.post('/user/save', this.dataForm).then(() => {
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