/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100137
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 100137
 File Encoding         : 65001

 Date: 06/05/2019 22:48:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_article_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_comment`;
CREATE TABLE `blog_article_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL,
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_article_comment
-- ----------------------------
INSERT INTO `blog_article_comment` VALUES (2, 6, 2, '2019-05-05 19:35:26', '2019-05-05 19:35:26');

-- ----------------------------
-- Table structure for blog_article_content
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_content`;
CREATE TABLE `blog_article_content`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_article_content
-- ----------------------------
INSERT INTO `blog_article_content` VALUES (7, '## 需求\n\n之前碰到一个问题，没经历过还挺麻烦的。现在将解决方法分享出来，希望能帮助到遇到相同问题的人。\n\n实验室有一台HP工作站的机器，有一个公网IP，导师希望布置一些网站，和同学们在任何地方都能通过公网IP连到服务器上，本来很简单的问题，但很坑爹的是通过这个公网IP不能上外网，连什么Mysql,github等都不可以（可以连，但是开通要收巨额流量费，一晚几千那种），于是有了下面的架构。\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190415205357311.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDY5ODA4Mg==,size_16,color_FFFFFF,t_70)\n\n## 问题\n\n需要配置访问者通过公网IP通过**路由器2**，**网卡2**进入服务器，服务器访问网络通过**网卡1**。\n如果访问IP (如：**A**)固定的话有个解决方案，配置路由表，设置去往**A**的流量通过网卡2即可，但是访问服务器的IP不可能固定\n 1. 访问者不一定有公网IP\n 2. 每次访问的IP很有可能是变化的\n\n如过配置访问者通过**路由器1**，**网卡1**进入服务器，行不通，因为**路由器1**不具有公网IP\n\n\n\n## 结论\n配置单一的路由表不能解决问题\n理想的路径如下图\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190415212832862.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDY5ODA4Mg==,size_16,color_FFFFFF,t_70)\n\n## 解决办法\n\n**配置多张路由表**\n>网卡1为 **eth1**,网卡2为**eth0**\n\n  1. 编辑 /etc/iproute2/rt_tables 增加两个public 和 private策略\n  其中public策略表对应网卡2（公网IP），private策略表对应网卡1（无公网IP）\n	![在这里插入图片描述](https://img-blog.csdnimg.cn/20190415213524639.png)\n  3. \n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190415215206341.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDY5ODA4Mg==,size_16,color_FFFFFF,t_70)\n下面是具体配置命令\n\n  		route del -net 0.0.0.0                          删除默认路由策略\n   		route add -net 0.0.0.0 gw 192.168.80.1          增加默认路由网关 即从能访问国际网的路由器走\n   	eth0的配置命令\n   		ip route flush table public                     清空public策略表\n   		ip route add default via 192.168.90.1 dev eth0 table public \n   														public策略表默认网关为外网连进来的路由器\n   		ip rule add from 192.168.90.0/24 table public   从192.168.90.X网段来的流量都从public策略表走，即从90.1网关走\n   		\n    eth1的配置命令 (同上)\n		ip route flush table private\n		ip route add default via 192.168.80.1 dev eth1 table private\n		ip rule add from 192.168.80.0/24 table private\n  \n##  路由展示\n默认路由\n  ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190415215626773.PNG)\n\nip策略表\n\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190415215712828.PNG)\n路由表的内容\n\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190415220218771.PNG)\n\n\n## 后续问题\n安装docker后，发现docker container 里不能访问互联网\n\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190415222630709.PNG)\n\n\n解决办法\n在 private路由表中添加docker路由信息\n![在这里插入图片描述](https://img-blog.csdnimg.cn/201904152227455.PNG)\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20190415222647855.PNG)\n\n## 网卡配置\n  1. centOS版（网桥版，为何是网桥版，请看\n  https://blog.csdn.net/weixin_44698082/article/details/89322728\n \n  在 /etc/sysconfig/network-scripts/下\n有ifcfg-enp3s0 、 ifcfg-enp5s0 两张网卡信息，编辑如下\n\nifcfg-enp3s0\n	\n	TYPE=Ethernet                          类型  以太网\n	#PROXY_METHOD=none\n	#BROWSER_ONLY=no\n	#BOOTPROTO=dhcp\n	#DEFROUTE=yes\n	#NM_CONTROLLED=no\n	BRIDGE=br0                            网桥 br0\n	#USERCTL=no\n	#IPV6INIT=yes\n	#IPV4_FAILURE_FATAL=no\n	#IPV6_AUTOCONF=yes\n	#IPV6_DEFROUTE=yes\n	#IPV6_FAILURE_FATAL=no\n	#IPV6_ADDR_GEN_MODE=stable-privacy\n	NAME=enp3s0                          名称\n	UUID=c05227b6-aac4-462d-b83a-6157d1354410   UUID 唯一\n	DEVICE=enp3s0                          设备\n	ONBOOT=yes                            启动的时候启用该设置\n	#NETMASK=255.255.255.0\n	#GATEWAY=192.168.90.1\n\n\nifcfg-enp5s0 同理 网桥选择br1\n\n创建网桥配置文件 ifcfg-br0  ifcfg-br1\nifcfg-br0\n\n	TYPE=Bridge                       类型 网桥\n	PROXY_METHOD=none				  无代理?\n	BROWSER_ONLY=no					\n	BOOTPROTO=static	              配置ip获取类型 这里设置为静态，也可以在路由器里设置mac绑定\n	DEFROUTE=yes                      定义路由？\n	NM_CONTROLLED=no				  设置不实时生效\n	#BRIDGE=br0\n	USERCTL=no\n	IPV6INIT=yes\n	IPV4_FAILURE_FATAL=no\n	IPV6_AUTOCONF=yes\n	IPV6_DEFROUTE=yes\n	IPV6_FAILURE_FATAL=no\n	IPV6_ADDR_GEN_MODE=stable-privacy\n	NAME=br0							名称\n	#UUID=c05227b6-aac4-462d-b83a-6157d1354410\n	DEVICE=br0                          设备\n	ONBOOT=yes							启动的时候启用该设置\n	IPADDR=192.168.90.2                 静态IP地址\n	NETMASK=255.255.255.0				子网掩码\n	NETWORK=192.168.90.0				网段\n	#GATEWAY=192.168.90.1\n	DNS1=223.6.6.6                      DNS服务器 阿里云的\n	DNS2=223.5.5.5\nifcfg-br1\n\n	TYPE=Bridge\n	PROXY_METHOD=none\n	BROWSER_ONLY=no\n	BOOTPROTO=static\n	DEFROUTE=yes\n	NM_CONTROLLED=no\n	#BRIDGE=br0\n	USERCTL=no\n	IPV6INIT=yes\n	IPV4_FAILURE_FATAL=no\n	IPV6_AUTOCONF=yes\n	IPV6_DEFROUTE=yes\n	IPV6_FAILURE_FATAL=no\n	IPV6_ADDR_GEN_MODE=stable-privacy\n	NAME=br1\n	#UUID=c05227b6-aac4-462d-b83a-6157d1354410\n	DEVICE=br1\n	ONBOOT=yes\n	IPADDR=192.168.80.3\n	NETMASK=255.255.255.0\n	NETWORK=192.168.80.0\n	GATEWAY=192.168.80.1\n	DNS1=223.6.6.6\n	DNS2=223.5.5.5\n~                \n  3. ubuntu版\n	打开ubuntu的/etc/network/interfaces\n> #and how to activate them. For more information, see interfaces(5).\n\n	source /etc/network/interfaces.d/*\n\n> #The loopback network interface\n> \n	auto lo\n	iface lo inet loopback\n\n> #The primary network interface\n> \n	auto eth0\n	iface eth0 inet dhcp             //第一张网卡DHCP  但是因为MAC绑定过了，所以是静态地址\n	auto eth1\n	iface eth1 inet dhcp             //第二张网卡DHCP\n\n## 开机启动\n将解决方法里的命令写到开机脚本 rc.local\n赋予rc.local执行权限');
INSERT INTO `blog_article_content` VALUES (8, '这是测试文档，第二篇博客');

-- ----------------------------
-- Table structure for blog_article_info
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_info`;
CREATE TABLE `blog_article_info`  (
  `id` int(40) NOT NULL AUTO_INCREMENT,
  `title` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `traffic` int(11) NOT NULL DEFAULT 0,
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `is_effective` tinyint(1) NOT NULL DEFAULT 1,
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `content_id` int(40) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_article_info
-- ----------------------------
INSERT INTO `blog_article_info` VALUES (7, 'linux 双路由双网卡公私网访问环境配置', 0, '2019-05-04 23:54:26', '2019-05-06 17:03:45', 1, 'http://pic40.nipic.com/20140413/12524762_163417225134_2.jpg', 7);
INSERT INTO `blog_article_info` VALUES (8, '第二篇博客', 0, '2019-05-06 17:02:47', '2019-05-06 17:02:47', 1, 'http://img0.imgtn.bdimg.com/it/u=3464499095,1074840881&fm=26&gp=0.jpg', 8);

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_effective` tinyint(1) NOT NULL DEFAULT 1,
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_category
-- ----------------------------
INSERT INTO `blog_category` VALUES (1, '2019', 1, '2019-05-03 01:34:56', '2019-05-03 01:34:56');
INSERT INTO `blog_category` VALUES (2, '2019.5', 1, '2019-05-04 16:45:08', '2019-05-04 16:45:08');
INSERT INTO `blog_category` VALUES (3, '20199', 1, '2019-05-04 23:53:45', '2019-05-04 23:53:45');
INSERT INTO `blog_category` VALUES (4, '测试', 1, '2019-05-06 17:02:47', '2019-05-06 17:02:47');

-- ----------------------------
-- Table structure for blog_category_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_category_article`;
CREATE TABLE `blog_category_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `is_effective` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_category_article
-- ----------------------------
INSERT INTO `blog_category_article` VALUES (7, 3, 7, '2019-05-04 23:54:48', '2019-05-04 23:54:48', 1);

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'passenger',
  `email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `reference` int(11) UNSIGNED NOT NULL DEFAULT 0,
  `is_effective` tinyint(1) NOT NULL DEFAULT 1,
  `is_expected` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
INSERT INTO `blog_comment` VALUES (2, '很好2', 'www', 'www@qq.com', 0, 1, 0);

-- ----------------------------
-- Table structure for blog_notify
-- ----------------------------
DROP TABLE IF EXISTS `blog_notify`;
CREATE TABLE `blog_notify`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified_by` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `is_effective` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_notify
-- ----------------------------
INSERT INTO `blog_notify` VALUES (1, '11@qq.com', '2019-05-03 02:19:39', '2019-05-03 02:19:39', 1);

-- ----------------------------
-- Table structure for sys_admin_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_log`;
CREATE TABLE `sys_admin_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `operation_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_admin_log
-- ----------------------------
INSERT INTO `sys_admin_log` VALUES (1, '127.0.0.1', '2019-05-03 01:18:36', 'localhost/admin/article/delete/1');
INSERT INTO `sys_admin_log` VALUES (2, '127.0.0.1', '2019-05-03 01:19:28', 'localhost/admin/article/delete/1');
INSERT INTO `sys_admin_log` VALUES (3, '127.0.0.1', '2019-05-03 01:24:31', 'localhost/admin/article/delete/1');

-- ----------------------------
-- Table structure for sys_visit
-- ----------------------------
DROP TABLE IF EXISTS `sys_visit`;
CREATE TABLE `sys_visit`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_visit
-- ----------------------------
INSERT INTO `sys_visit` VALUES (1, '127.0.0.1', '2019-05-03 00:57:12');
INSERT INTO `sys_visit` VALUES (2, '127.0.0.1', '2019-05-03 01:07:47');
INSERT INTO `sys_visit` VALUES (3, '127.0.0.1', '2019-05-03 01:08:27');
INSERT INTO `sys_visit` VALUES (4, '127.0.0.1', '2019-05-03 01:08:40');
INSERT INTO `sys_visit` VALUES (5, '127.0.0.1', '2019-05-03 01:09:02');

SET FOREIGN_KEY_CHECKS = 1;
