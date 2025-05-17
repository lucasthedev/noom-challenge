CREATE TABLE sleep (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    sleep_date DATE NOT NULL,
    start_bed_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_bed_time TIMESTAMP WITHOUT TIME ZONE,
    total_bed_time_minutes INT,
    morning_feel morning_feel,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);