DROP TABLE IF EXISTS reimbursements;
DROP table if EXISTS users;
DROP TABLE IF EXISTS reimbursement_status;
DROP TABLE IF EXISTS reimbursement_type;
DROP TABLE IF EXISTS user_roles;




CREATE TABLE user_roles (
	USER_ROLE_ID INTEGER PRIMARY KEY,
	USER_ROLE VARCHAR(50) NOT NULL
	
);


CREATE TABLE users (
	USERS_ID SERIAL PRIMARY KEY,
	USERNAME VARCHAR(50) NOT NULL,
	USER_PASSWORD VARCHAR(50) NOT null,
	FIRST_NAME VARCHAR(100) NOT null,
	LAST_NAME VARCHAR(100) NOT null,
	USER_EMAIL VARCHAR(100) NOT null,
	USER_ROLE_ID INTEGER NOT null,
	UNIQUE(USERNAME, USER_EMAIL),
	
	constraint fk_USER_ROLE_ID foreign key(USER_ROLE_ID) REFERENCES user_roles(USER_ROLE_ID) on delete cascade
	
);

CREATE TABLE reimbursement_status (
	REIMB_STATUS_ID INTEGER PRIMARY KEY,
	REIMB_STATUS VARCHAR(10) NOT null

);

CREATE TABLE reimbursement_type (
	REIMB_TYPE_ID INTEGER PRIMARY KEY,
	REIMB_TYPE VARCHAR(10) NOT NULL,
	
	CHECK (REIMB_TYPE IN('Lodging', 'Travel', 'Food', 'Other'))

);


CREATE TABLE reimbursements (
	REIMB_ID SERIAL PRIMARY KEY,
	REIMB_AMOUNT INTEGER NOT NULL,
	REIMB_SUBMITTED VARCHAR(250) NOT NULL,
	REIMB_RESOLVED VARCHAR(250) ,
	REIMB_DESCRIPTION VARCHAR(250),
	REIMB_RECEIPT text,
	REIMB_AUTHOR INTEGER NOT NULL,
	REIMB_RESOLVER INTEGER,
	REIMB_STATUS_ID INTEGER default 2,
	REIMB_TYPE_ID INTEGER NOT NULL,
	
	CONSTRAINT fk_REIMB_AUTHOR foreign key(REIMB_AUTHOR) references users(USERS_ID) on delete CASCADE,
	constraint fk_REIMB_RESOLVER foreign key(REIMB_RESOLVER) references users(USERS_ID) on delete cascade,
	CONSTRAINT fk_REIMB_STATUS foreign key(REIMB_STATUS_ID) references reimbursement_status(REIMB_STATUS_ID) on delete cascade,
	CONSTRAINT fk_REIMB_TYPE_ID foreign key(REIMB_TYPE_ID) references reimbursement_type(REIMB_TYPE_ID) on delete cascade

);



insert into reimbursement_status (REIMB_STATUS_ID, REIMB_STATUS)
values
(1, 'Approved'),
(2, 'Pending'),
(3, 'Rejected');

insert into reimbursement_type (REIMB_TYPE_ID, REIMB_TYPE)
values
(1, 'Lodging'),
(2, 'Travel'),
(3, 'Food'),
(4, 'Other');

insert into user_roles (user_role_id, user_role)
values
(1, 'Employee'),
(2, 'Manager');

INSERT INTO users (username, user_password, first_name, last_name, user_email, user_role_id)
values
('wumby', 'password', 'Jack', 'Ziegler', 'jack@gmail.com', 1),
('bach_tran', 'password', 'Bach', 'Tran','bach@gmail.com', 2);




insert into reimbursements  (REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION, REIMB_AUTHOR,REIMB_STATUS_ID, REIMB_TYPE_ID)
VALUES
(250, '01-01-2022 5:30', 'bus ticket',1,2, 2),
(2530, '01-01-2022 5:30', 'burger',1,2, 3),
(2540, '01-01-2022 5:30', 'strip club',1,2, 4);


SELECT u.users_id, u.username, u.user_password, u.first_name,u.last_name, u.user_email, ur.user_role
FROM users u 
INNER JOIN user_roles ur 
ON u.user_role_id = ur.user_role_id 
WHERE u.username = 'wumby' AND u.user_password = 'password';


SELECT r.reimb_id , r.reimb_amount, r.reimb_submitted,r.reimb_description ,r.reimb_author,rs.reimb_status ,rt.reimb_type ,e.USERS_ID as employee_id, e.username,e.user_password,e.first_name,e.last_name,e.user_email,m.USERS_ID, m.username,m.user_password,m.first_name,m.last_name,m.user_email FROM reimbursements r left join users e on e.USERS_ID = r.reimb_author left join users m on m.USERS_ID = r.reimb_resolver 
left join reimbursement_status rs on rs.reimb_status_id = r.reimb_status_id
left join reimbursement_type rt on rt.reimb_type_id = r.reimb_type_id 




