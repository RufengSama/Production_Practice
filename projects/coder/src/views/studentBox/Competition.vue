
<template>
<div id="main" class="main"></div>
</template>
<script>

import * as echarts from 'echarts'
import axios from 'axios'
export default {
  data () {
    return {
      items: []
    }
  },

  created () {
    axios.get('/stu/info/api/competition/number')
      .then((response) => {
        console.log(response.data)
      })
  },
  mounted () {
    var chartDom = document.getElementById('main')
    var myChart = echarts.init(chartDom)
    var option

    setTimeout(function () {
      option = {
        legend: {},
        tooltip: {
          trigger: 'axis',
          showContent: false
        },
        dataset: {
          source: [['product', '2012', '2013', '2014', '2015', '2016', '2017'],
            ['Atcoder', 56, 82, 88, 70, 53, 85],
            ['Codeforces', 51, 51, 55, 53, 73, 68]]
        },
        xAxis: { type: 'category' },
        yAxis: { gridIndex: 0 },
        grid: { top: '55%' },
        series: [
          {
            type: 'line',
            smooth: true,
            seriesLayoutBy: 'row',
            emphasis: { focus: 'series' }
          },
          {
            type: 'line',
            smooth: true,
            seriesLayoutBy: 'row',
            emphasis: { focus: 'series' }
          },
          {
            type: 'line',
            smooth: true,
            seriesLayoutBy: 'row',
            emphasis: { focus: 'series' }
          },
          {
            type: 'line',
            smooth: true,
            seriesLayoutBy: 'row',
            emphasis: { focus: 'series' }
          },
          {
            type: 'pie',
            id: 'pie',
            radius: '30%',
            center: ['50%', '25%'],
            emphasis: {
              focus: 'self'
            },
            label: {
              formatter: '{b}: {@2012} ({d}%)'
            },
            encode: {
              itemName: 'product',
              value: '2012',
              tooltip: '2012'
            }
          }
        ]
      }
      myChart.on('updateAxisPointer', function (event) {
        const xAxisInfo = event.axesInfo[0]
        if (xAxisInfo) {
          const dimension = xAxisInfo.value + 1
          myChart.setOption({
            series: {
              id: 'pie',
              label: {
                formatter: '{b}: {@[' + dimension + ']} ({d}%)'
              },
              encode: {
                value: dimension,
                tooltip: dimension
              }
            }
          })
        }
      })
      myChart.setOption(option)
    })

    option && myChart.setOption(option)
  }
}
</script>
<style scoped>
.main{
  width: 1000px;
  height: 600px;
  margin-left: 10%;
  margin-top: 30px;
}
</style>
