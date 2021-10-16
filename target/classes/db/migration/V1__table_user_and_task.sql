create table user_info
(
    id bigint auto_increment,
    qq_number bigint not null comment 'qq号',
    role int default 0 not null comment '权限',
    nickname varchar(30) default '小主' not null comment '希望被机器人喊得昵称，默认是小主',
    gmt_create varchar(20) not null,
    gmt_modified varchar(20) not null,
    constraint user_pk
        primary key (id)
)
    comment '用户表';

create table group_info
(
    id bigint auto_increment,
    group_number bigint not null comment 'qq群号',
    group_name varchar(30) not null comment '群名称',
    gmt_create varchar(20) not null,
    gmt_modified varchar(20) not null,
    constraint group_pk
        primary key (id)
)
    comment 'QQ群表';

create table task
(
    id bigint auto_increment,
    creator_id bigint not null comment '创建者id，个人任务是user_id,群任务是group_id',
    remind_str varchar(50) not null comment '提醒内容',
    remind_date varchar(20) not null comment '任务运行日期',
    remind_time varchar(20) not null comment '任务运行时间',
    state int default 0 not null comment '任务状态：0-正常执行、1-已取消',
    p_or_g int default 0 not null comment '个人任务还是群任务：0-个人任务、1-群任务',
    type int default 0 not null comment '任务类型：0-一次性任务、1-每日任务、2-工作日任务、3-周末任务、4-每周自定义任务',
    gmt_create varchar(20) not null,
    gmt_modified varchar(20) not null,
    constraint task_pk
        primary key (id)
)
    comment '任务表';
