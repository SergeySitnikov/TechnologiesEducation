INSERT INTO user VALUES
(1, "Admin", "$2a$12$75GdCWX55cedEEHLaWFp.u2bSnmAfLascYaCEJ03JrpciMI4r.pLy", "ACTIVE"),
(2, "User", "$2a$12$75GdCWX55cedEEHLaWFp.u2bSnmAfLascYaCEJ03JrpciMI4r.pLy", "ACTIVE");

INSERT INTO role VALUES
(1, ADMIN),
(2, USER);

INSERT INTO user_roles VALUES
(1, 1),
(1, 2),
(2, 2);