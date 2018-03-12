CREATE TABLE departments
(
  department_id INT AUTO_INCREMENT PRIMARY KEY,
  department_name VARCHAR(32) NOT NULL
);
CREATE UNIQUE INDEX departments_department_name_uindex ON departments (department_name);

CREATE TABLE employees
(
  employee_id INT AUTO_INCREMENT PRIMARY KEY,
  employee_name VARCHAR(32) NOT NULL,
  employee_surname VARCHAR(32) NOT NULL,
  employee_dob DATE NOT NULL,
  employee_salary FLOAT NOT NULL,
  employee_email VARCHAR(32) NOT NULL,
  department_id INT,
  CONSTRAINT employees_departments_department_id_fk FOREIGN KEY (department_id) REFERENCES departments (department_id)
);
CREATE UNIQUE INDEX employees_employee_email_uindex ON employees (employee_email);


INSERT INTO departments VALUES (1, 'Sales');
INSERT INTO departments VALUES (2, 'Programmers');
INSERT INTO departments VALUES (3, 'Managers');

INSERT INTO employees VALUES (1, 'Gleb', 'Bruner', '1996-1-1', 23000.0, 'glebbruner@gmail.com', 1);
INSERT INTO employees VALUES (2, 'Vasya', 'Pupkin', '1986-1-2', 89000.0, 'vasyapupkin@gmail.com', 2);
INSERT INTO employees VALUES (3, 'Ivan', 'Ivanov', '1999-1-3', 13000.0, 'ivanivanov@gmail.com', 2);

