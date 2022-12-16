INSERT INTO role (id, name) VALUES
('1', 'ADMIN'),
('2', 'USER');

INSERT INTO customer (id, username, password, status) VALUES
('1', '123456789', '$2a$12$E0zVVT5LZUJPTgX7bXmdvO4OLcOv2q8rJiKkkiSPr0.3h3kGa680S', 'ACTIVE');

INSERT INTO customer (id, username, password, status) VALUES
('2', '123456789', '$2a$12$E0zVVT5LZUJPTgX7bXmdvO4OLcOv2q8rJiKkkiSPr0.3h3kGa680S', 'DELETED');

INSERT INTO customer_roles (customer_id, role_id) VALUES
('1', '1'),
('1', '2');

INSERT INTO user_statistic_record (id, record_name, user_id, number, description, creationDate) VALUES
('1', 'test', '1', '10', 'TestDescription', '2020-12-22');
