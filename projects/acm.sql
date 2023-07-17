CREATE TABLE `atcoder`
(
    `id`               int(11) NOT NULL COMMENT 'ID',
    `participate`      int(11) DEFAULT NULL COMMENT '参赛人数',
    `start_time_stamp` varchar(255) DEFAULT NULL COMMENT '开始时间',
    `end_time_stamp`   varchar(255) DEFAULT NULL COMMENT '结束时间',
    `type`             varchar(255) DEFAULT NULL COMMENT '比赛类型',
    `name`             varchar(255) DEFAULT NULL COMMENT '比赛名称',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `codeforces`
(
    `cid`              int(11) NOT NULL COMMENT 'ID',
    `participate`      int(11) DEFAULT NULL COMMENT '参赛人数',
    `start_time_stamp` varchar(255) DEFAULT NULL COMMENT '开始时间',
    `end_time_stamp`   varchar(255) DEFAULT NULL COMMENT '结束时间',
    `type`             varchar(255) DEFAULT NULL COMMENT '比赛类型',
    `name`             varchar(255) DEFAULT NULL COMMENT '比赛名称',
    PRIMARY KEY (`cid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `student`
(
    `stu_no`        varchar(255) NOT NULL COMMENT '学号',
    `real_name`     varchar(255) DEFAULT NULL COMMENT '姓名',
    `class_name`    varchar(255) DEFAULT NULL COMMENT '班级',
    `atcoder_id`    varchar(255) DEFAULT NULL COMMENT 'Atcoder账号',
    `codeforces_id` varchar(255) DEFAULT NULL COMMENT 'Codeforces账号',
    `year`          int(11) DEFAULT NULL COMMENT '年级',
    `school_name`   int(11) DEFAULT NULL COMMENT '学校名称',
    PRIMARY KEY (`stu_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;