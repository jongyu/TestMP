drop table if exists `element`;
create table `element`
(
    `id`         bigint       not null comment '页面编号',
    `chinese`    varchar(16)  not null comment '元素名称',
    `name`       varchar(64)  not null comment '元素名称',
    `by`         varchar(16)  not null comment '定位方式',
    `value`      varchar(128) not null comment '定位值',
    `created_by` varchar(32) default null comment '创建人',
    `created_at` datetime    default null comment '创建时间',
    `updated_by` varchar(32) default null comment '更新任务',
    `updated_at` datetime    default null comment '更新时间',
    primary key (`id`),
    unique key `chinese` (`chinese`)
);


drop table if exists `page`;
create table `page`
(
    `id`         bigint      not null comment '页面编号',
    `chinese`    varchar(16) not null comment '页面名称',
    `name`       varchar(64) not null comment '页面名称',
    `created_by` varchar(32) default null comment '创建人',
    `created_at` datetime    default null comment '创建时间',
    `updated_by` varchar(32) default null comment '更新任务',
    `updated_at` datetime    default null comment '更新时间',
    primary key (`id`),
    unique key `chinese` (`chinese`)
);
