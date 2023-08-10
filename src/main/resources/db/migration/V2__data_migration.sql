INSERT INTO project(title, description) VALUES ('project 1', 'project 1 desc');
INSERT INTO task(title, description, project_id) VALUES ('task 1', 'task 1 desc', 1);
INSERT INTO task(title, description, project_id) VALUES ('task 2', 'task 2 desc', 1);
INSERT INTO employee("name") VALUES ('Artem Yunin');
INSERT INTO employee("name") VALUES ('Jason Statham');
INSERT INTO employee_task(task_id, employee_id) VALUES (1, 1);
INSERT INTO employee_task(task_id, employee_id) VALUES (1, 2);
INSERT INTO employee_task(task_id, employee_id) VALUES (2, 2);