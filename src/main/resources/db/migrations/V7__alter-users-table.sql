ALTER TABLE tb_user
    ADD COLUMN username VARCHAR(100) UNIQUE;

UPDATE tb_user
    SET username = id::text;

ALTER TABLE tb_user
    ALTER COLUMN username SET NOT NULL;


