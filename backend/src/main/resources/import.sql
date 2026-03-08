INSERT INTO chat_user(id, username, password) VALUES
                               (1, 'Ben Utzer', '$2a$12$M5ZR2K31ZYdg1nZQHfbJnuiSCravfcg1hCZYhUrggk8V43SGL3I7y'),
                               (2, 'admin', '$2a$12$M5ZR2K31ZYdg1nZQHfbJnuiSCravfcg1hCZYhUrggk8V43SGL3I7y');
SELECT setval('chat_user_seq', 2);

INSERT INTO server(id, name) VALUES
                                 (1, 'Test Server1'),
                                 (2, 'Test Server2');
SELECT setval('server_seq', 2);

/*INSERT INTO message(id, content, user_id, server_id) VALUES
                                              (1, 'First Message', 1, 1),
                                              (2, 'Second Message', 1, 2),
                                              (3, 'Last Message', 2, 1);*/