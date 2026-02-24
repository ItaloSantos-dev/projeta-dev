CREATE TABLE tb_user(
    id BIGSERIAL PRIMARY KEY,
    name  VARCHAR(50)  NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    telephone_number VARCHAR(12) NOT NULL UNIQUE,
    experience_level VARCHAR(255) NOT NULL,
    principal_stack TEXT,
    created_at DATE NOT NULL
)