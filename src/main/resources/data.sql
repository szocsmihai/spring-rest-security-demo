INSERT INTO roles (name) VALUES ('CLIENT');
INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO privileges (name) VALUES ('CAN_READ');
INSERT INTO privileges (name) VALUES ('CAN_WRITE');

INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1, 1);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2, 1);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2, 2);

INSERT INTO clients (name, password, role_id, status) VALUES ('client', '$2a$12$PYm.oYEYaAGTTlvwtxHOruRRMGZ0.VMRsU2nfd2keohfIONkrJP7u', 1, 1);
INSERT INTO clients (name, password, role_id, status) VALUES ('admin', '$2a$12$ivR.DVoagDPgyi6aQzPmDuJ53T2f3t5lLhqpFXtNQqG/J/gnqJhyC', 2, 1);