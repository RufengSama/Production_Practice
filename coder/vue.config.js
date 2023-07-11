module.exports = {
  lintOnSave: false,
  devServer: {
    open: true,
    host: 'localhost',
    port: 8080,
    https: false,
    // 以上的ip和端口是我们本机的;下面为需要跨域的
    proxy: { // 配置跨域
      '/stu/info': {
        target: 'http://127.0.0.1:8080/',
        ws: true,
        changeOrigin: true, // 允许跨域
        pathRewrite: {
          '^/stu/info': ''
        }
      }
    }
  }
}
