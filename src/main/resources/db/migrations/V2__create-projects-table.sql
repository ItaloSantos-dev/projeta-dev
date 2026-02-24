CREATE TABLE tb_project(
    id BIGSERIAL PRIMARY KEY,
    title  VARCHAR(50)  NOT NULL,
    img_url TEXT,
    description TEXT NOT NULL,
    user_creator_id BIGINT NOT NULL,
    stack TEXT NOT NULL,
    status VARCHAR(100) NOT NULL,
    input_type VARCHAR(100) NOT NULL,
    repository_link TEXT,
    created_at DATE NOT NULL,

    FOREIGN KEY (user_creator_id) REFERENCES tb_user(id),
    CONSTRAINT unique_title_project UNIQUE(title, user_creator_id)
)
