CREATE TABLE tb_project_request (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    created_at DATE NOT NULL,
    responded_at DATE,
    status VARCHAR(100) NOT NULL,

    FOREIGN KEY(user_id) REFERENCES tb_user(id),
    FOREIGN KEY(project_id) REFERENCES tb_project(id),

    CONSTRAINT project_user_unique UNIQUE (project_id, user_id)
)