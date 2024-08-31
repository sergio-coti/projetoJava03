package controllers;

import java.util.Scanner;
import java.util.UUID;

import entities.Produto;
import repositories.ProdutoRepository;

public class ProdutoController {

	//atributo
	private Scanner scanner = new Scanner(System.in);
	
	/*
	 * Método para fazermos a captura dos dados do produto
	 * para realização do cadastro
	 */
	public void cadastrarProduto() {
		
		try {
			
			System.out.println("\nCADASTRO DE PRODUTOS:\n");
			
			System.out.print("NOME DO PRODUTO...: ");
			var nome = scanner.nextLine();
			
			System.out.print("PREÇO.............: ");
			var preco = Double.parseDouble(scanner.nextLine());
			
			System.out.print("QUANTIDADE........: ");
			var quantidade = Integer.parseInt(scanner.nextLine());
			
			//instanciando produto já passando os parametros no construtor
			var produto = new Produto(UUID.randomUUID(), nome, preco, quantidade);
			
			//Enviar o produto para ser cadastrado no banco de dados
			var produtoRepository = new ProdutoRepository();
			produtoRepository.inserir(produto);			
		}
		catch(Exception e) {
			//imprimir mensagem de erro
			System.out.println("\nFalha ao cadastrar o produto!");
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Método para fazermos a captura dos dados do produto
	 * para realização da atualização
	 */
	public void atualizarProduto() {
		
		try {
			
			System.out.println("\nATUALIZAÇÃO DE PRODUTOS:\n");
			
			System.out.print("INFORME O ID DO PRODUTO.: ");
			var id = UUID.fromString(scanner.nextLine());
			
			//consultando o produto no banco de dados através do id
			var produtoRepository = new ProdutoRepository();
			var produto = produtoRepository.obterPorId(id);
			
			//verificando se o produto foi encontrado
			if(produto != null) {
				
				System.out.println("\nDADOS DO PRODUTO:");
				System.out.println("ID........: " + produto.getId());
				System.out.println("NOME......: " + produto.getNome());
				System.out.println("PREÇO.....: " + produto.getPreco());
				System.out.println("QUANTIDADE: " + produto.getQuantidade());
				System.out.println("");
				
				System.out.print("ALTERE O NOME.........: ");
				produto.setNome(scanner.nextLine());
				
				System.out.print("ALTERE O PREÇO........: ");
				produto.setPreco(Double.parseDouble(scanner.nextLine()));
				
				System.out.print("ALTERE A QUANTIDADE...: ");
				produto.setQuantidade(Integer.parseInt(scanner.nextLine()));
				
				//atualizando os dados do produto no banco
				produtoRepository.atualizar(produto);				
			}
			else {
				System.out.println("\nProduto não encontrado. Verifique o ID informado.");
			}
		}
		catch(Exception e) {
			System.out.println("\nFalha ao atualizar o produto!");
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Método para fazermos a captura dos dados do produto
	 * para realização da exclusão
	 */	
	public void excluirProduto() {
		
		try {
			
			System.out.println("\nEXCLUSÂO DE PRODUTOS:\n");
			
			System.out.print("INFORME O ID DO PRODUTO.: ");
			var id = UUID.fromString(scanner.nextLine());
			
			//consultando o produto no banco de dados através do id
			var produtoRepository = new ProdutoRepository();
			var produto = produtoRepository.obterPorId(id);
			
			//verificando se o produto foi encontrado
			if(produto != null) {
				
				System.out.println("\nDADOS DO PRODUTO:");
				System.out.println("ID........: " + produto.getId());
				System.out.println("NOME......: " + produto.getNome());
				System.out.println("PREÇO.....: " + produto.getPreco());
				System.out.println("QUANTIDADE: " + produto.getQuantidade());
				System.out.println("");
								
				//excluindo os dados do produto no banco
				produtoRepository.excluir(produto.getId());				
			}
			else {
				System.out.println("\nProduto não encontrado. Verifique o ID informado.");
			}
		}
		catch(Exception e) {
			System.out.println("\nFalha ao excluir o produto!");
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Método para consultar todos os produtos
	 * e imprimi-los no terminal (console)
	 */	
	public void consultarProdutos() {
		
		try {
			
			System.out.println("\nCONSULTA DE PRODUTOS:\n");
			
			//consultando os produtos no banco de dados (lista de produtos)
			var produtoRepository = new ProdutoRepository();
			var lista = produtoRepository.consultar();
			
			//percorrer todos os produtos contidos na lista
			for(Produto produto : lista) { //foreach (para cada item, leia...)
				
				System.out.println("ID.........: " + produto.getId());
				System.out.println("NOME.......: " + produto.getNome());
				System.out.println("PREÇO......: " + produto.getPreco());
				System.out.println("QUANTIDADE.: " + produto.getQuantidade());
				System.out.println("");
			}			
		}
		catch(Exception e) {
			System.out.println("\nFalha ao consultar os produtos!");
			System.out.println(e.getMessage());
		}
	}
}





