<template>
  <div>
    <div class="search-bar">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="姓名">
          <el-input v-model="searchForm.realName" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="班级">
          <el-input v-model="searchForm.className" placeholder="请输入班级"></el-input>
        </el-form-item>
        <el-form-item label="入学年份">
          <el-input v-model="searchForm.year" placeholder="请输入入学年份"></el-input>
        </el-form-item>
        <el-form-item label="学校名称">
          <el-input v-model="searchForm.schoolName" placeholder="请输入学校名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="add-btn" @click="showAddDialog">新增</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="table-container">
      <el-table :data="studentList" style="width: 100%">
        <el-table-column prop="stuNo" label="学号"></el-table-column>
        <el-table-column prop="realName" label="姓名"></el-table-column>
        <el-table-column prop="className" label="班级"></el-table-column>
        <el-table-column prop="atcoderId" label="AtCoder ID"></el-table-column>
        <el-table-column prop="codeforcesId" label="Codeforces ID"></el-table-column>
        <el-table-column prop="year" label="入学年份"></el-table-column>
        <el-table-column prop="schoolName" label="学校名称">
          <template slot-scope="scope">
          {{ getSchoolName(scope.row.schoolName) }}
        </template>
        </el-table-column>
        <el-table-column label="操作" width="160" align="center">
          <template slot-scope="scope">
            <el-button size="mini"  type="primary" @click="showEditDialog(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="deleteStudent(scope.row.stuNo)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        :current-page="pagination.pageNo"
        :page-size="pagination.pageSize"
        :total="pagination.total"
        @current-change="handlePageChange"
        layout="total, prev, pager, next, jumper">
      </el-pagination>
    </div>
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" append-to-body center>
      <el-form :model="dialogForm" :rules="dialogRules" ref="dialogForm" :inline="true" label-width="100px">
        <el-form-item label="学号" prop="stuNo">
          <el-input v-model="dialogForm.stuNo" :disabled="dialogType === 'edit'"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="realName">
          <el-input v-model="dialogForm.realName"></el-input>
        </el-form-item>
        <el-form-item label="班级" prop="className">
          <el-input v-model="dialogForm.className"></el-input>
        </el-form-item>
        <el-form-item label="AtCoder ID" prop="atcoderId">
          <el-input v-model="dialogForm.atcoderId"></el-input>
        </el-form-item>
        <el-form-item label="Codeforces ID" prop="codeforcesId">
          <el-input v-model="dialogForm.codeforcesId"></el-input>
        </el-form-item>
        <el-form-item label="入学年份" prop="year">
          <el-input v-model="dialogForm.year"></el-input>
        </el-form-item>
        <el-form-item label="学校名称" prop="schoolName">
          <el-input v-model="dialogForm.schoolName"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'

export default {
  data () {
    return {
      searchForm: {
        realName: '',
        className: '',
        atcoderId: '',
        codeforcesId: '',
        year: '',
        schoolName: ''
      },
      studentList: [],
      pagination: {
        pageNo: 1,
        pageSize: 10,
        total: 0
      },
      dialogVisible: false,
      dialogType: '',
      dialogTitle: '',
      dialogForm: {
        stuNo: '',
        realName: '',
        className: '',
        atcoderId: '',
        codeforcesId: '',
        year: '',
        schoolName: ''
      },
      dialogRules: {
        stuNo: [
          { required: true, message: '请输入学号', trigger: 'blur' }
        ],
        realName: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        className: [
          { required: true, message: '请输入班级', trigger: 'blur' }
        ],
        atcoderId: [
          { required: true, message: '请输入 AtCoder ID', trigger: 'blur' }
        ],
        codeforcesId: [
          { required: true, message: '请输入 Codeforces ID', trigger: 'blur' }
        ],
        year: [
          { required: true, message: '请输入入学年份', trigger: 'blur' }
        ],
        schoolName: [
          { required: true, message: '请输入学校名称', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    ...mapState(['token'])
  },
  created () {
    this.search()
  },
  methods: {
    search () {
      axios.get('/stu/info/api/students/page', {
        params: {
          realName: this.searchForm.realName,
          className: this.searchForm.className,
          atcoderId: this.searchForm.atcoderId,
          codeforcesId: this.searchForm.codeforcesId,
          year: this.searchForm.year,
          schoolName: this.searchForm.schoolName,
          pageNo: this.pagination.pageNo,
          pageSize: this.pagination.pageSize
        }
      })
        .then(res => {
          this.studentList = res.data.records
          this.pagination.total = res.data.total
        })
        .catch(error => {
          console.log(error)
        })
    },
    getSchoolName (schoolId) {
      switch (schoolId) {
        case 1:
          return '北京化工大学'
        case 2:
          return '南通大学'
        case 3:
          return '云南大学'
        default:
          return ''
      }
    },
    handlePageChange (pageNo) {
      this.pagination.pageNo = pageNo
      this.search()
    },
    showAddDialog () {
      this.dialogType = 'add'
      this.dialogTitle = '新增学生信息'
      this.dialogForm = {
        stuNo: '',
        realName: '',
        className: '',
        atcoderId: '',
        codeforcesId: '',
        year: '',
        schoolName: ''
      }
      this.dialogVisible = true
    },
    showEditDialog (student) {
      this.dialogType = 'edit'
      this.dialogTitle = '编辑学生信息'
      this.dialogForm = { ...student }
      this.dialogVisible = true
    },
    save () {
      this.$refs.dialogForm.validate(valid => {
        if (valid) {
          let url = ''
          let method = ''
          if (this.dialogType === 'add') {
            url = '/api/students/add'
            method = 'post'
          } else {
            url = `/api/students/${this.dialogForm.stuNo}`
            method = 'put'
          }
          axios({
            method: method,
            url: url,
            data: this.dialogForm
          })
            .then(res => {
              this.dialogVisible = false
              this.search()
            })
            .catch(error => {
              console.log(error)
            })
        }
      })
    },
    deleteStudent (stuNo) {
      this.$confirm('确认删除该学生信息吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          axios.delete(`/api/students/${stuNo}`, {
            headers: {
              Authorization: `Bearer ${this.token}`
            }
          })
            .then(res => {
              this.search()
            })
            .catch(error => {
              console.log(error)
            })
        })
        .catch(() => {})
    }
  }
}
</script>

<style>
.search-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
  display: inline-block;
}
.search-form {
  flex: 1;
  display: flex;
  justify-content:flex-start;
}
.add-btn {
margin-left: 16px;
}
.table-container {
margin-bottom: 16px;
}
</style>
