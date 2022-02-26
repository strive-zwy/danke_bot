alter table group_info
    add isMonitor int default 0 not null comment '是否开启监控，默认0未开启，1开启' after group_name;

alter table group_info modify gmt_modified varchar(20) not null after isMonitor;

