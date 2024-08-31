package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Produto;
import factories.ConnectionFactory;

public class ProdutoRepository {

	/*
	 * Método para receber um objeto produto e grava-lo
	 * na tabela do banco de dados do PostGreSQL
	 */
	public void inserir(Produto produto) {
		
		try {
			
			//Abrindo conexão com o PostGreSQL
			var connection = ConnectionFactory.getConnection();
			
			//escrever o script SQL para gravar os dados do produto na tabela
			var statement = connection.prepareStatement("INSERT INTO produto(id, nome, preco, quantidade) VALUES(?,?,?,?)");
			statement.setObject(1, produto.getId()); //preenchendo o id
			statement.setString(2, produto.getNome()); //preenchendo o nome
			statement.setDouble(3, produto.getPreco()); //preenchendo o preco
			statement.setInt(4, produto.getQuantidade()); //preenchendo a quantidade
			statement.execute(); //executando o comando no banco de dados
			
			//fechando a conexão com o banco de dados
			connection.close();
			
			System.out.println("\nPRODUTO CADASTRADO COM SUCESSO!");
		}
		catch(Exception e) {
			System.out.println("\nFalha ao inserir produto.");
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Método para atualizar os dados do produto
	 * na tabela do banco de dados
	 */
	public void atualizar(Produto produto) {
		
		try {
			
			//abrindo conexão com o banco de dados
			var connection = ConnectionFactory.getConnection();
			
			//escrevendo o comando SQL para atualizar um produto
			var statement = connection.prepareStatement("UPDATE produto SET nome=?, preco=?, quantidade=? WHERE id=?");
			statement.setString(1, produto.getNome()); //preenchendo o nome
			statement.setDouble(2, produto.getPreco()); //preenchendo o preco
			statement.setInt(3, produto.getQuantidade()); //preenchendo a quantidade
			statement.setObject(4, produto.getId()); //preenchendo o id
			statement.execute(); //executar o comando SQL
			
			//fechando a conexão
			connection.close();
			
			System.out.println("\nPRODUTO ATUALIZADO COM SUCESSO.");
		}
		catch(Exception e) {
			System.out.println("\nFalha ao atualizar produto.");
			System.out.println(e.getMessage());
		}		
	}
	
	/*
	 * Método para excluir um produto
	 * do banco de dados
	 */
	public void excluir(UUID id) {
		
		try {
			
			//abrindo conexão com o banco de dados
			var connection = ConnectionFactory.getConnection();
			
			//escrever o comando SQL para excluir o produto no banco de dados
			var statement = connection.prepareStatement("DELETE FROM produto WHERE id=?");
			statement.setObject(1, id); //preenchendo o id
			statement.execute(); //executando o comando SQL
			
			connection.close(); //fechando a conexão do banco de dados
			
			System.out.println("\nPRODUTO EXCLUÍDO COM SUCESSO!");
		}
		catch(Exception e) {
			System.out.println("\nFalha ao excluir produto.");
			System.out.println(e.getMessage());
		}		
	}
	
	/*
	 * Método para consultar todos os produtos do banco de dados
	 * e retornar uma lista com esses produtos
	 */
	public List<Produto> consultar() {
		
		//declarando uma variável do tipo lista de produtos
		var lista = new ArrayList<Produto>();
		
		try {
			
			//abrindo conexão com o banco de dados
			var connection = ConnectionFactory.getConnection();
			
			//escrevendo o comando SQL para consultar os produtos no banco de dados
			var statement = connection.prepareStatement("SELECT id, nome, preco, quantidade FROM produto ORDER BY nome");
			var result = statement.executeQuery();
			
			//enquanto houver produtos, leia..
			while(result.next()) {
				
				//capturando os dados de cada produto liddo da consulta
				var produto = new Produto();
				produto.setId(UUID.fromString(result.getString("id"))); //lendo o campo 'id'
				produto.setNome(result.getString("nome")); //lendo o campo 'nome'
				produto.setPreco(result.getDouble("preco")); //lendo o campo 'preco'
				produto.setQuantidade(result.getInt("quantidade")); //lendo o campo 'quantidade'
				
				lista.add(produto); //adicionando o produto dentro da lista
			}			
			
			//fechando a conexão do banco de dados
			connection.close();
		}
		catch(Exception e) {
			System.out.println("\nFalha ao consultar produtos.");
			System.out.println(e.getMessage());
		}
		
		//retornando a lista
		return lista;
	}
	
	/*
	 * Método para consultar 1 produto no banco de dados
	 * através do ID (chave primária)
	 */
	public Produto obterPorId(UUID id) {
		
		//Declarando um objeto produto vazio
		Produto produto = null;
		
		try {
			
			//abrindo conexão com o banco de dados
			var connection = ConnectionFactory.getConnection();
			
			//escrevendo o comando SQL que será executado no banco de dados
			var statement = connection.prepareStatement("SELECT id, nome, preco, quantidade FROM produto WHERE id=?");
			statement.setObject(1, id);
			var result = statement.executeQuery();
			
			//lendo o produto se for encontrado
			if(result.next()) {
				
				//instanciando o produto
				produto = new Produto();
				
				produto.setId(UUID.fromString(result.getString("id")));
				produto.setNome(result.getString("nome"));
				produto.setPreco(result.getDouble("preco"));
				produto.setQuantidade(result.getInt("quantidade"));
			}
			
			//fechando a conexão
			connection.close();
		}
		catch(Exception e) {
			System.out.println("\nFalha ao consultar por id.");
			System.out.println(e.getMessage());
		}
		
		//retornando o produto
		return produto;
	}
	
}

















