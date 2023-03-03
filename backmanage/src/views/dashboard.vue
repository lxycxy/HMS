<template v-show="user.is_login">
	<div>
		<el-row :gutter="20">
			<el-col :span="8">
				<el-card shadow="hover" class="mgb20" style="margin-top: 10px">
					<div class="user-info">
						<el-avatar :size="120" :src="qqImg" />
            <div class="user-info-cont">
              <div class="user-info-name">{{ name }}</div>
            </div>
					</div>

					<div class="user-info-list">
						<span >管理员</span>
					</div>
				</el-card>
			</el-col>
			<el-col :span="16">
        <el-card class="box-card">
          <div class="grid-content grid-con-1">
            <el-icon class="grid-con-icon"><User /></el-icon>
            <div class="grid-cont-right">
              <div class="grid-num">{{userCount}}</div>
              <div>用户数量</div>
            </div>
          </div>
          <div class="grid-content grid-con-2">
            <el-icon class="grid-con-icon"><FirstAidKit /></el-icon>
            <div class="grid-cont-right">
              <div class="grid-num">{{ drugCount }}</div>
              <div>药品种类</div>
            </div>
          </div>
        </el-card>

			</el-col>
		</el-row>
	</div>
</template>

<script setup lang="ts" name="dashboard">
import qqImg from '../assets/img/qq.png';
import axios from "axios";
import {ref} from "vue";

let userCount = ref(0);
let drugCount = ref(0);
const fresh = () => {

  axios({
    url:"http://localhost:3000/api/getdata/",
    method: "GET",
  }).then((resp) => {
    userCount.value = resp.data.userCount;
    drugCount.value = resp.data.drugCount;
  })
}

fresh();

const name = sessionStorage.getItem("username");
</script>

<style scoped>

.grid-content {
	display: flex;
	align-items: center;
  margin-top: 5px;
  height: 100px;
}

.grid-cont-right {
	flex: 1;
	text-align: center;
	font-size: 14px;
	color: #999;
}

.grid-num {
	font-size: 30px;
	font-weight: bold;
}

.grid-con-icon {
	font-size: 50px;
	width: 100px;
	height: 100px;
	text-align: center;
	line-height: 100px;
	color: #fff;
}

.grid-con-1 .grid-con-icon {
	background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
	color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
	background: rgb(100, 213, 114);
}

.grid-con-2 .grid-num {
	color: rgb(100, 213, 114);
}

.grid-con-3 .grid-con-icon {
	background: rgb(242, 94, 67);
}


.grid-con-3 .grid-num {
	color: rgb(242, 94, 67);
}

.user-info {
	display: flex;
	text-align: center;
	align-items: center;
	padding-bottom: 20px;
	border-bottom: 2px solid #ccc;
	margin-bottom: 20px;
}

.user-info-cont {
	padding-left: 50px;
	flex: 1;
	font-size: 14px;
	color: #999;
}

.user-info-cont div:first-child {
	font-size: 30px;
	color: #222;
}

.user-info-list {
	font-size: 14px;
	color: #999;
	line-height: 25px;
  margin-top: 35px;
}

.user-info-list span {
	font-size: 40px;
	color: black;
	margin-left: 100px;
}

.mgb20 {
	margin-bottom: 20px;
}
.box-card{
  margin-top: 10px;
}

</style>
