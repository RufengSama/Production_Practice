<template>
  <div>
    <el-card class="box-card">
      <span>比赛场数数据变化趋势</span>
      <div id="initChart" class="initChart"></div>
    </el-card>
    <el-card class="box-card">
      <span>比赛人数数据变化趋势</span>
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
    const initChart = echarts.init(document.getElementById('initChart'))

    // 第一个图表的配置项和数据
    const option1 = {
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      legend: {
        data: ['Forest', 'Steppe', 'Desert', 'Wetland']
      },
      toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
          mark: { show: true },
          dataView: { show: true, readOnly: false },
          magicType: { show: true, type: ['line', 'bar', 'stack'] },
          restore: { show: true },
          saveAsImage: { show: true }
        }
      },
      xAxis: [
        {
          type: 'category',
          axisTick: { show: false },
          data: ['2010', '2011', '2012', '2013', '2014', '2015', '2016', '2017', '2018', '2019', '2020', '2021', '2022', '2023']
        }
      ],
      yAxis: [
        {
          type: 'value'
        }
      ],
      series: [
        {
          name: 'Atcoder',
          type: 'bar',
          barGap: 0,
          label: {
            show: true,
            position: 'inside',
            formatter: '{c}'
          },
          emphasis: {
            focus: 'series'
          },
          data: [320, 332, 301, 334, 390, 456, 234, 457, 776, 234, 345, 456, 123, 221]
        },
        {
          name: 'Codeforces',
          type: 'bar',
          label: {
            show: true,
            position: 'inside',
            formatter: '{c}'
          },
          emphasis: {
            focus: 'series'
          },
          data: [220, 182, 191, 234, 290, 456, 234, 457, 776, 234, 456, 234, 457, 776]
        }
      ]
    }

    // 第二个图表的配置项和数据
    const option2 = {
      legend: {},
      tooltip: {
        trigger: 'axis',
        showContent: false
      },
      dataset: {
        source: [
          [
            'product', '2010', '2011', '2012', '2013', '2014', '2015', '2016', '2017', '2018', '2019', '2020', '2021', '2022', '2023'
          ],
          [
            'Atcoder', 10, 12, 15, 24, 34, 46, 56, 82, 88, 70, 53, 85, 95, 100
          ],
          [
            'Codeforces', 23, 42, 35, 14, 34, 46, 36, 82, 58, 75, 34, 95, 67, 88
          ]
        ]
      },
      xAxis: { type: 'category' },
      yAxis: {},
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
        }
      ]
    }

    // 初始化第一个图表
    mainChart.setOption(option1)

    // 延迟初始化第二个图表，确保 DOM 渲染完成
    setTimeout(() => {
      initChart.setOption(option2)

      // 监听 updateAxisPointer 事件，更新饼图的标签和数据
      initChart.on('updateAxisPointer', (event) => {
        const xAxisInfo = event.axesInfo[0]
        if (xAxisInfo) {
          const dimension = xAxisInfo.value + 1
          initChart.setOption({
            series: {
              id: 'pie',
              label: {
                formatter: '{b}: {c} ({d}%)'
              },
              encode: {
                value: dimension,
                itemName: 'product'
              }
            }
          })
        }
      })
    })
  }
}
</script>

<style>
.main {
  height: 400px;
}

.initChart {
  height: 400px;
}
</style>
