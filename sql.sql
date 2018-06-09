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

create table sharedNotes
(
	id int auto_increment primary key,
	user_id int not null,
	note_id int not null,
	constraint sharedNotes_user_id_fk
		foreign key (user_id) references user (id)
			on update cascade on delete cascade,
	constraint sharedNotes_note_id_fk
		foreign key (note_id) references note (id)
			on update cascade on delete cascade
)
engine=InnoDB
;

create index sharedNotes_note_id_fk
	on sharedNotes (note_id)
;

create index sharedNotes_user_id_fk
	on sharedNotes (user_id)
;


INSERT INTO keep.user
(id, username, password, name, status, role)
VALUES (1, 'test@mail.com', '1122', 'Test', 'active', 'admin');

INSERT INTO keep.note
(note, user_id, createdDate, title)
VALUES ('test text', 1, '2018-05-19:12:12:12', 'Test');

INSERT INTO keep.sharedNotes
(id, user_id, note_id) VALUES (1, 1, 1);
