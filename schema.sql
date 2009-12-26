BEGIN;

DROP TABLE "pass-code";
DROP TABLE user_settings;
DROP TABLE time_modified_log;
DROP TABLE time_log;
DROP TABLE employees;
DROP TABLE departments;
DROP TABLE classifications;
DROP TABLE actions;
DROP TABLE pay_types;

CREATE TABLE departments (
	id SERIAL PRIMARY KEY,
	department VARCHAR NOT NULL
);

INSERT INTO departments (department) VALUES ('Admin');
INSERT INTO departments (department) VALUES ('Auto');
INSERT INTO departments (department) VALUES ('Billing');
INSERT INTO departments (department) VALUES ('Home');
INSERT INTO departments (department) VALUES ('IT');
INSERT INTO departments (department) VALUES ('Recruiters');

CREATE TABLE pay_types (
	id SERIAL PRIMARY KEY,
	"type" VARCHAR NOT NULL,
	UNIQUE ("type")
);
INSERT INTO pay_types VALUES (1, 'hourly');
INSERT INTO pay_types VALUES (2, 'salary');
INSERT INTO pay_types VALUES (3, 'commission');
INSERT INTO pay_types VALUES (4, 'unknown');

CREATE TABLE classifications (
	id SERIAL PRIMARY KEY,
	classification VARCHAR NOT NULL,
	pay_type INTEGER REFERENCES pay_types(id) NOT NULL
);

INSERT INTO classifications VALUES (1, 'full-time', 2);
INSERT INTO classifications VALUES (2, 'full-time', 1);
INSERT INTO classifications VALUES (3, 'part-time', 1);
INSERT INTO classifications VALUES (4, 'temporary', 1);
INSERT INTO classifications VALUES (5, 'temporary', 2);
INSERT INTO classifications VALUES (6, 'future', 4);

CREATE TABLE actions (
	id SERIAL PRIMARY KEY,
	action VARCHAR NOT NULL,
	UNIQUE (action)
);

INSERT INTO actions VALUES (1, 'in');
INSERT INTO actions VALUES (2, 'out');
INSERT INTO actions VALUES (3, 'break');

CREATE TABLE employees (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR NOT NULL,
	middle_initial CHAR(1),
	last_name VARCHAR NOT NULL,
	department_id INTEGER REFERENCES departments (id) NOT NULL,
	is_manager BOOLEAN DEFAULT FALSE NOT NULL
);
INSERT INTO employees VALUES ('1', 'Scott', '', 'Noel-Hemming', 5, 'f');

CREATE TABLE time_log (
	id SERIAL PRIMARY KEY,
	employee_id INTEGER REFERENCES employees (id) NOT NULL,
	date_time TIMESTAMP WITH time zone NOT NULL,
	action_type INTEGER REFERENCES actions (id),
	comment VARCHAR
);

CREATE TABLE time_modified_log (
	time_log_id INTEGER REFERENCES time_log (id) NOT NULL,
	modified_by INTEGER REFERENCES employees (id) NOT NULL,
	comment VARCHAR NOT NULL
);

CREATE TABLE "pass-code" (
	id SERIAL PRIMARY KEY,
	code VARCHAR NOT NULL,
	employee_id INTEGER REFERENCES employees (id),
	UNIQUE (employee_id),
	UNIQUE (code)
);
INSERT INTO "pass-code" VALUES (1, md5('a'), 1);

CREATE TABLE user_settings (
	id SERIAL PRIMARY KEY,
--	name_ordering VARCHAR DEFAULT 'last, first' NOT NULL,
	ntp_server VARCHAR DEFAULT '1.us.pool.ntp.org' NOT NULL,
	time_format VARCHAR DEFAULT 'minutes' NOT NULL,
	employee_id INTEGER REFERENCES employees (id)--,
--	show_summary BOOLEAN DEFAULT FALSE NOT NULL
);

--ROLLBACK;
COMMIT;
