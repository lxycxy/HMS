import {createRouter, createWebHistory, RouteRecordRaw} from "vue-router";
import Home from "../views/home.vue";
const routes:RouteRecordRaw[] = [
    {
        path: '/',
        redirect: '/dashboard',
    }, {
        path: "/",
        name: "Home",
        component: Home,
        children: [
            {
                path: "/dashboard",
                name: "dashboard",
                meta: {
                    title: '系统首页',
                    author: true,
                },
                component: () => import ( /* webpackChunkName: "dashboard" */ "../views/dashboard.vue")
            },{
                path: "/table",
                name: "table",
                meta: {
                    title: '药品信息管理',
                    author: true,
                },
                component: () => import ( /* webpackChunkName: "table" */ "../views/table.vue")
            },
            {
                path: "/userlist",
                name: "userslist",
                meta: {
                    title: '用户管理',
                    author: true,
                },
                component: () => import ( /* webpackChunkName: "userlist" */ "../views/userlist.vue")
            },
            {
                path: "/user",
                name: "user",
                meta: {
                    title: '个人中心',
                    author: true,
                },
                component: () => import ( /* webpackChunkName: "user" */ "../views/user.vue")
            },
        ]
    }, {
        path: "/login",
        name: "Login",
        meta: {
            title: '登录',
            author: false,
        },
        component: () => import ( /* webpackChunkName: "login" */ "../views/login.vue")
    },{
        path: "/:catchAll(.*)",
        redirect: "/404/"
    },{
        path:'/404/',
        name: '404',
        component: () => import (/* webpackChunkName: "404" */ '../views/404.vue')
    }
];
const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    if(to.meta.author && !sessionStorage.getItem("is_login")) {
        next({name:"Login"});
    } else {
        next();
    }
});

export default router;