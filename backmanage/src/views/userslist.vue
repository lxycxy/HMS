<template>
  <div class="userlist">
    <div class="users">
      <el-table :data="filterTableData" style="width: 100%">
        <el-table-column label="姓名" prop="name" />
        <el-table-column label="年龄" prop="age" />
        <el-table-column label="性别" prop="sex" />
        <el-table-column label="职务" prop="role" />
        <el-table-column label="注册时间" prop="date" />
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
interface User {
  date: string
  age:number
  sex:string
  name: string
  role:string
  tel:string,
  address: string
  password:string
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
/**
 * 搜索
 */
const filterTableData = computed(() =>
    tableData.filter(
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
  )
      .then(() => {
        ElMessage({
          type: 'success',
          message: '删除成功',
        })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '确认取消',
        })
      })
}

const tableData: User[] = [
  {
    date: '2016-05-03',
    age:18,
    sex:'男',
    tel:'110',
    role:'医师',
    name: 'Tom',
    password:'111',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'John',
    age:18,
    tel:'110',
    sex:'男',
    role:'医师',
    password:'111',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Morgan',
    age:18,
    tel:'110',
    sex:'男',
    role:'医师',
    password:'111',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Jessy',
    age:18,
    sex:'男',
    tel:'110',
    role:'医师',
    password:'111',
    address: 'No. 189, Grove St, Los Angeles',
  },{
    date: '2016-05-01',
    name: 'Jessy',
    age:18,
    tel:'110',
    sex:'男',
    role:'医师',
    password:'111',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Jessy',
    age:18,
    tel:'110',
    sex:'男',
    role:'医师',
    password:'111',
    address: 'No. 189, Grove St, Los Angeles',
  },{
    date: '2016-05-01',
    name: 'Jessy',
    age:18,
    tel:'110',
    sex:'男',
    role:'医师',
    password:'111',
    address: 'No. 189, Grove St, Los Angeles',
  }
]
</script>

<style scoped>

</style>
