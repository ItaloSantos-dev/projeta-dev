ALTER TABLE tb_project
ADD COLUMN fixed_position INT,
ADD CONSTRAINT user_id_fixed_position_unique
UNIQUE (user_creator_id, fixed_position);