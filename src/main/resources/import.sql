INSERT INTO modules (name, base_path) VALUES ('AUTHENTICATION', '/security');


INSERT INTO operations (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE', '/authenticate', 'POST', true, 1);

INSERT INTO roles (description, status) VALUES ('ADMINISTRATOR', true);
INSERT INTO roles (description, status) VALUES ('CUSTOMER', true);

INSERT INTO users (username, password, rol_id, status) VALUES ('administrator', '$2a$10$7.kqP/Rb5aFoYnaPXt7jXOzJMrvXiLfYP1d5YSjqEIkpELpjjYl8C', 1, true);
INSERT INTO users (username, password, rol_id, status) VALUES ('customer', '$2a$10$7.kqP/Rb5aFoYnaPXt7jXOzJMrvXiLfYP1d5YSjqEIkpELpjjYl8C', 2, true);

