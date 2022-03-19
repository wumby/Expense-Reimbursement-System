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
	REIMB_SUBMITTED TIMESTAMPTZ NOT NULL,
	REIMB_DESCRIPTION VARCHAR(250),
	REIMB_RECEIPT text NOT NULL,
	REIMB_AUTHOR INTEGER NOT NULL,
	REIMB_RESOLVER INTEGER NOT NULL,
	REIMB_STATUS_ID INTEGER default 202,
	REIMB_TYPE_ID INTEGER NOT NULL,
	
	CONSTRAINT fk_REIMB_AUTHOR foreign key(REIMB_AUTHOR) references users(USERS_ID) on delete CASCADE,
	constraint fk_REIMB_RESOLVER foreign key(REIMB_RESOLVER) references users(USERS_ID) on delete cascade,
	CONSTRAINT fk_REIMB_STATUS foreign key(REIMB_STATUS_ID) references reimbursement_status(REIMB_STATUS_ID) on delete cascade,
	CONSTRAINT fk_REIMB_TYPE_ID foreign key(REIMB_TYPE_ID) references reimbursement_type(REIMB_TYPE_ID) on delete cascade

);



insert into reimbursement_status (REIMB_STATUS_ID, REIMB_STATUS)
values
(101, 'Approved'),
(202, 'Pending'),
(303, 'Rejected');

insert into reimbursement_type (REIMB_TYPE_ID, REIMB_TYPE)
values
(10, 'Lodging'),
(20, 'Travel'),
(30, 'Food'),
(40, 'Other');

insert into user_roles (user_role_id, user_role)
values
(100, 'Management'),
(200, 'Finance'),
(300, 'HR'),
(400, 'IT'),
(500, 'Marketing'),
(600, 'Sales'),
(700, 'Quality Assurance');

INSERT INTO users (username, user_password, first_name, last_name, user_email, user_role_id)
values
('CatMom1', 'ilovemycats!', 'Angela', 'Martin', 'angela_martin1@dundermifflen.net', 200),




insert into reimbursements  (REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION, REIMB_RECEIPT, REIMB_AUTHOR, REIMB_RESOLVER, REIMB_TYPE_ID)
VALUES
(250, '2022-03-18 13:49:51.873 -0600', 'Airfare for Company Conference','https://storage.googleapis.com/reimb-receipt-images/sample_receipt_1.png',7, 8 , 20);



SELECT *
FROM reimbursements 
