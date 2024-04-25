/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : zhinengxuexipingtai

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 06/06/2023 00:06:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置参数值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, '轮播图1', 'http://localhost:8080/zhinengxuexipingtai/upload/config1.jpg');
INSERT INTO `config` VALUES (2, '轮播图2', 'http://localhost:8080/zhinengxuexipingtai/upload/config2.jpg');
INSERT INTO `config` VALUES (3, '轮播图3', 'http://localhost:8080/zhinengxuexipingtai/upload/config3.jpg');

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dic_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段',
  `dic_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段名',
  `code_index` int(11) NULL DEFAULT NULL COMMENT '编码',
  `index_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码名字  Search111 ',
  `super_id` int(11) NULL DEFAULT NULL COMMENT '父字段id',
  `beizhu` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (1, 'kecheng_types', '词法和语法', 1, '词法分析', NULL, NULL, '2022-03-07 15:48:03');
INSERT INTO `dictionary` VALUES (2, 'kecheng_types', '词法和语法', 2, '化简DFA', NULL, NULL, '2022-03-07 15:48:03');
INSERT INTO `dictionary` VALUES (3, 'kecheng_types', '词法和语法', 3, 'NFA转DFA', NULL, NULL, '2022-03-07 15:48:03');
INSERT INTO `dictionary` VALUES (4, 'sex_types', '性别', 1, '男', NULL, NULL, '2022-03-07 15:48:03');
INSERT INTO `dictionary` VALUES (5, 'sex_types', '性别', 2, '女', NULL, NULL, '2022-03-07 15:48:03');
INSERT INTO `dictionary` VALUES (6, 'forum_state_types', '帖子状态', 1, '发帖', NULL, NULL, '2022-03-07 15:48:03');
INSERT INTO `dictionary` VALUES (7, 'forum_state_types', '帖子状态', 2, '回帖', NULL, NULL, '2022-03-07 15:48:03');
INSERT INTO `dictionary` VALUES (8, 'news_types', '公告类型', 1, '词法分析', NULL, NULL, '2022-03-07 15:48:03');
INSERT INTO `dictionary` VALUES (9, 'news_types', '公告类型', 2, '化简DFA', NULL, NULL, '2022-03-07 15:48:03');
INSERT INTO `dictionary` VALUES (10, 'news_types', '公告类型', 3, 'NFA转DFA', NULL, NULL, '2022-03-07 15:48:03');
INSERT INTO `dictionary` VALUES (11, 'examquestion_types', '习题类别', 1, '单选题', NULL, NULL, '2022-03-07 15:48:04');
INSERT INTO `dictionary` VALUES (12, 'examquestion_types', '习题类别', 2, '多选题', NULL, NULL, '2022-03-07 15:48:04');
INSERT INTO `dictionary` VALUES (13, 'examquestion_types', '习题类别', 3, '判断题', NULL, NULL, '2022-03-07 15:48:04');
INSERT INTO `dictionary` VALUES (14, 'examquestion_types', '习题类别', 4, '填空题', NULL, NULL, '2022-03-07 15:48:04');
INSERT INTO `dictionary` VALUES (15, 'exampaper_types', '习题状态', 1, '启用', NULL, NULL, '2022-03-07 15:48:04');
INSERT INTO `dictionary` VALUES (16, 'exampaper_types', '习题状态', 2, '禁用', NULL, NULL, '2022-03-07 15:48:04');

-- ----------------------------
-- Table structure for exampaper
-- ----------------------------
DROP TABLE IF EXISTS `exampaper`;
CREATE TABLE `exampaper`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exampaper_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '试卷名称 Search111',
  `exampaper_date` int(11) NOT NULL COMMENT '考试时长(分钟)',
  `exampaper_myscore` int(20) NOT NULL DEFAULT 0 COMMENT '试卷总分数',
  `exampaper_types` int(11) NOT NULL DEFAULT 0 COMMENT '试卷状态 Search111',
  `exampaper_delete` int(255) NULL DEFAULT 0 COMMENT '逻辑删除（0代表未删除 1代表已删除）',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间 show2 photoShow',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '试卷表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exampaper
