ALTER TABLE tb_project
    ADD COLUMN slug VARCHAR(500);

UPDATE tb_project
    SET slug = id::text;

ALTER TABLE tb_project
    ALTER COLUMN slug SET NOT NULL;

ALTER TABLE tb_project
    ADD CONSTRAINT unique_slug_creator UNIQUE(slug, user_creator_id);