<template>
  <div class="container">
    <el-table :data="getTableData" stripe border style="width: 100%" :loading="loading">
      <el-table-column prop="id" label="id" width="130px">
        <template slot-scope="scope">
          <span @click="showPopup(scope.row.id)" :style="{ color: getRandomColor(), fontSize: getRandomFontSize() }">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <!-- 省略其他el-table-column的代码 -->
    </el-table>
    <div class="block">
      <el-pagination
        layout="prev, pager, next"
        :total="totalNum"
        :page-size="pageSize"
        :current-page="currentPage"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
    <el-dialog :visible="popupVisible" @close="closePopup" :custom-class="'image-dialog'">
      <div class="image-container">
        <img :src="selectedPersonImage" alt="Person Image" />
      </div>
      <!-- 这里可以显示关于该人员的其他附加信息 -->
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      pageSize: 7,
      totalNum: 0,
      currentPage: 1,
      tableData: [],
      loading: true,
      popupVisible: false,
      selectedPersonId: '',
      selectedPersonImage: '',
      colors: ['#FF0000', '#00FF00', '#0000FF', '#FFFF00', '#00FFFF', '#FF00FF'],
      fontSizes: ['12px', '14px', '16px', '18px', '20px']
    }
  },
  created () {
    this.getInfo()
  },
  computed: {
    getTableData () {
      const start = (this.currentPage - 1) * this.pageSize
      const end = this.currentPage * this.pageSize
      return this.tableData.slice(start, end)
    }
  },
  methods: {
    getInfo () {
      axios.get('/stu/info/acmer/atcoder/all/1/100').then((res) => {
        if (res.data.code === 200) {
          this.loading = false
          const msgInfo = res.data.data.records
          for (const item in msgInfo) {
            this.tableData.push({
              id: msgInfo[item].acId,
              date: msgInfo[item].acDate,
              contest: msgInfo[item].acContest,
              rank: msgInfo[item].acRank,
              performance: msgInfo[item].acPerformance,
              newRating: msgInfo[item].acNewrating,
              diff: msgInfo[item].acDiff,
              count: msgInfo[item].acCount,
              maxrating: msgInfo[item].acMaxrating
            })
          }
          this.totalNum = this.tableData.length
        }
      })
    },
    handleCurrentChange (val) {
      this.currentPage = val
    },
    showPopup (id) {
      this.selectedPersonId = id
      this.selectedPersonImage = require('@/assets/image.jpg') // 替换为实际的图片URL或文件路径
      this.popupVisible = true
    },
    closePopup () {
      this.popupVisible = false
    },
    getRandomColor () {
      return this.colors[Math.floor(Math.random() * this.colors.length)]
    },
    getRandomFontSize () {
      return this.fontSizes[Math.floor(Math.random() * this.fontSizes.length)]
    }
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  background-color: white;
  box-sizing: border-box;
  border-bottom: #eceef0 solid 2px;
  padding: 0px 18px;
}

.headBox {
  width: 100%;
  height: 75px;
  display: flex;
  align-items: center;
  box-sizing: border-box;
}

.headBox p {
  display: flex;
  justify-content: center;
  align-items: center;
}

.headBox p span {
  display: inline-block;
  width: 70px;
  margin-right: 5px;
  color: #7a7d7f;
  font-weight: bold;
}

.headBox p:nth-child(1) span,
.headBox p:nth-child(2) span {
  margin-right: -10px;
}

.headBox p:nth-child(2) span,
.headBox p:nth-child(3) span,
.headBox button {
  margin-left: 7px;
}

.headBox button span {
  margin-left: 5px;
}

.container .block {
  display: flex;
  justify-content: center;
  margin-top: 5px;
}

.title {
  font-weight: bold;
  color: #333;
}

/* 新增样式 */
.image-dialog {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.image-container {
  max-width: 100%;
  max-height: 100%;
  display: inline-block;
}

.image-container img {
  max-width: 100%;
  max-height: 100%;
  display: inline-block;
  vertical-align: middle;
}
</style>