-- ----------------------------
INSERT INTO `exampaper` VALUES (1, '编译练习题', 60, 30, 1, 1, '2022-03-01 11:12:25');
INSERT INTO `exampaper` VALUES (2, '编译小知识', 60, 7, 1, 1, '2023-05-06 14:46:26');
INSERT INTO `exampaper` VALUES (3, '编译小知识', 60, 0, 1, 2, '2023-05-08 17:37:49');

-- ----------------------------
-- Table structure for examquestion
-- ----------------------------
DROP TABLE IF EXISTS `examquestion`;
CREATE TABLE `examquestion`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exampaper_id` int(20) NOT NULL COMMENT '所属试卷id（外键）',
  `examquestion_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '试题名称 Search111',
  `examquestion_options` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '选项，json字符串',
  `examquestion_score` int(20) NULL DEFAULT 0 COMMENT '分值 Search111',
  `examquestion_answer` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '正确答案',
  `examquestion_analysis` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '答案解析',
  `examquestion_types` int(20) NULL DEFAULT 0 COMMENT '试题类型',
  `examquestion_sequence` int(20) NULL DEFAULT 100 COMMENT '试题排序，值越大排越前面',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '试题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of examquestion
-- ----------------------------
INSERT INTO `examquestion` VALUES (1, 1, '1+1', '[{\"text\":\"1\",\"code\":\"A\"},{\"text\":\"2\",\"code\":\"B\"},{\"text\":\"3\",\"code\":\"C\"},{\"text\":\"4\",\"code\":\"D\"}]', 30, 'B', '1+1=2', 1, 1, '2022-03-04 11:13:10');
INSERT INTO `examquestion` VALUES (5, 2, 'LL(1)文法属于自下而上还是自上而下文法', '[{\"text\":\"自上而下\",\"code\":\"A\"},{\"text\":\"自下而上\",\"code\":\"B\"},{\"text\":\"自外而内\",\"code\":\"C\"},{\"text\":\"自内而外\",\"code\":\"D\"}]', 2, 'A', '查书', 1, 1, '2023-05-06 14:53:36');
INSERT INTO `examquestion` VALUES (6, 2, '你对于学习编译原理的感受是什么', '[]', 5, '很好很好', '开放性试题', 4, 1, '2023-05-08 17:39:14');

-- ----------------------------
-- Table structure for examrecord
-- ----------------------------
DROP TABLE IF EXISTS `examrecord`;
CREATE TABLE `examrecord`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `examrecord_uuid_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考试编号',
  `yonghu_id` int(20) NOT NULL COMMENT '考试用户',
  `exampaper_id` int(20) NOT NULL COMMENT '所属试卷id（外键）',
  `total_score` int(200) NULL DEFAULT NULL COMMENT '所得总分',
  `insert_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '考试时间',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '考试记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of examrecord
-- ----------------------------
INSERT INTO `examrecord` VALUES (1, '1646641780114', 1, 1, 90, '2022-03-07 16:29:40', '2022-03-07 16:29:40');
INSERT INTO `examrecord` VALUES (2, '1646641858736', 1, 1, 0, '2022-03-07 16:30:59', '2022-03-07 16:30:59');
INSERT INTO `examrecord` VALUES (3, '1683269605219', 1, 1, 10, '2023-05-05 14:53:25', '2023-05-05 14:53:25');
INSERT INTO `examrecord` VALUES (4, '1684137732360', 2, 3, 0, '2023-05-15 16:02:12', '2023-05-15 16:02:12');
INSERT INTO `examrecord` VALUES (5, '1684137745522', 2, 2, 2, '2023-05-15 16:02:26', '2023-05-15 16:02:26');
INSERT INTO `examrecord` VALUES (6, '1684150946759', 2, 3, 0, '2023-05-15 19:42:27', '2023-05-15 19:42:27');
INSERT INTO `examrecord` VALUES (7, '1684150955085', 2, 2, 2, '2023-05-15 19:42:35', '2023-05-15 19:42:35');
INSERT INTO `examrecord` VALUES (8, '1685371280118', 2, 2, 7, '2023-05-29 22:41:20', '2023-05-29 22:41:20');
INSERT INTO `examrecord` VALUES (9, '1685407102022', 2, 2, 2, '2023-05-30 08:38:22', '2023-05-30 08:38:22');

