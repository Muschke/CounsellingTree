INSERT INTO EMPLOYEES (status, name, surname, date_of_birth, start_date_contract, unit_id, enumerationlevels_id)
VALUES (0, 'testname', 'testsurname', PARSEDATETIME('01/01/1990','dd/MM/yyyy'), PARSEDATETIME('01/02/2015','dd/MM/yyyy'),
(SELECT id FROM units WHERE name = 'testunit'),(SELECT ID FROM enumerationlevels WHERE description ='testleveldescription'));