drop table if exists article;
drop table if exists article_content;
drop table if exists image;
drop table if exists myshop_member;
drop table if exists order_line;
drop table if exists product;
drop table if exists product_category;
drop table if exists purchase_order;
create table article
(
    id    bigint not null auto_increment,
    title varchar(255),
    primary key (id)
) engine = InnoDB;
create table article_content
(
    content      varchar(255),
    content_type varchar(255),
    id           bigint not null,
    primary key (id)
) engine = InnoDB;
create table image
(
    image_type varchar(31) not null,
    id         bigint      not null auto_increment,
    created_at datetime(6),
    path       varchar(255),
    product_id bigint,
    list_idx   integer,
    primary key (id)
) engine = InnoDB;
create table myshop_member
(
    id        bigint not null auto_increment,
    member_id varchar(255),
    name      varchar(255),
    password  varchar(255),
    primary key (id)
) engine = InnoDB;
create table order_line
(
    order_number bigint  not null,
    amounts      integer,
    price        integer,
    product_id   bigint  not null,
    quantity     integer not null,
    line_idx     integer not null,
    primary key (order_number, line_idx)
) engine = InnoDB;
create table product
(
    id     bigint not null auto_increment,
    detail varchar(255),
    name   varchar(255),
    price  integer,
    primary key (id)
) engine = InnoDB;
create table product_category
(
    product_id   bigint not null,
    category_ids bigint
) engine = InnoDB;
create table purchase_order
(
    id               bigint not null auto_increment,
    order_number     varchar(255),
    orderer_id       varchar(255),
    orderer_name     varchar(255),
    shipping_addr1   varchar(255),
    shipping_addr2   varchar(255),
    shipping_zipcode varchar(255),
    shipping_message varchar(255),
    receiver_name    varchar(255),
    receiver_phone   varchar(255),
    primary key (id)
) engine = InnoDB;
alter table article_content
    add constraint FKijcd9gaxlakp7mubg7f5r7gh1
        foreign key (id)
            references article (id);
alter table image
    add constraint FKgpextbyee3uk9u6o2381m7ft1
        foreign key (product_id)
            references product (id);
alter table order_line
    add constraint FK9cpxmssth2umx2gllhh2qtmt0
        foreign key (order_number)
            references purchase_order (id);
alter table product_category
    add constraint FK2k3smhbruedlcrvu6clued06x
        foreign key (product_id)
            references product (id);
