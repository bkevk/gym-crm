
INSERT INTO trainee (first_name, last_name, username, is_active, password, date_of_birth, address)
VALUES ('John', 'Smith', 'John.Smith', TRUE, 'password123', '2000-01-01', '123 Main St');

INSERT INTO trainee (first_name, last_name, username, is_active, password, date_of_birth, address)
VALUES ('Anna', 'Brown', 'Anna.Brown', TRUE, 'password456', '1995-05-15', '456 Oak Ave');

INSERT INTO trainer (first_name, last_name, username, is_active, password, specialization)
VALUES ('Jane', 'Doe', 'Jane.Doe', TRUE, 'password789', 'Yoga');

INSERT INTO trainer (first_name, last_name, username, is_active, password, specialization)
VALUES ('Mark', 'Lee', 'Mark.Lee', TRUE, 'password321', 'Strength');

INSERT INTO training (trainee_id, trainer_id, name, training_type, date, duration)
VALUES (1, 1, 'Yoga Basics', 'YOGA', '2024-03-01T10:00:00', 60);

INSERT INTO training (trainee_id, trainer_id, name, training_type, date, duration)
VALUES (2, 2, 'Strength 101', 'STRENGTH', '2024-03-02T11:00:00', 45);
