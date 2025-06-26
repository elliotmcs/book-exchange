CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE ROLE "springUser" WITH LOGIN ENCRYPTED PASSWORD 'postgres';

CREATE DATABASE book_exchange;
GRANT ALL PRIVILEGES ON DATABASE book_exchange TO "springUser";

\c book_exchange

GRANT ALL ON SCHEMA public TO "springUser";

-- CREATE TABLE users (
--     id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
--     username VARCHAR(50) NOT NULL,
--     email VARCHAR(254) NOT NULL,
--     rating INTEGER,
--     is_active BOOLEAN DEFAULT FALSE
-- );

-- CREATE TABLE samples (
--     id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
--     title VARCHAR(50) NOT NULL,
--     file_address VARCHAR,
--     rating INTEGER,
--     user_id uuid REFERENCES users(id),
--     is_published BOOLEAN DEFAULT FALSE
-- );

-- CREATE TABLE tags (
--     id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
--     label VARCHAR(50) NOT NULL
-- );

-- CREATE TABLE sample_to_tag (
--     sample_id uuid REFERENCES samples(id),
--     tag_id uuid REFERENCES tags(id),
--     PRIMARY KEY (sample_id, tag_id)
-- );