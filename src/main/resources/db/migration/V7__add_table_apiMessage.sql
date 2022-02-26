create table api_message
(
    id bigint auto_increment,
    sender bigint not null comment '发送QQ',
    msg varchar(300) not null comment '发送消息',
    gmt_create varchar(20) not null,
    constraint api_message_pk
        primary key (id)
);

