CREATE TABLE IF NOT EXISTS tb_associado (
    id   INTEGER       NOT NULL AUTO_INCREMENT,
    nome VARCHAR(128) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    PRIMARY KEY (id)
);