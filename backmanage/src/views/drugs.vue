<template>
  <div class="drugs">
    <el-card class="box-card" :body-style="{ padding: '0px' }">

      <template #header>
        <el-row :gutter="40">
          <el-col :span="4">
            <el-input v-model="idSearch" placeholder="输入药品ID进行查找" />
          </el-col>
          <el-col :span="4">
            <el-input v-model="nameSearch" placeholder="输入药品名称进行查找" />
          </el-col>

          <el-col :span="3" :offset="13">
            <el-button type="primary" :icon="Plus" @click="handleAdd">添加药品</el-button>
          </el-col>
        </el-row>
      </template>

      <el-table :data="filterTableData" height="633" style="width: 100%">
        <el-table-column fixed label="ID" prop="id" width="100" />
        <el-table-column fixed label="药品名称" prop="name" width="100" />
        <el-table-column label="进价" prop="purchasePrice" width="100" />
        <el-table-column label="售价" prop="price" width="100" />
        <el-table-column label="库存数量" prop="num" width="100" />
        <el-table-column label="引入日期" prop="introduceDate" width="100" />
        <el-table-column label="生产厂商" prop="productUnit" width="150" />
        <el-table-column label="生产日期" prop="productDate" width="100" />
        <el-table-column label="保质期" prop="qualityPeriod" width="100" />
        <el-table-column label="供货单位" prop="supplyUnit" width="150" />
        <el-table-column>
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">修改</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialogVisible" title="修改药品信息" width="30%" :close-on-click-modal="false" :show-close="false">
      <el-form :model="form">
        <el-form-item label="药品名称" label-width="80">
          <el-input v-model="form.name" placeholder="输入药品名称" />
        </el-form-item>
        <el-form-item label="售价" label-width="80">
          <el-input v-model="form.price" placeholder="输入售价" />
        </el-form-item>
        <el-form-item label="进价" label-width="80">
          <el-input v-model="form.purchasePrice" placeholder="输入进价" />
        </el-form-item>
        <el-form-item label="库存数量" label-width="80">
          <el-input v-model="form.num" placeholder="输入库存数量" />
        </el-form-item>
        <el-form-item label="生产日期" label-width="80">
          <el-date-picker value-format="YYYY-MM-DD" v-model="form.productDate" type="date" placeholder="选择生产日期" />
        </el-form-item>
        <el-form-item label="保质期" label-width="80">
          <el-date-picker value-format="YYYY-MM-DD" v-model="form.qualityPeriod" type="date" placeholder="选择保质期" />
        </el-form-item>
        <el-form-item label="引入日期" label-width="80">
          <el-date-picker value-format="YYYY-MM-DD" v-model="form.introduceDate" type="date" placeholder="选择引入日期" />
        </el-form-item>
        <el-form-item label="生产厂商" label-width="80">
          <el-input v-model="form.productUnit" placeholder="输入生产厂商" />
        </el-form-item>
        <el-form-item label="供货工厂" label-width="80">
          <el-input v-model="form.supplyUnit" placeholder="输入供货工厂" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeUpdate">取消</el-button>
          <el-button type="primary" @click="updateDrugs">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog v-model="addVisble" title="新增药品" width="30%" :close-on-click-modal="false" :show-close="false">
      <el-form :model="form">
        <el-form-item label="药品名称" label-width="80">
          <el-input v-model="form.name" placeholder="输入药品名称" />
        </el-form-item>
        <el-form-item label="售价" label-width="80">
          <el-input v-model="form.price" placeholder="输入售价" />
        </el-form-item>
        <el-form-item label="进价" label-width="80">
          <el-input v-model="form.purchasePrice" placeholder="输入进价" />
        </el-form-item>
        <el-form-item label="库存数量" label-width="80">
          <el-input v-model="form.num" placeholder="输入库存数量" />
        </el-form-item>
        <el-form-item label="生产日期" label-width="80">
          <el-date-picker value-format="YYYY-MM-DD" v-model="form.productDate" type="date" placeholder="选择生产日期" />
        </el-form-item>
        <el-form-item label="保质期" label-width="80">
          <el-date-picker value-format="YYYY-MM-DD" v-model="form.qualityPeriod" type="date" placeholder="选择保质期" />
        </el-form-item>
        <el-form-item label="引入日期" label-width="80">
          <el-date-picker value-format="YYYY-MM-DD" v-model="form.introduceDate" type="date" placeholder="选择引入日期" />
        </el-form-item>
        <el-form-item label="生产厂商" label-width="80">
          <el-input v-model="form.productUnit" placeholder="输入生产厂商" />
        </el-form-item>
        <el-form-item label="供货工厂" label-width="80">
          <el-input v-model="form.supplyUnit" placeholder="输入供货工厂" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeAdd">取消</el-button>
          <el-button type="primary" @click="addDrugs">
            确认
          </el-button>
        </span>
      </template>
    </el-dialog>

  </div>
