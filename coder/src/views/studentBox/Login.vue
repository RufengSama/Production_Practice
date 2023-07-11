<template>
  <div class="login-container">
    <h2>登录</h2>
    <form class="login-form" @submit="login">
      <div class="form-group">
        <label for="studentId">学号:</label>
        <input id="studentId" type="text" v-model="studentId" required>
      </div>
      <div class="form-group">
        <label for="name">姓名:</label>
        <input id="name" type="text" v-model="name" required>
      </div>
      <button type="submit">登录</button>
    </form>
  </div>
</template>

<style scoped>
.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.login-form {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 300px;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 15px;
}

label {
  font-weight: bold;
  display: block;
  margin-bottom: 5px;
}

input[type="text"] {
  padding: 8px;
  width: 100%;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>

<script>
export default {
  data () {
    return {
      studentId: '',
      name: ''
    }
  },
  methods: {
    login () {
      // 发送学号和姓名到后端进行登录逻辑处理
      // 可以使用axios或其他HTTP库发送POST请求
      // 示例代码:
      // axios.post('/api/login', {
      //   studentId: this.studentId,
      //   name: this.name
      // }).then(response => {
      //   // 登录成功的处理逻辑
      // }).catch(error => {
      //   // 登录失败的处理逻辑
      // });
      console.log('Logging in...')
    }
  }
}
</script>
