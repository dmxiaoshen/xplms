drop table if exists user;

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null auto_increment comment '用户id',
   username             varchar(50) comment '用户名',
   password             varchar(50) comment '密码',
   status               tinyint(3) default 0 comment '状态 0 使用 1 停用',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table user comment '用户表';
