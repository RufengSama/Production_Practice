CREATE TABLE IF NOT EXISTS `student` (
   `stu_no` varchar(255) NOT NULL,
   `stu_name` varchar(255) DEFAULT NULL,
   `stu_class` varchar(255) DEFAULT NULL,
   `stu_ac_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
   `stu_cf_id` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`stu_no`)
);
CREATE TABLE IF NOT EXISTS `atcoder` (
   `ac_id` varchar(255) NOT NULL COMMENT 'id',
   `ac_date` varchar(255) DEFAULT NULL COMMENT '日期',
   `ac_contest` varchar(255) DEFAULT NULL COMMENT '比赛名称',
   `ac_rank` varchar(255) DEFAULT NULL COMMENT '排名',
   `ac_performance` varchar(255) DEFAULT NULL COMMENT '表现',
   `ac_newRating` varchar(255) DEFAULT NULL COMMENT '新积分',
   `ac_diff` varchar(255) DEFAULT NULL COMMENT '积分变化',
   `ac_count` varchar(255) DEFAULT NULL COMMENT '比赛次数',
   `ac_maxRating` varchar(255) DEFAULT NULL COMMENT '最高积分',
   PRIMARY KEY (`ac_id`)
);
CREATE TABLE  IF NOT EXISTS `codeforces` (
  `cf_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `cf_contest` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '比赛名称',
  `cf_contest_id` varchar(255) DEFAULT NULL COMMENT '比赛名称id',
  `cf_rank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '比赛的排名',
  `cf_old_rating` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '旧积分',
  `cf_new_rating` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最新积分',
  `cf_sum_contest` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参与比赛的总数',
  `cf_date` varchar(255) DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`cf_id`) USING BTREE
);