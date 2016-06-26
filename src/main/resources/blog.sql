use blog;

drop table if exists `user`;
create table user
(
	id char(36),
    username varchar(8),
    email varchar(64) unique,
    password varchar(16),
    phone char(11) unique,
    avator varchar(256),
    is_admin boolean default false,
    deleted boolean default false,
    primary key (id),
    foreign key (role_id) references role(id)
);

drop table if exists `tag`;
create table tag
(
	id char(36) NOT NULL,
    name varchar(16),
    deleted boolean default false,
    primary key (id)
);

drop table if exists `topic`;
create table topic
(
	id char(36) NOT NULL,
    title varchar(24),
    content text,
    create_date datetime,
    update_date datetime,
    author_id char(36),
    tag_id char(36),
    reply_count int default 0,
    visit_count int default 0,
    good boolean,
    last_reply_date datetime,
    last_reply_user char(36),
    deleted boolean default false,
    primary key (id),
    foreign key (author_id) references user(id),
    foreign key (tag_id) references tag(id),
    foreign key (last_reply_user) references user(id)
);

drop table if exists `reply`;
create table reply
(
	id char(36) NOT NULL,
    content varchar(1024),
    author_id char(36),
    topic_id char(36),
    create_date datetime,
    update_date datetime,
    deleted boolean,
    primary key (id),
    foreign key (topic_id) references topic(id),
    foreign key (author_id) references user(id)
);

INSERT INTO tag VALUE(uuid(), "分享", false);
INSERT INTO tag VALUE(uuid(), "问答", false);
INSERT INTO tag VALUE(uuid(), "招聘", false);
