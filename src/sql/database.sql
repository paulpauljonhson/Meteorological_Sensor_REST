CREATE TABLE Measurement
(
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    value float NOT NULL CHECK ( value >= -100 AND value <= 100),
    raining bool NOT NULL,
    sensor_id int NOT NULL REFERENCES sensor(id) ON DELETE CASCADE,
    measurement_time TIMESTAMP
);

CREATE TABLE Sensor
(
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name varchar(30) NOT NULL UNIQUE
);