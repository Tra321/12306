-- ----------------------------
-- 数据库: 12306-ticket
-- ----------------------------
CREATE DATABASE IF NOT EXISTS `12306-ticket` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `12306-ticket`;

-- ----------------------------
-- 表结构: t_station (车站表)
-- ----------------------------
DROP TABLE IF EXISTS `t_station`;
CREATE TABLE `t_station` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '车站名称',
  `code` varchar(255) DEFAULT NULL COMMENT '车站编码',
  `pinyin` varchar(255) DEFAULT NULL COMMENT '拼音',
  `initial` char(1) DEFAULT NULL COMMENT '首字母',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车站表';

-- ----------------------------
-- 基础数据: t_station
-- ----------------------------
INSERT INTO `t_station` (`name`, `code`, `pinyin`, `initial`) VALUES
('北京', 'BJP', 'beijing', 'b'),
('上海', 'SHH', 'shanghai', 's'),
('广州', 'GZQ', 'guangzhou', 'g'),
('深圳', 'SZQ', 'shenzhen', 's'),
('成都', 'CDW', 'chengdu', 'c'),
('重庆', 'CQW', 'chongqing', 'c'),
('杭州', 'HZH', 'hangzhou', 'h'),
('南京', 'NJH', 'nanjing', 'n'),
('武汉', 'WCN', 'wuhan', 'w'),
('西安', 'XAY', 'xian', 'x');

-- ----------------------------
-- 表结构: t_train (列车表)
-- ----------------------------
DROP TABLE IF EXISTS `t_train`;
CREATE TABLE `t_train` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `train_number` varchar(255) DEFAULT NULL COMMENT '车次',
  `train_type` int(11) DEFAULT NULL COMMENT '列车类型 0:高铁 1:动车 2:普通',
  `start_station` varchar(255) DEFAULT NULL COMMENT '始发站',
  `end_station` varchar(255) DEFAULT NULL COMMENT '终点站',
  `departure_time` time DEFAULT NULL COMMENT '出发时间',
  `arrival_time` time DEFAULT NULL COMMENT '到达时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique_train_number` (`train_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='列车信息表';

-- ----------------------------
-- 基础数据: t_train
-- ----------------------------
INSERT INTO `t_train` (`train_number`, `train_type`, `start_station`, `end_station`, `departure_time`, `arrival_time`) VALUES
('G1', 0, '北京', '上海', '09:00:00', '13:48:00'),
('G2', 0, '上海', '北京', '09:00:00', '13:48:00'),
('G10', 0, '北京', '广州', '08:00:00', '17:59:00'),
('D311', 1, '北京', '上海', '19:14:00', '07:18:00'),
('K105', 2, '北京', '重庆', '22:40:00', '04:30:00');