-- ----------------------------
-- Table structure for examredetails
-- ----------------------------
DROP TABLE IF EXISTS `examredetails`;
CREATE TABLE `examredetails`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `examredetails_uuid_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '试卷编号',
  `yonghu_id` int(20) NOT NULL COMMENT '用户id',
  `examquestion_id` int(20) NOT NULL COMMENT '试题id（外键）',
  `examredetails_myanswer` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考生答案',
  `examredetails_myscore` int(20) NOT NULL DEFAULT 0 COMMENT '试题得分',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '答题详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of examredetails
-- ----------------------------
INSERT INTO `examredetails` VALUES (1, '1646641780114', 1, 4, '001', 0, '2022-03-07 16:29:45');
INSERT INTO `examredetails` VALUES (2, '1646641780114', 1, 3, 'A', 30, '2022-03-07 16:29:48');
INSERT INTO `examredetails` VALUES (3, '1646641780114', 1, 2, 'B,D', 30, '2022-03-07 16:29:52');
INSERT INTO `examredetails` VALUES (4, '1646641780114', 1, 1, 'B', 30, '2022-03-07 16:29:55');
INSERT INTO `examredetails` VALUES (5, '1646641858736', 1, 1, '未作答', 0, '2022-03-07 16:31:00');
INSERT INTO `examredetails` VALUES (6, '1646641858736', 1, 2, '未作答', 0, '2022-03-07 16:31:00');
INSERT INTO `examredetails` VALUES (7, '1646641858736', 1, 3, '未作答', 0, '2022-03-07 16:31:00');
INSERT INTO `examredetails` VALUES (8, '1646641858736', 1, 4, '未作答', 0, '2022-03-07 16:31:00');
INSERT INTO `examredetails` VALUES (9, '1683269605219', 1, 4, '8', 10, '2023-05-05 14:53:33');
INSERT INTO `examredetails` VALUES (10, '1683269605219', 1, 1, '未作答', 0, '2023-05-05 14:53:41');
INSERT INTO `examredetails` VALUES (11, '1683269605219', 1, 2, '未作答', 0, '2023-05-05 14:53:41');
INSERT INTO `examredetails` VALUES (12, '1683269605219', 1, 3, '未作答', 0, '2023-05-05 14:53:41');
INSERT INTO `examredetails` VALUES (13, '1684137745522', 2, 5, 'A', 2, '2023-05-15 16:02:31');
INSERT INTO `examredetails` VALUES (14, '1684137745522', 2, 6, '没什么感受', 0, '2023-05-15 16:02:41');
INSERT INTO `examredetails` VALUES (15, '1684150955085', 2, 5, 'A', 2, '2023-05-15 19:42:40');
INSERT INTO `examredetails` VALUES (16, '1684150955085', 2, 6, 'jskd', 0, '2023-05-15 19:42:45');
INSERT INTO `examredetails` VALUES (17, '1685371280118', 2, 5, 'A', 2, '2023-05-29 22:41:26');
INSERT INTO `examredetails` VALUES (18, '1685371280118', 2, 6, '很好很好', 5, '2023-05-29 22:41:34');
INSERT INTO `examredetails` VALUES (19, '1685407102022', 2, 5, 'A', 2, '2023-05-30 08:38:28');
INSERT INTO `examredetails` VALUES (20, '1685407102022', 2, 6, '结算卡', 0, '2023-05-30 08:38:35');

-- ----------------------------
-- Table structure for examrewrongquestion
-- ----------------------------
DROP TABLE IF EXISTS `examrewrongquestion`;
CREATE TABLE `examrewrongquestion`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `yonghu_id` int(20) NOT NULL COMMENT '用户id',
  `exampaper_id` int(20) NOT NULL COMMENT '试卷（外键）',
  `examquestion_id` int(20) NOT NULL COMMENT '试题id（外键）',
  `examredetails_myanswer` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考生作答',
  `insert_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '记录时间',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间 show3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '错题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of examrewrongquestion
