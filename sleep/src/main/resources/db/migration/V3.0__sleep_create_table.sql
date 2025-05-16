CREATE TABLE sleep (
    id SERIAL PRIMARY KEY,
    sleep_date DATE NOT NULL,
    start_bed_time TIME NOT NULL,
    end_bed_time TIME NOT NULL,
    total_bed_time INTERVAL NOT NULL,
    morning_feel morning_feel NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);