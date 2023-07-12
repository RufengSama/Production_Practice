**爬虫端：**

爬取AtCoder和Codeforces网站学生参加比赛的做题数据并存储至数据库中
https://codeforces.com/apiHelp    通过cf API可以获得比赛数据
https://atcoder.jp/ 解析atcoder网页可以获得比赛数据
然后student表那块https://www.buctcoder.com/ACDC/index在这里爬
就根据前端需要展示的数据 把需要的都爬下来 也可以尽可能多的爬取数据 让前端后端选择使用

**应用功能设计**

1. 主页
   1. 展示学生比赛信息，提供搜索、分页功能
   2. 单击学生姓名展示其CFRatingLine积分变化趋势图
   3. 统计学生各类题目做题情况，用极坐标图或表格显示
2. 比赛信息（CF）----对应codeforces problem菜单
   1. 显示codeforces题目信息，可以查看题目、提交代码。提交代码直接跳转至（https://codeforces.com/apiHelp）
3. 近期比赛统计 ----对应recent Contests菜单
   1. 显示相关信息（时间、名称、做题学生数等）
   2. 单击学生姓名可以查看参赛学生信息
   3. 单击view按钮可以查看学生提交的代码
   4. 显示学生做题情况和统计数据、学生个人信息和账号信息以及分数变化（用极坐标或者表格 可视化即可）
4. 比赛反馈 ----对应Local Contests菜单
   1. 针对某一项比赛填写问卷信息、查看问卷结果
   2. 单击view按钮查看比赛信息、解体情况等并可视化
5. 比赛安排 ----对应Schedule菜单
   1. 显示本星期比赛信息，单击list可展示为列表形式
6. 月度排名
   1. 根据codeforces和atcoder分数计算学生当月分数及排名
7. 训练信息 ----对应菜单Train
   1. 显示竞赛训练信息
   2. 单击name可查看学生信息
8. 获奖情况
   1. 查看学生获奖信息
9. 数据可视化 ----对应data visualization菜单
   1. 下设子菜单：BUCT（人数统计、排名信息、解题数量、以小提琴图展示数据）；其他学校；各类比赛信息统计。样例图可以借鉴一下图4.9
10. 信息注册
    1. 填写信息提交给管理员审核


