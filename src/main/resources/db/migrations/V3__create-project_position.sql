CREATE TABLE tb_project_position(
    id BIGINT PRIMARY KEY,
    name varchar(50) NOT NULL,
    project_id BIGINT NOT NULL,

    FOREIGN KEY(project_id) REFERENCES tb_project(id),
    CONSTRAINT unique_name_project UNIQUE(name, project_id)
)