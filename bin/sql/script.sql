--script para criação da tabela de produto
CREATE TABLE produto(
	id 			UUID			PRIMARY KEY,
	nome		VARCHAR(250)	NOT NULL,
	preco		NUMERIC(10,2)	NOT NULL,
	quantidade	INT				NOT NULL
);