-- ----------------------------
INSERT INTO `examrewrongquestion` VALUES (1, 1, 1, 4, '001', '2022-03-07 16:29:45', '2022-03-07 16:29:45');
INSERT INTO `examrewrongquestion` VALUES (2, 1, 1, 1, '未作答', '2022-03-07 16:31:00', '2022-03-07 16:31:00');
INSERT INTO `examrewrongquestion` VALUES (3, 1, 1, 2, '未作答', '2022-03-07 16:31:00', '2022-03-07 16:31:00');
INSERT INTO `examrewrongquestion` VALUES (4, 1, 1, 3, '未作答', '2022-03-07 16:31:00', '2022-03-07 16:31:00');
INSERT INTO `examrewrongquestion` VALUES (5, 1, 1, 4, '未作答', '2022-03-07 16:31:00', '2022-03-07 16:31:00');
INSERT INTO `examrewrongquestion` VALUES (6, 1, 1, 1, '未作答', '2023-05-05 14:53:41', '2023-05-05 14:53:41');
INSERT INTO `examrewrongquestion` VALUES (7, 1, 1, 2, '未作答', '2023-05-05 14:53:41', '2023-05-05 14:53:41');
INSERT INTO `examrewrongquestion` VALUES (8, 1, 1, 3, '未作答', '2023-05-05 14:53:41', '2023-05-05 14:53:41');
INSERT INTO `examrewrongquestion` VALUES (9, 2, 2, 6, '没什么感受', '2023-05-15 16:02:41', '2023-05-15 16:02:41');
INSERT INTO `examrewrongquestion` VALUES (10, 2, 2, 6, 'jskd', '2023-05-15 19:42:45', '2023-05-15 19:42:45');
INSERT INTO `examrewrongquestion` VALUES (11, 2, 2, 6, '结算卡', '2023-05-30 08:38:35', '2023-05-30 08:38:35');

-- ----------------------------
-- Table structure for forum
-- ----------------------------
DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `forum_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '帖子标题  Search111 ',
  `yonghu_id` int(11) NULL DEFAULT NULL COMMENT '学生',
  `jiaoshi_id` int(11) NULL DEFAULT NULL COMMENT '教师',
  `users_id` int(11) NULL DEFAULT NULL COMMENT '管理员',
  `forum_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '发布内容',
  `super_ids` int(11) NULL DEFAULT NULL COMMENT '父id',
  `forum_state_types` int(11) NULL DEFAULT NULL COMMENT '帖子状态',
  `insert_time` timestamp(0) NULL DEFAULT NULL COMMENT '发帖时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间 show2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '论坛' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of forum
