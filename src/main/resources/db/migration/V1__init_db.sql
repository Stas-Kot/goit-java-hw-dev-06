CREATE TABLE worker(
	id IDENTITY PRIMARY KEY,
	name VARCHAR(1000) CONSTRAINT worker_name_length CHECK (LENGTH(name)>=2) NOT NULL,
	birthday DATE CONSTRAINT year_values CHECK (birthday>='1901-01-01'),
	level enum('Trainee', 'Junior', 'Middle', 'Senior') NOT NULL,
	salary INTEGER CONSTRAINT salary_values CHECK (salary<=100000 AND salary>=100)
);



CREATE TABLE client(
	id IDENTITY PRIMARY KEY,
	name VARCHAR(1000) CONSTRAINT client_name_length CHECK (LENGTH(name)>=2) NOT NULL
);



CREATE TABLE project(
	id IDENTITY PRIMARY KEY,
	client_id BIGINT NOT NULL,
	start_date DATE,
	finish_date DATE
);

ALTER TABLE project
ADD CONSTRAINT IF NOT EXISTS client_id_fk
FOREIGN KEY(client_id)
REFERENCES client(id);



CREATE TABLE project_worker(
	project_id BIGINT NOT NULL,
	worker_id BIGINT NOT NULL,
	PRIMARY KEY (project_id, worker_id),
	FOREIGN KEY (project_id) REFERENCES project(id),
	FOREIGN KEY (worker_id) REFERENCES worker(id)
);