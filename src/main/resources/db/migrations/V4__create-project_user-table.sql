CREATE TABLE tb_project_user (
    id BIGINT PRIMARY KEY,
    project_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    position_id BIGINT NOT NULL,

    FOREIGN KEY(project_id) REFERENCES tb_project(id),
    FOREIGN KEY(user_id) REFERENCES tb_user(id),
    FOREIGN KEY(position_id) REFERENCES tb_project_position(id)
)