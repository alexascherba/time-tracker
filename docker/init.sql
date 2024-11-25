CREATE TABLE IF NOT EXISTS projects (
                                        id SERIAL PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        description TEXT
    );

CREATE TABLE IF NOT EXISTS records (
                                       id SERIAL PRIMARY KEY,
                                       start_time TIMESTAMP NOT NULL,
                                       end_time TIMESTAMP NOT NULL,
                                       project_id BIGINT NOT NULL,
                                       user_id VARCHAR(255) NOT NULL,
                                       FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);
