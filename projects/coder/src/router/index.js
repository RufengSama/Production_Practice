import Vue from 'vue'
import VueRouter from 'vue-router'
import StudentIndex from '../views/StudentIndex.vue'
import SelectAll from '../views/studentBox/SelectAll.vue'
import Student from '../views/studentBox/Student'
import Codeforces from '../views/studentBox/Codeforces.vue'
import Competition from '../views/studentBox/Competition.vue'

Vue.use(VueRouter)

// 解决在使用ElementUI中的导航时，默认情况下如果重复点击某选项，会报错。
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}
const originalReplace = VueRouter.prototype.replace
VueRouter.prototype.replace = function replace (location) {
  return originalReplace.call(this, location).catch(err => err)
}

const routes = [{
  path: '/',
  component: StudentIndex,
  redirect: '/SelectAll',
  children: [{
    path: '/SelectAll',
    name: 'SelectAll',
    component: SelectAll
  }, {
    path: '/Student',
    name: 'Student',
    component: Student
  },
  {
    path: '/Codeforces',
    name: 'Codeforces',
    component: Codeforces
  },
  {
    path: '/Competition',
    name: 'Competition',
    component: Competition
  }
  ]
}
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
