INSERT INTO modules (name, base_path) VALUES ('AUTHENTICATION', '/security');
INSERT INTO modules (name, base_path) VALUES ('MAINTENANCE', '/proxy-maintenance');
INSERT INTO modules (name, base_path) VALUES ('SALE', '/proxy-sale');


INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE', '/authenticate', 'POST', true, 1);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('MAINTENANCE_CREATE_ONE', '/master', 'POST', false, 2);
INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('MAINTENANCE_READ_ALL', '/master', 'GET', false, 2);

INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('SALE_CREATE_ONE', '/sale', 'POST', false, 3);


INSERT INTO roles (description, status) VALUES ('ADMINISTRATOR', true);
INSERT INTO roles (description, status) VALUES ('CUSTOMER', true);


INSERT INTO permissions (rol_id, operation_id) VALUES (1, 2);
INSERT INTO permissions (rol_id, operation_id) VALUES (1, 3);
INSERT INTO permissions (rol_id, operation_id) VALUES (1, 4);

-- INSERT INTO permissions (rol_id, operation_id) VALUES (2, 3);

INSERT INTO users (username, password, rol_id, status) VALUES ('administrator', '$2a$10$7.kqP/Rb5aFoYnaPXt7jXOzJMrvXiLfYP1d5YSjqEIkpELpjjYl8C', 1, true);
INSERT INTO users (username, password, rol_id, status) VALUES ('customer', '$2a$10$7.kqP/Rb5aFoYnaPXt7jXOzJMrvXiLfYP1d5YSjqEIkpELpjjYl8C', 2, true);

