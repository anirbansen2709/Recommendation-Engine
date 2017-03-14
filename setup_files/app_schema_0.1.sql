CREATE DATABASE /*!32312 IF NOT EXISTS*/`snmp` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `snmp`;

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL
);

/*Data for the table `role` */

insert  into `role`(`id`,`description`,`name`) values (1,'Application Administrator','ROLE_ADMIN'),(2,'Super User Has All Rights Except User Modification','ROLE_SUPER'),(3,'Application Administrator','ROLE_USER');

/*Table structure for table `roles_permissions` */

DROP TABLE IF EXISTS `roles_permissions`;

CREATE TABLE `roles_permissions` (
  `role_name` varchar(20) NOT NULL,
  `permission` varchar(10) DEFAULT NULL
);

/*Data for the table `roles_permissions` */

insert  into `roles_permissions`(`role_name`,`permission`) values ('ROLE_ADMIN','admin:*'),('ROLE_SUPER','admin:r'),('ROLE_USER','user:rw');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL DEFAULT '0',
  `username` varchar(20) NOT NULL DEFAULT ''
);

/*Data for the table `user_role` */

insert  into `user_role`(`user_id`,`role_id`,`username`) values (1401112525121,1,'admin');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `username` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(100) DEFAULT NULL,
  `valid` date DEFAULT NULL,
  `activated` tinyint(1) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
);

/*Data for the table `users` */

insert  into `users`(`id`,`first_name`,`last_name`,`username`,`password`,`valid`,`activated`,`email`) values (1401112525121,'Administrator',NULL,'admin','VvxYo/U2Udw=','2015-12-31',1,'abhijit@gammanalytics.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
CREATE TABLE device_map
(
  ip VARCHAR(250),
  mac_add VARCHAR(25) NOT NULL,
  time DATETIME NOT NULL,
  device_id VARCHAR(25) DEFAULT '' NOT NULL,
  hr_device_descr VARCHAR(255),
  hr_device_status VARCHAR(50),
  hr_device_errors VARCHAR(50),
  PRIMARY KEY (mac_add, time, device_id)
);
CREATE TABLE devices
(
  ip VARCHAR(250) NOT NULL,
  community VARCHAR(250) NOT NULL,
  time DATETIME NOT NULL
);
CREATE TABLE interface_map
(
  ip VARCHAR(250),
  mac_add VARCHAR(25) NOT NULL,
  time DATETIME NOT NULL,
  interface_id VARCHAR(255) DEFAULT '' NOT NULL,
  if_descr VARCHAR(255),
  if_type VARCHAR(255),
  if_mtu VARCHAR(255),
  if_speed VARCHAR(255),
  if_phys_address VARCHAR(255),
  if_admin_status VARCHAR(255),
  if_oper_status VARCHAR(255),
  if_last_change VARCHAR(255),
  if_in_octets INT,
  if_out_octets INT,
  PRIMARY KEY (mac_add, time, interface_id)
);
CREATE TABLE process_map
(
  ip VARCHAR(250),
  mac_add VARCHAR(25) NOT NULL,
  time DATETIME NOT NULL,
  process_id VARCHAR(25) DEFAULT '' NOT NULL,
  name VARCHAR(255),
  run_path VARCHAR(255),
  run_parameters VARCHAR(255),
  run_type VARCHAR(255),
  run_status VARCHAR(255),
  cpu_performance VARCHAR(255),
  memory VARCHAR(255),
  PRIMARY KEY (mac_add, time, process_id)
);
CREATE TABLE storage_map
(
  ip VARCHAR(250),
  mac_add VARCHAR(25) NOT NULL,
  time DATETIME NOT NULL,
  storage_id VARCHAR(25) DEFAULT '' NOT NULL,
  type VARCHAR(5),
  description VARCHAR(255),
  allocation_units VARCHAR(50),
  size VARCHAR(50),
  used VARCHAR(50),
  free VARCHAR(250),
  PRIMARY KEY (mac_add, time, storage_id)
);
CREATE TABLE sw_map
(
  ip VARCHAR(250),
  mac_add VARCHAR(25) NOT NULL,
  time DATETIME NOT NULL,
  sw_id VARCHAR(25) DEFAULT '' NOT NULL,
  sw_installed_name VARCHAR(255),
  sw_installed_date VARCHAR(50),
  PRIMARY KEY (mac_add, time, sw_id)
);
CREATE TABLE system_map
(
  ip VARCHAR(250),
  mac_add VARCHAR(25) NOT NULL,
  time DATETIME NOT NULL,
  ip_out_requests VARCHAR(255),
  hr_system_num_users VARCHAR(255),
  ip_default_TTL VARCHAR(255),
  ip_ad_ent_if_index VARCHAR(255),
  hr_memory_size VARCHAR(255),
  hr_sw_installed_last_change INT,
  ip_ad_ent_net_mask VARCHAR(255),
  hr_system_up_time INT,
  sys_name VARCHAR(255),
  sys_services VARCHAR(255),
  if_number VARCHAR(255),
  sys_up_time_instance INT,
  sys_contact VARCHAR(255),
  ip_out_discards VARCHAR(255),
  sys_descr VARCHAR(255),
  hr_system_processes VARCHAR(255),
  ip_forwarding VARCHAR(255),
  ip_out_no_routes VARCHAR(255),
  sys_objectID VARCHAR(255),
  sys_location VARCHAR(255),
  ip_in_discards VARCHAR(255),
  ip_ad_ent_addr VARCHAR(255),
  hr_printer_status VARCHAR(255),
  ip_in_delivers VARCHAR(255),
  ip_in_receives VARCHAR(255),
  PRIMARY KEY (mac_add, time)
);
CREATE UNIQUE INDEX ip ON devices (ip);


CREATE TABLE notification_param
(
  user_id VARCHAR(50) PRIMARY KEY NOT NULL,
  pendrive_detection_status TINYINT NOT NULL,
  ram_detection_status TINYINT NOT NULL,
  free_space_detection_status TINYINT NOT NULL,
  upload_detection_status TINYINT NOT NULL,
  download_detection_status TINYINT NOT NULL,
  ip_change_detection_status TINYINT NOT NULL,
  process_detection_status TINYINT NOT NULL,
  uptime_detection_status TINYINT NOT NULL,
  ram_limit INT,
  free_space_limit DOUBLE,
  upload_limit DOUBLE,
  download_limit DOUBLE,
  uptime_limit INT,
  process_name VARCHAR(1000),
  email VARCHAR(250) NOT NULL
);