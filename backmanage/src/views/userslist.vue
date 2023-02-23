<template>
  <div class="userlist">
    <div class="users">
      <el-table :data="filterTableData" style="width: 100%">
        <el-table-column label="ID" prop="id" />
        <el-table-column label="姓名" prop="name" />
        <el-table-column label="年龄" prop="age" />
        <el-table-column label="性别" prop="sex" />
        <el-table-column label="职务" prop="role" />
        <el-table-column label="注册时间" prop="time" />
        <el-table-column align="right">
          <template #header>
            <el-input v-model="search" size="small" placeholder="输入用户名查找" />
          </template>
          <template #default="scope">
            <el-button size="small" @click="handleModify(scope.$index, scope.row)"
            >详情</el-button
            >
            <el-button
                size="small"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>


<!--    修改框-->
    <el-dialog
        v-model="dialogVisible"
        title="修改信息"
        width="30%"
        :close-on-click-modal="false"
        :before-close="handleClose"
    >
      <el-form :model="form">
        <el-form-item label="姓名" label-width="80">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="密码" label-width="80">
          <el-input v-model="form.password" />
        </el-form-item>
        <el-form-item label="年龄" label-width="80">
          <el-input v-model="form.age" />
        </el-form-item>
        <el-form-item label="性别" label-width="80">
          <el-select v-model="form.sex">
            <el-option value="男" label="男"/>
            <el-option value="女" label="女"/>
          </el-select>
        </el-form-item>
        <el-form-item label="职务" label-width="80">
          <el-input v-model="form.role" />
        </el-form-item>
        <el-form-item label="电话号码" label-width="80">
          <el-input v-model="form.tel" />
        </el-form-item>
        <el-form-item label="家庭住址" label-width="80">
          <el-input v-model="form.address" />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogVisible = false">
          确认
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>


</template>

<script setup lang="ts" name="userlist">
import {computed, reactive, ref} from 'vue'
import {ElMessageBox} from "element-plus";
import {ElMessage} from "element-plus";
import axios from "axios";
interface User {
  id:number
  date: string
  age:number
  sex:string
  name: string
  role:string
  tel:string,
  address: string
  password:string
  time:Date
}

let form = reactive({
  name:'',
  age:'',
  sex:'',
  role:'',
  address:'',
  tel:'',
  password:'',
})
let dialogVisible = ref(false);
const search = ref('')

const freshTable = () => {
  axios({
    url:"http://localhost:3000/api/getusers/",
    method:"GET",
  }).then((resp) => {
    tableData.value = resp.data;
    console.log(resp.data);
  })
}

freshTable();

/**
 * 搜索
 */
const filterTableData = computed(() =>
    tableData.value.filter(
        (data) =>
            !search.value ||
            data.name.toLowerCase().includes(search.value.toLowerCase())
    )
)

const handleModify = (index: number, row: User) => {
  form.age = row.age.toString();
  form.sex = row.sex;
  form.name = row.name;
  form.role = row.role;
  form.address = row.address;
  form.tel = row.tel;
  form.password = row.password;


  dialogVisible.value = true;
}
const handleDelete = (index: number, row: User) => {
  ElMessageBox.confirm(
      '请确认删除',
      '警告',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      }
  ).then(() => {

        console.log(row.id)
        axios({
          url:"http://localhost:3000/api/deleteuser/",
          method:"POST",
          params:{
            id: row.id,
          }
        }).then((resp) => {
          console.log(resp.data)
          if(resp.data.info === "error") {
            ElMessage({
              type: 'error',
              message: '删除失败',
            })
          } else {
            freshTable();
            ElMessage({
              type: 'success',
              message: '删除成功',
            })
          }
        })

      }).catch(() => {
        ElMessage({
          type: 'info',
          message: '确认取消',
        })
      })
}

const tableData = ref<User[]> ([]);

</script>

<style scoped>

</style>
