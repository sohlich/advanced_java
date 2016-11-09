INSERT INTO AUTHOR (id, firstname, lastname, password, email) VALUES (hibernate_sequence.nextval, 'Jozef', 'Novak', 'heslo', 'jozef@novak.cz');
INSERT INTO AUTHOR (id, firstname, lastname, password, email) VALUES (hibernate_sequence.nextval, 'Jozef', 'Novak', 'heslo', 'jozef22@novak.cz');
INSERT INTO POST (id, content, published, title, author_id, create_date) VALUES (hibernate_sequence.nextval, 'Test content', TRUE, 'Test Title', 1, sysdate());
