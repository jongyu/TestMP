drop table if exists `user`;
create table user
(
    `id`       bigint      not null comment '用户编号',
    `username` varchar(16) not null comment '用户名',
    `password` varchar(64) not null comment '密码',
    primary key (`id`),
    unique key `username` (`username`)
);

drop table if exists `role`;
create table `role`
(
    `id`          int(11)     not null comment '角色编号',
    `name`        varchar(16) not null comment '角色名称',
    `description` varchar(16) default null comment '角色描述',
    primary key (`id`)
) comment '角色表';

create table `user_role`
(
    `user_id` bigint(20) not null comment '用户编号',
    `role_id` int(11)    not null comment '角色编号',
    key `user_id` (`user_id`),
    key `role_id` (`role_id`),
    constraint `user_role_fk_user_id` foreign key (`user_id`) references `user` (`id`) on delete cascade on update restrict,
    constraint `user_role_fk_role_id` foreign key (`role_id`) references `role` (`id`) on delete cascade on update restrict
) comment '用户角色表';

insert into `role`
values (1, 'ROLE_ADMIN', '超级管理员'),
       (2, 'ROLE_USER', '普通用户');

insert into `user`
values (1, 'admin', '$2a$10$toUSqtLOQiz1zg/6MLQT2ukwwUyA5rqgABSjnA3Xi/uhd3khpGsie');

insert into `user_role`
values (1, 1),
       (1, 2);
