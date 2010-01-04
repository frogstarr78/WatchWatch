\c watch_watch watch_watch

BEGIN;

DELETE FROM time_modified_log WHERE time_log_id IN (SELECT id FROM time_log JOIN employees ON time_log.employee_id = employees.id WHERE employees.first_name = 'Scott' AND employees.last_name = 'Noel-Hemming');
DELETE FROM time_log  WHERE employee_id IN (SELECT id FROM employees WHERE first_name = 'Scott' AND last_name = 'Noel-Hemming');
DELETE FROM "pass-code" WHERE employee_id = (SELECT id FROM employees WHERE first_name = 'Scott' AND last_name = 'Noel-Hemming');
DELETE FROM employees WHERE last_name = 'Noel-Hemming';

--ROLLBACK;
COMMIT;
