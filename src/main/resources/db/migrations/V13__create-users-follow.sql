CREATE TABLE tb_users_follow (
    id BIGSERIAL PRIMARY KEY,
    user_following_id BIGINT NOT NULL,
    user_followed_id BIGINT NOT NULL,
    created_at DATE,

    FOREIGN KEY (user_following_id) REFERENCES tb_user(id),
    FOREIGN KEY (user_followed_id) REFERENCES tb_user(id),
    CONSTRAINT user_following_user_followed UNIQUE(user_following_id, user_followed_id)

);