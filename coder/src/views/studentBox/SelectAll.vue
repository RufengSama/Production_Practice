<template>
  <div class="container">
    <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" stripe border style="width: 100%" v-loading="loading">
      <el-table-column prop="id" label="id" width="130px">
        <template slot-scope="scope">
          <span @click="showPopup(scope.row.id)">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="date" label="date" width="210"></el-table-column>
      <el-table-column prop="contest" label="contest" width="180px"></el-table-column>
      <el-table-column prop="rank" label="rank"></el-table-column>
      <el-table-column prop="performance" label="performance" width="120px"></el-table-column>
      <el-table-column prop="newRating" label="newRating" width="120px"></el-table-column>
      <el-table-column prop="diff" label="diff" width="80px"></el-table-column>
      <el-table-column prop="count" label="count"></el-table-column>
      <el-table-column prop="maxrating" label="maxrating"></el-table-column>
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
    <el-dialog :visible="popupVisible" @close="closePopup">
      <img :src="selectedPersonImage" alt="Person Image" />
      <!-- Additional information about the person can be displayed here -->
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
      selectedPersonImage: ''
    }
  },
  created () {
    this.getInfo()
  },
  methods: {
    getInfo () {
      axios.get('/stu/info/acmer/atcoder/all/1/100').then(res => {
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
      // Retrieve additional information for the selected person using the id
      // For example, make an API call to fetch the person's details including the image
      // Once the data is retrieved, update the selectedPersonId and selectedPersonImage
      // and set the popupVisible to true to show the popup
      this.selectedPersonId = id
      // Make an API call or retrieve the image URL based on the id
      this.selectedPersonImage = 'src/assets/image.jpg' // Replace with the actual image URL or file path
      this.popupVisible = true
    },
    closePopup () {
      this.popupVisible = false
      // Additional cleanup or actions can be performed here when the popup is closed
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
</style>
