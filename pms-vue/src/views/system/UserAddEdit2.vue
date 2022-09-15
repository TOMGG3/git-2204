<template>
  <div>
    <!--对话框的隐藏与显示是依靠visible属性来决定-->
    <!--在js中if判断条件中如果数据值为0,null,空字符串,undefined,NaN返回数据为false-->
    <el-dialog :title="dataForm.id ? '编辑' : '新增'" :visible.sync="visible" :close-on-click-modal="false" @closed="closeDialogHandle">
      <el-form ref="dataForm" :model="dataForm" label-width="80px" :rules="rules">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="mobile">
          <el-input v-model="dataForm.mobile" placeholder="手机号"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="dataForm.nickname" placeholder="昵称"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="dataForm.gender" placeholder="性别">
            <el-option label="男性" value="1"></el-option>
            <el-option label="女性" value="2"></el-option>
            <el-option label="保密" value="3"></el-option>
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
    // var checkNickname = function(rule, value, callback){
    //   console.log(rule);
    //   console.log(value);
    //   console.log(callback);
    //   if(value){
    //     if(value == 'test'){
    //       //校验成功
    //       callback();
    //     }else{
    //       callback(new Error('你为什么不为test'));
    //     }
    //   }else{
    //     //校验失败
    //     callback(new Error('错误提示信息'));
    //   }
    // }
    var checkUsername = (rule, value, callback) => {
      setTimeout(() => {
        if(value == 'zhangsan' || value == 'admin'){
          callback(new Error('用户名已存在'));
        }
        callback();
      }, 1500);
    }

    return {
      visible: false,
      dataForm: {
        id: 0,
        username: '',
        mobile: '',
        nickname: '',
        email: '',
        gender: ''
      },
      rules: {
        username: [
          {required: true, message: '用户名不能为空', trigger: 'blur'},
          {min: 4, max: 10, message: '长度在 4 到 10 个字符', trigger: 'blur'},
          {validator: checkUsername, trigger: 'blur'}
        ],
        mobile: [
          {required: true, message: '手机号不能为空', trigger: 'blur'}
        ],
        nickname: [
          {required: true, message: '昵称不能为空', trigger: 'blur'}
          // {validator: checkNickname, trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    initHandle(id){
      this.dataForm.id = id || 0;
      this.visible = true;
      
      this.$nextTick(() => {
        //清空表单
        this.closeDialogHandle();
        if(this.dataForm.id){
          this.$ajax.get('/user/' + this.dataForm.id).then(res => {
            console.log(res);
            this.dataForm.username = res.username;
            this.dataForm.mobile = res.mobile;
            this.dataForm.nickname = res.nickname;
            this.dataForm.email = res.email;
            this.dataForm.gender = res.gender;
          }).catch(error => console.log(error));
        }
      });
    },
    //表单提交
    submitFormHandle() {
      this.$refs.dataForm.validate(valid => {
        if(valid){
          if(this.dataForm.id){
            this.$ajax.put('/user/' + this.dataForm.id, this.dataForm).then(() => {
              this.$message.success('编辑成功');
              //关闭窗体
              this.visible = false;
              //调用父组件中的函数
              this.$emit('refreshTable');
            }).catch(error => console.log(error));
          }else{
            this.$ajax.post('/user', this.dataForm).then(res => {
              this.$message.success('新增成功' + res);
              //关闭窗体
              this.visible = false;
              //调用父组件中的函数
              this.$emit('refreshTable');
            }).catch(error => console.log(error));
          }
          
          //关闭窗体
          // this.visible = false;
          // var obj = {
          //   id: this.dataForm.id,
          //   username: this.dataForm.username,
          //   mobile: this.dataForm.mobile,
          //   nickname: this.dataForm.nickname,
          //   email: this.dataForm.email,
          //   gender: this.dataForm.gender
          // };
          // console.log('表单清空之前：', JSON.stringify(obj));
          //清空表单
          // this.closeDialogHandle();
          // console.log('表单清空之后：', JSON.stringify(this.dataForm));
        }
      });
    },
    closeDialogHandle() {
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