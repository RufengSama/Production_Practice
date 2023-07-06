<template>
  <div class="container clearfix" ref="page">
    <!-- 左侧导航栏开始 -->
    <el-row class="tac">
      <el-col :span="12" class="navBox">
        <el-menu
          :default-active="defaultActive"
          class="el-menu-vertical-demo"
          background-color="#243946"
          text-color="#96afbe"
          active-text-color="#ffffff"
        >
          <h2>BUCTCODER</h2>
          <el-menu-item index="1" @click="borrow()">
            <i class="iconfont icon-sousuo2"></i>
            <span slot="title">信息查询</span>
          </el-menu-item>
          <el-menu-item index="2" @click="stuInfo()">
            <i class="iconfont icon-geren3"></i>
            <span slot="title">学生基本信息</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
    <!-- 左侧导航栏结束 -->
    <router-view class="right_bottom"></router-view>
  </div>
</template>

<script>

export default {
  data () {
    return {
      clientHeight: '',
      defaultActive: '1'
    }
  },
  async created () {
    if (this.$route.name === 'SelectAll') {
      this.defaultActive = '1'
    }
  },
  mounted () {
    // 获取浏览器可视区域高度
    this.clientHeight = `${document.documentElement.clientHeight}`
    const that = this
    window.onresize = function () {
      this.clientHeight = `${document.documentElement.clientHeight}`
      if (that.$refs.page) {
        that.$refs.page.style.height = this.clientHeight + 'px'
      }
    }
  },
  watch: {
    // 如果clientHeight 发生改变，这个函数就会运行
    clientHeight () {
      this.changeFixed(this.clientHeight)
    }
  },
  methods: {
    // 控制浏览器高度
    changeFixed (clientHeight) {
      if (this.$refs.page) {
        this.$refs.page.style.height = clientHeight + 'px'
      }
    },
    borrow () {
      if (this.$route.name !== 'SelectAll') {
        this.$router.replace({ path: '/SelectAll' })
      }
    },
    stuInfo () {
      if (this.$route.name !== 'StuInfo') {
        this.$router.replace({ path: '/StuInfo' })
      }
    }
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  margin: 0 auto;
  background-color: #f3f5f6;
}
.container .tac {
  float: left;
    position: fixed;
}
.container .tac,
.container .tac .navBox,
.container .tac .navBox ul {
  width: 220px;
  height: 100%;
}
.container .tac .navBox h2 {
  margin-left: 20px;
  line-height: 56px;
  color: white;
}
.container .tac .navBox p {
  line-height: 56px;
  color: #96afbe;
  margin-left: 10px;
}
.container .tac .navBox .iconfont {
  margin-right: 5px;
  width: 24px;
  text-align: center;
  font-size: 18px;
  vertical-align: middle;
  font-style: normal;
  font-weight: 400;
  font-variant: normal;
  text-transform: none;
  line-height: 1;
  display: inline-block;
}
.container .right_head {
  width: calc(100% - 220px);
  height: 55px;
  background-color: white;
  float: right;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-sizing: border-box;
  padding: 0px 18px;
  margin-bottom: 18px;
  box-shadow: 0px 2px 5px #dbdedf;
}
.container .right_head .logo {
  width: 40px;
  height: 40px;
}
.container .right_head div {
  color: #a9acae;
}
.container .right_head div img {
  width: 30px;
  height: 30px;
  display: inline-block;
  border-radius: 50%;
  vertical-align: middle;
}
.container .right_head span {
  font-weight: bold;
}

.container .right_head .el-button {
  background-color: #fff;
  color: black;
  font-size: 20px;
  border: 0px;
}
.container .right_head .el-button:hover {
  color: #409eff;
}
.container .right_bottom {
  width: calc(100% - 256px);
  float: right;
  margin-right: 18px;
}
.notice-info .el-tag {
  font-size: 20px;
  width: calc(100% - 10px);
  margin: 5px;
}
</style>
