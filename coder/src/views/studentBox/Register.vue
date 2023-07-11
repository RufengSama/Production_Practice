<template>
  <div class="register-container">
    <h2>注册</h2>
    <form class="register-form" @submit="register">
      <div class="form-group">
        <label for="studentId">学号:</label>
        <input id="studentId" type="text" v-model="studentId" required>
      </div>
      <div class="form-group">
        <label for="name">姓名:</label>
        <input id="name" type="text" v-model="name" required>
      </div>
      <button type="submit">注册</button>
    </form>
  </div>
</template>

<style scoped>
.register-container {
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

.register-form {
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
    register () {
      // 发送学号和姓名到后端进行注册逻辑处理
      // 可以使用axios或其他HTTP库发送POST请求
      // 示例代码:
      // axios.post('/api/register', {
      //   studentId: this.studentId,
      //   name: this.name
      // }).then(response => {
      //   // 注册成功的处理逻辑
      // }).catch(error => {
      //   // 注册失败的处理逻辑
      // });
      console.log('Registering...')
    }
  }
}
</script>
