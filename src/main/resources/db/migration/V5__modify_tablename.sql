rename table user_info to qq_info;

alter table qq_info
    add avatar varchar(100) null comment '该QQ号的头像';

alter table group_info
    add avatar varchar(100) null comment '该QQ群的头像';

alter table group_info drop column group_name;
