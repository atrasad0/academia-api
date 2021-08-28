CREATE DATABASE academia; 

ALTER DATABASE
    academia
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;
   
   CREATE TABLE planos (
	id_plano int(10) NOT NULL AUTO_INCREMENT,
	descricao mediumtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
	valor decimal(10,2) DEFAULT NULL,
	ativo smallint(1) NOT NULL DEFAULT 1,
	tipo varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
	PRIMARY KEY (id_plano)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE alunos (
	id_aluno int(10) NOT NULL AUTO_INCREMENT,
	nome varchar(50) NOT NULL,
	cpf varchar(30) NOT NULL,
	sexo varchar(20),
	data_nascimento datetime NOT NULL,
	data_cadastro datetime NOT NULL,
	data_pagamento datetime NOT NULL,
	id_plano int(10) DEFAULT NULL,
	PRIMARY KEY (id_aluno),
	CONSTRAINT fk_plano FOREIGN KEY (id_plano) REFERENCES planos (id_plano) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE telefones (
	id_telefone int(10) NOT NULL AUTO_INCREMENT,
	ddi int(10) NOT NULL,
	ddd int(10) NOT NULL,
	numero varchar(30),
	id_aluno  int(10) NOT NULL,
	PRIMARY KEY (id_telefone),
	CONSTRAINT fk_aluno FOREIGN KEY (id_aluno) REFERENCES alunos (id_aluno) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE aulas (
	id_aula int(10) NOT NULL AUTO_INCREMENT,
	tipo varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
	PRIMARY KEY (id_aula)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE aula_aluno (
	id_aluno_aula int(10) NOT NULL AUTO_INCREMENT,
	id_aula int(10) NOT NULL,
	id_aluno int(10) NOT NULL,
	PRIMARY KEY (id_turma),
	CONSTRAINT fk_turma_aula FOREIGN KEY (id_aula) REFERENCES aulas (id_aula) ON DELETE CASCADE ON UPDATE NO ACTION,
	CONSTRAINT fk_turma_aluno FOREIGN KEY (id_aluno) REFERENCES alunos (id_aluno) ON DELETE CASCADE ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;