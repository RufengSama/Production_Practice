<template>
  <div>
    <el-card class="box-card">
      <span>学校专业分布</span>
      <div id="main" class="main"></div>
    </el-card>
  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  data () {
    return {}
  },

  mounted () {
    const mainChart = echarts.init(document.getElementById('main'))

    // 第一个图表的配置项和数据
    const option1 = {
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
      },
      legend: {
        data: [
          '计算机科学与技术',
          '数据分析',
          '软件工程',
          '信息工程',
          '智能控制',
          '人工智能',
          '信息与通信工程',
          ''
        ]
      },
      series: [
        {
          name: '学校名称',
          type: 'pie',
          selectedMode: 'single',
          radius: [0, '40%'],
          label: {
            position: 'inner',
            fontSize: 14
          },
          labelLine: {
            show: false
          },
          data: [
            { value: 1548, name: '北京化工大学' },
            { value: 775, name: '南通大学' },
            { value: 679, name: '云南大学' }
          ]
        },
        {
          name: '专业名称',
          type: 'pie',
          radius: ['45%', '60%'],
          labelLine: {
            length: 30
          },
          label: {
            formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
            backgroundColor: '#F6F8FC',
            borderColor: '#8C8D8E',
            borderWidth: 1,
            borderRadius: 4,
            rich: {
              a: {
                color: '#6E7079',
                lineHeight: 22,
                align: 'center'
              },
              hr: {
                borderColor: '#8C8D8E',
                width: '100%',
                borderWidth: 1,
                height: 0
              },
              b: {
                color: '#4C5058',
                fontSize: 14,
                fontWeight: 'bold',
                lineHeight: 33
              },
              per: {
                color: '#fff',
                backgroundColor: '#4C5058',
                padding: [3, 4],
                borderRadius: 4
              }
            }
          },
          data: [
            { value: 1048, name: '计算机科学与技术' },
            { value: 335, name: '软件工程' },
            { value: 310, name: '信息工程' },
            { value: 251, name: '数据分析' },
            { value: 234, name: '机械自动化' },
            { value: 147, name: '智能控制' },
            { value: 135, name: '人工智能' },
            { value: 102, name: '信息与通信工程' }
          ]
        }
      ]
    }

    // 初始化第一个图表
    mainChart.setOption(option1)

    // 延迟初始化第二个图表，确保 DOM 渲染完成
    setTimeout(() => {
      // 监听 updateAxisPointer 事件，更新饼图的标签和数据

    })
  }
}
</script>

<style>
.main {
  height: 500px;
}
.el-card {
  margin-top: 20px;
}
</style>
