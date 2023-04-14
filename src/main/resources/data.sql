INSERT INTO UNITS (name) VALUES ('C&CA'),('Java Development'),('Software engineering'),('Sales'),('Customer Service');

INSERT INTO ENUMERATIONLEVELS (code, description)
VALUES ('A3', 'Young Professional'),('A4', 'Consultant'),('A5', 'Consultant'),('B1', 'Consultant'),
('B2', 'Senior Consultant'),('C1', 'Senior Consultant'),('C2', 'Manager'),('D1', 'Manager'),('D2', 'Senior Manager'),
('E1', 'Senior Manager'),('E2', 'Director'),('F1', 'Vice President'),('F2', 'Senior Vice President');

INSERT INTO EMPLOYEES (status, name, surname, date_of_birth, start_date_contract, unit_id, enumerationlevels_id)
VALUES (0, 'Yannick', 'Mussche', PARSEDATETIME('02/09/1992','dd/MM/yyyy'), PARSEDATETIME('06/03/2023','dd/MM/yyyy'),
(SELECT ID FROM units WHERE name = 'C&CA' LIMIT 1),(SELECT ID FROM enumerationlevels WHERE code ='A4' LIMIT 1)),
(0, 'Peter', 'Van Petegehem', PARSEDATETIME('01/08/1995','dd/MM/yyyy'), PARSEDATETIME('14/04/2023','dd/MM/yyyy'),
(SELECT id FROM units WHERE name = 'C&CA' LIMIT 1),(SELECT ID FROM enumerationlevels WHERE code ='A3' LIMIT 1)),
(0, 'Dirk', 'Maes', PARSEDATETIME('16/05/1990','dd/MM/yyyy'), PARSEDATETIME('01/02/2015','dd/MM/yyyy'),
(SELECT id FROM units WHERE name = 'C&CA' LIMIT 1),(SELECT ID FROM enumerationlevels WHERE code ='C1' LIMIT 1)),
(0, 'Jessica', 'Schrijvers', PARSEDATETIME('04/07/1993','dd/MM/yyyy'), PARSEDATETIME('11/03/2016','dd/MM/yyyy'),
(SELECT id FROM units WHERE name = 'C&CA' LIMIT 1),(SELECT ID FROM enumerationlevels WHERE code ='C1' LIMIT 1)),
(0, 'Brecht', 'Opdonders', PARSEDATETIME('17/10/1987','dd/MM/yyyy'), PARSEDATETIME('01/02/2017','dd/MM/yyyy'),
(SELECT id FROM units WHERE name = 'Sales' LIMIT 1),(SELECT ID FROM enumerationlevels WHERE code ='E2' LIMIT 1));