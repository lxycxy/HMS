<template>
	<div class="sidebar">
		<el-menu
			class="sidebar-el-menu"
			:default-active="onRoutes"
			background-color="#324157"
			text-color="#bfcbd9"
			active-text-color="#20a0ff"
			unique-opened
			router
		>
			<template v-for="item in items">
				<template v-if="item.subs">
					<el-sub-menu :index="item.index" :key="item.index">
						<template #title>
							<i :class="item.icon" style="margin-right: 8px;"></i>
							<span> {{item.title}} </span>
						</template>
						<template v-for="subItem in item.subs">
							<el-menu-item :index="subItem.index">
								<i :class="subItem.icon"></i>
								{{ subItem.title }}
							</el-menu-item>
						</template>
					</el-sub-menu>
				</template>
				<template v-else>
					<el-menu-item :index="item.index" :key="item.index">
						<template #title>
							<i :class="item.icon"></i>
							<span> {{item.title}} </span>
						</template>
					</el-menu-item>
				</template>
			</template>
		</el-menu>
	</div>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { useSidebarStore } from '../store/sidebar';
import { useRoute } from 'vue-router';

const items = [
	{
		icon: 'el-icon-home',
		index: '/dashboard',
		title: '系统首页',
	},
	{
		icon: 'el-icon-list',
		index: '/table',
		title: '疫情风险地管理',
	},
	{
		icon: 'el-icon-list',
		index: '/userslist',
		title: '用户列表',
	},
];

const route = useRoute();
const onRoutes = computed(() => {
	return route.path;
});

const sidebar = useSidebarStore();
</script>

<style scoped>

.sidebar {
	display: block;
	position: absolute;
	left: 0;
	top: 70px;
	bottom: 0;
	overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
	width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
	width: 250px;
}
.sidebar > ul {
	height: 100%;
}
</style>
