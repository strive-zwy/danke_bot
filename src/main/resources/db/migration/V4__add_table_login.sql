create table login
(
    id bigint auto_increment,
    nickname varchar(50) not null comment '用户登录昵称',
    location varchar(30) null comment '用户登录后获取的用户地址',
    avatar varchar(100) not null comment '用户头像',
    token varchar(100) not null comment '用户登录获取的token',
    can_add_qq_num int not null default 5 comment '当前还能加几个QQ',
    can_add_group_num int not null default 2 comment '当前还能加几个QQ群',
    role int not null default 0 comment '用户权限',
    api_key varchar(50) default 'NO_APPLY' not null comment '用户登录后申请的API Key',
    constraint login_pk
        primary key (id)
)
    comment '用户登录表';

alter table user_info drop column role;

alter table user_info
    add adder bigint not null comment '添加者的用户id （login_id）';
alter table user_info
    add description varchar(50) null comment 'QQ描述';


alter table group_info
    add adder bigint not null comment '添加者的用户id （login_id）';

