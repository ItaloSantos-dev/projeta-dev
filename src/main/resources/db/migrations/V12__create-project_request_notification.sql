CREATE TABLE tb_project_request_notification(
    id BIGSERIAL PRIMARY KEY,
    request_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    read BOOLEAN NOT NULL DEFAULT FALSE,
    type VARCHAR(100) NOT NULL,
    created_at DATE NOT NULL,
    read_at DATE,

    FOREIGN KEY(user_id) REFERENCES tb_user(id),
    FOREIGN KEY(request_id) REFERENCES tb_project_request (id)
)