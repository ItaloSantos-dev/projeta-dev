ALTER TABLE  tb_project
ADD COLUMN paid boolean;

UPDATE tb_project
SET paid = false;

ALTER TABLE  tb_project
ALTER COLUMN paid SET NOT NULL;