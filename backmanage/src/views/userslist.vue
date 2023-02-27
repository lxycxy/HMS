<template>
  <div class="userlist">
    <div class="users">
      <el-card class="box-card" :body-style="{ padding: '0px' }">
        <template #header>
          <el-row :gutter="40">
            <el-col :span="4">
              <el-input v-model="idSearch" type="number" placeholder="输入ID进行查找" />
            </el-col>
            <el-col :span="4">
              <el-input v-model="nameSearch" placeholder="输入名称进行查找" />
            </el-col>
            <el-col :span="5">
              <el-select v-model="shif" placeholder="筛选职务" clearable>
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                />
              </el-select>
            </el-col>

            <el-col :span="3" :offset="8">
              <el-button type="primary" :icon="Plus" @click="handleAdd">添加用户</el-button>
            </el-col>

          </el-row>

        </template>
        <el-table :data="filterTableData" style="width: 100%">
          <el-table-column label="ID" prop="id" width="60"/>
          <el-table-column label="姓名" prop="name" width="80"/>
          <el-table-column label="密码" prop="password" />
          <el-table-column label="年龄" sortable prop="age" width="80"/>
          <el-table-column label="性别" prop="sex" width="60"/>
          <el-table-column label="职务" prop="role" width="80"/>
          <el-table-column label="电话" prop="tel" width="120"/>
          <el-table-column label="地址" prop="address" />
          <el-table-column label="注册时间" sortable prop="time" width="110" />
          <el-table-column align="center" label="操作">
            <template #default="scope">
              <el-button size="small" @click="handleModify(scope.$index, scope.row)"
              >修改</el-button
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
      </el-card>

    </div>


    <!--    修改框-->
    <el-dialog
        v-model="dialogVisible"
        title="修改信息"
        width="30%"
        :close-on-click-modal="false"
        :show-close="false"
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
          <el-select v-model="form.role" placeholder="筛选职务">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="电话号码" label-width="80">
          <el-input v-model="form.tel" type="number" maxlength="11"/>
        </el-form-item>
        <el-form-item label="家庭住址" label-width="80">
          <el-input v-model="form.address" />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="closeUpdate">取消</el-button>
        <el-button type="primary" @click="updateUser">
          确认
        </el-button>
      </span>
      </template>
    </el-dialog>


    <!--    添加框-->
    <el-dialog
        v-model="addVisble"
        title="添加用户"
        width="30%"
        :close-on-click-modal="false"
        :show-close="false"
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
          <el-select v-model="form.role" placeholder="筛选职务">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="电话号码" label-width="80">
          <el-input v-model="form.tel" type="number" maxlength="11"/>
        </el-form-item>
        <el-form-item label="家庭住址" label-width="80">
          <el-input v-model="form.address" />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="closeAdd">取消</el-button>
        <el-button type="primary" @click="addUser">
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
import {Plus} from '@element-plus/icons-vue'
interface User {
  id:number
  age:number
  sex:string
  name: string
  role:string
  tel:string,
  address: string
  password:string
  time:Date
}

const options = [
  {
    value:"医生",
    lable:"医生",
  },
  {
    value:"药师",
    lable:"药师",
  },
  {
    value:"管理员",
    lable:"管理员",
  },
  {
    value:"患者",
    lable:"患者",
  },
]

let form = reactive({
  id:'',
  name:'',
  age:'',
  sex:'',
  role:'',
  address:'',
  tel:'',
  password:'',
})
const tableData = ref<User[]> ([]);
let shif = ref('');
let dialogVisible = ref(false);
let addVisble = ref(false)
const idSearch = ref('')
const nameSearch = ref('')

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
            ((!nameSearch.value ||
            data.name.toLowerCase().includes(nameSearch.value.toLowerCase()))
            &&  (!idSearch.value || data.id == parseInt(idSearch.value))
            && (!shif.value || data.role.includes(shif.value)))
    )
)
const handleAdd = () => {
  addVisble.value = true;
}
const addUser = () => {

  axios({
    url:"http://localhost:3000/api/adduser/",
    method:"POST",
    params:{
      name:form.name,
      password:form.password,
      role:form.role,
      tel:form.tel,
      age:form.age,
      sex:form.sex,
      address:form.address,
    }
  }).then((resp) => {
    if(resp.data.info === "error") {
      ElMessage({
        type: 'error',
        message: '添加失败',
      })
    } else {
      freshTable();
      addVisble.value = false;
      clearForm();
      ElMessage({
        type: 'success',
        message: '添加成功',
      })
    }
  })
}
const closeUpdate = () => {
  dialogVisible.value = false;
  clearForm();
}

const closeAdd = () => {
  addVisble.value = false;
  clearForm();
}
const updateUser = () => {
  axios({
    url:"http://localhost:3000/api/updateuser/",
    method:"POST",
    params:{
      id:form.id,
      name:form.name,
      password:form.password,
      role:form.role,
      tel:form.tel,
      age:form.age,
      sex:form.sex,
      address:form.address,
    }
  }).then((resp) => {
    if(resp.data.info === "error") {
      ElMessage({
        type: 'error',
        message: '更新失败',
      })
    } else {
      freshTable();
      dialogVisible.value = false;
      clearForm();
      ElMessage({
        type: 'success',
        message: '更新成功',
      })
    }
  })

}
const handleModify = (index: number, row: User) => {
  form.id = row.id.toString();
  form.age = row.age.toString();
  form.sex = row.sex;
  form.name = row.name;
  form.role = row.role;
  form.address = row.address;
  form.tel = row.tel;
  form.password = row.password;


  dialogVisible.value = true;
}

const clearForm = () => {
      form.id = '';
      form.name = '';
      form.age = '';
      form.sex = '';
      form.role = '';
      form.address = '';
      form.tel = '';
      form.password ='';
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
        axios({
          url:"http://localhost:3000/api/deleteuser/",
          method:"POST",
          params:{
            id: row.id,
          }
        }).then((resp) => {
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



</script>

<style scoped>

</style>
