<template>
  <div class="container">
    <div class="search">
      <el-form :inline="true" :model="searchForm" class="demo-form-inline">
        <el-form-item label="比赛名称">
          <el-input v-model="searchForm.name" placeholder="请输入比赛名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="search">搜索</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"  @click="showAddDialog">新增</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="table">
      <el-table :data="tableData" border style="width: 100%" v-loading="loading">
        <el-table-column prop="cid" label="ID"></el-table-column>
        <el-table-column prop="name" label="比赛名称"></el-table-column>
        <el-table-column prop="startTimeStamp" label="开始时间"></el-table-column>
        <el-table-column prop="endTimeStamp" label="结束时间"></el-table-column>
        <el-table-column prop="participate" label="参赛人数"></el-table-column>
        <el-table-column prop="type" label="比赛类型"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="showEditDialog(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDelete(scope.row.cid)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        v-if="total > 0"
        :current-page="currentPage"
        :page-size="pageSize"
        layout="total, prev, pager, next"
        :total="total"
        @current-change="handlePageChange"
      />
    </div>
    <!-- 新增弹窗 -->
    <el-dialog
      :title="addDialogTitle"
      :visible.sync="addDialogVisible"
      width="30%"
    >
      <el-form :model="addForm" ref="addForm" label-width="80px">
        <el-form-item label="CID" prop="cid">
          <el-input v-model="addForm.cid" placeholder="请输入CID"></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="addForm.name" placeholder="请输入名称"></el-input>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTimeStamp">
          <el-date-picker
            v-model="addForm.startTimeStamp"
            type="datetime"
            placeholder="选择日期时间"
            value-format="yyyy-MM-dd HH:mm:ss"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTimeStamp">
          <el-date-picker
            v-model="addForm.endTimeStamp"
            type="datetime"
            placeholder="选择日期时间"
            value-format="yyyy-MM-dd HH:mm:ss"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="参赛人数" prop="participate">
          <el-input-number v-model="addForm.participate" :min="1" placeholder="请输入参赛人数"></el-input-number>
        </el-form-item>
        <el-form-item label="比赛类型" prop="type">
          <el-input v-model="addForm.type" placeholder="请输入比赛类型"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleAdd">{{ addDialogOperation }}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      loading: false,
      tableData: [],
      total: 0,
      currentPage: 1,
      pageSize: 10,
      searchForm: {
        name: ''
      },
      addForm: {
        cid: '',
        name: '',
        startTimeStamp: '',
        endTimeStamp: '',
        participate: 0,
        type: ''
      },
      addDialogTitle: '新增比赛',
      addDialogOperation: '添加',
      addDialogVisible: false
    }
  },
  methods: {
    // 发送请求获取数据
    fetchData (params) {
      this.loading = true
      axios
        .get('/stu/info/api/codeforces/list', {
          params: params
        })
        .then((response) => {
          this.tableData = response.data.records
          this.total = response.data.total
          this.loading = false
        })
        .catch((error) => {
          console.log(error)
          this.loading = false
        })
    },
    // 搜索
    search () {
      const params = {
        name: this.searchForm.name,
        page: this.currentPage,
        limit: this.pageSize
      }
      this.fetchData(params)
    },
    // 分页
    handlePageChange (currentPage) {
      this.currentPage = currentPage
      const params = {
        name: this.searchForm.name,
        page: currentPage,
        limit: this.pageSize
      }
      this.fetchData(params)
    },
    // 新增
    handleAdd () {
      const form = this.$refs.addForm
      form.validate((valid) => {
        if (valid) {
          axios
            .post('/stu/info/api/codeforces/add', this.addForm)
            .then(() => {
              this.$message.success('新增成功')
              this.addDialogVisible = false
              this.search()
            })
            .catch((error) => {
              console.log(error)
              this.$message.error('新增失败')
            })
        } else {
          return false
        }
      })
    },
    // 编辑
    showEditDialog (row) {
      this.addDialogTitle = '编辑比赛'
      this.addDialogOperation = '保存'
      this.addDialogVisible = true
      Object.assign(this.addForm, row)
      this.$nextTick(() => {
        this.$refs.addForm.clearValidate()
      })
    },
    // 删除
    handleDelete (cid) {
      this.$confirm('确认删除该比赛吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          axios
            .delete(`/stu/info/api/codeforces/delete/${cid}`)
            .then(() => {
              this.$message.success('删除成功')
              this.search()
            })
            .catch((error) => {
              console.log(error)
              this.$message.error('删除失败')
            })
        })
        .catch(() => {})
    },
    // 显示新增弹窗
    showAddDialog () {
      this.addDialogTitle = '新增比赛'
      this.addDialogOperation = '添加'
      this.addForm = {
        name: '',
        startTimeStamp: '',
        endTimeStamp: '',
        participate: 0,
        type: ''
      }
      this.addDialogVisible = true
      this.$nextTick(() => {
        this.$refs.addForm.clearValidate()
      })
    },
    // 重置新增表单
    resetAddForm () {
      this.addForm = {
        name: '',
        startTimeStamp: '',
        endTimeStamp: '',
        participate: 0,
        type: ''
      }
    }
  },
  mounted () {
    this.search()
  }
}
</script>
<style>
.search {
  margin-top: 20px;
  display: inline-block;
}
</style>
