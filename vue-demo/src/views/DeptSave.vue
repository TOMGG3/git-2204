<template>
  <div>
    <h1>部门新增</h1>
    <form>
      <p>{{dataForm}}</p>
      <p>
        <label>部门名称：</label>
        <input type="text" v-model="dataForm.dname">
      </p>
      <p>
        <label>部门位置：</label>
        <input type="text" v-model="dataForm.loc">
      </p>
      <p>
        <button type="button" @click="submitHandle">提交</button>
      </p>
    </form>
  </div>
</template>

<script>
export default {
  name: "DeptSave",
  data(){
    return {
      dataForm: {
        dname: '',
        loc: ''
      }
    }
  },
  methods: {
    submitHandle() {
      this.$ajax.post('/dept/save', this.dataForm).then(res => {
        if(res.code == 0){
          alert('新增成功');
          this.dataForm.dname='';
          this.dataForm.loc='';
          this.$router.push('/dept');
          // this.$router.push({name: 'Dept'});
        }
      }).catch(error => console.log(error));
    }
  }
}
</script>

<style scoped>

</style>