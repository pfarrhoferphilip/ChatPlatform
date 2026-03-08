INSERT INTO chat_user(id, username) VALUES
                               (1, 'Ben Utzer'),
                               (2, 'Ali');
SELECT setval('chat_user_seq', 2);

INSERT INTO server(id, name) VALUES
                                 (1, 'Test Server1'),
                                 (2, 'Test Server2');
SELECT setval('server_seq', 2);

/*INSERT INTO message(id, content, user_id, server_id) VALUES
                                              (1, 'First Message', 1, 1),
                                              (2, 'Second Message', 1, 2),
                                              (3, 'Last Message', 2, 1);*/