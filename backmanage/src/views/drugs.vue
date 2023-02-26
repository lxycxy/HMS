<template>
  <div class="drugs">
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

    <el-dialog v-model="dialogVisible" title="修改药品信息" width="30%" :close-on-click-modal="false">
      <el-form :model="form">
        <el-form-item label="药品名称" label-width="80">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="售价" label-width="80">
          <el-input v-model="form.price" />
        </el-form-item>
        <el-form-item label="进价" label-width="80">
          <el-input v-model="form.purchasePrice" />
        </el-form-item>
        <el-form-item label="库存数量" label-width="80">
          <el-input v-model="form.num" />
        </el-form-item>
        <el-form-item label="生产日期" label-width="80">
          <el-date-picker v-model="form.productDate" type="date" placeholder="Pick a day" />
        </el-form-item>
        <el-form-item label="保质期" label-width="80">
          <el-date-picker v-model="form.qualityPeriod" type="date" placeholder="Pick a day" />
        </el-form-item>
        <el-form-item label="引入日期" label-width="80">
          <el-date-picker v-model="form.introduceDate" type="date" placeholder="Pick a day" />
        </el-form-item>
        <el-form-item label="生产厂商" label-width="80">
          <el-input v-model="form.productUnit" />
        </el-form-item>
        <el-form-item label="供货工厂" label-width="80">
          <el-input v-model="form.supplyUnit" />
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

<script lang="ts" setup>
import { computed, ref, reactive } from 'vue'
import { ElMessageBox } from "element-plus";
import { ElMessage } from "element-plus";
import axios from "axios";

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
  name: '',
  price: 0,
  purchasePrice: 0,
  num: 0,
  introduceDate: new Date,
  productDate: new Date,
  qualityPeriod: new Date,
  supplyUnit: '',
  productUnit: '',
})

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

const search = ref('')
const filterTableData = computed(() =>
  tableData.value.filter(
    (data) =>
      data.name.toLowerCase().includes(search.value.toLowerCase())
  )
)

let dialogVisible = ref(false);

const handleEdit = (index: number, row: Drugs) => {
  form.name = row.name,
    form.num = row.num,
    form.price = row.price,
    form.purchasePrice = row.purchasePrice

  form.introduceDate = row.introduceDate;
  form.productDate = row.productDate;
  form.qualityPeriod = row.qualityPeriod;

  form.productUnit = row.productUnit,
    form.supplyUnit = row.supplyUnit,

    dialogVisible.value = true
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
      console.log(resp.data)
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