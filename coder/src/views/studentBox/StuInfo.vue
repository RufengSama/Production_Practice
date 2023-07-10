<template>
  <div class="container">
    <div style="margin-top: 2%;margin-bottom: 1%">
      <el-button type="primary" @click="addStu">新增</el-button>
    </div>
    <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" stripe border style="width: 100%" v-loading="loading">
      <el-table-column prop="stuNo" label="stuNo" width="120"></el-table-column>
      <el-table-column prop="stuName" label="stuName" ></el-table-column>
      <el-table-column prop="stuClass" label="stuClass" ></el-table-column>
      <el-table-column prop="stuAcId" label="stuAcId" ></el-table-column>
      <el-table-column prop="stuCfId" label="stuCfId" ></el-table-column>
      <el-table-column  label="操作" width="100px">
      <template slot-scope="scope">
        <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
        <el-button @click="handleDel(scope.row)" type="text" size="small">删除</el-button>
      </template>
      </el-table-column>
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
    <el-dialog
      :visible.sync="showDialog"
      :title="dialogtitle">
      <el-form
        label-width="120px"
        ref="ruleFormRef"
        :model="formData"
      >
        <el-form-item label="学号">
          <el-input :disabled="dialogtitle==='编辑'" v-model="formData.stuNo"></el-input>
        </el-form-item>
        <el-form-item label="名字">
          <el-input  v-model="formData.stuName"></el-input>
        </el-form-item>
        <el-form-item label="班级">
          <el-input  v-model="formData.stuClass"></el-input>
        </el-form-item>
        <el-form-item label="Atcoder账号">
          <el-input  v-model="formData.stuAcId"></el-input>
        </el-form-item>
        <el-form-item label="Codeforces账号">
          <el-input  v-model="formData.stuCfId"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="submit"> 确认</el-button>
        <el-button @click="showDialog=false"> 取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      pageSize: 7, // 表示一页多少条数据
      totalNum: 0,
      currentPage: 1,
      tableData: [],
      loading: true,
      formData: {},
      showDialog: false,
      dialogtitle: ''
    }
  },
  created () {
    this.getInfo()
  },
  methods: {
    getInfo () {
      axios.get('/stu/info/acmer/student/all/1/100').then(res => {
        if (res.data.code === 200) {
          this.loading = false
          const msgInfo = res.data.data.records
          this.tableData = []
          for (const item in msgInfo) {
            this.tableData.push({
              stuNo: msgInfo[item].stuNo,
              stuName: msgInfo[item].stuName,
              stuClass: msgInfo[item].stuClass,
              stuAcId: msgInfo[item].stuAcId,
              stuCfId: msgInfo[item].stuCfId
            })
          }
          this.totalNum = this.tableData.length
        }
      })
    },
    handleCurrentChange (val) {
      this.currentPage = val
    },
    handleEdit (data) {
      this.dialogtitle = '编辑'

      this.showDialog = true
      this.formData = {
        ...data
      }
    },
    handleDel (data) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      }).then(() => {
        this.$axios('/stu/info/acmer/student/remove/' + data.stuNo).then((res) => {
          this.getInfo()
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    addStu () {
      this.formData = {}
      this.dialogtitle = '新增'
      this.showDialog = true
    },
    submit () {
      this.showDialog = false
      if (this.dialogtitle === '新增') {
        this.$axios.post('/stu/info/acmer/student/insert', this.formData).then(res => {
          console.log(res)
          this.getInfo()
        })
      } else {
        this.$axios.post('/stu/info/acmer/student/update', this.formData).then(res => {
          console.log(res)
          this.getInfo()
        })
      }
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
