CREATE TABLE tb_hability(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL NOT NULL,
    title TEXT NOT NULL,
    have_icon BOOLEAN NOT NULL,
    icon_link TEXT,
    created_at DATE NOT NULL,

    CONSTRAINT unique_user_hability_title UNIQUE (user_id, title)
)