</template>

<script lang="ts" setup>
import { computed, ref, reactive } from 'vue'
import { ElMessageBox } from "element-plus";
import { ElMessage } from "element-plus";
import axios from "axios";
import { Plus } from '@element-plus/icons-vue'

interface Drugs {
  id: number
  name: string
  price: number
  purchasePrice: number
  num: number
  introduceDate: Date
  productDate: Date
  qualityPeriod: Date
  supplyUnit: string
  productUnit: string
}

let form = reactive({
  id: '',
  name: '',
  price: '',
  purchasePrice: '',
  num: '',
  introduceDate: '',
  productDate: '',
  qualityPeriod: '',
  supplyUnit: '',
  productUnit: '',
})

const idSearch = ref('')
const nameSearch = ref('')
const filterTableData = computed(() =>
  tableData.value.filter(
    (data) =>
      (!nameSearch.value || data.name.toLowerCase().includes(nameSearch.value.toLowerCase()))
      && (!idSearch.value || data.id == parseInt(idSearch.value))
  )
)

const tableData = ref<Drugs[]>([]);

const freshTable = () => {
  axios({
    url: "http://localhost:3000/api/getDrugs/",
    method: "POST",
  }).then((resp) => {
    tableData.value = resp.data;
  })
}

freshTable();

let addVisble = ref(false)
let dialogVisible = ref(false);

const handleAdd = () => {
  addVisble.value = true;
}

const addDrugs = () => {
  axios({
    url: "http://localhost:3000/api/addDrugs/",
    method: "POST",
    params: {
      name: form.name,
      num: form.num,
      price: form.price,
      purchasePrice: form.purchasePrice,
      introduceDate: form.introduceDate,
      productDate: form.productDate,
      qualityPeriod: form.qualityPeriod,
      productUnit: form.productUnit,
      supplyUnit: form.supplyUnit
    }
  }).then((resp) => {
    if (resp.data.info === "error") {
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

const handleEdit = (index: number, row: Drugs) => {
  form.id = row.id.toString();
  form.name = row.name;
  form.num = row.num.toString();
  form.price = row.price.toString();
  form.purchasePrice = row.purchasePrice.toString();

  form.introduceDate = row.introduceDate.toString();
  form.productDate = row.productDate.toString();
  form.qualityPeriod = row.qualityPeriod.toString();

  form.productUnit = row.productUnit;
  form.supplyUnit = row.supplyUnit;

  dialogVisible.value = true
}

const clearForm = () => {
  form.id = '';
  form.name = '';
  form.num = '';
  form.price = '';
  form.purchasePrice = '';

  form.introduceDate = '';
  form.productDate = '';
  form.qualityPeriod = '';

  form.productUnit = '';
  form.supplyUnit = '';
}

const updateDrugs = () => {
  axios({
    url: "http://localhost:3000/api/updateDrugs/",
    method: "POST",
    params: {
      id: form.id,
      name: form.name,
      num: form.num,
      price: form.price,
      purchasePrice: form.purchasePrice,
      introduceDate: form.introduceDate,
      productDate: form.productDate,
      qualityPeriod: form.qualityPeriod,
      productUnit: form.productUnit,
      supplyUnit: form.supplyUnit
    }
  }).then((resp) => {
    if (resp.data.info === "error") {
      ElMessage({
        type: 'error',
        message: '更新失败',
      })
    } else {
      freshTable();
      clearForm();
      dialogVisible.value = false;
      ElMessage({
        type: 'success',
        message: '更新成功',
      })
    }
  })
}

const closeUpdate = () => {
  dialogVisible.value = false;
  ElMessage({
    type: 'info',
    message: '取消更新',
  })
  clearForm();
}

const closeAdd = () => {
  addVisble.value = false;
  ElMessage({
    type: 'info',
    message: '取消添加',
  })
  clearForm();
}

const handleDelete = (index: number, row: Drugs) => {
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
      url: "http://localhost:3000/api/deleteDrugs/",
      method: "POST",
      params: {
        id: row.id,
      }
    }).then((resp) => {
      if (resp.data.info === "error") {
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