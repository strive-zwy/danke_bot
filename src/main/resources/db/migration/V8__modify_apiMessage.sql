# 修改列名以及数据类型
alter table api_message change sender receiver varchar(20) not null comment '接收QQ';

