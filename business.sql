/* 用户表 */
CREATE TABLE `healthhelper_user`(
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username` varchar(50) NOT NULL COMMENT '用户名',
    `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
    `userpic` varchar(100) COMMENT '用户头像',
    `email` varchar(50) NOT NULL COMMENT '用户email',
    `phone` varchar(50) NOT NULL COMMENT '用户phone',
    `question` varchar(100) NOT NULL COMMENT '找回密码问题',
    `answer` varchar(100) NOT NULL COMMENT '找回密码答案',
    `role` int(4) NOT NULL COMMENT '角色0-管理员，1-普通用户',
    `creat_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
    `ip` varchar(20) DEFAULT NULL COMMENT '用户登录设备的ip地址',
    /* 设置id为主键 */
    PRIMARY KEY (`id`),
    /* 设置用户名为唯一约束,不可重复，将用户名设为索引，提高查询效率 */
    UNIQUE KEY `user_name_unique` (`username`) USING BTREE
    /* id从1开始增长 */
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/* 类别表 */
CREATE TABLE `healthhelper_category`(
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别id',
    `parent_id` int(11) DEFAULT NULL COMMENT '父类Id，当parent_id=0，说明是根节点，一级类别',
    `main_image` varchar(100) DEFAULT NULL COMMENT '类别主图，url相对地址',
    `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
    `status` int(4) DEFAULT NULL COMMENT '类别状态1-正常，2-已废弃',
    `sort_order` int(4) DEFAULT NULL COMMENT '排序编号，同类展示顺序，数值相等则自然排序',
    `creat_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
    /* 设置id为主键 */
    PRIMARY KEY (`id`)
    /* id从1开始增长 */
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/* 睡眠表 */
CREATE TABLE `healthhelper_sleep`(
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '睡眠id',
    `user_id` int(11) NOT NULL COMMENT '用户id',
    `sleep_start_time` varchar (20) NOT NULL COMMENT '开始睡觉时间',
    `sleep_end_time` varchar (20) NOT NULL COMMENT '结束睡觉时间',
    `sleep_total_time` varchar(20) NOT NULL COMMENT '睡眠时长',
    `sleep_condition` varchar(20) DEFAULT NULL COMMENT '睡眠状况',
    `creat_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
    /* 设置id为主键 */
    PRIMARY KEY (`id`)
    /* id从1开始增长 */
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/* 档案表 */
CREATE TABLE `healthhelper_archive`(
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '档案id',
    `user_id` int(11) NOT NULL COMMENT '用户id',
    `birthday` varchar (20) NOT NULL COMMENT '出生日期',
    `blood` varchar (10) NOT NULL COMMENT '血型',
    `height` decimal (10,2) NOT NULL COMMENT '身高',
    `weight` decimal (10,2) NOT NULL COMMENT '体重',
    `waist_size` decimal (10,2) NOT NULL COMMENT '腰围',
    `medicine` varchar (20) DEFAULT NULL COMMENT '当前用药',
    `past_illness` varchar (20) DEFAULT NULL COMMENT '既往病史',
    `family_illness` varchar (20) DEFAULT NULL COMMENT '家庭病史',
    `disability` varchar (20) DEFAULT NULL COMMENT '残疾情况',
    `intolerance` varchar (20) DEFAULT NULL COMMENT '过敏反应',
    `meal_rule` varchar (100) DEFAULT NULL COMMENT '三餐规律',
    `taste` varchar (20) DEFAULT NULL COMMENT '口味喜好',
    `meat_vegetables` varchar (20) DEFAULT NULL COMMENT '荤素喜好',
    `smoke` varchar (20) DEFAULT NULL COMMENT '吸烟情况',
    `drink` varchar (20) DEFAULT NULL COMMENT '喝酒情况',
    `creat_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
    /* 设置id为主键 */
    PRIMARY KEY (`id`)
    /* id从1开始增长 */
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
