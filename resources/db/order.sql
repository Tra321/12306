-- ----------------------------
-- 数据库: 12306-order
-- ----------------------------
CREATE DATABASE IF NOT EXISTS `12306-order` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `12306-order`;

-- ----------------------------
-- 表结构: t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_id`        varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '订单号',
    `user_id`         bigint(20) unsigned DEFAULT NULL COMMENT '用户ID',
    `train_number`    varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '车次',
    `departure`       varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '出发地',
    `destination`     varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '目的地',
    `departure_time`  datetime                                 DEFAULT NULL COMMENT '出发时间',
    `passenger_name`  varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '乘客姓名',
    `seat_info`       varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '座位信息',
    `price`           decimal(10,2)                            DEFAULT NULL COMMENT '订单金额',
    `status`          varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '订单状态',
    `deletion_time`   bigint(20) DEFAULT '0' COMMENT '注销时间戳',
    `create_time`     datetime                                 DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime                                 DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `del_flag`        tinyint(1) DEFAULT '0' COMMENT '删除标识',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_id` (`order_id`, `deletion_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` (`order_id`, `user_id`, `train_number`, `departure`, `destination`, `departure_time`, `passenger_name`, `seat_info`, `price`, `status`) VALUES ('ORDER_20250804001', 1, 'G1234', '北京南', '上海虹桥', '2025-08-05 09:00:00', '张三', '商务座 02车 01F', 1748.00, '已支付');
INSERT INTO `t_order` (`order_id`, `user_id`, `train_number`, `departure`, `destination`, `departure_time`, `passenger_name`, `seat_info`, `price`, `status`) VALUES ('ORDER_20250804002', 1, 'D789', '广州南', '深圳北', '2025-08-06 14:30:00', '李四', '二等座 05车 12A', 74.50, '待支付');
