create table user
(
	id int auto_increment
		primary key,
	username varchar(100) not null,
	password varchar(20) not null,
	name varchar(40) null,
	status enum('active', 'deleted', 'banned') default 'active' null,
	role enum('admin', 'user') default 'user' null,
	constraint user_username_uindex
		unique (username)
)
comment 'users for notes' engine=InnoDB;

CREATE TABLE note
(
    id int(11) PRIMARY KEY AUTO_INCREMENT,
    note longtext,
    user_id int(11) NOT NULL,
    createdDate varchar(25),
    title varchar(200),
    CONSTRAINT note_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);
ALTER TABLE note COMMENT = 'notes of users';

INSERT INTO xkeep3.user
(id, username, password, name, status, role)
VALUES (1, 'igor@lyutak.com', '1122', 'Igor', 'active', 'admin');

INSERT INTO xkeep3.note
(note, user_id, createdDate, title)
VALUES ('test text', 1, '2018-05-19:12:12:12', 'Test');