-- ----------------------------
INSERT INTO `forum` VALUES (1, '问题1', 3, NULL, NULL, '发布内容1', NULL, 1, '2022-03-07 15:48:13', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `forum` VALUES (2, '问题2', 3, NULL, NULL, '发布内容2', NULL, 1, '2022-03-07 15:48:13', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `forum` VALUES (3, '问题3', 2, NULL, NULL, '发布内容3', NULL, 1, '2022-03-07 15:48:13', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `forum` VALUES (4, '问题4', 1, NULL, NULL, '发布内容4', NULL, 1, '2022-03-07 15:48:13', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `forum` VALUES (5, '问题5', 3, NULL, NULL, '发布内容5', NULL, 1, '2022-03-07 15:48:13', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `forum` VALUES (6, '学生发布帖子', 1, NULL, NULL, '帖子内容111·1', NULL, 1, '2022-03-07 16:29:03', NULL, '2022-03-07 16:29:03');
INSERT INTO `forum` VALUES (7, NULL, NULL, NULL, 1, '管理回复', 6, 2, '2022-03-07 16:34:30', NULL, '2022-03-07 16:34:30');
INSERT INTO `forum` VALUES (8, NULL, 2, NULL, NULL, '我觉得此事不妥', 6, 2, '2023-05-15 16:05:53', NULL, '2023-05-15 16:05:53');

-- ----------------------------
-- Table structure for jiaoshi
-- ----------------------------
DROP TABLE IF EXISTS `jiaoshi`;
CREATE TABLE `jiaoshi`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `jiaoshi_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师姓名 Search111 ',
  `jiaoshi_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `sex_types` int(11) NULL DEFAULT NULL COMMENT '性别 Search111 ',
  `jiaoshi_phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `jiaoshi_email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `jiaoshi_delete` int(11) NULL DEFAULT 1 COMMENT '假删',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiaoshi
-- ----------------------------
INSERT INTO `jiaoshi` VALUES (1, 'a1', '123456', '教师姓名1', 'http://localhost:8080/zhinengxuexipingtai/upload/jiaoshi1.jpg', 1, '17703786901', '1@qq.com', 1, '2022-03-07 15:48:13');
INSERT INTO `jiaoshi` VALUES (2, 'a2', '123456', '教师姓名2', 'http://localhost:8080/zhinengxuexipingtai/upload/jiaoshi2.jpg', 2, '17703786902', '2@qq.com', 1, '2022-03-07 15:48:13');
INSERT INTO `jiaoshi` VALUES (3, 'huahangwansui1', '123456', '教师姓名3', 'http://localhost:8080/zhinengxuexipingtai/upload/jiaoshi3.jpg', 2, '17703786903', '3@qq.com', 1, '2022-03-07 15:48:13');
INSERT INTO `jiaoshi` VALUES (4, 'a4', '123456', '教师姓名4', 'http://localhost:8080/zhinengxuexipingtai/upload/jiaoshi4.jpg', 1, '17703786904', '4@qq.com', 1, '2022-03-07 15:48:13');
INSERT INTO `jiaoshi` VALUES (5, 'a5', '123456', '教师姓名5', 'http://localhost:8080/zhinengxuexipingtai/upload/jiaoshi5.jpg', 2, '17703786905', '5@qq.com', 1, '2022-03-07 15:48:13');
INSERT INTO `jiaoshi` VALUES (6, '王明', '1234567', '王明', NULL, NULL, '18713486529', '3175874297@qq.com', 1, '2023-05-28 21:53:06');
INSERT INTO `jiaoshi` VALUES (7, 'huahangwansui', '1314666888', '王明', NULL, NULL, '13315688889', '3175874297@qq.com', 1, '2023-06-02 11:26:21');

-- ----------------------------
-- Table structure for kecheng
-- ----------------------------
DROP TABLE IF EXISTS `kecheng`;
CREATE TABLE `kecheng`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `kecheng_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程标题 Search111 ',
  `kecheng_types` int(11) NULL DEFAULT NULL COMMENT '课程类型 Search111',
  `kecheng_photo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程图片 ',
  `kecheng_video` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程视频 ',
  `kecheng_file` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文档下载',
  `jiaoshi_id` int(11) NULL DEFAULT NULL COMMENT '课程教师',
  `kecheng_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '课程详情',
  `insert_time` timestamp(0) NULL DEFAULT NULL COMMENT '发布时间',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间 show1 show2 photoShow',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kecheng
-- ----------------------------
INSERT INTO `kecheng` VALUES (1, '编译资料1', 1, 'http://localhost:8080/zhinengxuexipingtai/upload/1685677539181.jpg', 'http://localhost:8080/zhinengxuexipingtai/upload/video.mp4', 'http://localhost:8080/zhinengxuexipingtai/upload/file.rar', 1, '<p>资料详情1</p>', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `kecheng` VALUES (2, '编译资料2', 3, 'http://localhost:8080/zhinengxuexipingtai/upload/kecheng2.jpg', 'http://localhost:8080/zhinengxuexipingtai/upload/video.mp4', 'http://localhost:8080/zhinengxuexipingtai/upload/file.rar', 2, '<p>资料详情2</p>', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `kecheng` VALUES (3, '编译资料3', 2, 'http://localhost:8080/zhinengxuexipingtai/upload/kecheng3.jpg', 'http://localhost:8080/zhinengxuexipingtai/upload/video.mp4', 'http://localhost:8080/zhinengxuexipingtai/upload/file.rar', 3, '资料详情3', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `kecheng` VALUES (4, '编译资料4', 3, 'http://localhost:8080/zhinengxuexipingtai/upload/1685677615590.jpg', 'http://localhost:8080/zhinengxuexipingtai/upload/video.mp4', 'http://localhost:8080/zhinengxuexipingtai/upload/file.rar', 4, '<p>资料详情4</p>', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `kecheng` VALUES (5, '编译资料5', 1, 'http://localhost:8080/zhinengxuexipingtai/upload/1685677648539.jpg', 'http://localhost:8080/zhinengxuexipingtai/upload/video.mp4', 'http://localhost:8080/zhinengxuexipingtai/upload/file.rar', 5, '<p>资料详情5</p>', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `kecheng` VALUES (6, 'NFA转化为DFA的学习', 3, 'http://localhost:8080/zhinengxuexipingtai/upload/1685676445923.png', 'http://localhost:8080/zhinengxuexipingtai/upload/1685676458022.mp4', 'http://localhost:8080/zhinengxuexipingtai/upload/1685676549131.pdf', 7, '<p>NFA转化为DFA的操作，大家可以仔细研读</p>', '2023-06-02 11:42:55', '2023-06-02 11:42:55');
INSERT INTO `kecheng` VALUES (7, 'NFA转DFA', 3, 'http://localhost:8080/zhinengxuexipingtai/upload/1685874600263.jpg', 'http://localhost:8080/zhinengxuexipingtai/upload/1685874637590.mp4', 'http://localhost:8080/zhinengxuexipingtai/upload/1685874648356.pptx', 7, '<p><img src=\"http://localhost:8080/zhinengxuexipingtai/upload/1685874739850.png\">111</p>', '2023-06-04 18:31:29', '2023-06-04 18:31:29');

-- ----------------------------
-- Table structure for kecheng_liuyan
-- ----------------------------
DROP TABLE IF EXISTS `kecheng_liuyan`;
CREATE TABLE `kecheng_liuyan`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `kecheng_id` int(11) NULL DEFAULT NULL COMMENT '课程信息',
  `yonghu_id` int(11) NULL DEFAULT NULL COMMENT '用户',
  `kecheng_liuyan_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '留言内容',
  `reply_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复内容',
  `insert_time` timestamp(0) NULL DEFAULT NULL COMMENT '留言时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '回复时间',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '作业记录留言' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kecheng_liuyan
-- ----------------------------
INSERT INTO `kecheng_liuyan` VALUES (1, 1, 1, '问题1', '回复信息1', '2022-03-07 15:48:13', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `kecheng_liuyan` VALUES (2, 2, 2, '问题2', '回复信息2', '2022-03-07 15:48:13', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `kecheng_liuyan` VALUES (3, 3, 1, '问题3', '回复信息3', '2022-03-07 15:48:13', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `kecheng_liuyan` VALUES (4, 4, 1, '问题4', '回复信息4', '2022-03-07 15:48:13', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `kecheng_liuyan` VALUES (5, 5, 3, '问题5', '回复信息5', '2022-03-07 15:48:13', '2022-03-07 15:48:13', '2022-03-07 15:48:13');
INSERT INTO `kecheng_liuyan` VALUES (6, 4, 1, '问题6', NULL, '2022-03-07 16:29:28', NULL, '2022-03-07 16:29:28');
INSERT INTO `kecheng_liuyan` VALUES (7, 5, 2, '我觉得此事很好', NULL, '2023-05-15 16:06:17', NULL, '2023-05-15 16:06:17');
INSERT INTO `kecheng_liuyan` VALUES (8, 5, 2, '很好很好', '真的很好', '2023-05-15 17:59:36', '2023-05-15 18:15:06', '2023-05-15 17:59:36');
INSERT INTO `kecheng_liuyan` VALUES (9, 3, 2, '您好老师我有点不太懂，编译原理NFA转化为DFA的子集法是怎么一回事', '其实很简单，只需要这样，在那样，最后再这样', '2023-05-29 15:54:50', '2023-05-29 18:07:07', '2023-05-29 15:54:50');
INSERT INTO `kecheng_liuyan` VALUES (10, 5, 4, '你好你好我叫汪欣琦', '', '2023-05-29 22:15:22', NULL, '2023-05-29 22:15:22');
INSERT INTO `kecheng_liuyan` VALUES (11, 2, 2, '这个问题我不懂', '我哦也不懂', '2023-05-30 08:39:42', '2023-05-30 08:40:45', '2023-05-30 08:39:42');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `news_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告标题  Search111 ',
  `news_types` int(11) NULL DEFAULT NULL COMMENT '公告类型  Search111 ',
  `news_photo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告图片',
  `insert_time` timestamp(0) NULL DEFAULT NULL COMMENT '公告时间',
  `news_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公告详情',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间 show1 show2 nameShow',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '公告标题1', 3, 'http://localhost:8080/zhinengxuexipingtai/upload/news1.jpg', '2022-03-07 15:48:13', '公告详情1', '2022-03-07 15:48:13');
INSERT INTO `news` VALUES (2, '公告标题2', 3, 'http://localhost:8080/zhinengxuexipingtai/upload/news2.jpg', '2022-03-07 15:48:13', '公告详情2', '2022-03-07 15:48:13');
INSERT INTO `news` VALUES (3, '公告标题3', 1, 'http://localhost:8080/zhinengxuexipingtai/upload/news3.jpg', '2022-03-07 15:48:13', '公告详情3', '2022-03-07 15:48:13');
INSERT INTO `news` VALUES (4, '公告标题4', 3, 'http://localhost:8080/zhinengxuexipingtai/upload/news4.jpg', '2022-03-07 15:48:13', '公告详情4', '2022-03-07 15:48:13');
INSERT INTO `news` VALUES (5, '公告标题5', 1, 'http://localhost:8080/zhinengxuexipingtai/upload/news5.jpg', '2022-03-07 15:48:13', '公告详情5', '2022-03-07 15:48:13');

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `tablename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  `expiratedtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'token表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES (1, 1, 'a1', 'yonghu', '学生', 'c912610lw5hxuac3fylatjfsiwgm65qo', '2022-03-07 16:02:12', '2023-05-25 11:04:25');
INSERT INTO `token` VALUES (2, 1, 'admin', 'users', '管理员', 'odbswomcy1bxi1ph6lm98xkbw6vs8ckg', '2022-03-07 16:04:22', '2023-05-15 16:55:18');
INSERT INTO `token` VALUES (3, 1, 'a1', 'jiaoshi', '教师', 'lvxy4y13mlkwc38o7wu2w0njpuqp4h8u', '2022-03-07 16:32:26', '2023-06-02 12:44:32');
INSERT INTO `token` VALUES (4, 3, 'a3', 'yonghu', '学生', 'ik59aw6eoauyptr0jverg40n9yduuz5m', '2023-05-06 15:04:46', '2023-06-03 21:56:09');
INSERT INTO `token` VALUES (5, 5, 'a5', 'jiaoshi', '教师', 'oov3b61h2bldbwdlm8kglpvitx90ltf0', '2023-05-06 16:45:27', '2023-06-02 12:47:13');
INSERT INTO `token` VALUES (6, 2, 'a2', 'jiaoshi', '教师', 'odpo3qkj1n0cushcdnw2zgq4d7sc01ne', '2023-05-08 00:46:48', '2023-06-02 12:44:08');
INSERT INTO `token` VALUES (7, 2, 'a2', 'yonghu', '学生', 'glhyvq6wcjcepyiuaa2a7kdk2dazlcf2', '2023-05-08 12:20:53', '2023-06-03 21:59:56');
INSERT INTO `token` VALUES (8, 3, 'a3', 'jiaoshi', '教师', '666heqa5h1nrnyu8shez9gblr3rtlfoo', '2023-05-18 22:10:33', '2023-06-02 13:29:33');
INSERT INTO `token` VALUES (9, 6, '王明', 'jiaoshi', '教师', 'i1z0x5vzhe4wxt2vywu0tw1g61q6lekw', '2023-05-28 21:53:17', '2023-05-28 22:53:18');
INSERT INTO `token` VALUES (10, 4, 'wxq', 'yonghu', '学生', '1smhyja5mcl7fkru8enkqq4r4mjc1kdo', '2023-05-29 22:14:01', '2023-05-29 23:14:02');
INSERT INTO `token` VALUES (11, 5, '3175874297', 'yonghu', '学生', '2dlab0zsy60nrys1s2lgv7ec93g32al9', '2023-06-02 11:19:42', '2023-06-04 01:14:25');
INSERT INTO `token` VALUES (12, 7, 'huahangwansui', 'jiaoshi', '教师', '65u9m72z0bd1tiasbhpt7po39oszn6cl', '2023-06-02 11:26:31', '2023-06-04 19:29:44');
INSERT INTO `token` VALUES (13, 4, 'a4', 'jiaoshi', '教师', '37awxnklscnbdjh8217j3iodizodfz6r', '2023-06-02 11:46:09', '2023-06-02 12:46:34');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'admin', '管理员', '2022-05-01 00:00:00');

-- ----------------------------
-- Table structure for yonghu
-- ----------------------------
DROP TABLE IF EXISTS `yonghu`;
CREATE TABLE `yonghu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `yonghu_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生姓名 Search111 ',
  `yonghu_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex_types` int(11) NULL DEFAULT NULL COMMENT '性别 Search111 ',
  `yonghu_phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `yonghu_id_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生身份证号 ',
  `yonghu_email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `yonghu_delete` int(11) NULL DEFAULT 1 COMMENT '假删',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yonghu
-- ----------------------------
INSERT INTO `yonghu` VALUES (1, 'a1', '123456', '学生姓名1', 'http://localhost:8080/zhinengxuexipingtai/upload/yonghu1.jpg', 2, '17703786901', '410224199610232001', '1@qq.com', 1, '2022-03-07 15:48:13');
INSERT INTO `yonghu` VALUES (2, 'a2', '1234567', '学生姓名2', 'http://localhost:8080/zhinengxuexipingtai/upload/yonghu2.jpg', 1, '17703786902', '410224199610232002', '3145874297@qq.com', 1, '2022-03-07 15:48:13');
INSERT INTO `yonghu` VALUES (3, '3175874297zx', '123456', '学生姓名3', 'http://localhost:8080/zhinengxuexipingtai/upload/yonghu3.jpg', 1, '17703786903', '410224199610232003', '3@qq.com', 1, '2022-03-07 15:48:13');
INSERT INTO `yonghu` VALUES (4, 'wxq', 'wxqazx', '汪欣琦', NULL, NULL, '17731680175', '123456789789456321', '2370134675@qq.com', 1, '2023-05-29 22:13:52');
INSERT INTO `yonghu` VALUES (5, '3175874297', 'zxawxq1314', '张旭', NULL, NULL, '18713486529', '12345678911235468X', '3175874297@qq.com', 1, '2023-06-02 11:19:35');

SET FOREIGN_KEY_CHECKS = 1;
