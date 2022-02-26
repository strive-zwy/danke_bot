create table keyword
(
    id bigint auto_increment,
    group_id bigint not null comment '群id',
    kw varchar(30) not null comment '关键词',
    state int not null default 1 comment '状态，1：正常监控 0：暂停监控',
    gmt_create varchar(20) not null,
    gmt_modified varchar(20) not null,
    constraint keyword_pk
        primary key (id)
)
    comment '群内禁止发送的关键词';

